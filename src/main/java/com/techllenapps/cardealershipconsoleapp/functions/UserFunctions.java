package com.techllenapps.cardealershipconsoleapp.functions;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techllenapps.cardealershipconsoleapp.entities.User;

public class UserFunctions implements Serializable{
	//serial version ID is named as per today's date and time
	 private static final long serialVersionUID = 2021_07_05__19_58L;


	static class UserList implements Serializable {
		//serial version ID is named as per today's date and time
		 private static final long serialVersionUID = 2021_07_05__20_00L;
        ArrayList<User> list = new ArrayList<User>();
        
        @Override
 	   public String toString() {
 	       return String.valueOf(list);
 	   }
}
	
	

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
		FileInputStream fin = new FileInputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.ser");
		ObjectInputStream in = new ObjectInputStream(fin);
		Object retrievedUserListObject = new Object();
		retrievedUserListObject=(UserList)in.readObject();
		//UserList retrievedUser = new UserList();
		//retrievedAllUsersList = (Object)in.readObject();
		//iterating through a list to get the user
		//System.out.println(retrievedAllUsersList);
//		for(int c=0;c<retrievedAllUsersList.size();c++){
//			System.out.println(retrievedAllUsersList.get(c));
//			System.out.println(retrievedAllUsersList.size());
//		}
//		for (User user : retrievedAllUsersList) {
//			System.out.println(user);
//		}
		 
		 
		System.out.println(retrievedUserListObject.toString());
		in.close();
		fin.close();
	}

	public  void register() throws IOException, ClassNotFoundException {
		File file = new File("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.ser");
		//FileList list = new FileList();
		UserList users = new UserList();
		Scanner scn = new Scanner(System.in);
		while (true) {
			System.out.println("*****************************************************\n");
			System.out.println("Please enter your username and password to register");
			System.out.println("\n****************************************************");
			Scanner in = new Scanner(System.in);
			System.out.println("Username");
			String name = in.nextLine();
			System.out.println("Password");
			String passcode = in.nextLine();

			//list.list.add(new FileData());
			users.list.add(new User(name,passcode));
			File tmp = new File("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.ser");
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp))) {
				oos.writeObject(users);
			}
			Files.move(tmp.toPath(), file.toPath(), REPLACE_EXISTING);
			System.out.println("User Added");
			break;
		}
	}
}
