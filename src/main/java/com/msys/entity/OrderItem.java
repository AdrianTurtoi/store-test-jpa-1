package com.msys.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id; 
	
	private Set <OrdersItems> ordersItems;
	
	public OrderItem(Long id, Set<OrdersItems> ordersItems, Article article, int quantity, Supplier supplier) {
		super();
		this.id = id;
		this.ordersItems = ordersItems;
		this.article = article;
		this.quantity = quantity;
		this.supplier = supplier;
	}

	private Article article;
	
	@Column(name = "QUANTITY")
	private int quantity;
	
	private Supplier supplier;
	
	public OrderItem(Long id, Article article, int quantity, Supplier supplier) {
		super();
		this.id = id;
		this.article = article;
		this.quantity = quantity;
		this.supplier = supplier;
	}
 
	public OrderItem() {
	}

	public OrderItem(Article article, int quantity, Supplier supplier) {
		super();
		this.article = article;
		this.quantity = quantity;
		this.supplier = supplier;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "ORDER_ITEM")
	public Set<OrdersItems> getOrdersItems() {
		return ordersItems;
	}

	public void setOrdersItems(Set<OrdersItems> ordersItems) {
		this.ordersItems = ordersItems;
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
		return "OrderItem [id=" + id + ", ordersItems=" + ordersItems + ", article=" + article + ", quantity="
				+ quantity + ", supplier=" + supplier + "]";
	} 
}
