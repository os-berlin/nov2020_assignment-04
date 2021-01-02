package com.coderscampus.olaf.assignment04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileService {

	public void readFile(String fileName) throws IOException {

		int i = 0;

		try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName));) {

			String line;
			while ((line = fileReader.readLine()) != null) {
				String[] userDetails = line.split(", ");

				if ("super_user".equals(userDetails[3])) {
					UserService.users[i++] = new SuperUser(userDetails);
				} else {
					UserService.users[i++] = new NormalUser(userDetails);
				}
			}
		}
	}

	static protected void writeFile(String fileName) throws IOException {

		Arrays.sort(UserService.users);

		try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName));) {

			String userRole;

			for (User user : UserService.users) {

				if (user instanceof SuperUser) {
					userRole = "super_user";
				} else {
					userRole = "normal_user";
				}

				fileWriter.write(user.getUsername() + ", " + user.getPassword() + ", " + user.getName() + ", "
						+ userRole + "\n");
			}
		}
	}

}
