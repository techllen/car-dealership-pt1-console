package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.techllenapps.cardealershipconsoleapp.entities.User;

public class UserFunctions extends User{
	private static final long serialVersionUID =  202107051958L;
	public static String filePath = "//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.txt";
	Scanner scan = new Scanner(System.in);
	

	//default method to create an admin
	public boolean registerAdministrator() throws IOException{
		ArrayList<User> firstUserList = new ArrayList<User>();
		//setting administrator as first user
		User firstUser =new User("Admin","Admin","Administrator");
		firstUserList.add(firstUser);
		FileOutputStream fop=new FileOutputStream(filePath);
		ObjectOutputStream oos=new ObjectOutputStream(fop);
		oos.writeObject(firstUserList);
		boolean administratorAlredySet = true;
		return administratorAlredySet;
	}


	public void register(String userRole) throws IOException, ClassNotFoundException{
		User user=new User();
		//Retrieve stored list from file
		ArrayList<User> retrievedListFromFile = login();
		//System.out.println(retrievedListFromFile);
		System.out.println("Enter the username:\n");
		String name = scan.nextLine();
		user.setUserName(name);
		System.out.println("Enter the password:\n");
		String passcode = scan.nextLine();
		user.setPassWord(passcode);
		user.setUserRole(userRole);
		//creating another user being registered apart from administrator
		//Adding the new user to an arraylist
		ArrayList<User> allUsersList = new ArrayList<User>();
		allUsersList.add(user);
		//appending arraylist of new users to the arraylist retrieved from the file
		allUsersList.addAll(retrievedListFromFile);
		//removing any duplicate in the list
		allUsersList=removeDuplicates(allUsersList);
		FileOutputStream fos=new FileOutputStream(filePath);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(allUsersList);	
	}

	public ArrayList<User> login() throws IOException, ClassNotFoundException { 
		FileInputStream fis=new FileInputStream(filePath);
		ObjectInputStream ois=new ObjectInputStream(fis);
		ArrayList<User> userList=new ArrayList<User>();
		userList=(ArrayList<User>)ois.readObject();

		//for testing purposes
		for(int i=0;i<userList.size();i++){
			System.out.println(userList.get(i));
			System.out.println(userList.size());
		}
		return userList;
	}

	public static ArrayList<User> removeDuplicates(ArrayList<User> userList)
	{
		ArrayList<User> noDuplicateUserList = new ArrayList<User>();
		for (User user : userList) {
			if (!noDuplicateUserList.contains(user)) {
				noDuplicateUserList.add(user);
			}	
		}
		return noDuplicateUserList;
	}
}

