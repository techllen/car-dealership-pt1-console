package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver{

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		if(UserFunctions.adminCheck()==false) {
			UserFunctions.mainMenu();
		}
	}
}
