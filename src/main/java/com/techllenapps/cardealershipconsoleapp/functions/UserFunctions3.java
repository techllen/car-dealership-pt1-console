package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.techllenapps.cardealershipconsoleapp.entities.User;

public class UserFunctions3 extends User{

	public UserFunctions3(String userName, String passWord) {
		super(userName, passWord);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		setObject();
		getObject();

	}

	public static void setObject() throws IOException {
		User usr1=new User("Matare","Mwita");
		User usr2=new User("Matare","Allen");
		User usr3=new User("Matare","Kikula");

		ArrayList<User> woi=new ArrayList<User>();

		FileOutputStream fop=new FileOutputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.txt");
		ObjectOutputStream oos=new ObjectOutputStream(fop);
		woi.add(usr1);
		woi.add(usr2);
		woi.add(usr3);
		oos.writeObject(woi);	
	}

	public static void getObject() throws IOException, ClassNotFoundException { 
		try {
			FileInputStream fis=new FileInputStream("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.txt");
			ObjectInputStream ois=new ObjectInputStream(fis);


			ArrayList<User> userList=new ArrayList<User>();

			userList=(ArrayList<User>)ois.readObject();
			
			for(int i=0;i<userList.size();i++){
				System.out.println(userList.get(i));
			}
		} finally {}
	}

}
