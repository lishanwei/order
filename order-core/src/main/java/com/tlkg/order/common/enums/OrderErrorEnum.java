package com.tlkg.order.common.enums;

import com.tlkg.order.exception.OrderException;


/**
 * @author shellingford
 * @version 创建时间：2012-2-2 下午01:22:47
 * 
 */
public enum OrderErrorEnum {
	
	// 酒店错误信息
	findHotelinfo("-100", "酒店信息错误。"),
	notfindHotel("-101", "酒店不存在。"),
	roomNUll("-102","酒店房间已满,客官请下次再来."),
	notfindHotelRoomtype("-103","酒店房型不存在。"),
	
	// 订单错误信息
	saveOrder("-100","保存订单失败."),
	saveOrderPms("-101","保存订单失败:PMS错误."),
	saveOrderCost("-102","保存订单失败:价格计算错误."),
	saveOrderRoomConflict("-103","保存订单失败:住房时间冲突."),
	saveOrderInTimeByTime("-104","入住时间必须大于现在时间!"),
	
	// 其他错误信息
	customError("0000","");
	
	private final String errorCode;
	private final String errorMsg;
	
	private OrderErrorEnum(String errorCode,String errorMsg){
		this.errorCode=errorCode;
		this.errorMsg=errorMsg;
	}
	
	public OrderException getMyException(){
		return getOrderException(errorMsg);
	}
	
	public OrderException getOrderException(String msg){
		return new OrderException(errorCode, "", msg);//  返回输入的错误信息
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	 
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public static OrderErrorEnum findByCode(String code){
		for (OrderErrorEnum value : OrderErrorEnum.values()) {
			if(value.errorCode.equalsIgnoreCase(code)){
				return value;
			}
		}
		return null;
	}
}
