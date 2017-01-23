package com.tlkg.order.kafka;

import com.mk.kafka.client.stereotype.MkMessageService;

@MkMessageService
public class CreateOrderConsumer {

//	@Autowired
//	CreateOrderHandler createOrderHandler;
//	@Autowired
//	UpdateOrderHandler updateOrderHandler;
//
//	@MkTopicConsumer(topic = "newOrder", group = "SwitchGroup", serializerClass = "com.mk.kafka.client.serializer.SerializerDecoder")
//	public void doPrepareOrder(OrderContext prepareOrder) {
//		createOrderHandler.distributionRoomAndCreatePmsOrder(prepareOrder);
//	}
//
//	@MkTopicConsumer(topic = "updatePmsOrder", group = "SwitchGroup", serializerClass = "com.mk.kafka.client.serializer.SerializerDecoder")
//	public void doUpdatePmsOrder(Order order) {
//		updateOrderHandler.updatePmsOrder(order);
//	}
//
//	/**
//	 * 取消订单
//	 * 
//	 * @param order
//	 */
//	@MkTopicConsumer(topic = "cancelPmsOrder", group = "SwitchGroup", serializerClass = "com.mk.kafka.client.serializer.SerializerDecoder")
//	public void doCancelPmsOrder(Order order) {
//		updateOrderHandler.cancelPmsOrder(order);
//	}
}
