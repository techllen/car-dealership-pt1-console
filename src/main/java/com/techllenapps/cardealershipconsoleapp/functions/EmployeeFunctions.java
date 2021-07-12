package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.IOException;
import java.util.Scanner;

public class EmployeeFunctions extends UserFunctions{
	private static final long serialVersionUID = 202107104L;
	Scanner scan = new Scanner(System.in);

public void customerMenu() throws ClassNotFoundException, IOException{

	while(true) {
		System.out.println("****************************************************************");
		System.out.println("Wellcome to the Employee Menu\n");
		System.out.println("****************************************************************");
		System.out.println("Please select the number to reflect the choices below");
		//the administrator will assign employees and their roles as well as view appplication logs
		System.out.println("1.Add cars to the lot");
		System.out.println("2.Offer Desicion(Accept /reject offer)");
		System.out.println("3.View the cars that I own");
		System.out.println("4.Remove a car from the lot");
		System.out.println("5.View all the paymants\n");
		System.out.println("6.Exit\n");
		int choice = scan.nextInt();

		switch (choice) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			//take the user to the main menu
			UserFunctions.mainMenu();
			break;
		default:
			System.out.println("Invalid Choice,Please select 1 or 2");
			break;
		}
	}

	}
}
