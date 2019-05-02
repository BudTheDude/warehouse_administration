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
import model.Manufacturer;
import model.Product;


public class ProductInserter extends JFrame{
	
	JFrame ref=this;
	JPanel pan=new JPanel();
	JLabel name=new JLabel("Name:");
	JTextField name1=new JTextField();
	
	JLabel manu=new JLabel("Manufacturer:");
	JComboBox manuList;
	
	JLabel stock=new JLabel("Stock:");
	JTextField stock1=new JTextField();
	
	JLabel price=new JLabel("Price($):");
	JTextField price1=new JTextField();
	
	JButton add=new JButton("Add product");

	
	
	JLabel productSelect=new JLabel("Select Product:");
	JComboBox prodList;
	JButton delete=new JButton("Delete product");
	JButton update=new JButton("Update product");
	
	
	public ProductInserter() {
		this.setSize(600, 400);
		this.setVisible(true);
		this.setResizable(true);
		
		String[] manus= (new Utilities()).getPossibleValues((new Querries()).selectALL(new Manufacturer()));
		manuList=new JComboBox(manus);
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		this.add(pan);
		
		pan.add(name);
		pan.add(name1);
		pan.add(manu);
		pan.add(manuList);
		pan.add(stock);
		pan.add(stock1);
		pan.add(price);
		pan.add(price1);
		pan.add(add);
		Handler h=new Handler();
		add.addActionListener(h);
	}
	
	//constructor for deleting
	public ProductInserter(int del) {
		this.setSize(600, 400);
		this.setVisible(true);
		this.setResizable(true);
		
		String[] products= (new Utilities()).getPossibleValues((new Querries()).selectALL(new Product()));
		prodList=new JComboBox(products);
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		this.add(pan);
		pan.add(productSelect);
		pan.add(prodList);
		pan.add(delete);
		Handler h=new Handler();
		delete.addActionListener(h);
	}
	
	//constructor for updating
	public ProductInserter(int updater,int updater2) {
		this.setSize(600, 400);
		this.setVisible(true);
		this.setResizable(true);
		String[] manus= (new Utilities()).getPossibleValues((new Querries()).selectALL(new Manufacturer()));
		manuList=new JComboBox(manus);
		String[] products= (new Utilities()).getPossibleValues((new Querries()).selectALL(new Product()));
		prodList=new JComboBox(products);
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		this.add(pan);
		
		pan.add(productSelect);
		pan.add(prodList);
		
		pan.add(name);
		pan.add(name1);
		pan.add(manu);
		pan.add(manuList);
		pan.add(stock);
		pan.add(stock1);
		pan.add(update);
		Handler h=new Handler();
		update.addActionListener(h);
	}
	
	
	
private class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==add) {
				
				int manuID;
				int i=0;
				
				while(Character.isDigit(manuList.getSelectedItem().toString().charAt(i))) {
					i++;
				}
				manuID=Integer.parseInt(manuList.getSelectedItem().toString().substring(0, i));
				
			try {
				try {
				int ID=(new Utilities()).getFreeID((new Querries()).selectALL(new Product()));
					(new Querries()).insert(new Product(ID,name1.getText(),manuID,Integer.parseInt(stock1.getText()),Integer.parseInt(price1.getText())));
				}catch(IndexOutOfBoundsException ex) {
					(new Querries()).insert(new Product(1,name1.getText(),manuID,Integer.parseInt(stock1.getText()),Integer.parseInt(price1.getText())));
				}
			}catch(NumberFormatException ex) {
				(new Utilities()).displayPopupMessage("Invalid Input");
			}
				ref.dispose();
			}
			if(e.getSource()==delete) {
				int prodID;
				int i=0;
				
				while(Character.isDigit(prodList.getSelectedItem().toString().charAt(i))) {
					i++;
				}
				prodID=Integer.parseInt(prodList.getSelectedItem().toString().substring(0, i));
				(new Querries()).delete(new Product(prodID,"a",0,0,0));
				ref.dispose();
			}
			if(e.getSource()==update) {
				int prodID=0;
				int manuID;
				int i=0;
				
				while(Character.isDigit(manuList.getSelectedItem().toString().charAt(i))) {
					i++;
				}
				manuID=Integer.parseInt(manuList.getSelectedItem().toString().substring(0, i));
				
				i=0;
				while(Character.isDigit(prodList.getSelectedItem().toString().charAt(i))) {
					i++;
				}
				prodID=Integer.parseInt(prodList.getSelectedItem().toString().substring(0, i));
				
				try {
				(new Querries()).update(new Product(prodID,name1.getText(),manuID,Integer.parseInt(stock1.getText()),Integer.parseInt(price1.getText())),prodID);
				}catch(NumberFormatException ex) {
					(new Utilities()).displayPopupMessage("Invalid Input");
				}
				ref.dispose();
			}
		}
}
	
}
