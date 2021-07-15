package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.techllenapps.cardealershipconsoleapp.entities.Car;
import com.techllenapps.cardealershipconsoleapp.entities.Car.DriveTrain;
import com.techllenapps.cardealershipconsoleapp.entities.Car.FuelType;
import com.techllenapps.cardealershipconsoleapp.entities.Car.Transmission;

public class EmployeeFunctions extends Car implements Serializable{
	private static final long serialVersionUID = 202107104L;
	public static String filePath = "//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//cars.txt";
	static Car car = new Car();
	static ArrayList<Car> carList = new ArrayList<Car>();


	public static void employeeMenu() throws ClassNotFoundException, IOException{

		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("****************************************************************");
			System.out.println("Wellcome to the Employee Menu\n");
			System.out.println("****************************************************************");
			System.out.println("Please select the number to reflect the choices below");
			//the administrator will assign employees and their roles as well as view appplication logs
			System.out.println("1.Add cars to the lot");
			System.out.println("2.Offer Desicion(Accept /reject offer)");
			System.out.println("3.Remove a car from the lot");
			System.out.println("4.View all the paymants");
			System.out.println("5.Exit\n");
			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				addCar();
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
				System.out.println("Invalid Choice,Please select 1 to 6");
				break;
			}
		}

	}

	public static void addCar() throws ClassNotFoundException, IOException{
		ArrayList<Car> carListToFile = new ArrayList<Car>();
		carList=extractCarsFromFile();
		Scanner scan = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);

		System.out.println("\nEnter the Car Price in US Dollars:");
		Double price = scan.nextDouble();
		car.setPrice(price);

		System.out.println("\nEnter the car milage in miles:");
		Double milage = scan.nextDouble();
		car.setMilage(milage);

		System.out.println("\nEnter the number of owners previously owned the car:");
		Double noOfOwners = scan.nextDouble();
		car.setMilage(noOfOwners);

		System.out.println("\nEnter the color of the car:");
		String color = sc.nextLine();
		car.setColor(color);

		System.out.println("\nEnter the drivetrain fo the car(fill AWD OR RWD OR FOURWD):");
		String driveTrain = sc.nextLine();
		car.setDriveTrain(DriveTrain.valueOf(driveTrain.toLowerCase()));

		System.out.println("\nEnter the type of fuel the car uses(fill Electric OR Gasoline OR Diesel OR NaturalGas):");
		String fuelType = sc.nextLine();
		car.setFuelType(FuelType.valueOf(fuelType.toLowerCase())); 

		System.out.println("\nEnter the type of transmission(fill Manual OR Automatic)");
		String transmission = sc.nextLine();
		car.setTransmission(Transmission.valueOf(transmission.toLowerCase())); 

		System.out.println("\nEnter the VIN of the car");
		String VIN = sc.nextLine();
		car.setVIN(VIN); 

		System.out.println("\nEnter the location");
		String location = sc.nextLine();
		car.setLocation(location);

		System.out.println("\nEnter the year the car was made");
		int year = sc.nextInt();
		car.setYear(year);

		System.out.println("\nEnter the model of the car\n");
		String model = sc.nextLine();
		car.setModel(model);

		Date  date = new Date();
		car.setDatePosted(date);

		//setting default offer made by as NO Offer
		car.setOfferMadeBy("none(No Offer)");

		//setting default amount offered to 0
		car.setAmountOffered(0.0);

		//setting default offer status to 
		car.setOfferStatus(null);

		carListToFile.add(car);
		carListToFile.addAll(carList);
		removeDuplicatesCars(carListToFile);

		FileOutputStream fis = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fis);
		oos.writeObject(carListToFile);
	}

	public static ArrayList<Car> extractCarsFromFile() throws ClassNotFoundException {
		//checking of the file is empty 
		File file = new File(filePath);
		try {
			if (file.length() == 0) {
				//System.out.println("There are no cars in the list please add cars as prompted below\n");
				addCar();
			}else{
				FileInputStream fis = new FileInputStream(filePath);
				ObjectInputStream ois = new ObjectInputStream(fis);
				carList=(ArrayList<Car>)ois.readObject();
			}
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}	
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("There are no cars in the list please add");
		}
		return carList;
	}

	public static ArrayList<Car> removeDuplicatesCars(ArrayList<Car> userList)
	{
		ArrayList<Car> noDuplicateCarList = new ArrayList<Car>();
		for (Car Car : carList) {
			if (!noDuplicateCarList.contains(Car)) {
				noDuplicateCarList.add(Car);
			}	
		}
		return noDuplicateCarList;
	}
}
