package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Sale {
	private Date date;
	private String email;
	private String paymentMethod;
	private int count;
	private Map<String, Integer> id = new HashMap<>();

	public int getCount() {
		return count;
	}

	public Date getDate() {
		return date;
	}

	public String getEmail() {
		return email;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Map<String, Integer> getId() {
		return id;
	}

	public void setId(Map<String, Integer> id) {
		this.id = id;
	}
}
