package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.techllenapps.cardealershipconsoleapp.entities.Car;
import com.techllenapps.cardealershipconsoleapp.entities.CarPayment;
import com.techllenapps.cardealershipconsoleapp.entities.LoanData;
import com.techllenapps.cardealershipconsoleapp.entities.MonthlyPayment;
import com.techllenapps.cardealershipconsoleapp.entities.PaymentHistory;

public class CustomerFunctions{
	private static final long serialVersionUID = 202107103L;
	public static String filePathForCarPayment = "//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//payments.txt";
	public static String offersFilePath = "//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//offers.txt";
	public static ArrayList<CarPayment> carPayMentList = new ArrayList<CarPayment>();
	static Scanner scan = new Scanner(System.in);


	public static void customerMenu() throws ClassNotFoundException, IOException{

		while(true) {
			System.out.println("****************************************************************");
			System.out.println("Wellcome to the Customer Menu\n");
			System.out.println("****************************************************************");
			System.out.println("Please select the number to reflect the choices below");
			//the administrator will assign employees and their roles as well as view appplication logs
			System.out.println("1.View all available car offers and their status");
			System.out.println("2.Make/update an offer for a car");
			System.out.println("3.View the cars that I own");
			System.out.println("4.Make Payment for my car(can only do this if your offer is accepted)");
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
					viewCarsThatIOwn();
					CarPayment cp = new CarPayment();
					System.out.println("\nPlease select the VIN of the car that you want to pay for from the list of accepted offers that you made as shown above");
					//Buffered reader was used as alternative to input to the function because scanner could not work
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					String VIN = reader.readLine();
					System.out.println("\nPlease enter the amount that you want to pay int $ e.g 200.75");
					Double amountPaid = Double.parseDouble(reader.readLine());
					cp=calculateMonthlyPayment(VIN);
					addFirstCarPayment(cp);
					updateCarPayment(cp,VIN);
					cp=addPayment(VIN, amountPaid);
					addFirstCarPayment(cp);
					updateCarPayment(cp,VIN);
					System.out.println("\nThank you for paying for this car");
				}else {
					viewCarsThatIOwn();
					CarPayment cp = new CarPayment();
					System.out.println("\nPlease select the VIN of the car that you want to pay for from the list of accepted offers that you made as shown above");
					//Buffered reader was used as alternative to input to the function because scanner could not work
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					String VIN = reader.readLine();
					System.out.println("\nPlease enter the amount that you want to pay int $ e.g 200.75");
					Double amountPaid = Double.parseDouble(reader.readLine());
					cp=calculateMonthlyPayment(VIN);
					addOtherCarPayMents(cp);
					updateCarPayment(cp,VIN);
					cp=addPayment(VIN, amountPaid);
					addOtherCarPayMents(cp);
					updateCarPayment(cp,VIN);
					System.out.println("\nThank you for paying for this car");
				}
				break;
			case 5:
				viewCarsThatIOwn();
				//viewPayMentSchedule();
				viewPaymentHistory();
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
		Car carOffer = new Car();
		System.out.println("\n\n Enter the ID number of the car that you want to make an offer for");
		int ID = sc.nextInt();
		sc.nextLine();
		System.out.println("\nEnter the amount that you want to offer for the car");
		try {
			Double amountOffered = sc.nextDouble();
			setAmountOfferedToACar(ID, amountOffered,UserFunctions.userInSession);
		} catch (Exception e) {
			System.out.println("Please enter the amount in numbers eg. 345679.90");	
			e.printStackTrace();
		}
	}

