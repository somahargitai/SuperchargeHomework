import java.util.Date;

public class AccountHistoryRecord {
	public AccountHistoryRecord(
			 int balanceAfter,
			 int amount,
			 Boolean isItDeposit,
			 Date date
			) {
		this.balanceAfter = balanceAfter;
		this.amount = amount;
		this.isItDeposit = isItDeposit;
		this.date = date;
	}
	
	protected int balanceAfter;
	protected int amount;
	protected Boolean isItDeposit;
	protected Date date;
	
	public int getBalanceAfter() {
		return balanceAfter;
	}
	public int getAmount() {
		return amount;
	}
	public Boolean getIsItDeposit() {
		return isItDeposit;
	}
	public Date getDate() {
		return date;
	}
}
