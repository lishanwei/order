package com.tlkg.order.exception;

import java.io.Serializable;

import com.tlkg.order.common.enums.OrderErrorEnum;

/**
 * @author shellingford
 * @version 创建时间：2012-2-2 下午01:09:49
 * 
 */
public class OrderException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorKey;
	
	public OrderException(String errorCode, String errorKey, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorKey = errorKey;
	}

	public OrderException(String errorCode,String errorKey){
		super("errorcode:"+errorCode);
		this.errorCode=errorCode;
		this.errorKey=errorKey;
	}
	
	
	public OrderException(OrderErrorEnum errorEnum){
		this(errorEnum.getErrorCode(),errorEnum.getErrorMsg());
	}
	
	public OrderErrorEnum getMyErrorEnum(){
		return OrderErrorEnum.findByCode(errorCode);
	}

	public final String getErrorKey() {
		return errorKey;
	}

	public final void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
}
