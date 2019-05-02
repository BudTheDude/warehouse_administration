package data_access.tema3;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import bussiness_logic.Utilities;

import java.sql.Connection;
import java.sql.ResultSet;

import model.Address;
import model.Customer;
import model.Manufacturer;
import model.Orders;
import model.Product;

public class Querries<T> {
	Connector con;
	public Querries() {
		con=new Connector();
	}
	public String retrieveFields(Object o) {
		String s="(";
		for(Field field: o.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value;
			
				try {
					value=field.get(o);
					if(value.getClass()==Integer.class) {
						s=s+value+",";
					}
					if(value.getClass()==String.class) {
						s=s+"\""+value+"\",";
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		}
		s = s.substring(0, s.length()-1);
		s+=");";
		return s;
	}
	
	
	
	
	
	
	public String retrieveFieldsUpdate(Object o) {
		String s="";
		for(Field field: o.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value;
			
				try {
					value=field.get(o);
					if(value.getClass()==Integer.class) {
						s=s+field.getName()+"="+value+",";
					}
					if(value.getClass()==String.class) {
						s=s+field.getName()+"="+"\""+value+"\",";
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		}
		s = s.substring(0, s.length()-1);
		
		return s;
	}
	
	
	
	
	
	
	public void insert(Object o) {
		
		Statement stmt;
		try {
			stmt = con.retrieveConnection().createStatement();
			stmt.executeUpdate("Insert into "+o.getClass().getSimpleName()+" values"+retrieveFields(o));
		} catch (SQLException  e) {
			(new Utilities()).displayPopupMessage("Two maufacturers cannot have the same address!");

		}
		
		
	}
	public void update(Object o,int ID) {
		Statement stmt;
		try {
			stmt = con.retrieveConnection().createStatement();
			stmt.executeUpdate("Update "+o.getClass().getSimpleName()+" set "+retrieveFieldsUpdate(o)+" where ID="+ID+";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delete(Object o) {
		Statement stmt;
		Field field=null;
		
		     try {
				field=o.getClass().getDeclaredField("ID");
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    field.setAccessible(true);
		     
		     
			try {
				stmt = con.retrieveConnection().createStatement();
				stmt.executeUpdate("Delete from "+o.getClass().getSimpleName()+" where ID="+field.get(o));

			} catch (SQLException | IllegalArgumentException |  SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
		
	public ArrayList<Object> selectALL(Object o){
		ArrayList<Object> objects = new ArrayList<Object>();
		Statement stmt;
		ResultSet res=null;
		try {
			stmt = con.retrieveConnection().createStatement();
			res=stmt.executeQuery("Select * from "+o.getClass().getSimpleName()+";");

		} catch (SQLException | IllegalArgumentException |  SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			try {
				while(res.next()) {
					Object instance=null;
					try {
						instance=Class.forName(o.getClass().getName()).newInstance();
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					for(Field field: instance.getClass().getDeclaredFields()) {
						Object value=res.getObject(field.getName());
						field.setAccessible(true);
						PropertyDescriptor prop=new PropertyDescriptor(field.getName(),instance.getClass());
						Method method= prop.getWriteMethod();
						
						try {
							method.invoke(instance, value);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
					
										
				objects.add(instance);	
					
					}
			} catch (SecurityException | SQLException | IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return objects;
		
		
	}
	public static void main(String[] args) {
		Querries c=new Querries();
		//c.insert(new Address(1,"Baia Mare","Malinului",12));
		//c.insert(new Customer(3,"Bud","Bogdan",1,12345,"bud@barosanu"));
		c.insert(new Manufacturer(1,"Aramis",1));
		//c.delete(new Manufacturer(1,"Bosch",2));
		//c.insert(new Orders(1,1,4));
		//c.selectALL(new Customer());
		//c.update(new Customer(5,"Flaviu","Cristian",43,11,"%"), 5);
		
		
	}
	
}
