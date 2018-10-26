package com.cisco.blockchain.training;

import java.security.MessageDigest;

public class BlockChainUtilities {
	
	public static String getHashToHexString(byte[] cipher_byte) {
		
		try {
			/*MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(cipher_byte);
			cipher_byte = md.digest();*/
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
	
	public static String setHash(String str) {
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
	
	

}
