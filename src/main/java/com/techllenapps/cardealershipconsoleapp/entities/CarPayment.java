package com.techllenapps.cardealershipconsoleapp.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class CarPayment implements Serializable{
	private static final long serialVersionUID = 8379661550286909422L;

	@Override
	public String toString() {
		return "CarPayment [loandata=" + loandata + ", montlyPaymentSchedule=" + montlyPaymentSchedule + "]";
	}
	LoanData loandata = new LoanData();
	private ArrayList<MonthlyPayment> montlyPaymentSchedule = new ArrayList<MonthlyPayment>(60);
	public LoanData getLoandata() {
		return loandata;
	}
	public void setLoandata(LoanData loandata) {
		this.loandata = loandata;
	}
	public ArrayList<MonthlyPayment> getMontlyPaymentSchedule() {
		return montlyPaymentSchedule;
	}
	public void setMontlyPaymentSchedule(ArrayList<MonthlyPayment> montlyPaymentSchedule) {
		this.montlyPaymentSchedule = montlyPaymentSchedule;
	}
	
}
