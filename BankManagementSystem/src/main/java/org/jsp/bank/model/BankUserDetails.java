package org.jsp.bank.model;

import java.time.LocalDate;

public class BankUserDetails {
	private int userid;
	private String username;
	private String userbankemailid;
	private String useremailid;
	private int  userpassword;
	private String usergender;
	private String useraddress;
	private LocalDate userdateofbirth;
	private int useraccountnumber;
	private double useramount;
	private String usermobilenumber;
	
	
	public BankUserDetails ()
	{
		
	}

	public BankUserDetails(int userid, String username, String userbankemailid, String useremailid,
			int userpassword, String usergender, String useraddress, LocalDate userdateofbirth,
			int useraccountnumber, double useramount, String usermobilenumber) {
		this.userid = userid;
		this.username = username;
		this.userbankemailid = userbankemailid;
		this.useremailid = useremailid;
		this.userpassword = userpassword;
		this.usergender = usergender;
		this.useraddress = useraddress;
		this.userdateofbirth = userdateofbirth;
		this.useraccountnumber = useraccountnumber;
		this.useramount = useramount;
		this.usermobilenumber = usermobilenumber;
		
	}

	@Override
	public String toString() {
		return "Bank_User_Details [userid=" + userid + ", username=" + username + ", userbankemailid="
				+ userbankemailid + ", useremailid=" + useremailid + ", userpassword=" + userpassword
				+ ", usergender=" + usergender + ", useraddress=" + useraddress + ", userdateofbirth="
				+ userdateofbirth + ", useraccountnumber=" + useraccountnumber + ", useramount=" + useramount
				+ ", usermobilenumber=" + usermobilenumber +  "]";
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserbankemailid() {
		return userbankemailid;
	}

	public void setUserbankemailid(String userbankemailid) {
		this.userbankemailid = userbankemailid;
	}

	public String getUseremailid() {
		return useremailid;
	}

	public void setUseremailid(String useremailid) {
		this.useremailid = useremailid;
	}

	public int getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(int userpassword) {
		this.userpassword = userpassword;
	}

	public String getUsergender() {
		return usergender;
	}

	public void setUsergender(String usergender) {
		this.usergender = usergender;
	}

	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public LocalDate getUserdateofbirth() {
		return userdateofbirth;
	}

	public void setUserdateofbirth(LocalDate userdateofbirth) {
		this.userdateofbirth = userdateofbirth;
	}

	public int getUseraccountnumber() {
		return useraccountnumber;
	}

	public void setUseraccountnumber(int useraccountnumber) {
		this.useraccountnumber = useraccountnumber;
	}

	public double getUseramount() {
		return useramount;
	}

	public void setUseramount(double useramount) {
		this.useramount = useramount;
	}

	public String getUsermobilenumber() {
		return usermobilenumber;
	}

	public void setUsermobilenumber(String usermobilenumber) {
		this.usermobilenumber = usermobilenumber;
	}

}
