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

public class AddressInserter extends JFrame {
	
	JFrame ref=this;
	JPanel pan=new JPanel();
	JLabel city=new JLabel("City:");
	JTextField city1=new JTextField();
	
	JLabel street=new JLabel("Street:");
	JTextField street1=new JTextField();
	
	JLabel number=new JLabel("Number:");
	JTextField number1=new JTextField();
	
	JButton add=new JButton("Add address");
	
	public AddressInserter() {
		this.setSize(600, 400);
		this.setVisible(true);
		this.setResizable(true);
		
		
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		this.add(pan);
		pan.add(city);
		pan.add(city1);
		pan.add(street);
		pan.add(street1);
		pan.add(number);
		pan.add(number1);
		pan.add(add);
		Handler h=new Handler();
		add.addActionListener(h);
		}
	private class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==add) {
				int ID=(new Utilities()).getFreeID((new Querries()).selectALL(new Address()));
				(new Querries()).insert(new Address(ID,city1.getText(),street1.getText(),Integer.parseInt(number1.getText())));
				ref.dispose();
				
			}
		}
}
	public static void main(String[] args) {
		AddressInserter t=new AddressInserter();
	}
}
