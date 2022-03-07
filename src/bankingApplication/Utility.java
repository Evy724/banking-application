package bankingApplication;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Utility {
	
static Set<Customer> customers = new HashSet<Customer>();

static ArrayList<Customer> pendingAccounts = new ArrayList<>();

	public static void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream("./src/customers.ser");
			ObjectOutputStream  objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(customers);
			objectOut.close();
			fileOut.close();	
			
		} catch(IOException e) {
			System.out.println("Exception");
			e.printStackTrace();
		}	
	}

	@SuppressWarnings("unchecked")
	public static void deserialize() {
		try {
			FileInputStream fileIn = new FileInputStream("./src/customers.ser");
			ObjectInputStream  objectIn = new ObjectInputStream(fileIn);
			customers = (HashSet<Customer>) objectIn.readObject();
			objectIn.close();
			fileIn.close();	
			
		} catch (IOException e) {
			System.out.println("Exception");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e1) {
			System.out.println("Class not found Exception");
			e1.printStackTrace();
		}
	}
}