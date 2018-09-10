import java.util.ArrayList;
import java.util.Random;

public class Bank {
	private static Bank bank;

	protected ArrayList<Account> accounts;
	
	private Bank() {
		accounts = new ArrayList<Account>();
	}	
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	static public Bank initBank() {
		if(bank == null) {
			bank = new Bank();
		}
		return bank;
	}
	
	public Account createAccount(int startBalance, String firstName, String lastName) {
		int id = createRandomId();
		Account account = new Account(startBalance, firstName, lastName, id); 
		accounts.add(account);
		return account;
	}
	
	/**
	 * creating random and unique Id for an account
	 * @return Id
	 */
	public int createRandomId() {
		boolean isIdUnique = false;
		Random rand = new Random();
		int id = 0;
		while(!isIdUnique) {
			id = rand.nextInt(1000) + 1;
			isIdUnique = true;
			for(int i = 0; i < this.accounts.size() && isIdUnique; i++) {
				if(accounts.get(i).GetId() == id) {
					isIdUnique = false;
				}
			}			
		}
		return id; 
	}

	/**
	 * initialization example data
	 */
	public static void initExampleAccounts() {
		bank.createAccount(1000, "Boy", "George");
		bank.createAccount(1000, "George", "Harrison");
		bank.createAccount(1000, "Chewbacca", "The Giant Wookie");		
	}
}
