package model;

public class Book {
	private String id;
	private String title;
	private String author;
	private double price;

	public String getAuthor() {
		return author;
	}

	public String getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
