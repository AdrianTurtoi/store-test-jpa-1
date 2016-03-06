package com.msys.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class SameDayOrder extends Order { 
	 

	public SameDayOrder(Date deliveryDate, Set<OrderItem> orderItems, Date validFrom, Date validTo) {
		super(deliveryDate, orderItems, validFrom, validTo);	
	}

	@Override
	public Date getValidTo() {
		return getDeliveryDate();
	}

	@Override
	public Date getValidFrom() {
		return getDeliveryDate();
	}
}
