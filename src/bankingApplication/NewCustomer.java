package bankingApplication;

import java.util.*;

public class NewCustomer extends Utility {
	
	public String firstName;
	public String lastName;
	
	public String firstNameJoint;
	public String lastNameJoint;
	
	public String userName;
	private String password;
	
	private double balance;
	
	public void accountCreationClient(Scanner s) {
		boolean existingAccount = false;
		System.out.println("******* Account Creation ********\n" 
						 + "*********************************");
		System.out.println("Please enter your first name: ");
		s.nextLine();
		String temporaryFirstName = s.nextLine();
		
		System.out.println("Please enter your last name: ");
		String temporaryLastName= s.nextLine();
		
		System.out.println("Would you like to make a Personal or Joint Account? \n"
						 + "1 - Personal \n" 
						 + "2 - Joint ");
		
		int input = s.nextInt();
		if (input == 1 ) {
			System.out.println("Please enter a login username: ");
			s.nextLine();
			String temporaryUserName = s.nextLine();
			
			for(Customer customer: customers) {
				if(customer.userName.equals(temporaryUserName)) 
					existingAccount = true;	
			
				else if(!existingAccount) {
					System.out.println("Please enter a password: ");
					String temporaryPassword = s.nextLine();
			
					System.out.println("Please enter an initial deposit ammount: ");
					this.balance= Integer.parseInt(s.nextLine());
					
					System.out.println("Thank you for your application. \n" 
									 + "Your account requires Employee approval. ");
					
					this.firstName = temporaryFirstName;
					this.lastName = temporaryLastName;
				
					this.userName = temporaryUserName;
					this.password = temporaryPassword;
					
					createAccount();
				} else {
					System.out.println("An account already exists under this name. ");
				}
			}
		} else if (input == 2) {
				System.out.println("Please enter the Joint Account holder's first name: ");
				s.nextLine();
				String temporaryFirstNameJoint = s.nextLine();
				System.out.println("Please enter the Joint Account holder's last name: ");
				String temporaryLastNameJoint= s.nextLine();
				System.out.println("Please enter a login username for the Joint Account: ");
				String temporaryUserNameJoint = s.nextLine();
					
				for(Customer customer: customers) {
					if(customer.userName.equals(temporaryUserNameJoint)) {
						existingAccount=true;
					}
				}
				if(!existingAccount) {
					System.out.println("Please enter a password: ");
					String temporaryPassword = s.nextLine();
					System.out.println("Please enter an initial deposit ammount: ");
					this.balance= Integer.parseInt(s.nextLine());
					System.out.println("Thank you for your application. \n" 
									 + "Your account requires Admin approval. \n");
						
					this.firstName= temporaryFirstName;
					this.lastName= temporaryLastName;
					
					this.firstNameJoint=temporaryFirstNameJoint;
					this.lastNameJoint=temporaryLastNameJoint;
					
					this.userName=temporaryUserNameJoint;
					this.password=temporaryPassword;
						
					createJointAccount();
					} else {
						System.out.println("This account already exists. ");
					}
		}
	}
	
	public void createAccount() {
		Customer customer = new Customer(this.firstName, this.lastName, this.userName, this.password, this.balance);
		pendingAccounts.add(customer);
		Utility.serialize();
	}
	
	public void createJointAccount() {
		Customer customer = new Customer(this.firstName, this.lastName, this.firstNameJoint, this.lastNameJoint, this.userName, this.password, this.balance);
		pendingAccounts.add(customer);
		Utility.serialize();
	}
}