package com.test.util;

import java.util.Scanner;

public class Main {
	
	byte by;
	short sh;
	boolean proceed;
	boolean[] blA;
	String[] inputSts;
	String[] keySts;
	byte[] input;
	byte[] key;
	byte[] output;
	String outputSt;
	Scanner reader;
	
	public Main() {
		reader = new Scanner (System.in);
		proceed = true;
		while(proceed == true) {
			outputSt = "Output: \n";
			System.out.println("Input:");
			System.out.print("> ");
			inputSts = reader.nextLine().split(" ");
			if(inputSts[0].equals("q")) {
				proceed = false;
			}
			if(proceed == true) {
				input = new byte[inputSts.length];
				for(int i = 0; i < input.length; i++) {
					try {
						input[i] = Byte.parseByte(inputSts[i]);
					}
					catch(NumberFormatException ex) {
						input[i] = 0;
					}
				}
				System.out.println("Key: ");
				System.out.print("> ");
				keySts = reader.nextLine().split(" ");
				key = new byte[keySts.length];
				for(int i = 0; i < key.length; i++) {
					try {
						key[i] = Byte.parseByte(keySts[i]);
					}
					catch(NumberFormatException ex) {
						key[i] = 0;
					}
				}
				output = XOR(input, key);
				for(int i = 0; i < output.length; i++) {
					outputSt += output[i] + " ";
				}
				System.out.println(outputSt);
			}
			
		}
		System.out.println("Goodbye!");
		reader.close();

	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	//XOR Ciphers a string of bytes based on an inputted key
		public byte[] XOR(byte[] input, byte[] key) {
			byte[] output = new byte[input.length];
			boolean[] in8;								//(stores the relevant byte of the input as a boolean array)
			boolean[] key8;								//(stores the relevant byte of the key as a boolean array)
			boolean[] out8 = new boolean[8];			//(stores relevant byte of output array)
			for(int x = 0; x < input.length; x++) {		//for each byte of input:
				in8 = toBinary(input[x]);					//represent the input byte and the corresponding key byte in binary as boolean arrays
				key8 = toBinary(key[x%key.length]);
				for(int i = 0; i < in8.length; i++) {		//for each pair of corresponding bits in the key and input bytes:
					if(in8[i] ^ key8[i]) {						//if they are the same, store 1/true in corresponding bit of output byte
						out8[i] = true;
					}
					else {										//otherwise, store 0/false in corresponding bit of output byte
						out8[i] = false;
					}
				}
				output[x] = toByte(out8);						//convert output byte from boolean[] to byte and store in output array
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
		
		
		/*
		 * output = "";
			System.out.print("> ");
			input = reader.nextLine();
			try {
				by = Byte.valueOf(input);
			}
			catch(NumberFormatException ex) {
				proceed = false;
			}
			if(proceed == true) {
				blA = toBinary(by);
				for(int i = blA.length - 1; i > -1; i--) {
					if(blA[i] == true) {
						output += "1";
					}
					else {
						output += "0";
					}
				}
				System.out.println(output);
			}
			
			byte by2 = toByte(blA);
			System.out.println(by2);
		 */
}