	public static void  setAmountOfferedToACar(int ID,Double amountOffered,String userInSession) throws ClassNotFoundException, IOException {
		ArrayList<Car> carListToView = EmployeeFunctions.extractCarsFromFile();
		List<Car> newCarListOffer = new ArrayList<Car>();
		Car newCarOffer = new Car();
		for (int c=0;c<carListToView.size();c++) {
			//making the system reject for a new offer if the offer has been placed already
			//accept offer if and only iff its not accepted(either null or rejected)

			if ((c==ID-1)&&
					!(carListToView.get(c).getOfferStatus().compareTo(Car.OfferStatus.accepted)==0)){
				System.out.println(carListToView.get(c).getOfferStatus().compareTo(Car.OfferStatus.accepted)==0);
				newCarOffer.setPrice(carListToView.get(c).getPrice());
				newCarOffer.setMilage(carListToView.get(c).getMilage());
				newCarOffer.setNoOfOwners(carListToView.get(c).getNoOfOwners());
				newCarOffer.setColor(carListToView.get(c).getColor());
				newCarOffer.setDriveTrain(carListToView.get(c).getDriveTrain());
				newCarOffer.setFuelType(carListToView.get(c).getFuelType());
				newCarOffer.setTransmission(carListToView.get(c).getTransmission());
				newCarOffer.setVIN(carListToView.get(c).getVIN());
				newCarOffer.setLocation(carListToView.get(c).getLocation());
				newCarOffer.setYear(carListToView.get(c).getYear());
				newCarOffer.setModel(carListToView.get(c).getModel());
				newCarOffer.setDatePosted(carListToView.get(c).getDatePosted());
				newCarOffer.setOfferStatus(null);
				newCarOffer.setCarPaymet(carListToView.get(c).getCarPaymet());
				newCarOffer.setAmountOffered(amountOffered);
				newCarOffer.setOfferMadeBy(userInSession);
				//testing
				System.out.println("\n******"+newCarOffer);
				System.out.println("\n******"+carListToView + "\n******"+carListToView.size() );
				newCarListOffer.add(newCarOffer);
			}else if ((c==ID-1)&&(carListToView.get(c).getOfferStatus().compareTo(Car.OfferStatus.accepted)==0)) {
				System.out.println("\nThis car is not available for making offers\n");
			}
		}
		carListToView.addAll(newCarListOffer);
		//testing
		//System.out.println(carListToView);
		//remove duplicates before adding to the list
		//carListToView=removeDuplicatesCarsOffers(carListToView);
		EmployeeFunctions.updateCar(carListToView);
	}

