package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.techllenapps.cardealershipconsoleapp.entities.Car;

public class CustomerFunctions{
	private static final long serialVersionUID = 202107103L;


	public static void customerMenu() throws ClassNotFoundException, IOException{

		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("****************************************************************");
			System.out.println("Wellcome to the Customer Menu\n");
			System.out.println("****************************************************************");
			System.out.println("Please select the number to reflect the choices below");
			//the administrator will assign employees and their roles as well as view appplication logs
			System.out.println("1.View all available cars");
			System.out.println("2.Make/update an offer for a car");
			System.out.println("3.View the cars that I own");
			System.out.println("4.Make Payment for my car");
			System.out.println("5.Vew panding payment for my car");
			System.out.println("6.Exit\n");
			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				EmployeeFunctions.viewCars();
				break;
			case 2:
				makeAnOffer();
				System.out.println("\n\nOffer Made");
				break;
			case 3:
				viewCarsThatIOwn();
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
				System.out.println("Invalid Choice,Please select 1 to 6");
				break;
			}
		}

	}

	public static void makeAnOffer() throws ClassNotFoundException, IOException {
		EmployeeFunctions.viewCars();
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n Enter the ID number of the car that you want to make an offer for");
		int ID = sc.nextInt();
		sc.nextLine();
		System.out.println("\nEnter the amount that you want to offer for the car");
		try {
			Double amountOffered = sc.nextDouble();
			setAmountOfferedToACar(ID, amountOffered,UserFunctions.userInSession);
		} catch (Exception e) {
			System.out.println("Please amount in numbers eg. 345679.90");			
		}	
	}

	public static void setAmountOfferedToACar(int ID,Double amountOffered,String userInSession) throws ClassNotFoundException, IOException {
		ArrayList<Car> carListToView = EmployeeFunctions.extractCarsFromFile();
		for (int c=0;c<carListToView.size();c++) {
			if (c==(ID-1)) {
				carListToView.get(c).setAmountOffered(amountOffered);
				carListToView.get(c).setOfferMadeBy(userInSession);

				//testing
				//System.out.println(carListToView.get(c));
			}
		}
		//testing
		//System.out.println(carListToView);
		EmployeeFunctions.updateCar(carListToView);
	}

	public static void viewCarsThatIOwn() throws ClassNotFoundException, IOException {
		ArrayList<Car> extractedCarList = EmployeeFunctions.extractCarsFromFile();
		ArrayList<Car> myOwnCar = new ArrayList<Car>();
		for (Car car : extractedCarList) {
			//checking for all the cars that the user in the system owns
			try {
				if(car.getOfferMadeBy().equals(UserFunctions.userInSession)) {
					myOwnCar.add(car);
				}
			} catch (Exception e) {
				System.out.println("Please make an offer for a car first");			}
		}
		System.out.println("ID"+"  Model"+"  Price"+"  Milage"+"  NoOfOwners"+"  Color"+"  DriveTrain"+"  FuelType"+"  Transmission"+"  VIN"+"  Location"+"  Year"+"  Model"+"  DatePosted"+"  OfferMadeBy"+"  AmountOffered"+"  OfferStatus");
		for (int c=0;c<myOwnCar.size();c++) {
			System.out.println(
					(c+1)
					+"  "+ 		
					myOwnCar.get(c).getModel()
					+"  "+
					myOwnCar.get(c).getPrice()
					+"  "+
					myOwnCar.get(c).getMilage()
					+"  "+
					myOwnCar.get(c).getNoOfOwners()
					+
					myOwnCar.get(c).getColor()
					+"  "+
					myOwnCar.get(c).getDriveTrain()
					+"  "+
					myOwnCar.get(c).getFuelType()
					+"  "+
					myOwnCar.get(c).getTransmission()
					+"  "+
					myOwnCar.get(c).getVIN()
					+"  "+
					myOwnCar.get(c).getLocation()
					+"  "+
					myOwnCar.get(c).getYear()
					+"  "+
					myOwnCar.get(c).getModel()
					+"  "+
					myOwnCar.get(c).getDatePosted()
					+"  "+
					myOwnCar.get(c).getOfferMadeBy()
					+"  "+
					myOwnCar.get(c).getAmountOffered()
					+"  "+
					myOwnCar.get(c).getOfferStatus()

					);
		}
	}
}

