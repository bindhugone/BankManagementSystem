package org.jsp.bank.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminHelperClass {
	public static AdminDAO adminHelperMethod() {
		AdminDAO dao=new AdminDAOimp();
		return dao;
		
	}

}
