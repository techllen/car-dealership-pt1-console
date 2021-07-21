package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.techllenapps.cardealershipconsoleapp.entities.Car;
import com.techllenapps.cardealershipconsoleapp.entities.CarPayment;
import com.techllenapps.cardealershipconsoleapp.entities.LoanData;
import com.techllenapps.cardealershipconsoleapp.entities.MonthlyPayment;

public class CustomerFunctions{
	private static final long serialVersionUID = 202107103L;
	public static String filePathForCarPayment = "//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//payments.txt";
	public static ArrayList<CarPayment> carPayMentList = new ArrayList<CarPayment>();
	static Scanner scan = new Scanner(System.in);


	public static void customerMenu() throws ClassNotFoundException, IOException{

		while(true) {
			System.out.println("****************************************************************");
			System.out.println("Wellcome to the Customer Menu\n");
			System.out.println("****************************************************************");
			System.out.println("Please select the number to reflect the choices below");
			//the administrator will assign employees and their roles as well as view appplication logs
			System.out.println("1.View all available cars");
			System.out.println("2.Make/update an offer for a car");
			System.out.println("3.View the cars that I own");
			System.out.println("4.Make Payment for my car");
			System.out.println("5.Vew pending payment for my car");
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
				if (checkCarPayMent() == false) {
					System.out.println("\nThe is no any payment plan in our file system \n");
					processPayment();
					addFirstCarPayment(processPayment());
					System.out.println("\nThank you for paying for this car");
				}else {
					processPayment();
					addFirstCarPayment(processPayment());
					System.out.println("\nThank you for paying for this car");
				}
				break;
			case 5:
				viewPayMentSchedule();
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

	//	public static CarPayment makePaymentForTheCar() throws ClassNotFoundException, IOException {
	//		viewCarsThatIOwn();
	//		System.out.println("Please select the VIN of the car that you want to pay for from the list of offers that you made as shown above");
	//		String VIN = scan.nextLine();
	//		CarPayment carPayment = new CarPayment();
	//		carPayment = processPayment(VIN);
	//		return carPayment;
	//	}

	public static CarPayment processPayment() throws ClassNotFoundException, IOException {
		viewCarsThatIOwn();
		System.out.println("\nPlease select the VIN of the car that you want to pay for from the list of offers that you made as shown above");
		String VIN = scan.nextLine();
		CarPayment carPayment = new CarPayment();
		LoanData loandata = new LoanData();
		MonthlyPayment monthlyPayment = new MonthlyPayment();
		ArrayList<MonthlyPayment> monthlyPaymentSchedule = new ArrayList<MonthlyPayment>();
		System.out.println();
		ArrayList<Car> extractedCars = EmployeeFunctions.extractCarsFromFile();
		System.out.println(extractedCars);
		for (Car car : extractedCars) {
			if(car.getVIN().equals(VIN)) {
				System.out.println("*************"+car.getOfferMadeBy());
				loandata.setOwner(car.getOfferMadeBy());
				loandata.setVIN(car.getVIN());
				loandata.setModel(car.getModel());
				loandata.setPrincipal(car.getPrice());
				System.out.println("\n********************" + loandata.getPrincipal()+"   "+car.getPrice());
				double monthlYPaymentAmount;
				monthlYPaymentAmount=(loandata.getPrincipal())*((loandata.getMonthlyInterestRate()*(Math.pow((1+loandata.getMonthlyInterestRate()), loandata.getTermInMonths())))/((Math.pow((1+loandata.getMonthlyInterestRate()), loandata.getTermInMonths())-1)));
				loandata.setMonthlYPaymentAmount(monthlYPaymentAmount);
				carPayment.setLoandata(loandata);
				monthlyPayment.setMonthlyInstallation(loandata.getMonthlYPaymentAmount());
				for(int c=0;c<60;c++) {
					if (c==0) {
						monthlyPayment.setMonth(c);
						monthlyPayment.setMonthlyInstallation(0);
						monthlyPayment.setInterestToBePaid(0);
						monthlyPayment.setPrincipalToBePaid(0);
						monthlyPayment.setBalance(car.getPrice());
					}
					
					if (c>0) {
						monthlyPayment.setMonth(c);
						monthlyPayment.setMonthlyInstallation(carPayment.getLoandata().getMonthlYPaymentAmount());
						monthlyPayment.setInterestToBePaid((carPayment.getLoandata().getInterestRate())*carPayment.getLoandata().getPrincipal());
						monthlyPayment.setPrincipalToBePaid(monthlyPayment.getMonthlyInstallation()-monthlyPayment.getInterestToBePaid());
						monthlyPayment.setBalance(monthlyPayment.getBalance()-monthlyPayment.getMonthlyInstallation());
					}
					monthlyPaymentSchedule.add(monthlyPayment);
				}
				carPayment.setMontlyPaymentSchedule(monthlyPaymentSchedule);
				System.out.println(monthlyPaymentSchedule);
			}
		}
		return carPayment;	
	}

	public static void addFirstCarPayment(CarPayment firstcarpayment) throws ClassNotFoundException, IOException{
		ArrayList<CarPayment> carPaymentListToFile = new ArrayList<CarPayment>();
		carPaymentListToFile.add(firstcarpayment);
		FileOutputStream fis = new FileOutputStream(filePathForCarPayment);
		ObjectOutputStream oos = new ObjectOutputStream(fis);
		oos.writeObject(carPaymentListToFile);
	}

	public static void addOtherCarPayMents(CarPayment carpayment) throws ClassNotFoundException, IOException{
		carPayMentList=extractCarPayMentsFromFile();
		ArrayList<CarPayment> carPaymentListToFile = new ArrayList<CarPayment>();
		carPaymentListToFile.add(carpayment);
		carPaymentListToFile.addAll(carPaymentListToFile);
		removeDuplicatesCarPayment(carPaymentListToFile);
		FileOutputStream fis = new FileOutputStream(filePathForCarPayment);
		ObjectOutputStream oos = new ObjectOutputStream(fis);
		oos.writeObject(carPaymentListToFile);
	}

	public static ArrayList<CarPayment> extractCarPayMentsFromFile() throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(filePathForCarPayment);
		ObjectInputStream ois=new ObjectInputStream(fis);
		carPayMentList=(ArrayList<CarPayment>)ois.readObject();
		return carPayMentList;
	}

	public static ArrayList<CarPayment> removeDuplicatesCarPayment(ArrayList<CarPayment> carPayMentList)
	{
		ArrayList<CarPayment> noDuplicateCarPaymentList = new ArrayList<CarPayment>();
		for (CarPayment carpayment : noDuplicateCarPaymentList) {
			if (!noDuplicateCarPaymentList.contains(carpayment)) {
				noDuplicateCarPaymentList.add(carpayment);
			}	
		}
		return noDuplicateCarPaymentList;
	}

	//checking of the file is empty 
	public static boolean checkCarPayMent() {
		boolean paymentAvailable=false;
		File file = new File(filePathForCarPayment);
		if (file.length() == 0) {
			paymentAvailable=false;
		}else{
			paymentAvailable=true;
		}	
		return paymentAvailable;
	}

	public static void viewPayMentSchedule() throws ClassNotFoundException, IOException {
		viewCarsThatIOwn();
		ArrayList<MonthlyPayment> montlyPaymentSchedule = new ArrayList<MonthlyPayment>();
		System.out.println("Enter the VIN to view specific repayment schedule");
		Scanner sc3 = new Scanner(System.in);
		String VIN = sc3.nextLine();
		carPayMentList=extractCarPayMentsFromFile();
		System.out.println("Month   "+"Monthly Installation Amount    "+"Interest      "+"Principal      "+"Balance");
		for (CarPayment carPayment : carPayMentList) {
			if(carPayment.getLoandata().getVIN().equals(VIN)) {
				montlyPaymentSchedule=carPayment.getMontlyPaymentSchedule();
				//payment is done for 60 months for all loans
//				for (int c=0;c<2;c++) {
//					if(c==0) {
//						montlyPaymentSchedule.get(c).setMonth(c);
//						montlyPaymentSchedule.get(c).setMonthlyInstallation(0);
//						montlyPaymentSchedule.get(c).setInterestToBePaid(0);
//						montlyPaymentSchedule.get(c).setPrincipalToBePaid(0);
//						montlyPaymentSchedule.get(c).setBalance(carPayment.getLoandata().getPrincipal());	
//					}
//					else if(c>0) {
//						montlyPaymentSchedule.get(c).setMonth(c);
//						montlyPaymentSchedule.get(c).setMonthlyInstallation(carPayment.getLoandata().getMonthlYPaymentAmount());
//						montlyPaymentSchedule.get(c).setInterestToBePaid((carPayment.getLoandata().getInterestRate())*carPayment.getLoandata().getPrincipal());
//						montlyPaymentSchedule.get(c).setPrincipalToBePaid(carPayment.getLoandata().getMonthlYPaymentAmount()-montlyPaymentSchedule.get(c).getInterestToBePaid());
//						montlyPaymentSchedule.get(c).setBalance(montlyPaymentSchedule.get(c-1).getBalance()-montlyPaymentSchedule.get(c).getMonthlyInstallation());	
//					}
//				}
//				System.out.println("\n"+montlyPaymentSchedule.toString());
				
			for (MonthlyPayment monthlyPayment : montlyPaymentSchedule) {
				System.out.println();
			}
			}
		}
	}
}

