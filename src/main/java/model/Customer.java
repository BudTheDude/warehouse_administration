package model;

public class Customer {
	private int ID;
	private String first_name;
	private String last_name;
	private int addressID;
	private int phone_nr;
	private String email;
	
	public Customer(int ID, String first_name, String last_name,int addressID,int phone_nr, String email) {
		this.ID=ID;
		this.first_name=first_name;
		this.last_name=last_name;
		this.addressID=addressID;
		this.phone_nr=phone_nr;
		this.email=email;
	}
	public Customer() {
		
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public int getPhone_nr() {
		return phone_nr;
	}

	public void setPhone_nr(int phone_nr) {
		this.phone_nr = phone_nr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
