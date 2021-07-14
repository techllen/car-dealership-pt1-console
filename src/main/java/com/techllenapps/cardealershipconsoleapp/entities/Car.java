package com.techllenapps.cardealershipconsoleapp.entities;

import java.io.Serializable;
import java.util.Date;

public class Car implements Serializable{
	
	private Double price;
	private Double milage;
	private Double noOfOwners;
	private String color;
	private DriveTrain driveTrain;
	public enum DriveTrain{FOURWD,AWD,RWD}
	private FuelType fuelType;
	public enum FuelType{Electric,Gasoline,Diesel,NaturalGas}
	private Transmission transmission;
	public enum Transmission{Manual,Automatic}
	private String VIN;
	private String Location;
	private int year;
	private String model;
	private Date datePosted;
	private String offerMadeBy;
	private Double amountOffered;
	private OfferStatus offerStatus;
	public enum OfferStatus{Accepted,Rejected,Processing,None}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getMilage() {
		return milage;
	}
	public void setMilage(Double milage) {
		this.milage = milage;
	}
	public Double getNoOfOwners() {
		return noOfOwners;
	}
	public void setNoOfOwners(Double noOfOwners) {
		this.noOfOwners = noOfOwners;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public DriveTrain getDriveTrain() {
		return driveTrain;
	}
	public void setDriveTrain(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
	}
	public FuelType getFuelType() {
		return fuelType;
	}
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	public Transmission getTransmission() {
		return transmission;
	}
	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	public String getOfferMadeBy() {
		return offerMadeBy;
	}
	public void setOfferMadeBy(String offerMadeBy) {
		this.offerMadeBy = offerMadeBy;
	}
	public Double getAmountOffered() {
		return amountOffered;
	}
	public void setAmountOffered(Double amountOffered) {
		this.amountOffered = amountOffered;
	}
	public OfferStatus getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}
	@Override
	public String toString() {
		return "Car [price=" + price + ", milage=" + milage + ", noOfOwners=" + noOfOwners + ", color=" + color
				+ ", driveTrain=" + driveTrain + ", fuelType=" + fuelType + ", transmission=" + transmission + ", VIN="
				+ VIN + ", Location=" + Location + ", year=" + year + ", model=" + model + ", datePosted=" + datePosted
				+ ", offerMadeBy=" + offerMadeBy + ", amountOffered=" + amountOffered + ", offerStatus=" + offerStatus
				+ "]";
	}
	
	
}
