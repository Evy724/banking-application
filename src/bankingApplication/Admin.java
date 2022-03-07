package bankingApplication;

import java.util.*;

public class Admin extends Utility {
	
	private static final String adminUsername = "admin";	//Admin account info is hardcoded for simplicity
	private static final String adminPassword = "password";

	String input;
	static boolean loggedIn = false;
	
	public static void adminLogin(Scanner s) {
		System.out.println("Please enter admin username: ");
		s.nextLine();
		String temporaryAdminUsername = s.nextLine();
		System.out.println("Please enter admin password: ");
		String temporaryAdminPassword= s.nextLine();
		
		if(temporaryAdminUsername.equalsIgnoreCase(adminUsername) && temporaryAdminPassword.equals(adminPassword)) {
			loggedIn = true;
			employeeMenu(s);
		} else {
			System.out.println("User name and passcode not correct try again");
			System.out.println("*********************************");
			System.out.println("*********************************");
			return;
		}
		return;
	}
	
	public static void employeeMenu(Scanner s) {
		do {
			System.out.println("Please select an option: ");
		
			System.out.println("*********************************");
			System.out.println("1 - Approve an Account");
			System.out.println("2 - Withdraw from an Account");	
			System.out.println("3 - Deposit into an Account");
			System.out.println("4 - Transfer Funds");
			System.out.println("5 - View Account Information");
			System.out.println("6 - Delete an Account");
			System.out.println("7 - Log out");
			System.out.println("*********************************");
			int input = s.nextInt();
		
			switch(input) {
			case 1: apprroveAccount();
				break;
			case 2:  withdraw();
				break;
			case 3 : deposit();
				break;
			case 4: transfer();
				break;
			case 5: showAccountInfo();
				break;
			case 6: deleteAccount();
				break;
			case 7:
				System.out.println("Thank you for working Jones Bank.  Goodbye!");
				loggedIn = false;
				return;
			default:
				System.out.println("Invalid input. "); 
				employeeMenu(s);
			} 
		} while (loggedIn = true);
		return;
	}
	
	public static void showAccountInfo() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please enter the first name of the account holder: ");
			String temporaryFirstName = scanner.nextLine();
			System.out.println("Please enter the last name of the account holder: ");
			String temporaryLastName = scanner.nextLine();
			
