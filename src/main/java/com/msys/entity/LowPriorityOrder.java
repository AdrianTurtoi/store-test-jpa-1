package com.msys.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class LowPriorityOrder extends Order {  

	public LowPriorityOrder(Long id, Date deliveryDate, Set<OrdersItems> ordersItems, Date validFrom, Date validTo) {
		super(id, deliveryDate, ordersItems, validFrom, validTo); 
	}

	@Override
	public Date getDeliveryDate() {
		return null;
	}
}
