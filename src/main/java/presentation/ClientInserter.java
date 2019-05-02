package presentation;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import data_access.tema3.Querries;
import model.Address;
import model.Customer;

public class ClientInserter extends JFrame{
	
	JFrame ref=this;
	JPanel pan=new JPanel();
	JLabel firstName=new JLabel("First Name:");
	JTextField firstName1=new JTextField();
	
	JLabel lastName=new JLabel("Last Name:");
	JTextField lastName1=new JTextField();
	
	JComboBox addressList;
	JLabel address=new JLabel("Address:");
	

	JLabel phone=new JLabel("Phone:");
	JTextField phone1= new JTextField();
	
	JLabel email=new JLabel("Email:");
	JTextField email1= new JTextField();
	
	
	//delete&update
	JLabel clientSelect=new JLabel("Select Client:");
	JComboBox clientList;
	JButton delete=new JButton("Delete customer");
	
	JButton add=new JButton("Add Customer");
	JButton update=new JButton("Update");
	
	//insert constructor
	public ClientInserter() {
		this.setSize(600, 400);
		this.setVisible(true);
		this.setResizable(true);
		
		String[] addresses= (new Utilities()).getPossibleValues((new Querries()).selectALL(new Address()));
		addressList=new JComboBox(addresses);
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		this.add(pan);
		pan.add(firstName);
		pan.add(firstName1);
		pan.add(lastName);
		pan.add(lastName1);
		pan.add(address);
		pan.add(addressList);
		pan.add(phone);
		pan.add(phone1);
		pan.add(email);
		pan.add(email1);
		pan.add(add);
		Handler h=new Handler();
		add.addActionListener(h);
		
	}
	
	//construct for delete
	public ClientInserter(int del) {
		this.setSize(600, 400);
		this.setVisible(true);
		this.setResizable(true);
		
		String[] clients= (new Utilities()).getPossibleValues((new Querries()).selectALL(new Customer()));
		clientList=new JComboBox(clients);
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		this.add(pan);
		pan.add(clientSelect);
		pan.add(clientList);
		pan.add(delete);
		Handler h=new Handler();
		delete.addActionListener(h);
	}
	
	//construct for update
	public ClientInserter(int updater,int updater2) {
		this.setSize(600, 400);
		this.setVisible(true);
		this.setResizable(true);
		
		String[] clients= (new Utilities()).getPossibleValues((new Querries()).selectALL(new Customer()));
		clientList=new JComboBox(clients);
		

		String[] addresses= (new Utilities()).getPossibleValues((new Querries()).selectALL(new Address()));
		addressList=new JComboBox(addresses);
		
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		this.add(pan);
		pan.add(clientSelect);
		pan.add(clientList);
		pan.add(firstName);
		pan.add(firstName1);
		pan.add(lastName);
		pan.add(lastName1);
		pan.add(address);
		pan.add(addressList);
		pan.add(phone);
		pan.add(phone1);
		pan.add(email);
		pan.add(email1);
		pan.add(update);
		Handler h=new Handler();
		
		update.addActionListener(h);
	}
	
	
	
	
	private class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==add) {
				int addressID=0;
				int i=0;
				
				while(Character.isDigit(addressList.getSelectedItem().toString().charAt(i))) {
					i++;
				}
					addressID=Integer.parseInt(addressList.getSelectedItem().toString().substring(0, i));
				
				try {
				int ID=(new Utilities()).getFreeID((new Querries()).selectALL(new Customer()));
				
				(new Querries()).insert(new Customer(ID,firstName1.getText(),lastName1.getText(),addressID,Integer.parseInt(phone1.getText()),email1.getText()));
				}catch(IndexOutOfBoundsException ex) {
					(new Querries()).insert(new Customer(1,firstName1.getText(),lastName1.getText(),addressID,Integer.parseInt(phone1.getText()),email1.getText()));

				}
				catch(NumberFormatException ex) {
					(new Utilities()).displayPopupMessage("Invalid input");
				}
				ref.dispose();
				
				
			}
			if(e.getSource()==delete) {
				int custID;
				int i=0;
				
				while(Character.isDigit(clientList.getSelectedItem().toString().charAt(i))) {
					i++;
				}
				custID=Integer.parseInt(clientList.getSelectedItem().toString().substring(0, i));
				(new Querries()).delete(new Customer(custID,"a","b",0,1,"c"));
				ref.dispose();
			}
			if(e.getSource()==update) {
				int custID=0;
				int addressID;
				int i=0;
				
				while(Character.isDigit(clientList.getSelectedItem().toString().charAt(i))) {
					i++;
				}
				custID=Integer.parseInt(clientList.getSelectedItem().toString().substring(0, i));
				
				
				i=0;
				while(Character.isDigit(addressList.getSelectedItem().toString().charAt(i))) {
					i++;
				}
				addressID=Integer.parseInt(addressList.getSelectedItem().toString().substring(0, i));
				
				(new Querries()).update(new Customer(custID,firstName1.getText(),lastName1.getText(),addressID,Integer.parseInt(phone1.getText()),email1.getText()), custID);
				ref.dispose();
			}
		}
}

	public static void main(String[] args) {
		ClientInserter t=new ClientInserter();
	}
}
