package com.cisco.blockchain.training;

import java.security.MessageDigest;
import java.util.ArrayList;

public class MerkleTree {
	ArrayList<String> elements = new ArrayList<String>();
	String root;
	
	public MerkleTree(ArrayList<String> merkleTree) {
		this.elements = merkleTree;
		this.root = "";
	}

	public ArrayList<String> getNextMerkleRow(ArrayList<String> list) {
		int index = 0;
		ArrayList<String> nextRow = new ArrayList<String>();
		
		while(index <list.size())  {
			String left = list.get(index);
			String right  ="";
			if(index < list.size()-1) {
				right = list.get(index+1);
			}else {
				right = list.get(index);
			}
			String combineString  = left+right;
			//System.out.println(index);
			nextRow.add(getSHA2HexValue(combineString));
			index++;
		}
		
		return nextRow;
	}

	public String getSHA2HexValue(String str) {
		byte[] cipher_byte;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			cipher_byte = md.digest();
			StringBuilder sb = new StringBuilder(2* cipher_byte.length);
			for(byte b: cipher_byte) {
				sb.append(String.format("%02x", b&0xff));
			}
			return sb.toString();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void Merkle_Tree() {
		ArrayList<String> tempTxList = new ArrayList<String>();
		for(String element: elements) {
			tempTxList.add(element);
		}
		ArrayList<String> newTxList =  getNextMerkleRow(tempTxList);
		int index  = newTxList.size();
		while(index != 0) {
			 newTxList =  getNextMerkleRow(tempTxList);
			 index--;
		}
		root = newTxList.get(0);
	}


	public static void main(String[] args) {
		
		ArrayList<String> tempTxList = new ArrayList<String>();
		tempTxList.add("a");
		tempTxList.add("b");
		tempTxList.add("c");
		tempTxList.add("d");
		tempTxList.add("e");
		
		MerkleTree merkleTree = new MerkleTree(tempTxList);
		merkleTree.Merkle_Tree();
		System.out.println("root:  " + merkleTree.getRoot());
		

	}

	public String getRoot() {
		return root;
	}

}
