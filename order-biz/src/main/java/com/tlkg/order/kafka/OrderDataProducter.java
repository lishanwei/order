package com.tlkg.order.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mk.kafka.client.stereotype.MkMessageService;
import com.mk.kafka.client.stereotype.MkTopicProducer;

/**
 * 订单发布kafka消息
 * 
 * @author lsw
 *
 */
@MkMessageService
public class OrderDataProducter {

	private static final Logger logger = LoggerFactory.getLogger(OrderDataProducter.class);

	@MkTopicProducer(topic = "Order_SyncOrderStatusRE", replicationFactor=1, serializerClass = "com.mk.kafka.client.serializer.StringEncoder")
	public void sendSyncOrderStatusRE(String pmsOrderStr) {
		logger.info("发送sendSyncOrderStatusRE消息成功，" + pmsOrderStr);
	}

	@MkTopicProducer(topic = "Order_SyncOrderStatusRX", replicationFactor=1, serializerClass = "com.mk.kafka.client.serializer.StringEncoder")
	public void sendSyncOrderStatusRX(String pmsOrderStr) {
		logger.info("发送sendSyncOrderStatusRX消息成功，" + pmsOrderStr);
	}
	
	@MkTopicProducer(topic = "Order_SyncOrderStatusIN", replicationFactor=1, serializerClass = "com.mk.kafka.client.serializer.StringEncoder")
	public void sendSyncOrderStatusIN(String pmsOrderStr) {
		logger.info("发送sendSyncOrderStatusIN消息成功，" + pmsOrderStr);
	}
	
	@MkTopicProducer(topic = "Order_SyncOrderStatusPM", replicationFactor=1, serializerClass = "com.mk.kafka.client.serializer.StringEncoder")
	public void sendSyncOrderStatusPM(String pmsOrderStr) {
		logger.info("发送sendSyncOrderStatusPM消息成功，" + pmsOrderStr);
	}

	@MkTopicProducer(topic = "Order_SyncOrderStatusOK", replicationFactor=1, serializerClass = "com.mk.kafka.client.serializer.StringEncoder")
	public void sendSyncOrderStatusOK(String pmsOrderStr) {
		logger.info("发送sendSyncOrderStatusOK消息成功，" + pmsOrderStr);
	}

	@MkTopicProducer(topic = "Order_SyncOrderStatusIX", replicationFactor=1, serializerClass = "com.mk.kafka.client.serializer.StringEncoder")
	public void sendSyncOrderStatusIX(String pmsOrderStr) {
		logger.info("发送sendSyncOrderStatusIX消息成功，" + pmsOrderStr);
	}

	@MkTopicProducer(topic = "order_order_left", replicationFactor=1, serializerClass = "com.mk.kafka.client.serializer.StringEncoder")
	public void sendOrderLeft(String order) {
		logger.info("发送sendOrderLeft消息成功，" + order);
	}
	
	@MkTopicProducer(topic = "Order_ChangeRoom", replicationFactor=1, serializerClass = "com.mk.kafka.client.serializer.StringEncoder")
	public void sendRoomChangeInfo(String changeroom) {
		logger.info("发送sendRoomChangeInfo消息成功，" + changeroom);
	}
}
