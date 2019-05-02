package presentation;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bussiness_logic.Utilities;
import data_access.tema3.Connector;
import data_access.tema3.Querries;
import model.Address;
import model.Customer;
import model.Orders;
import model.Product;


public class OrderInserter extends JFrame{
	JFrame ref=this;
	JPanel pan=new JPanel();
	Connector con=new Connector();
	JComboBox custList;
	JLabel customer=new JLabel("Customer:");
	
	JComboBox prodList;
	JLabel Product=new JLabel("Product:");
	
	JLabel quantity=new JLabel("Quantity:");
	JTextField quantity1=new JTextField();
	
	JButton add=new JButton("Place order");
	
	public OrderInserter() {
		
		this.setSize(600, 400);
		this.setVisible(true);
		this.setResizable(true);
		
		String[] customers= (new Utilities()).getPossibleValues((new Querries()).selectALL(new Customer()));
		custList=new JComboBox(customers);
		String[] products= (new Utilities()).getPossibleValues((new Querries()).selectALL(new Product()));
		prodList=new JComboBox(products);
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		this.add(pan);
		pan.add(customer);
		pan.add(custList);
		pan.add(Product);
		pan.add(prodList);
		pan.add(quantity);
		pan.add(quantity1);
		pan.add(add);
		
		Handler h=new Handler();
		add.addActionListener(h);
		
	}
	
private class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==add) {
				
				int custID;
				int i=0;
				
				while(Character.isDigit(custList.getSelectedItem().toString().charAt(i))) {
					i++;
				}
				custID=Integer.parseInt(custList.getSelectedItem().toString().substring(0, i));
				
				int prodID;
				 i=0;
				
				while(Character.isDigit(prodList.getSelectedItem().toString().charAt(i))) {
					i++;
				}
				prodID=Integer.parseInt(prodList.getSelectedItem().toString().substring(0, i));
				
				int checkStock=0;
				
				Statement stmt;
				ResultSet res=null;
				Object obj=null;
				try {
					stmt = con.retrieveConnection().createStatement();
					res=stmt.executeQuery("Select stock from Product where ID="+prodID+";");
					res.next();
					
				} catch (SQLException | IllegalArgumentException |  SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					obj=res.getObject("stock");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				checkStock=(int)obj;
				
				String name=null;
				int maID=0;
				int pret=0;
				
				ArrayList<Product> prod=(new Querries()).selectALL(new Product());
				for(int j=0;j<prod.size();j++) {
					if(prod.get(j).getID()==prodID) {
						name=prod.get(j).getName();
						maID=prod.get(j).getManufacturerID();
						pret=prod.get(j).getPrice();
						
					}
				}
				try {
				if(Integer.parseInt(quantity1.getText())<=checkStock ) {
					
					try {
						PrintWriter writer = new PrintWriter("Bill"+(new Querries().selectALL(new Orders()).size())+".txt", "UTF-8");
						writer.println("Client: "+custList.getSelectedItem().toString());
						writer.println("Product: "+prodList.getSelectedItem().toString());
						writer.println("Quantity: "+quantity1.getText());
						writer.println("Price($): "+(Integer.parseInt(quantity1.getText())*pret));
						
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
						LocalDate localDate = LocalDate.now();
						
						
						writer.println("Date: "+dtf.format(localDate));
						
						
						File file=new File("Bill"+(new Querries().selectALL(new Orders()).size())+".txt");
						writer.close();
						
						Desktop.getDesktop().open(file);
						
					} catch (FileNotFoundException | UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					(new Querries()).insert(new Orders(custID,prodID,Integer.parseInt(quantity1.getText())));
					(new Querries()).update(new Product(prodID,name,maID,checkStock-Integer.parseInt(quantity1.getText()),pret), prodID);
					
				}
				else {
					(new Utilities()).displayPopupMessage("Understock");
				}
				}catch(NumberFormatException ex) {
					(new Utilities()).displayPopupMessage("Invalid input");
				}
				ref.dispose();
			}
		}
}
}
