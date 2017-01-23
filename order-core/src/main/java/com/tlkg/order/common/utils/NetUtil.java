package com.tlkg.order.common.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.xml.ws.http.HTTPException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

public class NetUtil {

	protected static Logger logger = LoggerFactory.getLogger(NetUtil.class);

	private static int timeout = 32 * 1000;

	public static String httpPostjson(String url, String send) throws Exception {
		logger.info("请求地址：url:{},timeout:{}", url, timeout);
		return postJsonAsHttp(url, send, timeout, 3);
	}
	public static String httpsPostJson(String url, String send) throws Exception {
		logger.info("请求地址：url:{},timeout:{}", url, timeout);
		return postJsonAsHttps(url, send, timeout, 3);
	}

	public static String postJsonAsHttp(String url, String send, int timeout, int tryTime) throws HttpException, IOException {

		HttpClient theclient = new HttpClient();
		PostMethod method = new PostMethod(url);

		Transaction t = Cat.newTransaction("OtsPostJson", url);

		try {
			method.setRequestEntity(new StringRequestEntity(send, "application/json", "UTF-8"));
			method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);
			theclient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(1, false));
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			int status = theclient.executeMethod(method);
			if (status == HttpStatus.SC_OK) {
				t.setStatus(Transaction.SUCCESS);
				return method.getResponseBodyAsString();
			} else {
				logger.info("访问url:{},返回状态:{}", url, status);
				throw new HTTPException(status);
			}
		} catch (Exception ex) {
			t.setStatus(ex);
			throw ex;
		} finally {
			t.complete();
			method.releaseConnection();
		}
	}

	/**
	 * @param url
	 * @param sendParamStr
	 * @param timeout
	 * @param tryTime
	 * @return
	 * @throws Exception
	 */
	public static String postJsonAsHttps(String url, String sendParamStr, int timeout, int tryTime) throws Exception {
		Transaction t = Cat.newTransaction("OtsHttpsPost", url);
		CloseableHttpClient httpclient = null;
		int code = 0;
		try {
			HttpPost post = new HttpPost(url);

			StringEntity entity = new StringEntity(sendParamStr, ContentType.create("application/json", "UTF-8"));
			post.setEntity(entity);
			httpclient = createSSLClientDefault(null);

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();// 设置请求和传输超时时间
			post.setConfig(requestConfig);
			CloseableHttpResponse response = httpclient.execute(post);
			code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				t.setStatus(Transaction.SUCCESS);
				return EntityUtils.toString(response.getEntity());
			} else {
				throw new HTTPException(code);
			}
		} catch (Exception e) {
			t.setStatus(e);
			throw e;
		} finally {
			if (httpclient != null) {
				httpclient.close();
			}
			t.complete();
		}
	}

	public static CloseableHttpClient createSSLClientDefault(CookieStore cookieStore) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			// 信任所有
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		if (cookieStore != null) {
			return HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultCookieStore(cookieStore).build();
		} else {
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		}
	}

	public static void setTimeout(int _timeout){
		timeout = _timeout;
	}
}
