package com.coderscampus.olaf.assignment04;

public class NormalUser extends User {

	private String role;

	NormalUser(String[] userDetails) {
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
