public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome in the Bank");
		Bank bank = Bank.initBank();
		Bank.initExampleAccounts();
		Menu.mainMenu(bank);
		System.out.println("Good Bye");
	}		
}
