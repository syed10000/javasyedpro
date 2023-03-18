





package com.mart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Databaseconnect mart=new Databaseconnect();
		while(true) {
			System.out.println("\t\t\t\t\t\tWecome to z-mart online shopping\n");
			System.out.println("\t\t\t\t\t1)USER LOGIN 2)ADMIN LOGIN");
			System.out.println("please select your options:");
			int user=sc.nextInt();
			if(user==1) {
				mart.userlog();
		System.out.print("Enter your option:");
		int option=sc.nextInt();
		
		if(option==1) {
			System.out.println("---------------------CREATE YOUR ZMART ACCOUNT---------------------------");
			System.out.print("Please Enter your name:");
			String name=sc.next();
			System.out.println("please enter your email id:");
			String emailid=sc.next();
			System.out.println("please enter your mobilenumber");
			String mobilenumber=sc.next();
			System.out.println("please enter your password");
			String password=sc.next();
			int result=mart.newuserdata(name, mobilenumber, emailid,password);
			System.out.println((result>=1)?"YOUR ID FOR ZMART IS CREATED SUCCESFULLY":"YOUR ID IS NOT CREATED");
		}else if(option==2) {
			System.out.println("---------------------LOGIN---------------------------");
			System.out.println("please Enter your email id:");
			String loginemailid=sc.next();
			System.out.println("please Enter your password:");
			String password=sc.next();
			boolean datacheck= mart.loginvalidation(loginemailid, password);
			if(datacheck==true) {
				int loginsuccessoption;
				mart.postloginvalidationsuccess();
	        do {      	
				System.out.println("-------------Hy welcome to our page---------------");
				System.out.println("select your options given below:");
				System.out.println("1)purchase\t\t2)offers\t\t3)exit");
				 loginsuccessoption=sc.nextInt();
				if(loginsuccessoption==1) {
					System.out.println("select your product category given below:");
					System.out.println("1)Drinks\t\t2)Biscuits\t\t3)Fruits\t\t4)vegetables\t\t5)households\t\t6)grocerys\t\t7)personalcares\t\t8)chocolates");
					System.out.println("Select your product category to purchase please.... ");
					int categoryoption=sc.nextInt();
					System.out.println("product_id\tproduct_name\tproduct_price");
					   mart.categoryoptionproduct(categoryoption);
					   System.out.println("Select your product Id:");
					   System.out.println("*NOTE TO CLOSE YOUR ORDER AT LAST ENTER NUMBER:0*");
					   int prid;
					   do{
					System.out.println("\t\t\t\t\t\t\t\t\t\tPRESS:0 CLOSE ORDER\t\t\t\t\t\t\t\t\t\tSELCT ID FOR FURTHER PURCHASE");
					    prid=sc.nextInt();
					    	if(prid==0) {
								   System.out.println("please enter 00 to know the total amount you added to the cart");
								   int totaloptioncheck=sc.nextInt();
								   if(totaloptioncheck==00) {
									   System.out.println("your total amount is:");
									   mart.total(1);
									   mart.setselectontozer_fortotalling(0, prid);
									   System.out.println("Enter 2 for confirm your order");
									   int confirmorder=sc.nextInt();
									   if(confirmorder==2) {
										   
									   }
									   mart.aftertotalsetdbtozero(prid);
									   break;
									  }
							   }
							   
					    	
					    System.out.println("press 1 to add to cart:");
						   int prid1=sc.nextInt();
					    int req=mart.setselectontozer_fortotalling(prid1, prid);
						   System.out.println((req>=1)?"added to caart":"not added");
					   mart.productselection(prid);
					   System.out.println("Select next order id>>> :");
					}while(prid!=0);
					   
					   
			}else if(loginsuccessoption==2) {
					System.out.println("Please wait getting your offers available");
					System.out.println("------**GET 10% DISOUNT ON YOUR TOTAL PURCHASE****-------");
					
				}
						else {
					System.out.println("Exiting.....");
				}}while(loginsuccessoption!=3);
			}else {
				System.out.print("invalid login");
			}
		}else if(option==3) {
			
			System.out.println("---------------------DELETION OF ACCOUNT---------------------------");
			System.out.println("--------TO DELETE YOUR ACCOUNT FIRST VALIDATE YOUR USERNAME AND PASSWORD");
			System.out.println("please Enter your email id:");
			String loginemailiddeletion=sc.next();
			System.out.println("please Enter your password:");
			String passworddeletion=sc.next();
			boolean datacheckfordeletion=mart.loginvalidation(loginemailiddeletion, passworddeletion);
	        if(datacheckfordeletion==true) {
	        	System.out.println("successfull login");
	        	System.out.println("TWO FACTOR AUTHENTICATION PROCESSING\nonce again enter your username and password to delete your account:");
	        	System.out.println("please Enter your email id:");
				String loginemailiddeletions=sc.next();
				System.out.println("please Enter your password:");
				String passworddeletions=sc.next();
				int delacc=mart.accountdeletion(loginemailiddeletions, passworddeletions);
				System.out.println((delacc>=1)?"YOUR ACCOUNT IS DELETED SUCCESFULLY":"YOUR ACCOUNT IS NOT DELETED CREATED");
	        }else {
	        	System.out.println("Invalid login cradentials to delete your account");
	        }
		}else if(option==4) {
			System.out.println("please enter your email id to change password:");
			String em=sc.next();
			System.out.println("please enter your exsisting password:");
			String pp=sc.next();
			boolean is=mart.loginvalidation(em, pp);
			if(is==true) {
				System.out.println("Two factor authentication processing......");
				System.out.println("Enter your email id:");
				String emm=sc.next();
				System.out.println("Enter your new password:");
				String ppp=sc.next();
				int changed=mart.changeuserpassword(emm,ppp);
				System.out.println((changed>=1)?"changed password":"not changed password");
			}
		}else if(option!=1||option!=2||option!=3||option!=4) {
			System.out.println("Invalid option input check your input");
		}
		
			}else if(user==2) {
				System.out.println("---------------------LOGIN---------------------------");
				System.out.println("please Enter your adminlog username:");
				String adminloginusrname=sc.next();
				System.out.println("please Enter your password:");
				String password=sc.next();
				boolean datacheckadmin=mart.adminloginvalidation(adminloginusrname, password);
				if(datacheckadmin==true) {
				mart.adminlog();
				System.out.println("Enter your optionS:");
				int adminoption=sc.nextInt();
				if(adminoption==1) {
					System.out.println("---------------------INSERT YOUR DATA TO ZMART ACCOUNT DATABASE---------------------------");
					System.out.println("please enter your PRODUCT ID:");
					int pid=sc.nextInt();
					System.out.print("Enter your PRODUCT NAME:");
					String productname=sc.next();
					System.out.println("please enter your PRODUCT PRICE:");
					int price=sc.nextInt();
					System.out.println("please enter your PRODUCT TYPE");
					System.out.println("1)Drinks\t\t2)Biscuits\t\t3)Fruits\t\t4)vegetables\t\t5)households\t\t6)grocerys\t\t7)personalcares\t\t8)chocolates");
					System.out.println("NOTE:please enter the Product type in name");
					String type=sc.next();
					System.out.println("Enter your product code");
					System.out.println("1---FOR Drinks\t\t2----FOR Biscuits\t\t3---FOR Fruits\t\t4---FOR vegetables\t\t5---FOR households\t\t6----FOR grocerys\t\t7----FOR personalcares\t\t8----FOR chocolates");
					System.out.println("NOTE:please enter the Product code in number as category above:");
					int code=sc.nextInt();
					int adminresult=mart.admininsertdata(pid, productname, price, type, code);
					System.out.println((adminresult>=1)?"YOUR data added":"YOUR data is NOT CREATED");
				}else if(adminoption==2) {
					System.out.println("product_id\t\tproduct_name\t\tproduct_price\t\tproduct_type");
					mart.adminviewproducts();
					System.out.println("Enter your product id to delete the product:");
					int aprid=sc.nextInt();
					mart.adminproductdeletion(aprid);
				}else if(adminoption==3) {
					mart.adminviewcustomerdetails();
				}else if(adminoption==4) {
					mart.adminviewcustomerdetails();
					System.out.println("Enter your customer id");
					int cstid=sc.nextInt();
					System.out.println("Enter your customer moile number:");
					String mn=sc.next();
					mart.adminaccountdeletionforcust(cstid, mn);
				}else if(adminoption==5){
					System.out.println("---------------------LOGIN---------------------------");
					System.out.println("First verify your exsisting username and password");
					System.out.println("please Enter your adminlog username:");
					String adminloginusrnameforchange=sc.next();
					System.out.println("please Enter your password:");
					String passwordforchange=sc.next();
					boolean datacheckadminforchange=mart.adminloginvalidation(adminloginusrnameforchange, passwordforchange);
					if(datacheckadminforchange==true) {
						System.out.println("Enter your exsisting username");
						String exn=sc.next();
						System.out.println("Enter your new password");
						String psn=sc.next();
						int r=mart.updateadminpass(exn, psn);
						System.out.println((r>=1)?"password changed":"password not changed");
					}
				}else if(adminoption!=1||adminoption!=2||adminoption!=3||adminoption!=4||adminoption!=5) {
				
					System.out.println("Invalid option");
				}
				}else {
					System.out.println("Invalid admin login Sorry!!!!!");
				}
			}else if(user!=1||user!=2) {
				System.out.println("Invalid option please select correct option");
			}
		}
	
	}
}
