package model.beans;

import java.io.Serializable;
import java.text.NumberFormat;


@SuppressWarnings("serial")
public class Product implements Serializable{
	private int product_code;
	private String name;
	private String description;
	private double price;
	private String category;
	private String imgURL;
	
	public Product() {
		this.product_code=0;
		this.name="";
		this.description="";
		this.price=0;
		this.category="";
		this.imgURL="";
	}

	public int getProduct_code() {
		return product_code;
	}

	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}	
	
	public String getPriceCurrencyFormat() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(price);
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	
}
