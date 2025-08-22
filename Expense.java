package expense.com;

import java.awt.DefaultFocusTraversalPolicy;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String date;
	private String category;
	private double amount;
	private String description;
	
	public Expense(String date, String category, double amount, String description) {
		this.date = date;
		this.category = category;
		this.amount = amount;
		this.description = description;
	}
	
	public String toString() {
		return date + " | " + category + " | " + amount + " | " + description;
		 
	}

public class ExpenseTrack {
	
	private static final String FILE_NAME = "expense.dat";
    private static List<Expense> expense = new ArrayList<>();
    
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		loadExpenses();
		
		 while (true) {
			 System.out.println("\n--- Expense Tracker ---");
			 System.out.println("1. Add Expense");
			 System.out.println("2. View Expenses");
			 System.out.println("3. Exit");
			 System.out.println("Choose: ");
			 int choice = sc.nextInt();
			 sc.nextLine();
			 
			 switch (choice) {
			 case 1 -> addExpense(sc);
			 case 2 -> viewExpenses();
			 case 3 -> {
				 saveExpenses();
				 System.out.println("Existing... Data saved");
				 return;
				
			 }
			 default -> System.out.println("Invalid choice!");
			 }
			
		    }
	}
    
   private static void addExpense(Scanner sc) {
	   System.out.println("Enter Date (dd-mm-yyyy): ");
	   String date = sc.nextLine();
	   
	   System.out.println("Enter Category: ");
	   String category = sc.nextLine();
	   
	   System.out.println("Enter Amount: ");
	   double amount = sc.nextDouble();
	   
	   sc.nextLine();
	   
	   System.out.println("Enter Description: ");
	   String description = sc.nextLine();
	   
	   expense.add(new Expense(date, category, amount, description));
	   System.out.println("Expense Added!");
}
   
   private static void viewExpenses() {
	   if (expense.isEmpty()) {
		   System.out.println("No Expenses found.");
	   }
	   else {
		   System.out.println("\n--- All Expenses ---");
		   for (Expense e : expense) {
			   System.out.println(e);
		   }
	   }
   }
   
   private static void saveExpenses() {
	   try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
		   ois.writeObject(expense);
	   }
	   catch (IOException e) {
		   e.printStackTrace();
	   }
   }
   
   private static void loadExpenses() {
	   try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
		   expense = (List<Expense>) ois.readObject();
	   }
	   catch (Exception e) {
		   expense = new ArrayList<>();
	   }
   }
	
}
}
