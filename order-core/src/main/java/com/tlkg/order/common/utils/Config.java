package com.tlkg.order.common.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Config {

	static Map<String, String> urls = new HashMap<String, String>();
	static {
		reloadYml();
	}

	/**
	 * 获取url
	 * 
	 * @param key 格式newpms.url、ots.url
	 * @return
	 */
	public static String getValue(String key) {
		return urls.get(key);
	}

	public static void reloadYml() {
		PropertiesUtil pro = new PropertiesUtil();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("taobao.properties");
		if (is != null) {
			pro.load(is);
			urls = pro.getMap();
		}
	}
}