package com.tlkg.order.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程本地变量，存储在一个请求周期，线程级共享的数据
 * <p>比如存储订单号，酒店id，可用于统一日志输出
 * 
 * @author 须俊杰
 * @date 2015年12月25日
 */
public class ThreadLocalContext {

	private static ThreadLocal<Map<Object, Object>> context = new ThreadLocal<Map<Object, Object>>();

	private static Map<Object, Object> init() {
		Map<Object, Object> value = context.get();
		if (value == null) {
			value = new HashMap<Object, Object>();
			context.set(value);
		}
		return value;
	}

	public static Object get(String key) {
		return init().get(key);
	}

	public static void set(String key, Object obj) {
		init().put(key, obj);
	}
}
