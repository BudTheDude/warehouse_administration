package presentation;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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

public class ManuInserter extends JFrame {
	JFrame ref=this;
	JPanel pan=new JPanel();
	JLabel name=new JLabel("Name:");
	JTextField name1=new JTextField();
	
	JLabel address=new JLabel("Address:");
	JComboBox addressList;
	
	
	
	JButton add=new JButton("Add manufacturer");
	
	public ManuInserter() {
		this.setSize(600, 400);
		this.setVisible(true);
		this.setResizable(true);
		
		String[] addresses= (new Utilities()).getPossibleValues((new Querries()).selectALL(new Address()));
		addressList=new JComboBox(addresses);
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		this.add(pan);
		pan.add(name);
		pan.add(name1);
		pan.add(address);
		pan.add(addressList);
		pan.add(add);
		Handler h=new Handler();
		add.addActionListener(h);
	}
	
private class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==add) {
				int ID=(new Utilities()).getFreeID((new Querries()).selectALL(new Manufacturer()));
				int addressID;
				int i=0;
				
				while(Character.isDigit(addressList.getSelectedItem().toString().charAt(i))) {
					i++;
				}
				addressID=Integer.parseInt(addressList.getSelectedItem().toString().substring(0, i));
				
				
					(new Querries()).insert(new Manufacturer(ID,name1.getText(),addressID));
				
				
				ref.dispose();
				
			}
		}
}

public static void main(String[] args) {
	ManuInserter t=new ManuInserter();
}
	
}
