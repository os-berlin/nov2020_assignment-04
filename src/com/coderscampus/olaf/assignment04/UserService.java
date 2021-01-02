package com.coderscampus.olaf.assignment04;

import java.util.Scanner;
import java.io.IOException;

public class UserService {

	static final int USER_ARRAY_SIZE = 20;
	static User[] users = new User[USER_ARRAY_SIZE];

	Scanner scanner = new Scanner(System.in);

	public User userLogin() {

		boolean successfulLogin = false;
		final int MAX_LOGIN_ATTEMPTS = 5;

		for (int i = 0; i < MAX_LOGIN_ATTEMPTS; i++) {
			if (i > 0 && i < MAX_LOGIN_ATTEMPTS - 1) {
				System.out.println("... " + (MAX_LOGIN_ATTEMPTS - i) + " attempts remaining");
			} else if (i == MAX_LOGIN_ATTEMPTS - 1) {
				System.out.println("last try, bro!");
			}
			System.out.println("Enter your email:");
			String inputUsername = scanner.nextLine();
			System.out.println("Enter your password:");
			String inputPassword = scanner.nextLine();

			for (User user : users) {
				if (inputUsername.equalsIgnoreCase(user.getUsername()) && inputPassword.equals(user.getPassword())) {
					successfulLogin = true;
					return user;
				}
			}
			System.out.println("\n-------------\n" + "Invalid login" + "\n-------------\n");
		}
		if (!successfulLogin) {
			System.out.println("Too many failed login attempts, you are now locked out.");
		}
		return null;
	}

	public void userMenu(User currentUser) throws IOException {

		Integer inputMenuItem = 0;

		while (inputMenuItem != 4) {
			boolean superUserPermission = false;

			System.out.println("\nWelcome: " + currentUser.getName());
			System.out.println("\n-------------\n" + "\n" + "Please choose from the following options:\n" + "\n");

			if (currentUser instanceof SuperUser) {
				System.out.println("(0) Log in as another user\n");
				superUserPermission = true;
			}

			System.out.println("(1) Update username\n" + "\n" + "(2) Update password\n" + "\n" + "(3) Update name\n"
					+ "\n" + "(4) Exit" + "\n");

			inputMenuItem = Integer.parseInt(scanner.nextLine());

			if (inputMenuItem.equals(0) && superUserPermission == true) {
				currentUser = switchUser();
			} else if (inputMenuItem.equals(1)) {
				System.out.println("\n" + "Please type in your new username:" + "\n");
				currentUser = updateUserDetails(currentUser, "username", scanner.nextLine());
			} else if (inputMenuItem.equals(2)) {
				System.out.println("\n" + "Please type in your new password:" + "\n");
				currentUser = updateUserDetails(currentUser, "password", scanner.nextLine());
			} else if (inputMenuItem.equals(3)) {
				System.out.println("\n" + "Please type in your new name:" + "\n");
				currentUser = updateUserDetails(currentUser, "name", scanner.nextLine());
			} else if (inputMenuItem.equals(4)) {
				System.out.println("\n" + "Bye bye!");
			} else {
				System.out.println("\n" + "Invalid input or super_user role required" + "\n" + "...terminating");
				System.exit(0);
			}
		}
	}

	private User switchUser() throws IOException {

		System.out.println("\n" + "Which user would you like to login as? (Type in a valid username)" + "\n");
		String inputUsername = scanner.nextLine();

		for (User switchToUser : users) {

			if (inputUsername.equalsIgnoreCase(switchToUser.getUsername())) {
				return switchToUser;
			}
		}
		System.out.println("Invalid username." + "\n" + "...terminating");
		return null;
	}

	private User updateUserDetails(User currentUser, String userDetail, String value) throws IOException {

		if (userDetail.equals("username")) {
			currentUser.setUsername(value);
		} else if (userDetail.equals("password")) {
			currentUser.setPassword(value);
		} else if (userDetail.equals("name")) {
			currentUser.setName(value);
		}

		FileService.writeFile("users.txt");
		return currentUser;
	}

}
