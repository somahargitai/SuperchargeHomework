import java.util.ArrayList;
import java.util.Date;

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
	
	public ArrayList<AccountHistoryRecord> getHistory(Date startDate, Date endDate, Boolean isItDeposit) {
		ArrayList<AccountHistoryRecord> filteredAccountHistory = new ArrayList<AccountHistoryRecord>();
		for(int i = 0; i < history.size(); i++) {
			if(
				(startDate == null || startDate.before(history.get(i).getDate()))
				&& (endDate == null || history.get(i).getDate().before(endDate)) 
				&& (isItDeposit == null || isItDeposit == history.get(i).getIsItDeposit())) {
				filteredAccountHistory.add(history.get(i));
			}
		}
		return filteredAccountHistory;
	}

	public void withdrawal(int amount_withdrawal) {
		if(balance > 0) {
			this.balance -= amount_withdrawal;
		} else {
			throw new IllegalArgumentException("negative values are not allowed in withdrawal processes");
		}
	} 
	
	public void deposit(int amount_deposit)  {
		if(balance > 0) {
			this.balance += amount_deposit;
		} else {
			throw new IllegalArgumentException("negative values are not allowed in deposit processes");
		}
	} 
	
	public void moneyTransfer(int targetUserId, int amount) {
		
	}
	
}
