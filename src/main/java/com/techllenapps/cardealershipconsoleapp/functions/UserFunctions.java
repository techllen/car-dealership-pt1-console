package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import com.techllenapps.cardealershipconsoleapp.entities.User;

public class UserFunctions extends User implements Serializable{

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;
	Scanner scan = new Scanner(System.in);
	User user = new User();


	public boolean login() {
		System.out.println("login");
		boolean available = false;
		return available;
	}

	public  void register() {
		System.out.println("*****************************************************\n");
		System.out.println("Please enter your username and password to register");
		System.out.println("\n****************************************************");
		System.out.println("Username:");
		//assigning input to username
		String registeredUserName = scan.nextLine();
		user.setUserName(registeredUserName);
		System.out.println("Password:");
		String passcode = scan.nextLine();
		user.setPassWord(passcode);
		//serialising the user credentials for persistence
		FileOutputStream fout;
		try {
			fout = new FileOutputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.ser");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(user);
			out.close();
			fout.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//boolean registered = true;
		//return registered;
	}

}
