package com.msys.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPLIER")
public class Supplier { 
	
	public Supplier(Long id, Set<OrderItem> orderItem, int supplierNo, String supplierName) {
		super();
		this.id = id;
		this.orderItem = orderItem;
		this.supplierNo = supplierNo;
		this.supplierName = supplierName;
	}

	public Supplier(Long id, int supplierNo, String supplierName) {
		super();
		this.id = id;
		this.supplierNo = supplierNo;
		this.supplierName = supplierName;
	}

	public Supplier(int supplierNo) {
		this.supplierNo = supplierNo;
		this.supplierName = "NoNameSupplier";
	}

	public Supplier(int supplierNo, String supplierName) {
		super();
		this.supplierNo = supplierNo;
		this.supplierName = supplierName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id; 
	
	Set <OrderItem> orderItem;
	
	@Column(name = "SUPPLIER_NO")
	private int supplierNo;
	
	@Column(name = "SUPPLIER_NAME")
	private String supplierName;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "SUPPLIER")
	public Set<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Set<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	
	public int getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(int supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", supplierNo=" + supplierNo + ", supplierName=" + supplierName + "]";
	} 
}
