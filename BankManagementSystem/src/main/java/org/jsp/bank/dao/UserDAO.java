package org.jsp.bank.dao;


public interface UserDAO {
	boolean userLogIn(String bankemailid,int password);
	void debit(int accountnumber,int password);
	void credit(int accountnumber,int password);
	void mobileToMobileTransaction(String mobileNo);
}
