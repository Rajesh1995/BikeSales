package model;

import java.util.Date;

public class Argument {
	private String books;
	private String sales;
	private Integer topSellingCount;
	private Integer topCustomers;
	private Date salesOnDate;

	public String getBooks() {
		return books;
	}

	public String getSales() {
		return sales;
	}

	public Date getSalesOnDate() {
		return salesOnDate;
	}

	public Integer getTopCustomers() {
		return topCustomers;
	}

	public Integer getTopSellingCount() {
		return topSellingCount;
	}

	public void setBooks(String books) {
		this.books = books;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public void setSalesOnDate(Date salesOnDate) {
		this.salesOnDate = salesOnDate;
	}

	public void setTopCustomers(Integer topCustomers) {
		this.topCustomers = topCustomers;
	}

	public void setTopSellingCount(Integer topSellingCount) {
		this.topSellingCount = topSellingCount;
	}

	public boolean validateArgs() {
		return true;
	}

}
