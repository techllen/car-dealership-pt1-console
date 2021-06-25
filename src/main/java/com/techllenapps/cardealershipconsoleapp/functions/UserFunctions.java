package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.File;
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

	private static final long serialVersionUID = 1L;
	Scanner scan = new Scanner(System.in);
	User user = new User();
	HashMap<String, String> allUsersMap = new HashMap<String, String>();
	ArrayList<HashMap<String, String>> allUsersList = new ArrayList<HashMap<String,String>>();
	FileOutputStream fout;
	FileInputStream fin;

	public void login() throws IOException {

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
		ArrayList<HashMap<String, String>> retrievedUsers = new ArrayList<HashMap<String, String>>();
		fin = new FileInputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.ser");
		ObjectInputStream in = new ObjectInputStream(fin);
		while(fin.available() > 0) {
		try {
			retrievedUsers = (ArrayList<HashMap<String, String>>)in.readObject();
			//in.close();
			//fin.close();
//		} catch (FileNotFoundException e){
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(retrievedUsers);
		System.out.println(retrievedUsers.size());
		//boolean available = false;
		//return available;
	}
	}

	public  void register() {
		System.out.println("*****************************************************\n");
		System.out.println("Please enter your username and password to register");
		System.out.println("\n****************************************************");
		System.out.println("Username:");
		String registeredUserName = scan.nextLine();
		user.setUserName(registeredUserName);
		System.out.println("Password:");
		String passcode = scan.nextLine();
		user.setPassWord(passcode);
		//assign passcode and username to the hashmap as key value respectively
		allUsersMap.put(user.getPassWord(), user.getUserName());
		//adding the users hashmap to the arraylist
		allUsersList.add(allUsersMap);
		//serialising the user credentials for persistence
		try {
			boolean exists = new File("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.ser\"").exists();
			fout = new FileOutputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.ser",true);
			//ObjectOutputStream out = new ObjectOutputStream(fout);
			ObjectOutputStream out = exists ? 
					new ObjectOutputStream(fout) {

				protected void writeStreamHeader() throws IOException {
					reset();
				}
			}:new ObjectOutputStream(fout);

			out.writeObject(allUsersList);
			System.out.println("\n SAVED!!!!");
			//out.close();
			//fout.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
