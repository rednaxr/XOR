package com.xor.ci;

public class Cipher {

	public Cipher() {
		
	}
	
	public byte[] XOR(byte[] input, byte[] key) {
		byte[] output = new byte[input.length];
		for(int x = 0; x < input.length; x++) {
			output[x] = key[x%key.length];
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
	
	//converts a byte to a boolean array of length 8
	private boolean[] toBinary(byte input) {
		short sh = (short)(input + 128);						//convert byte of range -128 - 127 into short of range 0 255
		System.out.println(sh);
		boolean[] output = new boolean[8];
		for(byte n = 0; n < output.length; n++) {			//for each power of two from 0 to 7:4
			if(sh%2 == 1) {
				output[n] = true;		//if byte contains a 2^n, put a 1/true in the nth index from the back
			}
			else {
				output[n] = false;
			}
			sh /= 2;											//divide by 2 to check for the 2^(n+1) on the next pass
		}
		return output;										//return boolean array that represents byte in a binary fashion
	}
	
	//converts a boolean array back to a byte
	private byte toByte(boolean[] input) {
		short sh = 0;										//stores value from 0-255
		for(int i = 0; i < input.length; i++) {				//for each power of two from 0 to 7
			if(input[i] == true) {							//if the binary number (represented by boolean array) contains it, add it to the decimal short
				sh += Math.pow(2, i);
			}
		}
		byte output = (byte)(sh - 128);						//store short of range 0 - 255 in byte of range -128 - 127 and return
		return output;
	}
	
}
