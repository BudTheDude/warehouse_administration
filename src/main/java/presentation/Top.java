package presentation;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bussiness_logic.Utilities;
import data_access.tema3.Connector;
import data_access.tema3.Querries;
import model.Address;
import model.Customer;
import model.Manufacturer;
import model.Orders;
import model.Product;

public class Top extends JFrame{
	
	JPanel clients=new JPanel();
	JPanel clientsButtons=new JPanel();
	JPanel products=new JPanel();
	JPanel productsButtons=new JPanel();
	JPanel orders=new JPanel();
	JPanel ordersButtons=new JPanel();
	JPanel manu=new JPanel();
	JPanel manuButtons=new JPanel();
	JPanel address=new JPanel();
	JPanel addressButtons=new JPanel();
	JPanel big=new JPanel();
	
	
	JLabel ClientTitle=new JLabel("CLIENTS");
	JLabel ProductTitle=new JLabel("PRODUCTS");
	JLabel OrdersTitle=new JLabel("ORDERS");
	JLabel ManuTitle=new JLabel("MANUFACTURERS");
	JLabel AddressTitle=new JLabel("ADDRESS");
	
	JButton addClient=new JButton("Add Client");
	JButton viewClient=new JButton("View Clients");
	JButton deleteClient=new JButton("Delete Client");
	JButton updateClient=new JButton("Update Client");
	
	JButton addProduct=new JButton("Add Product");
	JButton viewProducts=new JButton("View Products");
	JButton deleteProduct=new JButton("Delete Product");
	JButton updateProduct=new JButton("Update Product");
	
	JButton addOrder=new JButton("Add Order");
	JButton viewOrders=new JButton("View Orders");
	
	JButton addManu=new JButton("Add Manufacturer");
	JButton viewManu=new JButton("View Manufacturers");
	
	JButton addAddress=new JButton("Add Address");
	JButton viewAddress=new JButton("View Addresses");
	
