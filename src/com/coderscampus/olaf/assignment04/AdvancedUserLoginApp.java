package com.coderscampus.olaf.assignment04;

import java.io.IOException;

public class AdvancedUserLoginApp {

	public static void main(String[] args) {

		FileService fileService = new FileService();
		UserService userService = new UserService();
		try {
			fileService.readFile("users.txt");
		} catch (IOException e) {
			System.out.println("\n" + "Error: I/O exception!");
			e.printStackTrace();
			System.exit(0);
		}

		User currentUser = userService.userLogin();

		if (currentUser != null) {
			try {
				userService.userMenu(currentUser);
			} catch (IOException e) {
				System.out.println("\n" + "Error: I/O exception!");
				e.printStackTrace();
				System.exit(0);
			}

		}
		userService.scanner.close();
	}
}