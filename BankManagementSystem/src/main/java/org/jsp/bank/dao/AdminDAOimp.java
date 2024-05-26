package org.jsp.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;

import org.jsp.bank.model.BankUserDetails;

public class AdminDAOimp implements AdminDAO {
	
	final private String select="select * from bank_admin where Admin_Email_Id=? and Admin_Password=?";
    final private String url="jdbc:mysql://localhost:3307/bankmanagementsystem?user=root&password=12345"; 
    final private String insert="INSERT INTO bank_user_details (User_Id, User_Name, User_Bank_Email_Id, User_Email_Id, User_Password, User_Gender, User_Address, User_Date_Of_Birth, User_Account_Number, User_Amount, User_Mobile_Number) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    final private String selectall="select * from bank_user_details";
    final private String delete="delete from bank_user_details where  User_Account_Number=?";
    
	public boolean adminLogIn(String emailid, String password) {
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1, emailid);
			ps.setString(2, password);
			ResultSet set = ps.executeQuery();
			if(set.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	@Override
	public void userRegistration(BankUserDetails  bankuserdetails) {
		try {
			Connection connection =DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(insert);
			ps.setString(1, bankuserdetails.getUsername());
			Random random=new Random();
			int  emailidnum=random.nextInt();
			if(emailidnum<10)
			{
				emailidnum+=10;
			}
			String bankemailid=bankuserdetails.getUsername()+emailidnum+"@teca53.com";
			ps.setString(2, bankemailid);
			int password=random.nextInt(100);
			if(password<100)
			{
				password+=100;	
			}
			ps.setString(3, bankuserdetails.getUseremailid());
			ps.setInt(4,password);
			ps.setString(5, bankuserdetails.getUsergender());
			ps.setString(6, bankuserdetails.getUseraddress());
			LocalDate userdateofbirth=bankuserdetails.getUserdateofbirth();
			ps.setDate(7, Date.valueOf(userdateofbirth));
			int accountnumber=random.nextInt(1000000);
			if(accountnumber<1000000)
			{
				accountnumber+=1000000;
			}
			ps.setInt(8, accountnumber);
			ps.setDouble(9, bankuserdetails.getUseramount());
			ps.setString(10, bankuserdetails.getUsermobilenumber());
			ps.setString(11, "IFSCTECA53");
			int result=ps.executeUpdate();
			if(result!=0)
			{
				System.out.println("registration successfull");
			}
			else
			{
				System.out.println("provide valid details");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Override
	public void selectingAllUserDetails() {
		
			try {
				Connection connection=DriverManager.getConnection(url);
				Statement statement=connection.createStatement();
				ResultSet set=statement.executeQuery(selectall);
				if(set.isBeforeFirst())
				{
					while(set.next()) {
						System.out.println("Name of the User :"+set.getString("User_Name"));
						System.out.println("Bank Email Id :"+set.getString("User_BankEmail_Id"));
						System.out.println("Password of the User :"+set.getInt("User_Password"));
						System.out.println("Mobile Num Of the User :"+set.getString("User_Mobile_Number"));
						System.out.println("Gender of the User :"+set.getString("User_Gender"));
						System.out.println("Amount :"+set.getDouble("User_Amount"));
						
					}
				}
				else {
					System.out.println("Data Not Found");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	@Override
	public void deleteuserdetails(int accountnumber) {
		
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(delete);
			ps.setInt(1, accountnumber);
			int result=ps.executeUpdate();
			if(result!=0)
			{
				System.out.println("Account deleted");
			}
			else
			{
				System.out.println("No data found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