	public Top() {
		
	this.setSize(600, 400);
	this.setVisible(true);
	this.setResizable(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	big.setLayout(new BoxLayout(big, BoxLayout.Y_AXIS));
	
	clients.setLayout(new BoxLayout(clients, BoxLayout.Y_AXIS));
	products.setLayout(new BoxLayout(products, BoxLayout.Y_AXIS));
	orders.setLayout(new BoxLayout(orders, BoxLayout.Y_AXIS));
	manu.setLayout(new BoxLayout(manu, BoxLayout.Y_AXIS));
	address.setLayout(new BoxLayout(address, BoxLayout.Y_AXIS));
	
	clientsButtons.setLayout(new BoxLayout(clientsButtons, BoxLayout.X_AXIS));
	productsButtons.setLayout(new BoxLayout(productsButtons, BoxLayout.X_AXIS));
	ordersButtons.setLayout(new BoxLayout(ordersButtons, BoxLayout.X_AXIS));
	addressButtons.setLayout(new BoxLayout(addressButtons, BoxLayout.X_AXIS));
	manuButtons.setLayout(new BoxLayout(manuButtons, BoxLayout.X_AXIS));
	
	big.setSize(600, 400);
	clients.setSize(200, 400);
	products.setSize(200, 400);
	orders.setSize(200, 400);
	
	big.add(clients);
	big.add(clientsButtons);
	big.add(products);
	big.add(productsButtons);
	big.add(orders);
	big.add(ordersButtons);
	big.add(manu);
	big.add(manuButtons);
	big.add(address);
	big.add(addressButtons);
	
	this.add(big);
	
	clients.add(ClientTitle);
	products.add(ProductTitle);
	orders.add(OrdersTitle);
	address.add(AddressTitle);
	manu.add(ManuTitle);
	
	clientsButtons.add(addClient);
	clientsButtons.add(viewClient);
	clientsButtons.add(deleteClient);
	clientsButtons.add(updateClient);
	
	productsButtons.add(addProduct);
	productsButtons.add(viewProducts);
	productsButtons.add(deleteProduct);
	productsButtons.add(updateProduct);
	
	ordersButtons.add(addOrder);
	ordersButtons.add(viewOrders);
	
	addressButtons.add(addAddress);
	addressButtons.add(viewAddress);
	
	manuButtons.add(addManu);
	manuButtons.add(viewManu);
	
	ClientTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
	ProductTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
	OrdersTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
	AddressTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
	ManuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	addClient.setAlignmentX(Component.CENTER_ALIGNMENT);
	addProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
	addOrder.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	Handler h=new Handler();
	
	viewClient.addActionListener(h);
	viewOrders.addActionListener(h);
	viewAddress.addActionListener(h);
	viewManu.addActionListener(h);
	viewProducts.addActionListener(h);
	addAddress.addActionListener(h);
	addClient.addActionListener(h);
	addManu.addActionListener(h);
	addProduct.addActionListener(h);
	addOrder.addActionListener(h);
	deleteClient.addActionListener(h);
	updateClient.addActionListener(h);
	deleteProduct.addActionListener(h);
	updateProduct.addActionListener(h);

	}
	
	public JTable createTable(ArrayList<Object> array) {
		JTable tabel=null;
		Field[] attributes=array.get(0).getClass().getDeclaredFields();
		String[] columns=new String[attributes.length];
		for(int i=0;i<attributes.length;i++) {
			columns[i]=attributes[i].getName();
		}
		
		DefaultTableModel modelGeneric = new DefaultTableModel(columns,0);
		tabel = new JTable(modelGeneric);
		
		
		for(int i=0;i<array.size();i++) {
		String[] row=new String[columns.length];
		int k=0;
		for(Field field: array.get(i).getClass().getDeclaredFields()) {
			
			field.setAccessible(true);
			Object value;
			
				try {
					value=field.get(array.get(i));
					row[k]=""+value;
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				k++;
			
		}
		modelGeneric.addRow(row);
		}
		
		tabel.setModel(modelGeneric);
		tabel.setVisible(true);
		return tabel;
		
	}
	private JPanel intoarcePanou() {
		JFrame show=new JFrame();
		JPanel put=new JPanel();
		show.setSize(800, 600);
		show.setVisible(true);
		show.setResizable(true);
		show.add(put);
		put.setLayout(new BoxLayout(put, BoxLayout.Y_AXIS));
		return put;
	}
	
	
	
	
	private class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==viewClient) {
				try {
				intoarcePanou().add(new JScrollPane(createTable(new Querries().selectALL(new Customer()))));
				}catch(IndexOutOfBoundsException q){
					(new Utilities()).displayPopupMessage("Empty Table");
				}
			}
			if(e.getSource()==viewAddress) {
				try {
				intoarcePanou().add(new JScrollPane(createTable(new Querries().selectALL(new Address()))));
			}catch(IndexOutOfBoundsException q){
				(new Utilities()).displayPopupMessage("Empty Table");
			}
			}
			if(e.getSource()==viewManu) {
				try {
				intoarcePanou().add(new JScrollPane(createTable(new Querries().selectALL(new Manufacturer()))));
			}catch(IndexOutOfBoundsException q){
				(new Utilities()).displayPopupMessage("Empty Table");
			}
			}
			if(e.getSource()==viewProducts) {
				try {
				intoarcePanou().add(new JScrollPane(createTable(new Querries().selectALL(new Product()))));
			}catch(IndexOutOfBoundsException q){
				(new Utilities()).displayPopupMessage("Empty Table");
			}
			}
			if(e.getSource()==viewOrders) {
				try {
				intoarcePanou().add(new JScrollPane(createTable(new Querries().selectALL(new Orders()))));
			}catch(IndexOutOfBoundsException q){
				(new Utilities()).displayPopupMessage("Empty Table");
			}
			}
			if(e.getSource()==addClient) {
				new ClientInserter();
			}
			if(e.getSource()==addAddress) {
				new AddressInserter();
			}
			if(e.getSource()==addManu) {
				new ManuInserter();
			}
			if(e.getSource()==addProduct) {
				new ProductInserter();
			}
			if(e.getSource()==addOrder) {
				new OrderInserter();
			}
			if(e.getSource()==deleteClient) {
				new ClientInserter(0);
			}
			if(e.getSource()==updateClient) {
				new ClientInserter(0,0);
			}
			if(e.getSource()==deleteProduct) {
				new ProductInserter(0);
			}
			if(e.getSource()==updateProduct) {
				new ProductInserter(0,0);
			}
		}
		
		
	
	}	
	
	
	public static void main(String[] args) {
		Top t=new Top();
		
		
		
	}
}
