package com.cisco.blockchain.training;

import java.util.ArrayList;




public class Transaction {
	private String txId;
	private ArrayList<TXInput> txInputList;
	private ArrayList<TXOutput> txOutputList;
	public static int txCounter = 0;
	
	public Transaction(ArrayList<TXInput> txinput, ArrayList<TXOutput> txoutput) {
		this.txInputList = txinput;
		this.txOutputList = txoutput;
		this.txId = String.valueOf(txCounter++);
	}
	
	public static Transaction NewCoinbaseTX(String recipient, String msg) {
		
		TXInput newInput = new TXInput(String.valueOf(txCounter+1),msg,"");
		ArrayList<TXInput> newTxList = new ArrayList<TXInput>();
		newTxList.add(newInput);
		
		ArrayList<TXOutput> newOutputList = new ArrayList<TXOutput>();
		newOutputList.add(new TXOutput(recipient,100));
		Transaction newTx = new Transaction(newTxList,newOutputList);
		return newTx;
		
	}
	public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}
	public ArrayList<TXInput> getTxInputList() {
		return txInputList;
	}
	public void setTxInputList(ArrayList<TXInput> txInputList) {
		this.txInputList = txInputList;
	}
	public ArrayList<TXOutput> getTxOutputList() {
		return txOutputList;
	}
	public void setTxOutputList(ArrayList<TXOutput> txOutputList) {
		this.txOutputList = txOutputList;
	}
	

}
