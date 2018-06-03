package com.test.util;

import java.util.Scanner;

public class Main {
	
	byte by;
	short sh;
	boolean[] blA;
	String input;
	String output;
	Scanner reader;
	
	public Main() {
		reader = new Scanner (System.in);
		boolean proceed = true;
		while(proceed == true) {
			output = "";
			System.out.print("> ");
			input = reader.nextLine();
			try {
				by = Byte.valueOf(input);
			}
			catch(NumberFormatException ex) {
				proceed = false;
			}
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
			byte by2 = toByte(blA);
			System.out.println(by2);
		}
		reader.close();

	}
	
	public static void main(String[] args) {
		new Main();
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
