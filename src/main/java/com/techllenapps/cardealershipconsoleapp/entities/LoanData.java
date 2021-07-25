package com.techllenapps.cardealershipconsoleapp.entities;
import java.io.Serializable;
public class LoanData implements Serializable{

	private static final long serialVersionUID = 6673059310427127988L;

	private String owner;
	private String VIN;
	private String model;
	private Double principal=0.0D;
	//all our loans are 60 months at 0.99% APR
	private float termInMonths=60.0F;
	private float paymentsInAYear=12.0F;
	private float interestRate = 0.99F;
	private float monthlyInterestRate = (0.99F)/(100*paymentsInAYear);
	private double monthlYPaymentAmount; 
	//private double monthlYPaymentAmount = (principal)*((monthlyInterestRate*(Math.pow((1+monthlyInterestRate), termInMonths)))/((Math.pow((1+monthlyInterestRate), termInMonths)-1)));
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Double getPrincipal() {
		return principal;
	}
	public void setPrincipal(Double principal) {
		this.principal = principal;
	}
	public float getTermInMonths() {
		return termInMonths;
	}
	public void setTermInMonths(float termInMonths) {
		this.termInMonths = termInMonths;
	}
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	public float getMonthlyInterestRate() {
		return monthlyInterestRate;
	}
	public void setMonthlyInterestRate(float monthlyInterestRate) {
		this.monthlyInterestRate = monthlyInterestRate;
	}
	public double getMonthlYPaymentAmount() {
		return monthlYPaymentAmount;
	}
	public void setMonthlYPaymentAmount(double monthlYPaymentAmount) {
		this.monthlYPaymentAmount = monthlYPaymentAmount;
	}
	@Override
	public String toString() {
		return "LoanData [owner=" + owner + ", VIN=" + VIN + ", model=" + model + ", principal=" + principal
				+ ", termInMonths=" + termInMonths + ", interestRate=" + interestRate + ", monthlyInterestRate="
				+ monthlyInterestRate + ", monthlYPaymentAmount=" + monthlYPaymentAmount + "]";
	}


}
