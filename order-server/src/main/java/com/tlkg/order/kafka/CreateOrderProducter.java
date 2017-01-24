package com.tlkg.order.kafka;

import com.mk.kafka.client.stereotype.MkMessageService;

@MkMessageService
public class CreateOrderProducter {

//	/**
//	 * 预订单完成
//	 * 
//	 * @param prepareOrder
//	 */
//	@MkTopicProducer(topic = "newOrder", replicationFactor = 1, serializerClass = "com.mk.kafka.client.serializer.SerializerEncoder")
//	public void sendPrepareOrder(OrderContext orderContext) {
//
//	}
//
//	/**
//	 * 订单创建成功
//	 */
//	@MkTopicProducer(topic = "createOrderOk", replicationFactor = 1, serializerClass = "com.mk.kafka.client.serializer.SerializerEncoder")
//	public void sendCreateOrderOk(OrderContext OrderContext) {
//
//	}
//
//	/**
//	 * 修改PMS订单
//	 * 
//	 * @param order
//	 */
//	@MkTopicProducer(topic = "updatePmsOrder", replicationFactor = 1, serializerClass = "com.mk.kafka.client.serializer.SerializerEncoder")
//	public void sendUpdatePmsOrder(Order order) {
//
//	}
//
//	/**
//	 * 取消pms订单
//	 * 
//	 * @param order
//	 */
//	@MkTopicProducer(topic = "cancelPmsOrder", replicationFactor = 1, serializerClass = "com.mk.kafka.client.serializer.SerializerEncoder")
//	public void sendCancelPmsOrder(Order order) {
//
//	}
//
//	/**
//	 * 取消订单所有明细后，通知kafka
//	 * 
//	 * @param order
//	 */
//	@MkTopicProducer(topic = "cancelOrderDetailOK", replicationFactor = 1, serializerClass = "com.mk.kafka.client.serializer.SerializerEncoder")
//	public void sendCancelOrderDetailOK(Order order) {
//
//	}
}
