class AccountData {
	// Member variables
	String owner;
	String type;
	int balance;
	boolean ownerSet = false;
	boolean typeSet = false;
	boolean balanceSet = false;
	
	// Member methods
	void setOwner(String name) {
		if (ownerSet == false) {
			owner = name;
			ownerSet = true;
		} else {
			System.out.println("ERROR: owner already set");
		}
	}

	void setBalance(int amount) {
		if (balanceSet == false) {
			if (amount > 0) {
				balance = amount;
				balanceSet = true;
			} else {
				System.out.println("ERROR: negative balance");
			}
		} else {
			System.out.println("ERROR: balance already set");
		}
	}
	
	int getBalance() {
		if (balanceSet == false) {
			System.out.println("ERROR: balance not set");
			return 0;
		} else {
			return balance;
		}
	}
	
	void print() {
		if (balanceSet == false || ownerSet == false) {
			System.out.println("ERROR: balance or owner not set yet");
		} else {
			System.out.println("Account owner: " + owner + ", balance: $" + getBalance());
		}
	}
} // end class AccountData


public class BankAccount {

	public static void main(String[] args) {
		AccountData stephsChecking = new AccountData();
		stephsChecking.setOwner("Steph");
		stephsChecking.setBalance(455);

		AccountData klaysChecking = new AccountData();
		klaysChecking.setOwner("Klay");
		
		stephsChecking.print();
		klaysChecking.print();
		klaysChecking.setBalance(377);
	}

} // end class BankAccount