			for(Customer accounts: customers) {
				System.out.println("*********************************");
				if (accounts.firstName.equals(temporaryFirstName) && accounts.lastName.equals(temporaryLastName)) {
					System.out.println("First name: " + accounts.firstName);
					System.out.println("Last name: " + accounts.lastName);
					System.out.println("First name of Joint holder: " + accounts.firstNameJoint);
					System.out.println("Last Name of Joint holder: " + accounts.lastNameJoint);
					System.out.println("Account number: " + accounts.accountNumber);
					System.out.println("Account balance: " + accounts.balance);
					System.out.println("Account Approval status: " + accounts.approved);
					System.out.println("Account username: " + accounts.userName);
				} else {
					System.out.println("Account not found. ");
					return;
				}
			}
		}
	}
	
	public static void apprroveAccount() {
		System.out.println("The following accounts need approval: \n");
		System.out.println("*********************************");
		
		for (Customer accountPending: pendingAccounts) {
			System.out.println(accountPending.firstName + " " + accountPending.lastName + "- Username: " + accountPending.userName);
		}
		System.out.println("*********************************");

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please enter the first name of the account holder: ");
			String temporaryFirstName = scanner.nextLine();
			System.out.println("Please enter the last name of the account holder: ");
			String temporaryLastName = scanner.nextLine();
			
			for (Customer accounts: pendingAccounts) {
				System.out.println("*********************************");
				
				if (accounts.firstName.equals(temporaryFirstName) && accounts.lastName.equals(temporaryLastName)) {
					System.out.println("Please asign a 6 digit account number: ");
					String temporaryAccountNumber = scanner.nextLine();
					
					for(Customer accountPending: pendingAccounts) {
						
						while(accountPending.accountNumber.equals(temporaryAccountNumber)) {
							System.out.println("Account number is in use. ");
							System.out.println("Please asign a 6 digit account number: ");
							temporaryAccountNumber = scanner.nextLine();
						}
						
						while(temporaryAccountNumber.length()!= 6) {
							System.out.println("Invalid input. ");
							System.out.println("Please asign a 6 digit account number: ");
							temporaryAccountNumber = scanner.nextLine();
					}		
						accounts.accountNumber = temporaryAccountNumber;
						accounts.approved = true;
						System.out.println("Account has been approved. ");
						Utility.pendingAccounts.remove(accounts);
						Utility.customers.add(accounts);
						Utility.serialize();
						return;
					}
					
				} else {
					System.out.println("Account not found. ");
					return;
				}
			}
		}
	}
	
	public static void withdraw () {
		double temporaryBalance;
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please enter the account number for the account you want to withdraw from");
			String temporaryAccountNumber = scanner.nextLine();
			
			for(Customer withdrawAccount: customers) {
				
				if (temporaryAccountNumber.equals(withdrawAccount.accountNumber) && withdrawAccount.approved) {
					System.out.println("Please enter the amount to withdraw");
					double withdrawAmmount = scanner.nextDouble();
					temporaryBalance = withdrawAccount.balance-withdrawAmmount;
					
					if (temporaryBalance > 0) {
						
						if (withdrawAmmount > 0) {	
							withdrawAccount.balance= withdrawAccount.balance - withdrawAmmount;
							withdrawAccount.transactionHistory.add("Withdraw of :"+ String.valueOf(withdrawAmmount)+" Balance: "+ String.valueOf(withdrawAccount.balance));
							System.out.println("The new balance for account: " + withdrawAccount.accountNumber + " is: " +withdrawAccount.balance);
							Utility.serialize();
							return;
						} else {
							
							while(withdrawAmmount < 0) {
								System.out.println("Ammount can not be less than zero");
								System.out.println("Please enter amount to withdraw");
								withdrawAmmount = scanner.nextDouble();
								
								if(withdrawAmmount > 0) {
									withdrawAccount.balance= withdrawAccount.balance-withdrawAmmount;
									System.out.println("The new balance for account: " + withdrawAccount.accountNumber + " is: " +withdrawAccount.balance);
									Utility.serialize();
									return;
								}
							}
						}
					} else {
						System.out.println("Insufficient funds");
						withdraw();
					}
				} else {
			}	
			}
		}
		return;
	}
	
	public static void deposit () {
		try (Scanner deposit = new Scanner(System.in)) {
			System.out.println("Please enter account number of the recipient: ");
			String temporaryAccountNumber = deposit.nextLine();
			
			for(Customer account: customers) {
				
				if (temporaryAccountNumber.equals(account.accountNumber) && account.approved) {
					System.out.println("Please enter the amount you would like to deposit: ");
					double depositAmount = deposit.nextDouble();
					
					if(depositAmount > 0) {
						account.balance = account.balance + depositAmount;
						account.transactionHistory.add("Deposit: "+ String.valueOf(depositAmount)+" Balance: "+ String.valueOf(account.balance));
						System.out.println("The new balance for account: " + account.accountNumber + " is: " + account.balance);
						Utility.serialize();
						return;
						
					} else {
						while(depositAmount < 0) {
							System.out.println("Ammount can not be less than zero");
							System.out.println("Please enter amount to deposit");
							depositAmount = deposit.nextDouble();
							
							if(depositAmount > 0) {
							account.balance= account.balance+depositAmount;
							account.transactionHistory.add("Deposit: " + String.valueOf(depositAmount)  +" Balance: " + String.valueOf(account.balance));
							System.out.println("The new balance for account: " + account.accountNumber + " is: " + account.balance);
							Utility.serialize();
							return;
							}
						}
						return;
					}
				} else {
				}
			}
		}
		return;
	}
	public static void transfer() {
		
		boolean firstAccountFound = false;
		try (Scanner transfer = new Scanner(System.in)) {
			System.out.println("Please enter the account number from which you would like to transfer from: ");
			String temporaryAccountNumberFrom = transfer.nextLine();
			System.out.println("Please enter the account number of the recipient: ");
			String temporaryAccountNumberTo = transfer.nextLine();
			
			for(Customer transferFrom: customers) {	
				
				if (temporaryAccountNumberFrom.equals(transferFrom.accountNumber) && transferFrom.approved){
					firstAccountFound = true;
					
				} else {
					firstAccountFound=false;
				}
				
				if (firstAccountFound) {
					System.out.println("Please enter the amount you would like to transfer: ");
					double temporaryAmountFrom = Double.parseDouble(transfer.nextLine());
					double temporaryBalance = transferFrom.balance-temporaryAmountFrom;
					
					if(temporaryBalance > 0 ) {
						
						for(Customer transferTo: customers) {
							
							if (temporaryAccountNumberTo.equals(transferTo.accountNumber) && transferTo.approved) {
								transferFrom.balance = temporaryBalance;
								transferFrom.transactionHistory.add("Transfer from :" + String.valueOf(temporaryAmountFrom)+" Balance: "+ String.valueOf(transferFrom.balance));
								transferTo.balance = transferTo.balance+temporaryAmountFrom;
								transferTo.transactionHistory.add("Transfer to :" + String.valueOf(temporaryAmountFrom)+" Balance: "+ String.valueOf(transferTo.balance));
								System.out.println("The new balance for " + transferTo.firstName + " " + transferTo.lastName + "is: " + transferTo.balance);
								System.out.println("The new balance for " + transferFrom.firstName + " " + transferFrom.balance + "is: " + transferFrom.balance);						
								Utility.serialize();
								return;
								
							} else {
								System.out.println("Transaction failed. ");
								transfer();
							}
						}
						
					} else {
						System.out.println("Insufficient funds. ");
						transfer();
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		System.out.println("Transaction failed. ");
		transfer();
	}
	
	public static void deleteAccount() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please enter the first name of the account holder: ");
			String temporaryFirstName = scanner.nextLine();
			System.out.println("Please enter the last name of the account holder: ");
			String temporaryLastName = scanner.nextLine();
			
			for(Customer account: customers) {
				System.out.println(". . .");
				
				if (account.firstName.equals(temporaryFirstName) && account.lastName.equals(temporaryLastName)) {
					System.out.println("Please enter the account number: ");
					String temporaryAccountNumber = scanner.nextLine();
					
					if(temporaryAccountNumber.equals(account.accountNumber)) {
						customers.remove(account);
						System.out.println("The Account has been deleted. ");
						Utility.serialize();
					}
					return;
				}	
			}
		}
		System.out.println("Account Deletion failed. ");
	}
}