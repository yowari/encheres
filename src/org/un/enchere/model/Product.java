package org.un.enchere.model;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = -7741327757076207431L;
	
	private String title;
	private double price;
	private String seller;

	public Product(String title, double price, String seller) {
		this.price = price;
		this.title = title;
		this.seller = seller;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

}
