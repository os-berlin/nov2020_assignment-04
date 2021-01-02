package com.coderscampus.olaf.assignment04;

public class SuperUser extends User {

	private String role;

	SuperUser(String[] userDetails) {
		super(userDetails);
		this.role = "super_user";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
