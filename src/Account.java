import java.time.LocalDate;
import java.util.ArrayList;

public class Account {
	public Account(int startBalance, String firstName, String lastName, int id) {
		this.history = new ArrayList<AccountHistoryRecord>();
		this.balance = startBalance;
		this.clientFirstName = firstName;
		this.clientLastName = lastName;
		this.clientID = id;
	}
	
	protected String clientFirstName;
	protected String clientLastName;
	protected int clientID;
	protected int balance;	
	public String getClientFirstName() {
		return clientFirstName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	protected ArrayList<AccountHistoryRecord> history;

	public int getBalance() {
		return balance;
	}
	
	public int GetId() {
		return clientID;
	}
	
	/**
	 * returns with the filtered list of historical transactions.
	 */
	public ArrayList<AccountHistoryRecord> getHistory(LocalDate startDate, LocalDate endDate, Boolean isItDeposit) {
		ArrayList<AccountHistoryRecord> filteredAccountHistory = new ArrayList<AccountHistoryRecord>();
		try {
			for(int i = 0; i < history.size(); i++) {
				if((endDate == null || history.get(i).getDate().isBefore(endDate)) 
					&& (startDate == null || history.get(i).getDate().isAfter(startDate)) 
				    && (isItDeposit == null || isItDeposit.equals(history.get(i).getIsItDeposit()))
					) {
					filteredAccountHistory.add(history.get(i));
				}
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return filteredAccountHistory;
	}

	public void withdrawal(int amount_withdrawal) {
		if(amount_withdrawal > 0) {
			this.balance -= amount_withdrawal;
			LocalDate today = LocalDate.now();
			this.history.add(new AccountHistoryRecord(
			 balance,
			 amount_withdrawal,
			 false,
			 today));
		} else {
			throw new IllegalArgumentException("negative values are not allowed in withdrawal processes");
		}
	} 
	
	public void deposit(int amount_deposit)  {
		if(amount_deposit > 0) {
			this.balance += amount_deposit;
			LocalDate today = LocalDate.now();
			this.history.add(new AccountHistoryRecord(
			 balance,
			 amount_deposit,
			 true,
			 today));
		} else {
			throw new IllegalArgumentException("negative values are not allowed in deposit processes");
		}
	} 		
}
