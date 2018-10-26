package com.cisco.blockchain.training;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Block {
	private ArrayList<Transaction> transactionList;
	private String prevBlockHash;
	private String currentBlockHash;
	private int height;
	private String timestamp;
	private static int blockCounter = 0;
	
	public Block(String prevHash, ArrayList<Transaction> txList) {
		prevBlockHash = prevHash;
		this.transactionList = txList;
		timestamp = String.valueOf(new Date(Instant.now().toEpochMilli()));
		blockCounter++;
		if(prevHash !=null) {
		setCurrentBlockHash(BlockChainUtilities.setHash(prevBlockHash+timestamp));
		} else {
			setCurrentBlockHash(prevHash);
		}
		
	}

	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(ArrayList<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	public String getCurrentBlockHash() {
		return currentBlockHash;
	}

	public void setCurrentBlockHash(String currentBlockHash) {
		this.currentBlockHash = currentBlockHash;
	}

	public String getPrevBlockHash() {
		return prevBlockHash;
	}

	public void setPrevBlockHash(String prevBlockHash) {
		this.prevBlockHash = prevBlockHash;
	}
	

}
