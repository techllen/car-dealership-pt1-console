package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import com.techllenapps.cardealershipconsoleapp.entities.User;

public class UserFunctions {

	private static final long serialVersionUID = 1L;
	Scanner scan = new Scanner(System.in);
	ArrayList<User> allUsersList = new ArrayList<User>();

	public void login() throws IOException, ClassNotFoundException {
		ArrayList<User> retrievedAllUsersList = new ArrayList<User>();

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
		//ArrayList<HashMap<String, String>> retrievedUsers = new ArrayList<HashMap<String, String>>();
		//HashMap<String, String> retrievedUsers = new HashMap<String, String>();
		FileInputStream fin = new FileInputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.txt");
		ObjectInputStream in = new ObjectInputStream(fin);
		retrievedAllUsersList = (ArrayList<User>)in.readObject();
		//iterating through a list to get the user
		//System.out.println(retrievedAllUsersList);
//		for(int c=0;c<retrievedAllUsersList.size();c++){
//			System.out.println(retrievedAllUsersList.get(c));
//			System.out.println(retrievedAllUsersList.size());
//		}
		for (User user : retrievedAllUsersList) {
			System.out.println(user);
		}
		in.close();
		fin.close();
	}

	public  void register() throws IOException, ClassNotFoundException {
		System.out.println("*****************************************************\n");
		System.out.println("Please enter your username and password to register");
		System.out.println("\n****************************************************");
		System.out.println("Username:");
		String registeredUserName = scan.nextLine();
		//user.setUserName(registeredUserName);
		System.out.println("Password:");
		String passcode = scan.nextLine();
		//user.setPassWord(passcode);
		//adding the users to the arraylist
		allUsersList.add(new User(passcode,registeredUserName));
		allUsersList.add(new User(passcode,registeredUserName));
		allUsersList.add(new User(passcode,registeredUserName));
		//serialising the user credentials for persistence
		FileOutputStream fout = new FileOutputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.txt",true);
		ObjectOutputStream out = new ObjectOutputStream(fout);
		out.writeObject(allUsersList);
		System.out.println(allUsersList.size());
		System.out.println("\nSAVED!!!!");
		out.close();
		fout.close();
	}
}
