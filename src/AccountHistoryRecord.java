import java.time.LocalDate;

/**
 * History elements to store past transactions
 * @author Soma
 *
 */
public class AccountHistoryRecord {
	public AccountHistoryRecord(
			 int balanceAfter,
			 int amount,
			 Boolean isItDeposit,
			 LocalDate date
			) {
		this.balanceAfter = balanceAfter;
		this.amount = amount;
		this.isItDeposit = isItDeposit;
		this.date = date;
	}
	
	protected int balanceAfter;
	protected int amount;
	protected Boolean isItDeposit;
	protected LocalDate date;
	
	public int getBalanceAfter() {
		return balanceAfter;
	}
	public int getAmount() {
		return amount;
	}
	public Boolean getIsItDeposit() {
		return isItDeposit;
	}
	public LocalDate getDate() {
		return date;
	}
}
