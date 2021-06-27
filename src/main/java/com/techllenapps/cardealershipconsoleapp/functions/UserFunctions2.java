package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;
import com.techllenapps.cardealershipconsoleapp.entities.User;

public class UserFunctions2 extends User{

	private static final long serialVersionUID = 1L;
	Scanner scan = new Scanner(System.in);
	User user = new User();
	HashMap<String, String> allUsersMap = new HashMap<String, String>();
	FileOutputStream fout;
	FileInputStream fin;
	HashMap<String, String> retrievedUsers = new HashMap<String, String>();


	public void login() throws IOException, ClassNotFoundException {

		
		fin = new FileInputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.txt");
		ObjectInputStream in = new ObjectInputStream(fin);
			//retrievedUsers = (ArrayList<HashMap<String, String>>)in.readObject();
			Object obj = null;
			while(fin.available()>0) {
			retrievedUsers = (HashMap<String, String>)in.readObject();
			System.out.println(retrievedUsers);
				//System.out.println(in.readObject());
			}
			in.close();
		//boolean available = false;
		//return available;
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
		//allUsersList.add(allUsersMap);
		//serialising the user credentials for persistence
		File file = new File("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.txt");
		boolean append = file.exists();
				try {
			fout = new FileOutputStream(file,append);
			AppendingObjectOutputStream out = new AppendingObjectOutputStream(fout);
			out.writeObject(allUsersMap);
			System.out.println("\n SAVED!!!!");
			out.close();
			fout.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
