package com.cisco.blockchain.training;

public class TXInput {
	private String txId;
	private String senderId;
	private String txOutputId;
	
	public TXInput(String transctId, String outputId, String sender){
		this.setTxId(transctId);
		this.setSenderId(sender);
		this.setTxOutputId(outputId);
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getTxOutputId() {
		return txOutputId;
	}

	public void setTxOutputId(String txOutputId) {
		this.txOutputId = txOutputId;
	}
	

}