	public static ArrayList<Car> removeDuplicatesCarsOffers(ArrayList<Car> cars) throws ClassNotFoundException, IOException
	{
		ArrayList<Car> carList=	EmployeeFunctions.extractCarsFromFile();;
		ArrayList<Car> noDuplicateCarList = new ArrayList<Car>();
		for (Car Car : carList) {
			if (!noDuplicateCarList.contains(Car)) {
				noDuplicateCarList.add(Car);
			}	
		}
		return noDuplicateCarList;
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

	//	public static CarPayment processRepaymentSchedule(String VIN) throws ClassNotFoundException, IOException {
	//		CarPayment carPayment = new CarPayment();
	//		LoanData loandata = new LoanData();
	//
	//		ArrayList<MonthlyPayment> monthlyPaymentSchedule = new ArrayList<MonthlyPayment>(60);
	//		ArrayList<Car> extractedCars = EmployeeFunctions.extractCarsFromFile();
	//		for (Car car : extractedCars) {
	//			if(car.getVIN().equals(VIN)) {
	//				loandata.setOwner(car.getOfferMadeBy());
	//				loandata.setVIN(car.getVIN());
	//				loandata.setModel(car.getModel());
	//				loandata.setPrincipal(car.getPrice());
	//				loandata.setTotalPrincipalToBePaid(loandata.getPrincipal());
	//				loandata.setTotalInterestToBePaid(loandata.getPrincipal()*loandata.getInterestRate()*5/100);
	//				loandata.setTotalLoanAmount(loandata.getTotalInterestToBePaid()+loandata.getTotalPrincipalToBePaid());
	//				double monthlYPaymentAmount;
	//				monthlYPaymentAmount=(loandata.getPrincipal())*((loandata.getMonthlyInterestRate()*(Math.pow((1+loandata.getMonthlyInterestRate()), loandata.getTermInMonths())))/((Math.pow((1+loandata.getMonthlyInterestRate()), loandata.getTermInMonths()))-1));
	//				loandata.setMonthlYPaymentAmount(monthlYPaymentAmount);
	//				carPayment.setLoandata(loandata);
	//				for ( int month=0;month<61;month++) {
	//					if (month>0) {
	//						MonthlyPayment monthlyPayment = new MonthlyPayment();
	//						monthlyPayment.setMonth(month);
	//						monthlyPayment.setMonthlyInstallation(carPayment.getLoandata().getMonthlYPaymentAmount());
	//						monthlyPayment.setBalance((loandata.getPrincipal()-((month-1)*monthlyPayment.getMonthlyInstallation()))-monthlyPayment.getMonthlyInstallation());
	//						monthlyPayment.setInterestToBePaid(loandata.getMonthlyInterestRate()*monthlyPayment.getBalance());
	//						monthlyPayment.setPrincipalToBePaid(monthlyPayment.getMonthlyInstallation()-monthlyPayment.getInterestToBePaid());
	//						monthlyPaymentSchedule.add(monthlyPayment);
	//					}
	//				}
	//				carPayment.setMontlyPaymentSchedule(monthlyPaymentSchedule);
	//			}
	//		}
	//		return carPayment;	
	//	}

	//	public static  CarPayment processBankStatement(String VIN,Double amountPaid) throws ClassNotFoundException, IOException {
	//
	//		CarPayment carPayment = new CarPayment();
	//		LoanData loandata = new LoanData();
	//
	//		ArrayList<PaymentHistory> monthlyPaymentHistory = new ArrayList<PaymentHistory>();
	//		ArrayList<Car> extractedCars = EmployeeFunctions.extractCarsFromFile();
	//		Date today = new Date();
	//		for (Car car : extractedCars) {
	//
	//			if(car.getVIN().equals(VIN)) {
	//				//paymentHistory.setDebit(amountPaid2);
	//
	//				PaymentHistory paymentHistory = new PaymentHistory();
	//				paymentHistory.setCredit(amountPaid);
	//				paymentHistory.setTransactionDate(today);
	//				monthlyPaymentHistory.add(paymentHistory);
	//
	//			}
	//			carPayment.setMonthlyPaymentHistory(monthlyPaymentHistory);
	//		}
	//		return carPayment;
	//	}


	public static  CarPayment calculateMonthlyPayment(String VIN) throws ClassNotFoundException, IOException {
		ArrayList<Car> extractedCars = EmployeeFunctions.extractCarsFromFile();
		CarPayment carPayment = new CarPayment();
		LoanData loandata = new LoanData();
		//ArrayList<PaymentHistory> monthlyPaymentHistory = new ArrayList<PaymentHistory>();
		ArrayList<MonthlyPayment> monthlyPaymentSchedule = new ArrayList<MonthlyPayment>(60);
		//ArrayList<PaymentHistory> previousPaymentHistory  = new ArrayList<PaymentHistory>();
		Date today = new Date();
		for (Car car : extractedCars) {
			//if(car.getVIN().equals(VIN)) {
			//From the list of cars extracted,since we have a mix of cars with different status
			//pick one with the same vin and whose offerstatus is accepted
			try {
				if(car.getVIN().equals(VIN)) {
					if(car.getOfferStatus().compareTo(Car.OfferStatus.accepted)==0) {
						loandata.setOwner(car.getOfferMadeBy());
						loandata.setVIN(car.getVIN());
						loandata.setModel(car.getModel());
						loandata.setPrincipal(car.getPrice());
						loandata.setTotalPrincipalToBePaid(loandata.getPrincipal());
						loandata.setTotalInterestToBePaid(loandata.getPrincipal()*loandata.getInterestRate()*5/100);
						loandata.setTotalLoanAmount(loandata.getTotalInterestToBePaid()+loandata.getTotalPrincipalToBePaid());
						double monthlYPaymentAmount;
						monthlYPaymentAmount=(loandata.getPrincipal())*((loandata.getMonthlyInterestRate()*(Math.pow((1+loandata.getMonthlyInterestRate()), loandata.getTermInMonths())))/((Math.pow((1+loandata.getMonthlyInterestRate()), loandata.getTermInMonths()))-1));
						loandata.setMonthlYPaymentAmount(monthlYPaymentAmount);
						carPayment.setLoandata(loandata);
						for ( int month=0;month<61;month++) {
							if (month>0) {
								MonthlyPayment monthlyPayment = new MonthlyPayment();
								monthlyPayment.setMonth(month);
								monthlyPayment.setMonthlyInstallation(carPayment.getLoandata().getMonthlYPaymentAmount());
								monthlyPayment.setBalance((loandata.getPrincipal()-((month-1)*monthlyPayment.getMonthlyInstallation()))-monthlyPayment.getMonthlyInstallation());
								monthlyPayment.setInterestToBePaid(loandata.getMonthlyInterestRate()*monthlyPayment.getBalance());
								monthlyPayment.setPrincipalToBePaid(monthlyPayment.getMonthlyInstallation()-monthlyPayment.getInterestToBePaid());
								monthlyPaymentSchedule.add(monthlyPayment);
							}
						}
						carPayment.setMontlyPaymentSchedule(monthlyPaymentSchedule);
						//						//if(car.getVIN().equals(VIN)) {
						//						//paymentHistory.setDebit(amountPaid2);
						//						
						//						//To avoid overwriting previous payment history,retrieve it first then append the new one to it
						//						previousPaymentHistory = carPayment.getMonthlyPaymentHistory();
						//						System.out.println("the old hist is:" + previousPaymentHistory);
						//						PaymentHistory paymentHistory = new PaymentHistory();
						//						paymentHistory.setCredit(amountPaid);
						//						paymentHistory.setTransactionDate(today);
						//						monthlyPaymentHistory.add(paymentHistory);
						//						//appending the new monthly payment history to the older one
						//						monthlyPaymentHistory.addAll(previousPaymentHistory);
						//						carPayment.setMonthlyPaymentHistory(monthlyPaymentHistory);
						//						System.out.println(paymentHistory);
						//						System.out.println(carPayment);
					}
				}
				//the below will catch all cars with offerstatus value other than accepted and empty peyment history
			} catch (java.lang.NullPointerException e) {
			}
		}
		return carPayment;
	}

	//	public static  CarPayment addPayment(CarPayment carPayment,Double amountPaid) throws ClassNotFoundException, IOException {
	//		try {
	//			ArrayList<PaymentHistory> monthlyPaymentHistory = new ArrayList<PaymentHistory>();
	//			ArrayList<PaymentHistory> previousPaymentHistory  = new ArrayList<PaymentHistory>();
	//			Date today = new Date();
	//			//To avoid overwriting previous payment history,retrieve it first then append the new one to it
	//			previousPaymentHistory = carPayment.getMonthlyPaymentHistory();
	//			System.out.println("the old hist is:" + previousPaymentHistory);
	//			PaymentHistory paymentHistory = new PaymentHistory();
	//			paymentHistory.setCredit(amountPaid);
	//			paymentHistory.setTransactionDate(today);
	//			monthlyPaymentHistory.add(paymentHistory);
	//			//appending the new monthly payment history to the older one
	//			monthlyPaymentHistory.addAll(previousPaymentHistory);
	//			carPayment.setMonthlyPaymentHistory(monthlyPaymentHistory);
	//			//						System.out.println(paymentHistory);
	//			System.out.println(carPayment);
	//
	//		}
	//		//the below will catch all cars with offerstatus value other than accepted and empty peyment history
	//		catch (java.lang.NullPointerException e) {
	//		}
	//		return carPayment;
	//	}

	public static  CarPayment addPayment(String VIN,Double amountPaid) throws ClassNotFoundException, IOException {
		CarPayment carPayment = new CarPayment();

		try {
			carPayMentList=extractCarPayMentsFromFile();
			ArrayList<PaymentHistory> monthlyPaymentHistory = new ArrayList<PaymentHistory>();
			ArrayList<PaymentHistory> previousPaymentHistory  = new ArrayList<PaymentHistory>();
			Date today = new Date();
			for (CarPayment carPaymentFromFile : carPayMentList) {
				if(carPaymentFromFile.getLoandata().getVIN().equals(VIN)) {
					carPayment=carPaymentFromFile;
					System.out.println("file car payment:" + carPayment);
					previousPaymentHistory=carPayment.getMonthlyPaymentHistory();
					//testing
					System.out.println("the old hist is:" + previousPaymentHistory);
					PaymentHistory paymentHistory = new PaymentHistory();
					paymentHistory.setCredit(amountPaid);
					paymentHistory.setTransactionDate(today);
					monthlyPaymentHistory.add(paymentHistory);
					System.out.println("monthlyPaymentHistory:"+monthlyPaymentHistory);
					//appending the new monthly payment history to the older one
					monthlyPaymentHistory.addAll(previousPaymentHistory);
					carPayment.setMonthlyPaymentHistory(monthlyPaymentHistory);
					//						System.out.println(paymentHistory);
					System.out.println(carPayment);
				}
			}
		}catch (java.io.EOFException e) {
			System.out.println("Please enter the correct VIN or there are no payments for this car or the offer has not been accepted yet");
		}
		return carPayment;
	}


	public static void updateCarPayment(CarPayment carPayment,String VIN) throws ClassNotFoundException, IOException {
		ArrayList<Car> updatedCarListWithPayments = EmployeeFunctions.extractCarsFromFile();

		for (Car car : updatedCarListWithPayments) {
			if(car.getVIN().equals(VIN)) {
				car.setCarPaymet(carPayment);
			}
		}
		EmployeeFunctions.updateCar(updatedCarListWithPayments);
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
		try {
			ArrayList<MonthlyPayment> montlyPaymentSchedule = new ArrayList<MonthlyPayment>();
			System.out.println("Enter the VIN of the car to view specific repayment schedule");
			Scanner sc3 = new Scanner(System.in);
			String VIN = sc3.nextLine();
			carPayMentList=extractCarPayMentsFromFile();
			for (CarPayment carPayment : carPayMentList) {
				if(carPayment.getLoandata().getVIN().equals(VIN)) {
					System.out.println("Below are the details of the loan");
					System.out.println(
							"\nOffer By:"+carPayment.getLoandata().getOwner()+
							"\nVIN:"+carPayment.getLoandata().getVIN()+
							"\nCar Model:"+carPayment.getLoandata().getModel()+
							"\nCar Price:"+carPayment.getLoandata().getPrincipal()+
							"\nTotal No Of Month To Complete the payments:"+carPayment.getLoandata().getTermInMonths()+
							"\nAPR:"+carPayment.getLoandata().getInterestRate()
							);
					montlyPaymentSchedule=carPayment.getMontlyPaymentSchedule();
					System.out.println("Month   "+"Monthly Installation Amount    "+"Interest to be paid     "+"Principal  to be paid    "+"Balance");
					for (MonthlyPayment monthlyPayment : montlyPaymentSchedule) {
						System.out.println(
								"\n"+monthlyPayment.getMonth()
								+monthlyPayment.getMonthlyInstallation()
								+monthlyPayment.getInterestToBePaid()
								+monthlyPayment.getPrincipalToBePaid()
								+monthlyPayment.getBalance()
								);
					}
				}
			}
		} catch (java.io.EOFException e) {
			System.out.println("Currently the customer has placed no payment");
		}catch (java.lang.NullPointerException e1) {
			System.out.println("Please enter the correct VIN");
		}
	}	

	public static void viewPaymentHistory() throws ClassNotFoundException, IOException {
		try {
			ArrayList<PaymentHistory> monthlyPaymentHistory = new ArrayList<PaymentHistory>();
			System.out.println("Enter the VIN of the car to view specific payment History");
			//Buffered reader was used as alternative to input to the function because scanner could not work
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String VIN = reader.readLine();
			carPayMentList=extractCarPayMentsFromFile();
			//testing
			System.out.println(carPayMentList);
			System.out.println(VIN);
			for (CarPayment carPayment : carPayMentList) {
				if(carPayment.getLoandata().getVIN().equals(VIN)) {
					System.out.println("Below are the details of the loan");
					System.out.println(
							"\nOffer By:"+carPayment.getLoandata().getOwner()+
							"\nVIN:"+carPayment.getLoandata().getVIN()+
							"\nCar Model:"+carPayment.getLoandata().getModel()+
							"\nCar Price:"+carPayment.getLoandata().getPrincipal()+
							"\nTotal No Of Month To Complete the payments:"+carPayment.getLoandata().getTermInMonths()+
							"\nAPR:"+carPayment.getLoandata().getInterestRate()
							+"\n\n"
							);
					monthlyPaymentHistory=carPayment.getMonthlyPaymentHistory();
					//testing
					System.out.println(monthlyPaymentHistory.size());
					System.out.println(monthlyPaymentHistory);
					Double total = 0.0;
					System.out.println("Transaction Date   "+"Debit   "+"Credit    "+"Balance");

					for (int z=0;z<monthlyPaymentHistory.size();z++) {
						System.out.println(
								"\n"+monthlyPaymentHistory.get(z).getTransactionDate()
								+monthlyPaymentHistory.get(z).getDebit()
								+monthlyPaymentHistory.get(z).getCredit()
								+monthlyPaymentHistory.get(z).getBalance()
								);
						total = monthlyPaymentHistory.get(z).getTotalAmountPaid();  
						total=monthlyPaymentHistory.get(z).getAmountPaid()+total;
					}
					System.out.println("Total Amount paid for the loan is:"+ total);
					System.out.println("Remaining total Amount to be paid for the loan is:"+ (carPayment.getLoandata().getTotalLoanAmount()-total ));
				}
			}
		}catch (java.io.EOFException e) {
			System.out.println("Currently the customer has placed no payment");
		}catch (java.lang.NullPointerException e1) {
			System.out.println("Please enter the correct VIN or there are no payments for this car or the offer has not been accepted yet");
			e1.printStackTrace();
		}
	}
}

