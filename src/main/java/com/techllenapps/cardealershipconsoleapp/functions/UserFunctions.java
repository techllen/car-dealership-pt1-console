package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.techllenapps.cardealershipconsoleapp.entities.User;

public class UserFunctions extends User{

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;
	Scanner scan = new Scanner(System.in);
	User user = new User();
	ArrayList<HashMap<String, String>> allUsers = new ArrayList<HashMap<String, String>>();



	public void login() {
		
//		System.out.println("*****************************************************\n");
//		System.out.println("Please enter your username and password to login");
//		System.out.println("\n****************************************************");
//		System.out.println("Username:");
//		String enteredUserName = scan.nextLine();
//		user.setUserName(enteredUserName);
//		System.out.println("Password:");
//		String enteredPassCode = scan.nextLine();
//		user.setPassWord(enteredPassCode);
//		//assign passcode and username to the hashmap as key value respectively
//		users.put(user.getPassWord(), user.getUserName());
//		//serialising the user credentials for persistence
		FileInputStream fin;
		ArrayList<HashMap<String, String>> retrievedUsers = new ArrayList<HashMap<String, String>>();
		try {
			fin = new FileInputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.ser");
			ObjectInputStream in = new ObjectInputStream(fin);
			retrievedUsers = (ArrayList<HashMap<String, String>>)in.readObject();
			in.close();
			fin.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(retrievedUsers);
		//boolean available = false;
		//return available;
	}

	public  void register() {
		System.out.println("*****************************************************\n");
		System.out.println("Please enter your username and password to register");
		System.out.println("\n****************************************************");
		System.out.println("Username:");
		String registeredUserName = scan.nextLine();
		User usr = new User();
		usr.setUserName(registeredUserName);
		System.out.println("Password:");
		String passcode = scan.nextLine();
		usr.setPassWord(passcode);
		//assign passcode and username to the hashmap as key value respectively
		HashMap<String,String> users = new HashMap<String,String>();
		users.put(usr.getPassWord(), usr.getUserName());
		//serialising the user credentials for persistence
		FileOutputStream fout;
		try {
			fout = new FileOutputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.ser");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			//HashMap<String,String> usersToAdd = new HashMap<String,String>();
			allUsers.add(users);
			out.writeObject(allUsers);
			out.close();
			fout.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
