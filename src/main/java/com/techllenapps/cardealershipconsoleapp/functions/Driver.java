package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver{

	public static void main(String[] args) throws IOException {
		UserFunctions usroperation = new UserFunctions();
		boolean stickMenu = true;
		//receiving keyboard input
		Scanner scan = new Scanner(System.in);

		do {

			System.out.println("****************************************************************\n");
			System.out.println("Wellcome to the Car Dealesrhip Console Application Ver 1.01\n");
			System.out.println("****************************************************************");
			System.out.println("Please tell us what you want to do today select 1 to Signin and 2 to Register and Account\n");
			System.out.println("1.Register an account\n");
			System.out.println("2.Sign in\n");
			try {
				int menuSelection = scan.nextInt();

				//calling sign in or register function if appropriate selection is selected 
				//break the loop right after the condition is fulfilled
				if (menuSelection==1) {
					usroperation.register();
					break;
				}else if (menuSelection==2) {
					usroperation.login();
					break;
				}else {
					System.out.println("\nplease select 1 or 2");
					break;
				}
			}catch (InputMismatchException i) {
				System.out.println("\nplease enter integer 1 or 2");
				break;
			}
		}
		while (stickMenu==true);
		scan.close();
	}
}

