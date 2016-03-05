package com.msys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPLIER")
public class EUSupplier extends Supplier { 
	
	public EUSupplier(int supplierNo, String supplierName, byte isVATExtempt) {
		super(supplierNo, supplierName);
		this.isVATExtempt = isVATExtempt;
	}
	
	@Column(name = "IS_VAT_EXTEMPT")
	private byte isVATExtempt; 

	public byte getIsVATExtempt() {
		return isVATExtempt;
	}

	public void setIsVATExtempt(byte isVATExtempt) {
		this.isVATExtempt = isVATExtempt;
	}

	@Override
	public String toString() {
		return "EUSupplier [isVATExtempt=" + isVATExtempt + "]";
	}

}
