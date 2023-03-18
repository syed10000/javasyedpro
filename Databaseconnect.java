







package com.mart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Databaseconnect {
      private Connection con;
	public Databaseconnect() {
		String url="jdbc:mysql://localhost:3306/zmart";
		String user="root";
		String pass="";
		try {
			con=DriverManager.getConnection(url,user,pass);
			
		}catch(SQLException e) {
			System.out.print("error"+e);
		}
	}
	public void userlog() {
		System.out.println("\t\t\t\t\t\tWecome to z-mart online shopping\n");
		System.out.println("\t\t\t\t................LOGIN TO PURCHASE YOUR PRODUCTS........................");
		System.out.println("\t\t\t1.New user.........please register\n\t\t\t2.Log in........Already have an account on z-mart");
		System.out.println("\t\t\t3.Delete Account..........Delete your exsisting account");
		System.out.println("\t\t\t4.change password.");
	}
	public void adminlog() {
		System.out.println("\t\t\t\t................LOGIN FOR ADMIN ACCESS........................");
		System.out.println("\t\t\t1)FOR ADDING NEW PRODUCT \n\t\t\t2)DELETE YOUR PRODUCTS\n\t\t\t3)CUSTOMER DETAILS");
		System.out.println("\t\t\t4)DELETE CUSTOMER YOU WANT");
		System.out.println("\t\t\t5)CHANGE YOUR ADMIN PASSWORD");
	}
	public int newuserdata(String name,String mobilenumber,String emailid,String password) {
		int inserted=0;
		try {
			String query="insert into zmartcustomers(name,mobile_nummber,email_id,password) values(?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1,name);
			pst.setString(2,mobilenumber);
			pst.setString(3,emailid);
			pst.setString(4,password);
			inserted=pst.executeUpdate();
		}catch(SQLException a) {
			System.out.println("Error:"+a);
		}
	  return inserted;	
	}
	public boolean loginvalidation(String loginemailid,String password) {
		boolean exsist=false;
		try {
		String query="select email_id,password from zmartcustomers where email_id=? and password=?";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,loginemailid);
		pst.setString(2, password);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			String lm=rs.getString(1);
			String ps=rs.getString(2);
			if(lm.equals(loginemailid)&&ps.equals(password)) {
						exsist=true;
			}else{
				System.out.println("Invalid password or username");
				         exsist=false;
			}
		}
	}catch(SQLException e){
		System.out.println("Error"+e);
	}
		return exsist;
	}
	
public int accountdeletion(String loginemailiddeletion,String passworddeletion) {
	int del=0;
	try {
		String query="delete from zmartcustomers where email_id=? and password=?";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, loginemailiddeletion);
		pst.setString(2, passworddeletion);
		 del=pst.executeUpdate();
		 System.out.println("your account deleted succesfully");
	}catch(SQLException e) {
		System.out.println("Error:"+e);
	}
	return del;
	}
