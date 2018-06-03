package com.xor.ci;

public class Cipher {

	public Cipher() {
		
	}
	
	public byte[] XOR(byte[] input, byte[] key) {
		byte[] output = new byte[input.length];
		for(int x = 0; x < input.length; x++) {
			
		}
		return output;
	}
	
	public byte[] newRandomKey() {
		byte[] output = new byte[8];
		for(int i = 0; i < output.length; i++) {
			output[i] = (byte)(Math.random()*256);
		}
		return output;
	}
	
}
