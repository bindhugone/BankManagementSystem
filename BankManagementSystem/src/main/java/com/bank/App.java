package com.bank;



import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Scanner;

import org.jsp.bank.dao.AdminDAO;
import org.jsp.bank.dao.AdminHelperClass;
import org.jsp.bank.dao.UserDAO;
import org.jsp.bank.dao.UserHelperClass;
import org.jsp.bank.model.BankUserDetails;

/**
 * Hello world!
 *
 */
public class App
{
public static void main( String[] args )
    {
		 		AdminDAO admindao= AdminHelperClass.adminHelperMethod();
    	        UserDAO userdao=UserHelperClass.userHelperMethod();
    	        //userdao.debit(1088494, 178);
    	       // userdao.credit(1088494, 178);
    	        userdao.mobileToMobileTransaction("9642849587");
    	        boolean welcomestatus=true;
    	        while (welcomestatus) {
    	        	System.out.println("Enter \n 1. For Admin LogIn \n 2. For User LogIn");
    	        	Scanner scanner= new Scanner(System.in);
    	            int welcome = scanner.nextInt();
    	            
    	            switch (welcome) {
    	            case 1:
    	            	boolean adminloginstatus=true;
    	            	int count=0;
    	            	while(adminloginstatus)
    	            	{
    	            	System.out.println("Enter Your Email Id");
    	            	String emailid=scanner.next();
    	            	System.out.println("Enter Your Password");
    	            	String password=scanner.next();
    	            	if(admindao.adminLogIn(emailid,password)) {
    	            		adminloginstatus=false;
    	            		System.out.println("Enter \n 1. For User Registration"
    	            				               + "\n 2. For All User Details"
    	            				               + "\n 3. Update Details"
    	            				               + "\n 4. For Delete User Details");
    	            		
    	            int adminchoice=scanner.nextInt();
    	            switch(adminchoice)
    	            {
    	            case 1:
    	            	BankUserDetails userdetails=new BankUserDetails();
    	            	System.out.println("Enter your name");
    	            	String name=scanner.next();
    	            	userdetails.setUsername(name);
    	            	System.out.println("enter your gender");
    	            	String gender=scanner.next();
    	            	userdetails.setUsergender(gender);
    	            	System.out.println("enter your address");
    	            	String address=scanner.next();
    	            	userdetails.setUseraddress(address);
    	            	System.out.println("enter date of birth");
    	            	String date=scanner.next();
    	            	userdetails.setUserdateofbirth(LocalDate.parse(date));
    	            	System.out.println("enter your amount");
    	            	double amount=scanner.nextDouble();
    	            	userdetails.setUseramount(amount);
    	            	System.out.println("enter your mobile number");
    	            	String mobilenumber=scanner.next();
    	            	userdetails.setUsermobilenumber(mobilenumber);
    	            	System.out.println("enter your useremailid");
    	            	String useremailid=scanner.next();
    	            	userdetails.setUseremailid(useremailid);
    	            	admindao.userRegistration(userdetails);
    	            		
    	            break;
    	            case 2:admindao.selectingAllUserDetails();
    	            break;
    	            case 3:System.out.println("Update User Details");
    	            break;
    	            case 4:System.out.println("Enter user account number");
    	            		int accountnumber=scanner.nextInt();
    	            		admindao.deleteuserdetails(accountnumber);
    	            break;
    	            
    	            
    	            default:System.out.println("Enter Valid Choice");
    	            break;
    	            }
    	            	}
    	            	else
    	            	{
    	            		System.out.println("Invalid Details");
    	            		count++;
    	            	}
    	            	if(count==3)
    	            	{
    	            		adminloginstatus=false;
    	            	}
    	            	welcomestatus=false;
    	            	}
    	            	break;
    	            case 2:
    	            	System.out.println("Enter Your Bank Email Id");
    	            	String bankemailid=scanner.next();
    	            	
    	            	System.out.println("Enter Your Password");
    	            	int password=scanner.nextInt();
    	            	
    	            	if(userdao.userLogIn(bankemailid, password))
    	            	{
    	            		System.out.println("Enter  "
    	            				+ "\n 1.For Balance Enquiry "
    	            				+ "\n 2.For Withdraw"
    	            				+ "\n 3.For Credit "
    	            				+ "\n 4.For Change Password"
    	            				+ "\n 5.For Check Statement"
    	            				+ "\n 6.For Mobile to Mobile Transaction");
    	            	}
    	            	else
    	            	{
    	            		System.out.println("Invalid Details");
    	            	}
    	            	welcomestatus=false;
    	            	break;
    	            default:
    	            	System.out.println("Enter valid choice");
    	            	break;
    	            }
    	            System.out.println("Do You want to continue \n yes  \n No");
    	            String welcomeContinue=scanner.next();
    	            if(welcomeContinue.equalsIgnoreCase("yes"))
    	            {
    	            	welcomestatus=true;
    	            }
    	            else {
    	            	welcomestatus=false;
    	            	try {
    	            		Thread.sleep(2000);
    	            		System.out.println("Thank You Visit Again....!!!!!!!");
    	            	}catch (InterruptedException e){
    	            		e.printStackTrace();
    	            		
    	            	}
    	          
    	            	System.out.println("Enter valid choice");
    	            	break;
    	            }
    	            System.out.println("Do You want to continue \n yes  \n No");
    	            String welcomeContinue1=scanner.next();
    	            if(welcomeContinue1.equalsIgnoreCase("yes"))
    	            {
    	            	welcomestatus=true;
    	            }
    	            else {
    	            	welcomestatus=false;
    	            	try {
    	            		Thread.sleep(2000);
    	            		System.out.println("Thank You Visit Again....!!!!!!!");
    	            	}catch (InterruptedException e){
    	            		e.printStackTrace();
    	            		
    	            	}
    	         }
    	   }    
    	
     }

}
