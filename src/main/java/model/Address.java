package model;

public class Address {
	private int ID;
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	private String city;
	private String street;
	private int nr;
	
	public Address(int ID,String city, String street,int nr) {
		this.ID=ID;
		this.city=city;
		this.street=street;
		this.nr=nr;
		
	}
	
	public Address() {
		
	}
	
	public String toString() {
		String s="";
		s=s+ID+" "+city+" "+street+" "+nr;
		return s;
	}
	
}
