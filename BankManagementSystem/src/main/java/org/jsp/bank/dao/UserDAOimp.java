package org.jsp.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class UserDAOimp implements UserDAO {
	final private String credit="update bank_user_details set User_Amount=? where User_Account_Number=? and User_Password=?";
	final private String update= "update bank_user_details set User_Amount=? and User_Password=?";
	final private String selectaccountandpassword="select * from bank_user_details where User_Account_Number =? and User_Password=?";
	final private String url="jdbc:mysql://localhost:3307/bankmanagementsystem?user=root&password=12345";
	final private String select="select * from bank_user_details where User_Bank_Email_Id=? and User_Password=?";
	final private String insertionintostatement="insert into statement values(?,?,?,?,?,?,?,?,?)";
	Scanner sc=new Scanner(System.in);
	@Override
	public boolean userLogIn(String bankemailid, int password) {
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1, bankemailid);
			ps.setInt(2, password);
			ResultSet set=ps.executeQuery();
			if(set.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
			
	}

	@Override
	public void debit(int accountnumber, int password) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(selectaccountandpassword);
			ps.setInt(1,accountnumber);
			ps.setInt(2,password);
			ResultSet set=ps.executeQuery();
			if(set.next())
			{
				System.out.println("Enter your Amount");
				double amount=sc.nextDouble();
				if(amount>=0)
				{
					double databaseamount=set.getDouble("User_Amount");
					if(databaseamount>=amount)
					{
						double remainamount=databaseamount-amount;
						PreparedStatement ps1=connection.prepareStatement(update);
						ps1.setDouble(1, remainamount);
						ps1.setInt(2, password);
						int result=ps1.executeUpdate();
						if(result!=0)
						{
							PreparedStatement ps2=connection.prepareStatement(insertionintostatement);
							ps2.setString(1, "Debit");
							ps2.setDate(2, Date.valueOf(LocalDate.now()));
							ps2.setString(3, "online");
							Random random=new Random();
							int transactionid=random.nextInt(10000000);
							if(transactionid<1000000)
							{
								transactionid+=1000000;
							}
							ps2.setInt(4, transactionid);
							ps2.setString(5, amount+"dr");
							int id=set.getInt("User_Id");
							ps2.setInt(6, id);
							ps2.setInt(7, accountnumber);
							ps2.setTime(8, Time.valueOf(LocalTime.now()));
							ps2.setString(9, remainamount+"cr");
							int update2=ps2.executeUpdate();
							if(update2!=0) {
							System.out.println("Amount Deposited Successfully");
							}
						}
						else
						{
							System.out.println("Server 404");
						}
					}
					else
					{
						System.out.println("Insufficient Balance");
					}
				}
				else
				{
					System.out.println("Invalid amount");
				}
				
			}
			else
			{
				System.out.println("Invalid Account Details");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void credit(int accountnumber, int password) {
		// TODO Auto-generated method stub
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(selectaccountandpassword);
			ps.setInt(1, accountnumber);
			ps.setInt(2, password);
			ResultSet set=ps.executeQuery();
			if(set.next())
			{
				System.out.println("Enter credited Amount");
				double creditedamount=sc.nextDouble();
				double databaseamount=set.getDouble("User_Amount");
				double addedamount=databaseamount+creditedamount;
				PreparedStatement ps1=connection.prepareStatement(credit);
				ps1.setDouble(1, addedamount);
				ps1.setInt(2, accountnumber);
				ps1.setInt(3, password);
				int res=ps1.executeUpdate();
				if(res!=0)
				{
					PreparedStatement ps2=connection.prepareStatement(insertionintostatement);
					ps2.setString(1, "Credit");
					ps2.setDate(2, Date.valueOf(LocalDate.now()));
					ps2.setString(3, "Online");
					Random r=new Random(10000000);
					int transactionid=r.nextInt(1000000);
					if(transactionid<1000000)
					{
						transactionid+=1000000;
					}
					ps2.setInt(4, transactionid);
					ps2.setString(5, creditedamount+"cr");
					int id=set.getInt("User_Id");
					ps2.setInt(6, id);
					ps2.setInt(7, accountnumber);
					ps2.setTime(8,Time.valueOf(LocalTime.now()) );
					ps2.setString(9, addedamount+"cr");
					int update1=ps2.executeUpdate();
					if(update1!=0)
					{
						System.out.println("Amount credited Successfully");
					}
					else
					{
						System.out.println("Amount not credited");
					}
				}
				else
				{
					System.out.println("Sever 404");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private final String updateamount="update bank_user_details  set User_Amount=? where User_Mobile_Number=?";
	private final String selectmobile="select * from bank_user_details where User_Mobile_Number=?";
	@Override
	public void mobileToMobileTransaction(String mobileNo) {
		// TODO Auto-generated method stub
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(selectmobile);
			ps.setString(1, mobileNo);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				System.out.println("Enter Receivers mobile number");
				String rmobileNo=sc.next();
				PreparedStatement psr=connection.prepareStatement(selectmobile);
				psr.setString(1, rmobileNo);
				ResultSet setr=psr.executeQuery();
				if(setr.next()){
					System.out.println("Enter your Amount");
					double amount=sc.nextDouble();
					if(amount>=0)
					{
						double sendersdatabaseamount=rs.getDouble("User_Amount");
						if (sendersdatabaseamount>=amount) {
							double debit=sendersdatabaseamount-amount;
							PreparedStatement psu=connection.prepareStatement(updateamount);
							psu.setDouble(1, debit);
							psu.setString(2, mobileNo);
							int result=psu.executeUpdate();
							if(result!=0){
								PreparedStatement pssus=connection.prepareStatement(insertionintostatement);
								pssus.setString(1, "Debit");
								pssus.setDate(2, Date.valueOf(LocalDate.now()));
								pssus.setString(3, "Mobile Transaction");
								Random r=new Random(10000000);
								int transactionid=r.nextInt(1000000);
								if(transactionid<1000000){
									transactionid+=1000000;
								}
								pssus.setInt(4, transactionid);
								pssus.setString(5, amount+"dr");
								pssus.setInt(6, rs.getInt("User_Id"));
								pssus.setInt(7, rs.getInt("User_Account_Number"));
								pssus.setTime(8, Time.valueOf(LocalTime.now()));
								pssus.setString(9, debit+"rs");
								int statementresult=pssus.executeUpdate();
								if (statementresult!=0) {
									System.out.println("Amount Debited Successfully");
									PreparedStatement psur=connection.prepareStatement(updateamount);
									double credit=amount+setr.getDouble("User_Amount");
									psur.setDouble(1, credit);
									psur.setString(2, rmobileNo);
									int resultr=psur.executeUpdate();
									if (resultr!=0) {
										PreparedStatement psurs=connection.prepareStatement(insertionintostatement);
										psurs.setString(1, "Credit");
										psurs.setDate(2, Date.valueOf(LocalDate.now()));
										psurs.setString(3, "Mobile Transaction");
										Random r1=new Random(10000000);
										int transactionid1=r1.nextInt(1000000);
										if(transactionid1<1000000){
											transactionid1+=1000000;
										}
										psurs.setInt(4, transactionid1);
										psurs.setString(5, amount+"cr");
										psurs.setInt(6, setr.getInt("User_Id"));
										psurs.setInt(7, setr.getInt("User_Account_Number"));
										psurs.setTime(8, Time.valueOf(LocalTime.now()));
										psurs.setString(9, credit+"rs");
										int statementresultr=psurs.executeUpdate();
										if(statementresultr!=0){
											System.out.println("Amount Received successfully");
										}
										else{
											System.out.println("Transaction Failed");
										}
									} else {

									}
								}
								else{

								}
							}
							else{
								
							}
						} 
						else {
							System.out.println("Insufficient balance");
						}
					}
					else{
						System.out.println("Invalid amount");
					}
				}
				else{
					System.out.println("Invalid Data");
				}
			}
			else{
				System.out.println("Invalid mobile number");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
