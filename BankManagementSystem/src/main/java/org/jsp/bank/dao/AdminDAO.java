package org.jsp.bank.dao;

import org.jsp.bank.model.BankUserDetails;
public interface AdminDAO {
	boolean adminLogIn(String emailid,String password);

	void userRegistration(BankUserDetails  bankuserdetails);
	
	void selectingAllUserDetails();
	
	void deleteuserdetails(int accountnumber);
	

}
