package com.pablosoftware.consolecalculator.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
	
	private BufferedReader br;
	
	
	public App(){
		this.br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void run(){
		String line = null;
		try {
			
			while(true){
				System.out.println("Please enter a calculation:");
				line = br.readLine();
				
				if(line.trim().equals("")){
					break;
				}
				Operations op = new Operations();
				System.out.println(op.calculateString(line) + "\n");	
				//System.out.println(Operations.eval(line) + "\n");	
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error reading");
		}
		
		System.out.println("good bye!");
		
		
	}
	
}
