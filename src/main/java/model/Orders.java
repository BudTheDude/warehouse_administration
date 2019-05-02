package model;

public class Orders {
	private int custID;
	private int prodID;
	private int quantity;
	
	public Orders(int custID, int prodID, int quantity) {
		this.custID=custID;
		this.prodID=prodID;
		this.quantity=quantity;
	}
	public Orders() {
		
	}
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	public int getProdID() {
		return prodID;
	}
	public void setProdID(int prodID) {
		this.prodID = prodID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
