package com.tlkg.order.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mk.kafka.client.stereotype.MkMessageService;
/**
 * 订单消费kafka消息
 * 
 * @author lsw
 *
 */
@MkMessageService
public class OrderDataConsumer {
	final Logger logger = LoggerFactory.getLogger(OrderDataConsumer.class);
//	
//	@Autowired
//	OrderService orderService;
//	
//
//	@MkTopicConsumer(topic = "Switch_SyncPmsOrder", group = "order_group", serializerClass = "com.mk.kafka.client.serializer.StringDecoder")
//    public void doSyncPmsOrder(String message){
//    	logger.info("doSyncPmsOrder::message:{}" + message);
////    	pmsOrderService.syncPmsOrder(message);
//    }
//	
//	@MkTopicConsumer(topic = "Switch_SyncCheckInUser", group = "order_group", serializerClass = "com.mk.kafka.client.serializer.StringDecoder")
//    public void doSyncCheckinUser(String message){
//    	logger.info("doSyncCheckinUser::message:{}" + message);
////    	pmsOrderService.syncCheckinUser(message);
//    }
//	
//	@MkTopicConsumer(topic = "Order_SyncOrderStatusRE", group = "order_group", serializerClass = "com.mk.kafka.client.serializer.StringDecoder")
//    public void doSyncOrderStatusRE(String message){
//    	logger.info("doSyncOrderStatus::message:{}" + message);
//    	orderService.changeOrderStatusRE(message);
//    }
//	
//	@MkTopicConsumer(topic = "Order_SyncOrderStatusRX", group = "order_group", serializerClass = "com.mk.kafka.client.serializer.StringDecoder")
//    public void doSyncOrderStatusRX(String message){
//    	logger.info("doSyncOrderStatusRX::message:{}" + message);
//    	orderService.changeOrderStatusRX(message);
//    }
//	
//	@MkTopicConsumer(topic = "Order_SyncOrderStatusIX", group = "order_group", serializerClass = "com.mk.kafka.client.serializer.StringDecoder")
//    public void doSyncOrderStatusIX(String message){
//    	logger.info("doSyncOrderStatusIX::message:{}" + message);
//    	orderService.changeOrderStatusIX(message);
//    }
//	
//	@MkTopicConsumer(topic = "Order_SyncOrderStatusIN", group = "order_group", serializerClass = "com.mk.kafka.client.serializer.StringDecoder")
//    public void doSyncOrderStatusIN(String message){
//    	logger.info("doSyncOrderStatusIN::message:{}" + message);
//    	orderService.changeOrderStatusIN(message);
//    }
//	
//	@MkTopicConsumer(topic = "Order_SyncOrderStatusPM", group = "order_group", serializerClass = "com.mk.kafka.client.serializer.StringDecoder")
//    public void doSyncOrderStatusPM(String message){
//    	logger.info("doSyncOrderStatusPM::message:{}" + message);
//    	orderService.changeOrderStatusPM(message);
//    }
//	
//	@MkTopicConsumer(topic = "Order_SyncOrderStatusOK", group = "order_group", serializerClass = "com.mk.kafka.client.serializer.StringDecoder")
//    public void doSyncOrderStatusOK(String message){
//    	logger.info("doSyncOrderStatusOK::message:{}" + message);
//    	orderService.changeOrderStatusOK(message);
//    }
}
