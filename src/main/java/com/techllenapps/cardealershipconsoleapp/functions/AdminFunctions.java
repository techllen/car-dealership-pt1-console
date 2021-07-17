package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.source.tree.LambdaExpressionTree;
import com.techllenapps.cardealershipconsoleapp.entities.Car;
import com.techllenapps.cardealershipconsoleapp.entities.User;

public class AdminFunctions extends UserFunctions{
	private static final long serialVersionUID = 202107102L;
	Scanner scan = new Scanner(System.in);
	public static String filePathForLogs = "//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//log4japplicationlogs.txt";

	public void adminMenu() throws ClassNotFoundException, IOException{

		while(true) {
			System.out.println("****************************************************************");
			System.out.println("Wellcome Administrator Panel\n");
			System.out.println("****************************************************************");
			System.out.println("Please select 1 view system logs or 2 to register Employee");
			//the administrator will assign employees and their roles as well as view appplication logs
			System.out.println("1.View Logs");
			System.out.println("2.View Users");
			System.out.println("3.Register Employee");
			System.out.println("4.Exit\n");
			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				viewSystemLogs(filePathForLogs);
				break;
			case 2:
				viewSystemUsers();
				break;
			case 3:
				//will call register method and pass userole as employee
				register("Employee");
				break;
			case 4:
				//take the user to the main menu
				mainMenu();
				break;
			default:
				System.out.println("Invalid Choice,Please select 1 or 2");
				break;
			}
		}

	}

	public static void viewSystemLogs(String filePath) throws FileNotFoundException {
		// pass the path to the file as a parameter 
		File file = new File(filePath); 
		Scanner scan = new Scanner(file); 
		while (scan.hasNextLine()) 
			System.out.println(scan.nextLine());
	}
	
	public static void viewSystemUsers() throws ClassNotFoundException, IOException {
		// pass the path to the file as a parameter 
		ArrayList<User> extractedUserList = UserFunctions.extractUserList();
		System.out.println("UserName  "+"UserRole");
		extractedUserList.forEach(User -> System.out.println(User.getUserName()+"  "+User.getUserRole()));
	}	
}
