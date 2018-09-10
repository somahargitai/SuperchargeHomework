import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Menu {

	/**
	 * main menu for bank application
	 * @param bank
	 */
	public static void mainMenu(Bank bank) {
		boolean exit = false;
		do {
			System.out.println("\nPlease choose your account or create one!");
			System.out.println("\n1 - Create new Account");
			System.out.println("\n2 - Choose my Account");
			System.out.println("\nexit - leave the Bank");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        String readline;
			try {
				readline = reader.readLine().trim();
		        if(readline.equals("1")) {
		        	createMenu(bank);
		        } else if(readline.equals("2")) {
		        	accountMenu(bank);
		        } else if(readline.equals("exit")) {
		        	exit = true;
		        } else {
		        	System.out.println("\ninvalid message, please try again!");
		        }
			} catch (IOException e) {
				System.out.println("\nIOException occured on choosing option in Main Menu");
				e.printStackTrace();
				exit = true;
			}
		} while (!exit);
	}
	
	/**
	 * creating new account in the bank
	 * @param bank
	 */
	public static void createMenu(Bank bank) {
		System.out.println("\nA new customer! Welcome!");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String lastName;
        String firstName;
        int startAmount = 0;
		try {
			do {
				System.out.println("\nPlease type your first name");
				firstName = reader.readLine();				
				if(firstName.length() < 1) {
					System.out.println("\nSorry, empty name field is not accepted");
				}
			} while (firstName.length() < 1);
			do {
				System.out.println("\nPlease type your last name");
				lastName = reader.readLine();		
				if(lastName.length() < 1) {
					System.out.println("\nSorry, empty name field is not accepted");
				}
			} while (lastName.length() < 1);
			do {
				System.out.println("\nPlease type the amount the money you would like to start with");
			    try{
			        startAmount = Integer.parseInt(reader.readLine());
			    }catch(NumberFormatException nfe){
			        System.err.println("Invalid Format! You should type number");
			        startAmount = 0;
			    }
				if(startAmount < 1) {
					System.out.println("\nSorry, we cannot open your account with negative balance");
				}
			} while (startAmount < 1);
			Account account = bank.createAccount(startAmount, firstName, lastName);
			accountActionMenu(bank, account);
		} catch (IOException e) {
			System.out.println("\nIOException occured on choosing option in Create Account Menu");
			e.printStackTrace();
		}
	}	
	
	/**
	 * choose account from account list
	 * @param bank
	 */
	public static void accountMenu(Bank bank) {
		System.out.println("\nPlease choose your account!");
		printAccounts(bank);
		Account account = getAccountById(bank);
		accountActionMenu(bank, account);
	}
	

	/**
	 * find account in the bank
	 * @return selected account
	 */
	public static Account getAccountById(Bank bank) {
		System.out.println("\n\nType the id or type 'undo' to exit: ");
		Account account = null;;

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    boolean isValidId = false;

		int id = 0;
		ArrayList<Account> accounts = bank.getAccounts();
		
		do {			
		    try{
		    	String input = reader.readLine().trim();
		    	if(input.equals("undo")) return account;
		    	
		        id = Integer.parseInt(input);
		    }catch(IOException e){
		        System.err.println("Invalid Format! You should type a number or 'undo'");
		        id = 0;
		    }
			for(int accountCi = 0; accountCi < accounts.size() && !isValidId; accountCi++) {
				if(id == accounts.get(accountCi).GetId()) {
					isValidId = true;
					account =  accounts.get(accountCi);
				}
			} 
			if(!isValidId) {
				System.out.println("\n\nSorry, no such user exists");
			}
		} while (!isValidId);
		return account;
	}
	
	/**
	 * menu for account operations - deposit, withdrawal, transfer, history
	 * @param bank
	 * @param account
	 */
	public static void accountActionMenu(Bank bank, Account account) 
	{
		System.out.println("\nPlease choose an action");
		boolean exit = false;
		do {
			System.out.println("\nWhat do you want to do?!");
			System.out.println("\n1 - deposit");
			System.out.println("\n2 - withdrawal");
			System.out.println("\n3 - transfer to another account");
			System.out.println("\n4 - print history");
			System.out.println("\nexit - go back to the Main Menu");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        String readline;
			try {
				readline = reader.readLine();

				if(readline.equals("1")) {
					deposit(bank, account);
				} else if(readline.equals("2"))  {
					withdrawal(bank, account);
				} else if(readline.equals("3"))  {
                	transfer(bank, account);
				} else if(readline.equals("4"))  {
					printHistory(bank, account);
				} else if(readline.equals("exit"))  {
                    exit = true;					
				} else {
					System.out.println("\ninvalid message, please try again!");
				}
		        System.out.println("\nDone! Nice to meet you");
			} catch (IOException e) {
				System.out.println("\nIOException occured on choosing option in Account Actions Menu");
				e.printStackTrace();
				exit = true;
			}
		} while (!exit);
	}

	/**
	 * place money on your account
	 * @param bank
	 * @param account
	 */
	public static void deposit(Bank bank, Account account)	
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int amount;
		do {
			System.out.println("\nPlease type the amount of money to deposit");
		    try{
		    	String readline = reader.readLine().trim();
		        amount = Integer.parseInt(readline);
		    }catch(IOException e){
		        System.err.println("\nInvalid Format! You should type number");
		        amount = 0;
		    }		    
			if(amount < 1) {
				System.out.println("\nSorry, negative value is not accepted");
			}
		} while (amount < 1);
		account.deposit(amount);
		System.out.println("\n deposit placed");
	}
	
	/**
	 * withdraw money from your account
	 * @param bank
	 * @param account
	 */
	public static void withdrawal(Bank bank, Account account) 
	{		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int amount;
		do {
			System.out.println("\nPlease type the amount of money to withdrawal");
		    try{
		        amount = Integer.parseInt(reader.readLine().trim());
		    }catch(IOException e){
		        System.err.println("\nInvalid Format! You should type number");
		        amount = 0;
		    }		    
			if(amount < 1) {
				System.out.println("\nSorry, negative value is not accepted");
			}
		} while (amount < 1);
		account.withdrawal(amount);
		System.out.println("\n withdrawal done");		
	}
	
	/**
	 * transfer between actual and another accounts
	 * @param bank
	 * @param account
	 */
	public static void transfer(Bank bank, Account account) 
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int amount;
		do {
			System.out.println("\nPlease type the amount of money to transfer");
		    try{
		        amount = Integer.parseInt(reader.readLine().trim());
		    }catch(IOException e){
		        System.err.println("\nInvalid Format! You should type number");
		        amount = 0;
		    }		    
			if(amount < 1) {
				System.out.println("\nSorry, negative value is not accepted");
			}
		} while (amount < 1);

		System.out.println("\nPlease type the amount of money to transfer");
		System.out.println("\nPlease choose target account!");
		printAccounts(bank);
		Account targetAccount = getAccountById(bank);
		
		targetAccount.deposit(amount);
		account.withdrawal(amount);
		System.out.println("\n Transfer done");
	}
	
	/**
	 * print history of the given account, using date range and deposit filters
	 * @param bank
	 * @param account
	 */
	public static void printHistory(Bank bank, Account account) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		LocalDate startDate = null;
		LocalDate endDate = null;
		Boolean isDeposit = false;
		Boolean isValid = false;
		
		System.out.println("\nYou can limit your history print. If you don't want to apply a filter, simply press Enter");
		
		do {			
			System.out.println("\nType the start date you would like to print your history from (YYYY-MM-DD)");
			try {
				String input = reader.readLine();
				if(input.equals("")) {
					startDate = null;
					isValid = true;
				} else {					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					startDate = LocalDate.parse(input, formatter);
					isValid = true;
				}
			}catch(IOException e) {
		        System.err.println("Invalid Format! You should type a date.");
			}
		} while (!isValid);
		
		isValid = false;
		do {			
			System.out.println("\nType the end date you would like to print your history to (YYYY-MM-DD)");
			try {
				String input = reader.readLine().trim();
				if(input.equals("")){
					endDate = null;
					isValid = true;
				} else {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
					endDate = LocalDate.parse(input, formatter);
					isValid = true;
				}
			}catch(IOException e) {
		        System.err.println("Invalid Format! You should type a date.");
			}
		} while (!isValid);
		
		isValid = false;
		do {			
			System.out.println("\nType '1' to print deposits only or '2' to filter withdrawals");
			try {
				String input = reader.readLine().trim();
				if (input.equals("1")) {
					isValid = true;
					isDeposit = true;					
				} else if (input.equals("2")) {
					isValid = true;
					isDeposit = false;
				} else if (input.trim().equals("")) {
					isDeposit = null;
					isValid = true;
				} else {
					isValid = false;	
				}
			}catch(IOException e) {
		        System.err.println("Invalid Format! You should type 1, 2 or press Enter.");
			}
		} while (!isValid);
		
		ArrayList<AccountHistoryRecord> history = account.getHistory(startDate, endDate, isDeposit);
		
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.printf( "%-15s %15s %30s %30s", "amount", "balance after", "date", "+ / -");
		for(int historyCi = 0; historyCi < history.size(); historyCi++) {
			System.out.println("\n");
			System.out.printf( "%-15s %15s %30s %30s", 
					history.get(historyCi).getAmount(), 
					history.get(historyCi).getBalanceAfter(),
					history.get(historyCi).getDate(),
					history.get(historyCi).getIsItDeposit() ? "deposit" : "withdraw");
		}
		System.out.println("\n---------------------------------------------------------------------------------------------");
		

	}
	
	/**
	 * print all available accounts
	 * @param bank
	 */
	public static void printAccounts(Bank bank) {
		ArrayList<Account> accounts = bank.getAccounts();		
		for(int accountCi = 0; accountCi < accounts.size(); accountCi++) {
			System.out.println("\n" 
					+ accounts.get(accountCi).GetId() + "  " 
					+ accounts.get(accountCi).getClientLastName() + ", " 
					+ accounts.get(accountCi).getClientFirstName());
		}
		
	}
}
