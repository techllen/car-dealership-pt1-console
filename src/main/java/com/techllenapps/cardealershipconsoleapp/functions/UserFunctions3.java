package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.techllenapps.cardealershipconsoleapp.entities.User;

public class UserFunctions3 extends User{

	public UserFunctions3(String userName, String passWord) {
		super(userName, passWord);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID =  202107051958L;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
while(true) {
		Scanner in = new Scanner(System.in);
		System.out.println("Username");
		String name = in.nextLine();
		System.out.println("Password");
		String passcode = in.nextLine();
		setObject(name,passcode);
		//getObject();
}

	}

	public static void setObject(String name, String passcode) throws IOException, ClassNotFoundException {
		ArrayList<User> retrList = getObject();
		System.out.println(retrList);

	    User usr1=new User("Admin","Admin");

		User usr=new User(name,passcode);

		
		ArrayList<User> woi=new ArrayList<User>();

		
		woi.add(usr1);
		woi=removeDuplicates(woi);
		retrList.add(usr);
		woi.addAll(retrList);
		
		

		FileOutputStream fop=new FileOutputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.txt");
		ObjectOutputStream oos=new ObjectOutputStream(fop);
		oos.writeObject(woi);	
	}


	public static ArrayList<User> getObject() throws IOException, ClassNotFoundException { 
		try {
			FileInputStream fis=new FileInputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.txt");
			ObjectInputStream ois=new ObjectInputStream(fis);


			ArrayList<User> userList=new ArrayList<User>();

			userList=(ArrayList<User>)ois.readObject();
			
			for(int i=0;i<userList.size();i++){
				System.out.println(userList.get(i));
				System.out.println(userList.size());

			}
			return userList;
		} finally {}
	}
	//method to remove arraylist
	public static ArrayList<User> removeDuplicates(ArrayList<User> list)
    {
  
        // Create a new ArrayList
        ArrayList<User> newList = new ArrayList<User>();
  
        // Traverse through the first list
        
        for (User user : newList) {
        	if (!newList.contains(user)) {
                newList.add(user);
            }	
		}
        // return the new list
        return newList;
    }

}
