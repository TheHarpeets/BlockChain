package Blockchain;

import java.util.Date;

public class Block {
	
	public String hash;
	public Block previousHash;
	public double value; // our data will be a simple message.
	private long timeStamp; // as number of milliseconds since 1/1/1970.

	// Block Constructor.
	public Block(double cost, Block bloc) {
		this.value = cost;
		this.previousHash = bloc;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	// Initial Constructor
	public Block(double cost) {
		this.value = cost;
		this.previousHash = null;
		this.timeStamp = new Date().getTime();
		this.hash = calcInitialHash();
	}

	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(previousHash.hash + Long.toString(timeStamp) + value);
		return calculatedhash;
	}
	
	public String calcInitialHash() {
		String calculatedhash = StringUtil.applySha256("0" + Long.toString(timeStamp) + value);
		return calculatedhash;
	}
}
