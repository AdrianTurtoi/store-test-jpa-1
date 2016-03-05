package com.msys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	private String article;

	@Column(name = "QUANTITY")
	private int quantity;

	public Stock() {
	}

	public Stock(String article, int quantity) {
		super();
		this.article = article;
		this.quantity = quantity;
	}

	public Stock(Long id, String article, int quantity) {
		super();
		this.id = id;
		this.article = article;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", article=" + article + ", quantity=" + quantity + "]";
	} 
}
