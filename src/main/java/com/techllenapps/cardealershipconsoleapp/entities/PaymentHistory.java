package com.techllenapps.cardealershipconsoleapp.entities;

import java.io.Serializable;
import java.util.Date;

public class PaymentHistory implements Serializable{
	/**
	 * THE-Techllen the Allen
	 */
	private static final long serialVersionUID = 8052021L;
	private int month;
	private Date datePaid;
	private double monthlyInstallation;
	private double interestToBePaid;
	private double principalToBePaid;
	private double Balance;
	private double amountPaid;
	private double totalAmountPaid=0.0;
	private double remainingAmountToBePaid;
	private double totalLoanAmount;
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Date getDatePaid() {
		return datePaid;
	}
	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
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
	public double getTotalAmountPaid() {
		return totalAmountPaid;
	}
	public void setTotalAmountPaid(double totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}
	public double getRemainingAmountToBePaid() {
		return remainingAmountToBePaid;
	}
	public void setRemainingAmountToBePaid(double remainingAmountToBePaid) {
		this.remainingAmountToBePaid = remainingAmountToBePaid;
	}
	public double getTotalLoanAmount() {
		return totalLoanAmount;
	}
	public void setTotalLoanAmount(double totalLoanAmount) {
		this.totalLoanAmount = totalLoanAmount;
	}
	@Override
	public String toString() {
		return "PaymentHistory [month=" + month + ", datePaid=" + datePaid + ", monthlyInstallation="
				+ monthlyInstallation + ", interestToBePaid=" + interestToBePaid + ", principalToBePaid="
				+ principalToBePaid + ", Balance=" + Balance + ", amountPaid=" + amountPaid + ", totalAmountPaid="
				+ totalAmountPaid + ", remainingAmountToBePaid=" + remainingAmountToBePaid + ", totalLoanAmount="
				+ totalLoanAmount + "]";
	}
}
