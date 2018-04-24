package Blockchain;

import java.util.Date;

public class actualChain {

	public static Block constructChain() {

		Block genBlock = new Block(15);
		// System.out.println("Hash for block 1 : " + genesisBlock.hash);

		return genBlock;
	}

	public static String calculateHash(long timeStamp, String previousHash, double value) {
		String calculatedhash = StringUtil.applySha256(previousHash + Long.toString(timeStamp) + value);
		return calculatedhash;
	}
	
	public static void main(String[] args) {

		Block genesisBlock = new Block(15);
		// System.out.println("Hash for block 1 : " + genesisBlock.hash);

		Block secondBlock = new Block(125, genesisBlock);
		// System.out.println("Hash for block 2 : " + secondBlock.hash);

		Block thirdBlock = new Block(12.5, secondBlock);
		// System.out.println("Hash for block 3 : " + thirdBlock.hash);

	}
	
	
}