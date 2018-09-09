import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	/*
	 * Bevétel, kiadás
	 * lehessen másik felhasználónak küldeni pénzt
	 * Minden mûvelet legyen mentve historyba (date, amount, balance) (dátum, összeg, egyenleg)
	 * számlatörténetet lehessen kiiratni
	 * számlatörténetet lehessen szûrni, hogy befizetés vagy kiadás volt (deposit/withdrawal)
	 * számlatörténetet lehessen dátumra is szûrni
	 * 
	 * */
	
	public static void main(String[] args) {
		System.out.println("Welcome in the Bank");
		Bank bank = Bank.initBank();
		bank.initExampleAccounts();
		mainMenu(bank);
		
	}
	
	public static void mainMenu(Bank bank) {
		boolean exit = false;
		do {
			System.out.println("\n\nPlease choose your account or create one!");
			System.out.println("\n\n1 - Create new Account");
			System.out.println("\n\n2 - Choose my Account");
			System.out.println("\n\nexit - leave the Bank");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        String readline;
			try {
				readline = reader.readLine();
				
		        switch (readline) {
	                case "exit": exit = true;
     	            case "1": createMenu(bank);
	                case "2": accountMenu(bank);
	                default: System.out.println("\n\ninvalid message, please try again!");
	            }
			} catch (IOException e) {
				System.out.println("\n\nIOException occured on choosing option in Main Menu");
				e.printStackTrace();
				exit = true;
			}
		} while (!exit);
	}
	
	public static void createMenu(Bank bank) {
		System.out.println("\n\nA new customer! Welcome!");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readline;
        String lastName;
        String firstName;
        int startAmount = 0;
		try {
			do {
				System.out.println("\n\nPlease type your first name");
				firstName = reader.readLine();				
				if(firstName.length() < 1) {
					System.out.println("\n\nSorry, empty name field is not accepted");
				}
			} while (firstName.length() < 1);
			do {
				System.out.println("\n\nPlease type your last name");
				lastName = reader.readLine();		
				if(lastName.length() < 1) {
					System.out.println("\n\nSorry, empty name field is not accepted");
				}
			} while (lastName.length() < 1);
			do {
				System.out.println("\n\nPlease type the amount the money you would like to start with");
				readline = reader.readLine();		
			    try{
			        startAmount = Integer.parseInt(reader.readLine());
			    }catch(NumberFormatException nfe){
			        System.err.println("Invalid Format! You should type number");
			        startAmount = 0;
			    }
				if(startAmount < 1) {
					System.out.println("\n\nSorry, we cannot open your account with negative balance");
				}
			} while (lastName.length() < 1);
			bank.createAccount(startAmount, firstName, lastName);
			readline = reader.readLine();			

		} catch (IOException e) {
			System.out.println("\n\nIOException occured on choosing option in Create Account Menu");
			e.printStackTrace();
		}
	}	
	
	public static void accountMenu(Bank bank) {
		int id = 0;
		System.out.println("\n\nPlease choose your account by its id!");
		ArrayList<Account> accounts = bank.getAccounts();
		
		for(int accountCi = 0; accountCi < accounts.size(); accountCi++) {
			System.out.println("\n\n" + accounts.get(accountCi).GetId() + "  " + accounts.get(accountCi).getClientLastName() + ", " + accounts.get(accountCi).getClientFirstName());
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    boolean isValidId = false;
	    
		do {			
		    try{
		        id = Integer.parseInt(reader.readLine());
		    }catch(IOException e){
		        System.err.println("Invalid Format! You should type number");
		        id = 0;
		    }
			for(int accountCi = 0; accountCi < accounts.size() || isValidId; accountCi++) {
				if(id == accounts.get(accountCi).GetId()) {
					isValidId = true;
				}
			} 
			if(!isValidId) {
				System.out.println("\n\nSorry, no such user exists");
			}
		} while (!isValidId);
		
		actionMenu();
	}
	
	public static void actionMenu(Bank bank) {
		System.out.println("\n\nPlease choose an action");
		
	}
}
