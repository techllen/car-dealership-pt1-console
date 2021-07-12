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

	public static void mainMenu() throws IOException, ClassNotFoundException {
		UserFunctions usrOperation = new UserFunctions();
		boolean stickMenu = true;
		Scanner scan = new Scanner(System.in);
		//default method to register the administrator
		boolean adminExist = usrOperation.registerAdministrator();
		//run the program if and only iff we have admini registered 
		while((stickMenu) && (adminExist)) {
			System.out.println("****************************************************************");
			System.out.println("Wellcome to the Car Dealesrhip Console Application Ver 1.01\n");
			System.out.println("This is a simple console based Java App for learning purposes\n");
			System.out.println("****************************************************************");
			System.out.println("Please select 1 to log in as Admin, 2 to log in as Customer/Employee and 3 to register as a new Customer");
			//the administrator will assign employees and their roles as well as view appplication logs
			System.out.println("1.Administrator");
			System.out.println("2.User(Customer/Employee)");
			System.out.println("3.Register as Customer");


			int typeOfEmployeeChoice = scan.nextInt();

			switch (typeOfEmployeeChoice) {
			case 1:
				Scanner in = new Scanner(System.in);
				System.out.println("Enter Username:\n");
				String userName = in.nextLine();
				System.out.println("Enter Password:\n");
				String passCode = in.nextLine();
				System.out.println(usrOperation.validateUser(userName, passCode));
				if (usrOperation.validateUser(userName, passCode)==true){
					AdminFunctions adm = new AdminFunctions();
					adm.adminMenu();
				}else if (usrOperation.validateUser(userName, passCode)==false){
					System.out.println("Please check your username and password");
				}
				break;

			case 2:
				Scanner in1 = new Scanner(System.in);
				System.out.println("Enter Username:\n");
				String userName1 = in1.nextLine();
				System.out.println("Enter Password:\n");
				String passCode1 = in1.nextLine();
				//First check the user type customer/employee
				if ((usrOperation.checkUserType(userName1, passCode1)).equals("Customer")) {

				}else if ((usrOperation.checkUserType(userName1, passCode1)).equals("Employee")) {

				}else {
					System.out.println("Please check your username and password");
				}

				break;
			case 3:				
				//Register the new user as a customer by passing 
				//customer as user role in the register method
				usrOperation.register("Customer");
				break;

			default:
				System.out.println("Invalid Choice,Please select 1 ,2 or 3");
				break;
			}
		}


		//			System.out.println("Please tell us what you want to do today select 1 to Signin and 2 to Register and Account\n");
		//			System.out.println("1.Register an account\n");
		//			System.out.println("2.Sign in\n");
		//			try {
		//				int menuSelection = scan.nextInt();
		//
		//				//calling sign in or register function if appropriate selection is selected 
		//				//break the loop right after the condition is fulfilled
		//				if (menuSelection==1) {
		//					usroperation.register();
		//					break;
		//				}else if (menuSelection==2) {
		//					usroperation.login();
		//					break;
		//				}else {
		//					System.out.println("\nplease select 1 or 2");
		//					break;
		//				}
		//			}catch (InputMismatchException i) {
		//				System.out.println("\nplease enter integer 1 or 2");
		//				break;
		//			}
	}

	//scan.close();


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
		ArrayList<User> retrievedListFromFile = extractUserList();
		System.out.println(retrievedListFromFile);
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

	public ArrayList<User> extractUserList() throws IOException, ClassNotFoundException { 
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

	public boolean validateUser(String userName,String passWord ) throws IOException, ClassNotFoundException { 
		ArrayList<User> userListToValidate=extractUserList();
		boolean available=false;
		for (User user : userListToValidate) {
			if ((user.getPassWord().equals(passWord)) && (user.getUserName().equals(userName))) {
				available= true;
			}
		}
		return available;
	}

	public String checkUserType(String userName,String passWord ) throws IOException, ClassNotFoundException { 
		ArrayList<User> userListToValidate=extractUserList();
		String userRole = null;
		for (User user : userListToValidate) {
			try {
				if ((user.getPassWord().equals(passWord)) && (user.getUserName().equals(userName))) {
					userRole= user.getUserRole();
				}
			} catch (NullPointerException e) {
				System.out.println("The system has no users,See the system Admnistrator for further assistance");
			}
		}

		return userRole;
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

