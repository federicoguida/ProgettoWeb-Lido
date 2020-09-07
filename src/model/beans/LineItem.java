package model.beans;

import java.io.Serializable;
import java.text.NumberFormat;

@SuppressWarnings("serial")
public class LineItem implements Serializable{
	private Product product;
	private int quantity;
	
	public LineItem() {}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotal() {
		double total = product.getPrice() * this.quantity;
		return total;
	}
	
	public String getTotalCurrencyFormat() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(this.getTotal());
	}
}
