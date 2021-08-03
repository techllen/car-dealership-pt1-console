package com.techllenapps.cardealershipconsoleapp.entities;

import java.io.Serializable;

public class MonthlyPayment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7207711772660728948L;
	private int month;
	private double monthlyInstallation;
	private double interestToBePaid;
	private double principalToBePaid;
	private double Balance;
	private double amountPaid;

	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getMonthlyInstallation() {
		return monthlyInstallation;
	}
	public void setMonthlyInstallation(double monthlyInstallation) {
		this.monthlyInstallation = monthlyInstallation;
	}
	public double getInterestToBePaid() {
		return interestToBePaid;
	}
	public void setInterestToBePaid(double interestToBePaid) {
		this.interestToBePaid = interestToBePaid;
	}
	public double getPrincipalToBePaid() {
		return principalToBePaid;
	}
	public void setPrincipalToBePaid(double principalToBePaid) {
		this.principalToBePaid = principalToBePaid;
	}
	public double getBalance() {
		return Balance;
	}
	public void setBalance(double balance) {
		Balance = balance;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	@Override
	public String toString() {
		return "MonthlyPayment [month=" + month + ", monthlyInstallation=" + monthlyInstallation + ", interestToBePaid="
				+ interestToBePaid + ", principalToBePaid=" + principalToBePaid + ", Balance=" + Balance
				+ ", amountPaid=" + amountPaid + "]";
	}
}
