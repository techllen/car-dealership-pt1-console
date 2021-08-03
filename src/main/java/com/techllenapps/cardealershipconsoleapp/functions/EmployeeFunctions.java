package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.techllenapps.cardealershipconsoleapp.entities.Car;
import com.techllenapps.cardealershipconsoleapp.entities.User;


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
			System.out.println("4.View all the payments");
			System.out.println("5.Exit\n");
			try {
				int choice = scan.nextInt();

				switch (choice) {
				case 1:
					if (checkCars() == false) {
						System.out.println("The is no car inventory,please add as prompted below \n");
						Car car = addCarMenu();
						addFirstCar(car);
						System.out.println("\nYou added a car");
					}else {
						Car car = addCarMenu();
						addOtherCars(car);
						System.out.println("\nYou added a car");
					}
					break;
				case 2:
					offerDecision();
					System.out.println("Offer Decision Updated");
					break;
				case 3:
					removeCarFromTheLot();
					System.out.println("\n\none car was deleted from the list");
					break;
				case 4:
					viewCars();
					//CustomerFunctions.viewPayMentSchedule();
					break;
				case 5:
					//take the user to the main menu
					UserFunctions.mainMenu();
					break;
				default:
					System.out.println("Invalid Choice,Please select 1 to 6");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Choice,Please select 1 to 6");
				break;
			}
		}
	}

	public static Car addCarMenu() throws ClassNotFoundException, IOException{
		ArrayList<Car> carListToFile = new ArrayList<Car>();
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("\nEnter the Car Price in US Dollars:");
			Double price = scan.nextDouble();
			car.setPrice(price);

			System.out.println("\nEnter the car milage in miles:");
			Double milage = scan.nextDouble();
			car.setMilage(milage);

			System.out.println("\nEnter the number of owners previously owned the car:");
			Double noOfOwners = scan.nextDouble();
			scan.nextLine();
			car.setMilage(noOfOwners);

			System.out.println("\nEnter the color of the car:");
			String color = scan.nextLine();
			car.setColor(color);

			System.out.println("\nEnter the drivetrain fo the car(fill AWD OR RWD OR FOURWD):");
			String driveTrain = scan.nextLine();
			car.setDriveTrain(DriveTrain.valueOf(driveTrain.toLowerCase()));

			System.out.println("\nEnter the type of fuel the car uses(fill Electric OR Gasoline OR Diesel OR NaturalGas):");
			String fuelType = scan.nextLine();
			car.setFuelType(FuelType.valueOf(fuelType.toLowerCase())); 

			System.out.println("\nEnter the type of transmission(fill Manual OR Automatic)");
			String transmission = scan.nextLine();
			car.setTransmission(Transmission.valueOf(transmission.toLowerCase())); 

			System.out.println("\nEnter the VIN of the car");
			String VIN = scan.nextLine();
			car.setVIN(VIN); 

			System.out.println("\nEnter the location");
			String location = scan.nextLine();
			car.setLocation(location);

			System.out.println("\nEnter the year the car was made");
			int year = scan.nextInt();
			scan.nextLine();
			car.setYear(year);

			System.out.println("\nEnter the model of the car\n");
			String model = scan.nextLine();
			car.setModel(model);

			Date  date = new Date();
			car.setDatePosted(date);

			//setting default offer made by as NO Offer
			car.setOfferMadeBy("none(No Offer)");

			//setting default amount offered to 0
			car.setAmountOffered(0.0);

			//setting default offer status to 
			car.setOfferStatus(null);
		} catch (java.lang.IllegalArgumentException e) {
			System.out.println("Enter the required Input");
			UserFunctions.mainMenu();
		}

		return car;
	}

	public static void addFirstCar(Car firstcar) throws ClassNotFoundException, IOException{
		ArrayList<Car> carListToFile = new ArrayList<Car>();
		carListToFile.add(firstcar);
		FileOutputStream fis = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fis);
		oos.writeObject(carListToFile);
	}

	public static void addOtherCars(Car car) throws ClassNotFoundException, IOException{
		carList=extractCarsFromFile();
		ArrayList<Car> carListToFile = new ArrayList<Car>();
		carListToFile.add(car);
		carListToFile.addAll(carList);
		removeDuplicatesCars(carListToFile);
		FileOutputStream fis = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fis);
		oos.writeObject(carListToFile);
	}

	//the method below will deal with serializing a newly updated arraylist after user/employee does any manipulation on car object
	public static void updateCar(ArrayList<Car> updatedCarList) throws ClassNotFoundException, IOException{
		FileOutputStream fis = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fis);
		//overriding the previously stored list of cars with an updated one
		oos.writeObject(updatedCarList);
	}



	public static ArrayList<Car> extractCarsFromFile() throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(filePath);
		ObjectInputStream ois=new ObjectInputStream(fis);
		carList=(ArrayList<Car>)ois.readObject();
		return carList;
	}

	//checking of the file is empty 

	public static boolean checkCars() {
		boolean carAvailable=false;
		File file = new File(filePath);
		if (file.length() == 0) {
			carAvailable=false;
		}else{
			carAvailable=true;
		}	
		return carAvailable;
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

	public static void offerDecision() throws ClassNotFoundException, IOException {
		String offerStatus=null;
		if (checkCars() == false) {
			System.out.println("The is no car inventory,please add as prompted below \n");
			Car car = addCarMenu();
			addFirstCar(car);
		}else {
			viewCars();
			Scanner sc = new Scanner(System.in);
			System.out.println("\nEnter the ID number of the car that you want to update the offer status");
			int ID = sc.nextInt();
			sc.nextLine();
			System.out.println("\nEnter the offerstatus fill accepted or rejected");
			try {
				offerStatus = sc.nextLine();
				setOfferStatusToCar(ID, offerStatus);
			} catch (Exception e) {
				System.out.println("Please enter accepted or rejected or processing");			
			}	
		}
	}


	public static void viewCars() throws ClassNotFoundException, IOException {

		if (checkCars() == false) {
			System.out.println("The is no car inventory,please contact the dealership \n");
		}else {
			ArrayList<Car> carListToView = extractCarsFromFile();
			// 			testing
			//			System.out.println("\n"+ carListToView);
			//			System.out.println("\n"+ carListToView.size());
			System.out.println("ID"+"  Model"+"  Price"+"  Milage"+"  NoOfOwners"+"  Color"+"  DriveTrain"+"  FuelType"+"  Transmission"+"  VIN"+"  Location"+"  Year"+"  Model"+"  DatePosted"+"  AmountOffered"+"  OfferStatus");
			for (int c=0;c<carListToView.size();c++) {
				System.out.println(
						(c+1)
						+"  "+ 		
						carListToView.get(c).getModel()
						+"  "+
						carListToView.get(c).getPrice()
						+"  "+
						carListToView.get(c).getMilage()
						+"  "+
						carListToView.get(c).getNoOfOwners()
						+
						carListToView.get(c).getColor()
						+"  "+
						carListToView.get(c).getDriveTrain()
						+"  "+
						carListToView.get(c).getFuelType()
						+"  "+
						carListToView.get(c).getTransmission()
						+"  "+
						carListToView.get(c).getVIN()
						+"  "+
						carListToView.get(c).getLocation()
						+"  "+
						carListToView.get(c).getYear()
						+"  "+
						carListToView.get(c).getModel()
						+"  "+
						carListToView.get(c).getDatePosted()
						+"  "+
						//carListToView.get(c).getOfferMadeBy()
						//+"  "+
						carListToView.get(c).getAmountOffered()
						+"  "+
						carListToView.get(c).getOfferStatus()

						);
			}
		}
	}

	public static void setOfferStatusToCar(int ID,String offerStatus) throws ClassNotFoundException, IOException {
		ArrayList<Car> carListToView = extractCarsFromFile();
		for (int c=0;c<carListToView.size();c++) {
			if (c==(ID-1)&&(carListToView.get(c).getAmountOffered()!=null)) {
				carListToView.get(c).setOfferStatus(OfferStatus.valueOf(offerStatus.toLowerCase()));
				//testing
				//System.out.println(carListToView.get(c));
			}else if(c==(ID-1)&&(carListToView.get(c).getAmountOffered()==null)){
				System.out.println("there is no amount offered for this car to be bought");
			}
		}
		//testing
		//System.out.println(carListToView);
		updateCar(carListToView);
	}

	public static void removeCarFromTheLot() throws ClassNotFoundException, IOException {
		try {
			ArrayList<Car> carListToView = extractCarsFromFile();

			viewCars();
			Scanner sc = new Scanner(System.in);
			System.out.println("\nEnter the ID number of the car that you want to delete from the lot");
			int ID = sc.nextInt();
			for (int c=0;c<carListToView.size();c++) {
				if (c==(ID-1)&&(checkCars() == true)) {
					User user = new User();
					String VIN = carListToView.get(c).getVIN();
					carListToView.remove(c);
					applicationLogging.log.info("car with vin number"+ VIN +"has been removed by" + user.getUserName());
				}
				updateCar(carListToView);
			}
		} catch (Exception e) {
			System.out.println("There are no cars in the inventory");
		}	
	}
}