public void postloginvalidationsuccess() {
	System.out.println(" correct password....please wait logging you in");
}
public void categoryoptionproduct(int categoryproduct) {
	try {
		String query="select product_id,product_name,price from zmartproductmenu where product_code=?";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1,categoryproduct);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			int pid=rs.getInt(1);
			String pnme=rs.getString(2);
			
			int pr=rs.getInt(3);
			System.out.println(pid+"\t\t"+pnme+"\t\t"+pr);
		}
	}catch(SQLException c){
		System.out.println("Error"+c);
	}
}
public void productselectioninputgetting(String bucket[]) {
     int count=0;
		      for(int k=0;k<bucket.length;k++) {
			     if(bucket[k]!=null) {
				          count ++;
				          }
			     }
		for(int j=0;j<count;j++) {
		    System.out.println(bucket[j]);
		}
		
}
public void productselection(int prid) {
	
	try {
		String query="select product_id,product_name,price from zmartproductmenu where product_id=?";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1,prid);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			int pid=rs.getInt(1);
			String pnme=rs.getString(2);
			int pr=+rs.getInt(3);
			System.out.println(pid+"\t\t"+pnme+"\t\t"+pr);
		}}catch(SQLException q) {
			System.out.println("Error"+q);
		}
}
public int setselectontozer_fortotalling(int setone,int prid) {
	int n=0;
	try {
		String query="update zmartproductmenu set producton_select=? where product_id=?";
		PreparedStatement pst=con.prepareStatement(query);
		
		pst.setInt(1,setone);
		pst.setInt(2,prid);
		 n=pst.executeUpdate();
		}catch(SQLException q) {
			System.out.println("Error"+q);
		}
	return n;
}
public int total(int total) {
	int tot=0;
	try {
	String query="select sum(price) from zmartproductmenu where producton_select=?";
	PreparedStatement pst=con.prepareStatement(query);
	pst.setInt(1,total);
	ResultSet rs=pst.executeQuery();
	while(rs.next()) {
	int to=rs.getInt(1);
	System.out.println(to);
	
	}
	
}catch(SQLException n) {
	System.out.println("Error:"+n);
}
return tot;
}
public int  aftertotalsetdbtozero(int setzero) {
	int n=0;
	try {
		String query="update zmartproductmenu set producton_select=? where product_code in(1,2,3,4,5,6,7,8)";
	PreparedStatement pst=con.prepareStatement(query);
	pst.setInt(1,setzero);
	 n=pst.executeUpdate();
}catch(SQLException q) {
	System.out.println("Error:"+q);
}
	return n;
}
public int admininsertdata(int pid,String product,int price,String type,int code) {
	int inserted=0;
	try {
		String query="insert into zmartproductmenu(product_id,product_name,price,product_type,product_code) values(?,?,?,?,?)";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, pid);
		pst.setString(2,product);
		pst.setInt(3,price);
		pst.setString(4,type);
		pst.setInt(5,code);
		inserted=pst.executeUpdate();
	}catch(SQLException a) {
		System.out.println("Error:"+a);
	}
  return inserted;	
}
public int adminproductdeletion(int productid) {
	int admindel=0;
	try {
		String query="delete from zmartproductmenu where product_id=?";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, productid);
		 admindel=pst.executeUpdate();
		 System.out.println("your product deleted succesfully");
	}catch(SQLException e) {
		System.out.println("Error:"+e);
	}
	return admindel;
	}
public void adminviewproducts() {
	try {
		String query="select product_id,product_name,price,product_type from zmartproductmenu";
		PreparedStatement pst=con.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			int pid=rs.getInt(1);
			String pnme=rs.getString(2);
			int pr=+rs.getInt(3);
			String pt=rs.getString(4);
			System.out.println(pid+"\t\t"+pnme+"\t\t"+pr+"\t\t"+pt);
		}
		 System.out.println("your products");
	}catch(SQLException e) {
		System.out.println("Error:"+e);
	}
}
	public void adminviewcustomerdetails() {
		try {
			String query="select customer_id,name,mobile_nummber,email_id from zmartcustomers";
			PreparedStatement pst=con.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				int cs=rs.getInt(1);
				String nme=rs.getString(2);
				String mn=rs.getString(3);
				String em=rs.getString(4);
				System.out.println(cs+"\t\t"+nme+"\t\t"+mn+"\t\t"+em);
			}
			 System.out.println("your products");
		}catch(SQLException e) {
			System.out.println("Error:"+e);
		}
	}
	public int adminaccountdeletionforcust(int custid ,String mn) {
		int admincustdel=0;
		try {
			String query="delete from zmartcustomers where customer_id=? and mobile_nummber=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1,custid);
			pst.setString(2,mn);
			 admincustdel=pst.executeUpdate();
			 System.out.println("your selected customer id is deleted");
		}catch(SQLException e) {
			System.out.println("Error:"+e);
		}
		return admincustdel;
		}
	public boolean adminloginvalidation(String loginid,String password) {
		boolean adminexsist=false;
		try {
		String query="select username,password from adminlog where username=? and password=?";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,loginid);
		pst.setString(2, password);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			String lm=rs.getString(1);
			String ps=rs.getString(2);
			if(lm.equals(loginid)&&ps.equals(password)) {
						adminexsist=true;
			}else{
				System.out.println("Invalid password or username");
				         adminexsist=false;
			}
		}
	}catch(SQLException e){
		System.out.println("Error"+e);
	}
		return adminexsist;
	}
	public int  updateadminpass(String usernme,String pass) {
		int n=0;
		try {
			String query="update adminlog set password=? where username=?";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, pass);
		pst.setString(2, usernme);
		 n=pst.executeUpdate();
	}catch(SQLException q) {
		System.out.println("Error:"+q);
	}
		return n;
	}
	public int changeuserpassword(String mail,String pa) {
		int n=0;
		try {
			String query="update zmartcustomers set password=? where email_id=?";
			PreparedStatement pst=con.prepareStatement(query);
			
			pst.setString(1,pa);
			pst.setString(2,mail);
			 n=pst.executeUpdate();
			}catch(SQLException q) {
				System.out.println("Error"+q);
			}
		return n;
	}
}









