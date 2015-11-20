package com.certuit.saml2.entity;

public class User  {

	private String user_name;
	private String password;
	
	private int employee_id;	
	private String name;
	private String last_name;
	
	public String getPassword() {	
		return password;
	}

	public String getUserName() {
	
		return user_name;
	}

	public void setPassword(String password) {
		this.password = password;
		
		
	}

	public void setUserName(String userName) {		
		this.user_name = userName;
		
	}
	
	public int getEmployeeId() {
		return employee_id;
	}
	
	public void setEmployeeId(int employeeId) {
		employee_id = employeeId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return last_name;
	}
	
	public void setLastName(String lastName) {
		last_name = lastName;
	}
		
}
