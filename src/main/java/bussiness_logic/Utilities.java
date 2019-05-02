package bussiness_logic;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Utilities {
	
	public int getFreeID(ArrayList<Object> o) {
		int ID=0;
		int i=0;
		Field f=null;
		while(ID==0) {
		Object value=null;
		try {
			f=o.get(i).getClass().getDeclaredField("ID");
			f.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			value=f.get(o.get(i));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!(value.equals(i+1))) {
			
			ID=i+1;
			break;
		}
		if(i==o.size()-1) {
			ID=i+2;
			break;
		}
		i++;
		}
		return ID;
	}
	
	public String[] getPossibleValues(ArrayList<Object> o) {
		String[] result=new String[o.size()];
		
		for(int i=0;i<result.length;i++) {
			result[i]="";
			for(Field field: o.get(i).getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				
					try {
						value=field.get(o.get(i));
						result[i]=result[i]+value+"   ";
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				
			}
		}
		return result;
	}
	
	public void displayPopupMessage(final String message) {
		JOptionPane.showMessageDialog(null, message, "Popup message", JOptionPane.INFORMATION_MESSAGE);
	}
}
