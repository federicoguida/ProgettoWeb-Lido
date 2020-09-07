package model.beans;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Cart implements Serializable{
	private final ArrayList<LineItem> items;
	private double totalPrice;
	
	public Cart() {
		items = new ArrayList<>();
		totalPrice=0;
	}
	
	public ArrayList<LineItem> getItems() {
		return items;
	}
	
	public int getCount() {
		return items.size();
	}
	
	public void addItem(LineItem item) {
		int code = item.getProduct().getProduct_code();
		int quantity = item.getQuantity();
		for (LineItem lineItem : items) {
			if (lineItem.getProduct().getProduct_code() == code) {
				lineItem.setQuantity(lineItem.getQuantity() + quantity);
				return;
			}
		}
		items.add(item);
	}
	
	public void removeItem(int product_code) {
		for (int i = 0; i < items.size(); i++) {
			LineItem lineItem = items.get(i);
			if (lineItem.getProduct().getProduct_code()==product_code) {
				items.remove(i);
				return;
			}
		}
	}
	
	
	public double getTotalPrice() {
		this.totalPrice=0;
		for(LineItem var : items) {
			this.totalPrice+=var.getTotal();
		}
		return this.totalPrice;
	}
	
	public String getTotalCurrencyFormat() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(this.getTotalPrice());
	}
}
