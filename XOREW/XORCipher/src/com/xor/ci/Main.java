package com.xor.ci;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main implements ActionListener {
	
	//Global Variables
	byte[] input;
	byte[] output;
	byte[] key;
	boolean proceed;
	
	//Initiate File Reading & Cipher Classes
	PaperWork paperwork = new PaperWork();
	Cipher cipher = new Cipher();
	
	//Graphical Components
	JFrame window = new JFrame("XOR Cipher");
	JButton encryptBtn = new JButton("ENCRYPT");
	JButton decryptBtn = new JButton("DECRYPT");
	JLabel inputFileLbl = new JLabel("Input File:");
	JLabel outputFileLbl = new JLabel("Output File:");
	JLabel keyFileLbl = new JLabel("Key File:");
	JLabel statusLbl = new JLabel("");
	JTextField inputFileTF = new JTextField();
	JTextField outputFileTF = new JTextField();
	JTextField keyFileTF = new JTextField();
	
	
	public Main() {
		
		//Set up window
		window.setSize(400, 140);
		window.setLocation(100, 100);
		window.setLayout(new GridLayout(5, 2));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		//Set up components & to window
		window.add(inputFileLbl);
		inputFileLbl.setHorizontalAlignment(JLabel.CENTER);
		window.add(inputFileTF);
		window.add(outputFileLbl);
		outputFileLbl.setHorizontalAlignment(JLabel.CENTER);
		window.add(outputFileTF);
		window.add(keyFileLbl);
		keyFileLbl.setHorizontalAlignment(JLabel.CENTER);
		window.add(keyFileTF);
		window.add(encryptBtn);
		encryptBtn.addActionListener(this);
		window.add(decryptBtn);
		decryptBtn.addActionListener(this);
		window.add(statusLbl);
		statusLbl.setHorizontalAlignment(JLabel.CENTER);
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

	public void actionPerformed(ActionEvent ae) {
		proceed = true;														//Do not proceed if any files are invalid (scanner cannot  be created)
		Scanner inputfileTxtReader = null;
		Scanner outputfileTxtReader = null;
		Scanner keyfileTxtReader = null;
		try {
			inputfileTxtReader = new Scanner(new File(inputFileTF.getText()));
			outputfileTxtReader = new Scanner(new File(inputFileTF.getText()));
			keyfileTxtReader = new Scanner(new File(inputFileTF.getText()));
		}
		catch(FileNotFoundException ex) {
			proceed = false;
			statusLbl.setText("Invalid File(s)");
		}
		
		input = paperwork.readFile(inputFileTF.getText());					//read in input file
		
		if(proceed == true) {
			if(ae.getSource().equals(encryptBtn)) {							//If ENCRYPT is pushed...
				key = cipher.newRandomKey();								//create a random key
				output = cipher.XOR(input, key);							//encrypt input using that key
				paperwork.writeFile(outputFileTF.getText(), output);		//write output to output file
				paperwork.writeFile(keyFileTF.getText(), key);				//write key to key file
			}
			else {															//If Decrypt is pushed...
				key = paperwork.readFile(keyFileTF.getText());				//read in key file
				output = cipher.XOR(input, key);							//encrypt output using key
				paperwork.writeFile(outputFileTF.getText(), output);		//write output to output file
			}
		}
		
		inputfileTxtReader.close();
		outputfileTxtReader.close();
		keyfileTxtReader.close();
		
	}

}
