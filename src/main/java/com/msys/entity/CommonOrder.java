package com.msys.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class CommonOrder extends Order {

	public CommonOrder(Date deliveryDate, Set<OrderItem> orderItems, Date validFrom, Date validTo) {
		super(deliveryDate, orderItems, validFrom, validTo);		
	}

}
