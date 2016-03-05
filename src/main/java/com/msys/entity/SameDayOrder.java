package com.msys.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class SameDayOrder extends Order { 
	
	public SameDayOrder(Long id, Date deliveryDate, Set<OrdersItems> ordersItems, Date validFrom, Date validTo) {
		super(id, deliveryDate, ordersItems, validFrom, validTo); 
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
