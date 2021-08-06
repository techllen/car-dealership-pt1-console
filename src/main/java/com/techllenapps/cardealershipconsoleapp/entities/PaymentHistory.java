package com.techllenapps.cardealershipconsoleapp.entities;

import java.io.Serializable;
import java.util.Date;

public class PaymentHistory implements Serializable{
	/**
	 * THE-Techllen the Allen
	 */
	private static final long serialVersionUID = 80520212L;
	private Date transactionDate;
	
	private double debit=0.0;
	private double credit=0.0;
	private double Balance=0.0;
	private double amountPaid=0.0;
	private double totalAmountPaid=0.0;
	private double remainingAmountToBePaid=0.0;
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
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
	@Override
	public String toString() {
		return "PaymentHistory [transactionDate=" + transactionDate + ", debit=" + debit + ", credit=" + credit
				+ ", Balance=" + Balance + ", amountPaid=" + amountPaid + ", totalAmountPaid=" + totalAmountPaid
				+ ", remainingAmountToBePaid=" + remainingAmountToBePaid + "]";
	}
	
}
