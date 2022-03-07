package bankingApplication;

import java.io.Serializable;
import java.util.*;

public class Customer extends Utility implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String accountNumber;
	
	public String firstName;
	public String lastName;
	
	public String firstNameJoint;
	public String lastNameJoint;
	
	public boolean approved = false;
	
	public double balance;
	
	public String userName;
	private String password;
	
	public boolean active = false;
	
	ArrayList<String> transactionHistory = new ArrayList<>();

	Customer (String name, String lastName, String userN, String password, double balance) {	//Constructor for Customer with personal account
		this.firstName = name;
		this.lastName = lastName;
		this.userName = userN;
		this.password = password;
		this.balance = balance;
	}
	Customer (String name, String lastName, String nameJoint, String lastNameJoint, String userName, String password, double balance) {	//Constructor for Customer with joint account
		this.firstName = name;
		this.lastName = lastName;
		this.firstNameJoint = nameJoint;
		this.lastNameJoint = lastNameJoint;
		this.userName = userName;
		this.password = password;
		this.balance = balance;	
	}
	Customer (String name, String lastName, String userN, String password, double balance, boolean approved, String accountNum) {	//Constructor for approved account
		this.firstName = name;
		this.lastName = lastName;
		this.userName = userN;
		this.password = password;
		this.balance = balance;
		this.approved = approved;
		this.accountNumber = accountNum;
	}
	
	public void showTransactionHistory() {
		for (String s: transactionHistory) {
			System.out.println(s);
		}
	}
	
	public void approve(String accountNum) {
		approved = true;
		this.accountNumber = accountNum;
	}
	
	public void withdraw(double amount) {
		if (approved && this.balance - amount > 0) {
			this.balance = this.balance - amount;
			System.out.println("Your new balance is: " + this.balance);
		} else {
			System.out.println("Insufficient funds. ");
		}
		return;
	}
	
	public void deposit(double amount) {
		if (approved) {
		this.balance = this.balance + amount;
		System.out.println("Your new balance is: " + this.balance);
		} else {
			System.out.println("Your account requires Employee approval. ");
		}
		return;
	}
	
	public String getPassword() {
		return this.password;
	}
}
