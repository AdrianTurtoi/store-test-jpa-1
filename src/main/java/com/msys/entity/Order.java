package com.msys.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
public class Order {

	public Order() {
		orderItems = new HashSet<OrderItem>();
	}

	public Order(Date deliveryDate, Set<OrderItem> orderItems, Date validFrom, Date validTo) {
		super();
		this.deliveryDate = deliveryDate;
		this.orderItems = orderItems;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public Order(Date deliveryDate, Date validFrom, Date validTo) {
		super();
		this.deliveryDate = deliveryDate;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_entity_seq_gen")
	//@SequenceGenerator(name="my_entity_seq_gen", sequenceName="HIBERNATE_SEQUENCE")
	@Column(name = "ID")
	private Long id;

	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;

	private Set<OrderItem> orderItems;

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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ORDERS_ITEMS", joinColumns = @JoinColumn(name = "ID_ORDER", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID"))
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
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
		return "Order [id=" + id + ", deliveryDate=" + deliveryDate + ", ordersItems=" + orderItems + ", validFrom="
				+ validFrom + ", validTo=" + validTo + "]";
	}
}
