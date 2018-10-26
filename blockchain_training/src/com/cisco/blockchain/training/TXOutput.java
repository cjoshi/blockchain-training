package com.cisco.blockchain.training;

public class TXOutput {
	
	private int coinValue;
	private String coinRecipient;
	private String uuid;
	private static int TxOutputCounter = 0;
	
	public TXOutput(String recipient, int value) {
		this.coinRecipient = recipient;
		this.coinValue = value;
		TxOutputCounter++;
		this.uuid = String.valueOf(TxOutputCounter);
	}
	public int getCoinValue() {
		return coinValue;
	}
	public void setCoinValue(int coinValue) {
		this.coinValue = coinValue;
	}
	public String getCoinRecipient() {
		return coinRecipient;
	}
	public void setCoinRecipient(String coinRecipient) {
		this.coinRecipient = coinRecipient;
	}
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public static int getTxOutputCounter() {
		return TxOutputCounter;
	}
	
	public boolean CanUnlockOutputWith(String recipientId) {
		if(recipientId.equals(coinRecipient)) {
			return true;
		}
		return false;
	}

}
