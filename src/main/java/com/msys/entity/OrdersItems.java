package com.msys.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDERS_ITEMS")
public class OrdersItems implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private Order order;
	private OrderItem orderItem;

	@Id
	@ManyToOne
	@JoinColumn(name = "ID_ORDER")
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Id
	@ManyToOne
	@JoinColumn(name = "ID_ITEM")
	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
}
