package model;

public class Product {
	private int ID;
	private String name;
	private int manufacturerID;
	private int stock;
	private int price;
	
	public Product(int ID, String name, int manufacturerID, int stock,int price) {
		this.ID=ID;
		this.name=name;
		this.manufacturerID=manufacturerID;
		this.stock=stock;		
		this.price=price;
	}
	public Product() {
		
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getManufacturerID() {
		return manufacturerID;
	}
	public void setManufacturerID(int manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
