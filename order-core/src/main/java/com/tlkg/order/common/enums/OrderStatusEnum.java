package com.tlkg.order.common.enums;

/**
 * 
 * @author shellingford
 * @version 2014年12月22日
 */

public enum OrderStatusEnum {

	newOrder(100, "预订单"),
	// wait(110,"等待前台确认"),
	// WaitPay(120,"等待支付"), //创建订单 都是 OrderTypeEnum.YF ,PayStatusEnum.waitPay
	// 可以更改为到付 只修改OrderTypeEnum.PT ,PayStatusEnum.doNotPay
	Confirm(140, "已确认"), // 已经付款 OrderTypeEnum.YF PayStatusEnum.alreadyPay
	// CheckInOnline(160,"网上入住"), //（通过ota网上入住之后，实际入住之前的状态）
	CheckIn(180, "入住"), // （实际入住之后的状态）（实际入住之后的状态）
	Account(190, "挂账"), // （已退房，未结账）
	CheckOut(200, "离店"), // （用户离店）
	CancelByUser(510, "取消"), // （订单被用户取消）
	CancelBySystem(511, "取消"), // （订单被系统取消）
	// CancelByU_Refunded(512,"取消"), // （订单被用户取消，已退款）
	// (513,"取消"), // （订单被用户取消，无需退款）
	CancelByPMS(514, "PMS取消订单"), // （PMS取消订单）
	NoShow(520, "未到"); // （用户未到）

	private final Integer id;
	private final String name;

	private OrderStatusEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static OrderStatusEnum getOrderStatusEnum(Integer id) {
		for (OrderStatusEnum temp : OrderStatusEnum.values()) {
			if (temp.getId().equals(id)) {
				return temp;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return id.toString();
	}

}
