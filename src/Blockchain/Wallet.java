package Blockchain;

import java.util.ArrayList;

public class Wallet {

	public double Id;
	public double address;
	public double balance; // our data will be a simple message.
	public ArrayList<Block> history = new ArrayList<Block>();

	// Block Constructor.
	public Wallet() {
		this.Id = Math.random() * 10000000;
		this.address = Math.random() * 10000000;
		this.balance = 0.0;		
	}

	public double changeBalance(double change, double bal) {
		if (bal + change < 0) {
			throw new ArithmeticException("balance cannot handle this transaction");
		} else {
			bal += change;
			return bal;
		}
	}

}
