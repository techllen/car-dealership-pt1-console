package com.techllenapps.cardealershipconsoleapp.entities;

import java.io.Serializable;
import java.util.Date;

public class PaymentHistory2 implements Serializable{
	/**
	 * THE-Techllen the Allen
	 */
	private static final long serialVersionUID = 80520212L;
	private Date transactionDate;
	private double totalInterestToBePaid;
	private double totalPrincipalToBePaid;
	private double debit;
	private double credit;
	private double Balance;
	private double amountPaid;
	private double totalAmountPaid=0.0;
	private double remainingAmountToBePaid;
	private double totalLoanAmount;
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getTotalInterestToBePaid() {
		return totalInterestToBePaid;
	}
	public void setTotalInterestToBePaid(double totalInterestToBePaid) {
		this.totalInterestToBePaid = totalInterestToBePaid;
	}
	public double getTotalPrincipalToBePaid() {
		return totalPrincipalToBePaid;
	}
	public void setTotalPrincipalToBePaid(double totalPrincipalToBePaid) {
		this.totalPrincipalToBePaid = totalPrincipalToBePaid;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
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
		return "PaymentHistory2 [transactionDate=" + transactionDate + ", totalInterestToBePaid="
				+ totalInterestToBePaid + ", totalPrincipalToBePaid=" + totalPrincipalToBePaid + ", debit=" + debit
				+ ", credit=" + credit + ", Balance=" + Balance + ", amountPaid=" + amountPaid + ", totalAmountPaid="
				+ totalAmountPaid + ", remainingAmountToBePaid=" + remainingAmountToBePaid + ", totalLoanAmount="
				+ totalLoanAmount + "]";
	}
	
	
}
