package com.msys.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

	public OrderItem() {
		orders = new HashSet<Order>();
	}

	public OrderItem(Set<Order> orders, Article article, int quantity, Supplier supplier) {
		super();
		this.orders = orders;
		this.article = article;
		this.quantity = quantity;
		this.supplier = supplier;
	}
	
	public OrderItem(Article article, int quantity, Supplier supplier) {
		super();		
		this.article = article;
		this.quantity = quantity;
		this.supplier = supplier;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_entity_seq_gen")
	//@SequenceGenerator(name="my_entity_seq_gen", sequenceName="HIBERNATE_SEQUENCE")
	@Column(name = "ID")
	private Long id;

	private Set<Order> orders;

	private Article article;

	@Column(name = "QUANTITY")
	private int quantity;

	private Supplier supplier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany(mappedBy = "orderItems")
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ARTICLE", nullable = false)
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SUPPLIER", nullable = false)
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", ordersItems=" + orders + ", article=" + article + ", quantity=" + quantity
				+ ", supplier=" + supplier + "]";
	}
}
