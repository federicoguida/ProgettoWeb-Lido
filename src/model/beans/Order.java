package model.beans;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Order implements Serializable{
		private String order_id; 
		private int user_id;
		private int orderPay;
		private String instruction;
		private double total;
		private int delivered;
		private ArrayList<LineItem> items;
		
		
		public Order() {
			super();
			this.order_id="";
			this.user_id=0;
			this.orderPay=0;
			this.instruction="";
			this.total=0;
			this.delivered=0;
		}
		
		public ArrayList<LineItem> getItems() {
			return items;
		}


		public void setItems(ArrayList<LineItem> items) {
			this.items = items;
		}


		public int getDelivered() {
			return delivered;
		}


		public void setDelivered(int delivered) {
			this.delivered = delivered;
		}


		public double getTotal() {
			return total;
		}


		public void setTotal(double total) {
			this.total = total;
		}


		public String getOrder_id() {
			return order_id;
		}

		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}
	
		public int getUser_id() {
			return user_id;
		}
		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}
		public int getOrderPay() {
			return orderPay;
		}
		public void setOrderPay(int orderPay) {
			this.orderPay = orderPay;
		}
		
		public String getInstruction() {
			return instruction;
		}
		public void setInstruction(String instruction) {
			this.instruction = instruction;
		}
		
	
}
