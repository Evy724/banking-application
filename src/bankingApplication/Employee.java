package bankingApplication;

import java.util.*;

public class Employee extends Utility {
	
	private static final String employeeUsername = "employee";	//Employee account info is hardcoded for simplicity
	private static final String employeePassword = "password";
	
	static boolean loggedIn = false;

	public void employeeLogin(Scanner employee) {				//The first screen shown when an employee chooses to log in
		System.out.println("Please enter employee username: ");
		employee.nextLine();
		String temporaryEmployeeUsername = employee.nextLine();
		System.out.println("Please enter employee password: \n");
		String temporaryEmployeePassword= employee.nextLine();
		
		if (temporaryEmployeeUsername.equalsIgnoreCase(employeeUsername) && temporaryEmployeePassword.equals(employeePassword)) {	//If the login credentials are corrent, the employee is logged in
			loggedIn = true;
			employeeMenu(employee);
		} else {
			System.out.println("Invalid credentials. ");
			System.out.println("*********************************\n" 
							 + "*********************************");
			employeeLogin(employee);
		}
		return;
	}
	
	public static void employeeMenu(Scanner employee) {		//The menu an employee sees when they log in
		do {
			System.out.println("Please choose an option: ");
			System.out.println("*********************************");
			System.out.println("1 - Approve Account");
			System.out.println("2 - Show Account Information");
			System.out.println("3 - Log out");
			System.out.println("*********************************");

			int input = employee.nextInt();		
			switch (input) {
			case 1: 
				approveAccount();
				break;
			case 2: 
				showAccountInfo();
				break;
			case 3:
				System.out.println("Thank you for working at Jones Bank.  Goodbyue! ");
				loggedIn = false;
			default: 
				System.out.println("Invalid input. Try again. ");
				break;
			}
		} while (loggedIn = true);
		return;
	}
	
	public static void approveAccount() {
		System.out.println("The following accounts need approval");
		System.out.println("************************");
		
		for (Customer customer: pendingAccounts) {
			System.out.println(customer.firstName + " " + customer.lastName + "- Username: "+ customer.userName);
		}
		
		System.out.println("************************");
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please enter the first name of the Primary account holder: ");
			String temporaryFirstName = scanner.nextLine();
			System.out.println("Please enter the last name of the Primary account holder: ");
			String temporaryLastName = scanner.nextLine();
			
			for(Customer customer: pendingAccounts) {
				System.out.println(". . .");
				
				if (customer.firstName.equals(temporaryFirstName) && customer.lastName.equals(temporaryLastName)) {
					System.out.println("Please asign a 6 digit account number: ");
					String temporaryAccountNumber = scanner.nextLine();
					
					if (customer.accountNumber.equals(temporaryAccountNumber)) {
						System.out.println("Account number is in use. ");
						System.out.println("Please asign a 6 digit account number: ");
						temporaryAccountNumber = scanner.nextLine();
						
						while(temporaryAccountNumber.length()!= 6) {
							System.out.println("Invalid input. ");
							System.out.println("Please asign a 6 digit account number: ");
							temporaryAccountNumber = scanner.nextLine();
					}		
						customer.accountNumber = temporaryAccountNumber;
						customer.approved = true;
						System.out.println("Account has been approved. ");
						Utility.pendingAccounts.remove(customer);
						Utility.customers.add(customer);
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
	public static void showAccountInfo() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please enter the first name of the account holder: ");
			String temporaryFirstName = scanner.nextLine();
			System.out.println("Please enter the last name of the account holder: ");
			String temporaryLastName = scanner.nextLine();
			
			for(Customer customer: customers) {
				System.out.println(". . .");
				
				if (customer.firstName.equals(temporaryFirstName) && customer.lastName.equals(temporaryLastName)) {
					System.out.println("*********************************");
					System.out.println("Last name: " + customer.firstName);
					System.out.println("Last name: " + customer.lastName);
					System.out.println("First name of Joint holder: " + customer.firstNameJoint);
					System.out.println("Last Name of Joint holder: " + customer.lastNameJoint);
					System.out.println("Account number: " + customer.accountNumber);
					System.out.println("Account balance: " + customer.balance);
					System.out.println("Account Approval status: " + customer.approved);
					System.out.println("Account username: " + customer.userName);
					System.out.println("**********************************");
					return;
				} else {
					System.out.println("Account info not found. ");
					return;
				}
			}
			return;
		}
	}
}