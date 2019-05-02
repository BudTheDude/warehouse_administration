package model;

public class Manufacturer {
	private int ID;
	private String name;
	private int addressID;
	
	public Manufacturer(int ID, String name, int addressID) {
		this.ID=ID;
		this.name=name;
		this.addressID=addressID;
	}
	public Manufacturer() {
		
		
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
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	
}
