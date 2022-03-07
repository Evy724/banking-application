package bankingApplication;

import java.util.*;

public class BankAppMain {
	
	public static void main(String[] args) {

		Utility.deserialize();
		Scanner scanner = new Scanner(System.in);
		
//		Customer evan = new Customer("evan", "jones", "evan123", 
//									 "jones123", 1000, true, "123456");
//		
//		Customer rick = new Customer("rick", "sanchez", "picklerick",
//									 "pr123", 5000, true, "987654");
//		Utility.customers.add(evan);
//		Utility.customers.add(rick);
//		
//		Utility.serialize();
		
		try {	
				System.out.println("***** Welcome to Jones Bank *****\n");
				System.out.println("Please choose an option: ");
				System.out.println("*********************************\n" 
								 + "*********************************\n");	
				System.out.println("1 - Log in to Existing Account \n" 
								 + "2 - Create Account \n"
								 + "3 - Employee Login \n"
								 + "4 - Admin Login \n"
								 + "5 - Exit \n");
				System.out.println("*********************************\n" 
								 + "*********************************\n");
				
				int input = scanner.nextInt();
				switch(input) {	
				
				case 1: 
					Login userLogin = new Login();
					userLogin.login();
					break;
				case 2:
					
					NewCustomer newCustomer = new NewCustomer();
					newCustomer.accountCreationClient(scanner);
					break;
				case 3:
					
					Employee employee = new Employee();
					employee.employeeLogin(scanner);
					break;
					
				case 4: 
					Admin.adminLogin(scanner);
					break;
					
				case 5:
					System.out.println("Thank you for choosing Jones Bank.  Goodbye! ");
					System.exit(0);
					
				default: System.out.println("Invalid input. ");

				}
			
		
		} catch(Exception e) {
			System.out.println(e.getMessage());	
		}		
	}
}