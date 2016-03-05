package com.msys.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order { 

	public Order(Long id, Date deliveryDate, Set<OrdersItems> ordersItems, Date validFrom, Date validTo) {
		super();
		this.id = id;
		this.deliveryDate = deliveryDate;
		this.ordersItems = ordersItems;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id; 
	
	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;
	
	private Set <OrdersItems> ordersItems;
	
	@Column(name = "VALID_FROM")
	private Date validFrom;
	
	@Column(name = "VALID_TO")
	private Date validTo;

    public Long getId() {
		return id;
	}
    
    public void setId(Long id) {
		this.id = id;
	}
	public boolean availableAt(Date inputDate) {
		return true;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@OneToMany(mappedBy = "ORDER", cascade = CascadeType.ALL)
	public Set<OrdersItems> getOrdersItems() {
		return ordersItems;
	}

	public void setOrdersItems(Set<OrdersItems> ordersItems) {
		this.ordersItems = ordersItems;
	}

 	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", deliveryDate=" + deliveryDate + ", ordersItems=" + ordersItems + ", validFrom="
				+ validFrom + ", validTo=" + validTo + "]";
	} 
}
