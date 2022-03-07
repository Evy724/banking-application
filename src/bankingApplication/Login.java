package bankingApplication;

import java.util.*;

public class Login extends Utility {
	
	Scanner scanner = new Scanner(System.in);
	String temporaryUsername;
	String temporaryPassword;
	boolean loggedIn = false;
	
	public void login() {
		
		System.out.println("****************************");
		System.out.println("Please enter your username: ");
		temporaryUsername = scanner.nextLine();
		System.out.println("Please enter your password: ");
		temporaryPassword = scanner.nextLine();
		
		for(Customer customer: customers) {
			
			if (temporaryUsername.equalsIgnoreCase(customer.userName) && temporaryPassword.equals(customer.getPassword())) {
				
				loggedIn = true;
				if(customer.approved) {
					
					do {
						System.out.println("Please choose an option: ");
						System.out.println("*********************************\n"
										 + "*********************************");
						System.out.println("1 - Transaction History \n"
					  					 + "2 - Withdraw \n"
					 					 + "3 - Deposit \n"
					 					 + "4 - Account Info \n"
					 					 + "5 - Transfer \n"
					 					 + "6 - Log out");
						System.out.println("*********************************\n"
									 	 + "*********************************\n");
				
						int input = scanner.nextInt();		
						switch(input) {
						
						case 1:
							customer.showTransactionHistory();
							break;
							
						case 2:
							System.out.println("Please enter the amount you would like to withdraw: ");
							double withdrawAmount= scanner.nextDouble();
						
							if(withdrawAmount > 0) {
								customer.withdraw(withdrawAmount);
								Utility.serialize();
							
							} else {	
							
								while(withdrawAmount < 0) {								
									System.out.println("Ammount cannot be less than zero. ");
									System.out.println("Please enter the amount you would like to withdraw: ");
									double temporaryBalance = customer.balance-withdrawAmount;
									withdrawAmount = scanner.nextDouble();
								
									if(withdrawAmount > 0) {
										customer.withdraw(withdrawAmount);
										customer.transactionHistory.add("Withrawal of: " + String.valueOf(withdrawAmount) + " Balance: "+ String.valueOf(temporaryBalance));
										Utility.serialize();
									}
								}
							}
							break;
							
						case 3 :  
							System.out.println("Please enter the amount you would like to deposit: ");
							double depositAmount = scanner.nextDouble();
						
							if(depositAmount > 0) {
								customer.deposit(depositAmount);
								Utility.serialize();
							
							} else {
							
								while(depositAmount < 0) {
									System.out.println("Ammount can not be less than zero");
									System.out.println("Please enter deposit ammount");
									depositAmount= scanner.nextDouble();
									double temporaryBalance = customer.balance + depositAmount;
								
									if(depositAmount > 0) {
										customer.deposit(depositAmount);
										customer.transactionHistory.add("Deposit: "+ String.valueOf(depositAmount) + "Balance: "+ String.valueOf(temporaryBalance));
										Utility.serialize();
									}
								}
							}
							break;
							
						case 4 :
							System.out.println("*********************************\n");
							System.out.println("First Name: " + customer.firstName);
							System.out.println("Last name: " + customer.lastName);
							System.out.println("First name of Joint holder: " + customer.firstNameJoint);
							System.out.println("Last name of Joint holder: " + customer.lastNameJoint);
							System.out.println("Account number: " + customer.accountNumber);
							System.out.println("Account balance: " + customer.balance);
							System.out.println("Account Approval status: " + customer.approved);
							System.out.println("Account username: " + customer.userName);
							System.out.println("*********************************\n");
							break;
							
						case 5 :
							System.out.println("Please enter the account number you would like to transfer to: ");
							String temporaryAccountNumberTo = scanner.nextLine();
							System.out.println("Please enter amount you would like to transfer: ");
							double transferAmount= Double.parseDouble(scanner.nextLine());
						
							if(transferAmount > 0) {
								double temporaryBalance = customer.balance - transferAmount;
							
								if(temporaryBalance > 0) {
									for(Customer transferTo: customers) {
										if (temporaryAccountNumberTo.equals(transferTo.accountNumber) && transferTo.approved) {
											customer.balance=temporaryBalance;
											customer.transactionHistory.add("Transfer from:"+ String.valueOf(transferAmount)+" Balance: "+ String.valueOf(customer.balance));
											transferTo.balance = transferTo.balance+transferAmount;
											transferTo.transactionHistory.add("Transfer to:"+ String.valueOf(transferAmount)+" Balance: "+ String.valueOf(transferTo.balance));
											System.out.println("The new balance for " + transferTo.firstName + " " + transferTo.lastName + " is: " + transferTo.balance);
											System.out.println("The new balance for " + customer.firstName + " " + customer.lastName + "is: " + customer.balance)  ;
											Utility.serialize();
										}	
									}
									System.out.println("Account not found. ");
								
								}
								System.out.println("Insuficient funds. ");
							
							} else {
								System.out.println("Ammount cannot be zero. ");
								login();
							}
						case 6 :
							System.out.println("Thank you for choosing Jones Bank.  Goodbye! ");
							loggedIn = false;
							return;
							
						default: 
							System.out.println("Invalid input. ");
							login();
							break;
						}
					} while (loggedIn = true);
				
				} else {
					System.out.println("Account is pending Approval. ");
					return;
				}		
			}
			
		}
		System.out.println("Account not found. ");
		return;
	}

}
