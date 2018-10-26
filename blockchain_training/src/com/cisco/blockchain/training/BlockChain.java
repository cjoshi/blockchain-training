package com.cisco.blockchain.training;

import java.util.ArrayList;

public class BlockChain {
	ArrayList<Block> blockchain = new ArrayList<Block>();
	
	public BlockChain(Transaction tx) {
		addBlock(tx);
	}

	public ArrayList<Block> getBlockchain() {
		return blockchain;
	}

	public void addBlock(Block newblock) {
		this.blockchain.add(newblock);
	}
	
	public void addBlock(ArrayList<Transaction> txList) {
		String prevHash = null;
		if(blockchain!=null && blockchain.size() > 0) {
		Block lastBlock = blockchain.get(blockchain.size()-1); 
		prevHash = lastBlock.getPrevBlockHash();
		}
		Block newblock = new Block(prevHash, txList);
		this.blockchain.add(newblock);
	}
	
	public Block addNewBlock(ArrayList<Transaction> txList) {
		String prevHash = null;
		if(blockchain.size() > 0) {
		Block lastBlock = blockchain.get(blockchain.size()-1); 
		prevHash = lastBlock.getPrevBlockHash();
		}
		Block newblock = new Block(prevHash, txList);
		return newblock;
	}
	
	public void addBlock(Transaction tx) {
		ArrayList<Transaction> txList = new ArrayList<Transaction>();
		txList.add(tx);
		addBlock(txList);
	}
	
	public boolean UnSpentTransactions(TXOutput txoutput) {
		for(Block b: blockchain) {
			if(b!=null) {
			
			for(Transaction tx: b.getTransactionList()) {
				for(TXInput txinput: tx.getTxInputList()) {
					if (txoutput.getUuid() == txinput.getTxOutputId()){
						return false;
					}
				}
			}
			}
		}
		return true;
	}
	
	public ArrayList<TXOutput> FindUTXO(){
		ArrayList<TXOutput> utxolist = new ArrayList<TXOutput>();
		for(Block b: blockchain) {
			if(b!=null) {
				for(Transaction tx: b.getTransactionList()) {
					for(TXOutput txoutput: tx.getTxOutputList()) {
						if(UnSpentTransactions(txoutput)) {
							utxolist.add(txoutput);
						}
					}
				}
			}
		}
		return utxolist;
	}
	
	public Block sendMoney(String sender, String recipient, int amount) {
		int total =0;
		boolean fundTransfer = false;
		ArrayList<TXInput> inputList = new ArrayList<TXInput>();
		ArrayList<TXOutput> outputList = new ArrayList<TXOutput>();
		ArrayList<TXOutput> utxolist = new ArrayList<TXOutput>();
		utxolist = FindUTXO();
		for(TXOutput tx: utxolist) {
			if(tx.getCoinRecipient().equals(sender)) {
				total = tx.getCoinValue();
				TXInput newinputtx = new TXInput(String.valueOf(Transaction.txCounter+1),tx.getUuid(),sender);
				inputList.add(newinputtx);
			}
			if(total>= amount) {
				fundTransfer = true;
				break;
			}
		}
		if(!fundTransfer) {
			return null;
		} else {
			TXOutput txouput = new TXOutput(recipient, amount);
			outputList.add(txouput);
			
			int change = total-amount;
			if(change>0) {
				TXOutput changetx = new TXOutput(sender, change);
				outputList.add(changetx);
			}
			Transaction tx = new Transaction(inputList,outputList);
			ArrayList<Transaction> newtx = new ArrayList<Transaction>();
			newtx.add(tx);
			
			Block addedBlock = addNewBlock(newtx);
			return addedBlock;
		}
	}

	public int getBalance(String sender) {
		int balance = 0;
		ArrayList<TXOutput> utxoList = FindUTXO();
		for(TXOutput tx: utxoList) {
			if(tx.getCoinRecipient() == sender) {
				balance = tx.getCoinValue();
			}
		}
		return balance;
	}
	
public static void main(String[] args) {
	    BlockChain bc = new BlockChain(Transaction.NewCoinbaseTX("Bob", "very cool"));

		Block block = bc.sendMoney("Bob", "Sally", 10);
		Block block2 = bc.sendMoney("Bob", "Fred", 2);
		if(block==null) {
			System.out.println("Not enough funds!!");
		}else {
			bc.addBlock(block);
		}
		bc.addBlock(block2);
		System.out.println("Bob: " + bc.getBalance("Bob"));
		System.out.println("Sally: " + bc.getBalance("Sally"));
		System.out.println("Fred: " + bc.getBalance("Fred"));

	}

}
