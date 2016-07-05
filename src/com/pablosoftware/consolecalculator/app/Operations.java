package com.pablosoftware.consolecalculator.app;

import java.util.ArrayList;
/**
 * 
 * @author Pablo Martinez Piles
 * Using shunting yard algorithm
 *
 * https://en.wikipedia.org/wiki/Shunting-yard_algorithm
 */

public class Operations {
	
	private Parser parser;
	
	private ArrayList<Double>  n;
	private ArrayList<Operator> op;
	
	public Operations(){
		parser = new Parser();
		n = new ArrayList<Double>();
		op = new ArrayList<Operator>();
	}
	

	public double calculateString(String operation){
		try {
			return calculate(operation.toLowerCase().split(" "));
		} catch (WrongFormatException e) {
			System.out.println("Invalid expression format");
		}
		return 0.0;
	}
	
	private double calculate(String[] operationItems) throws WrongFormatException{
		
		
		for(int cont = 0 ; cont < operationItems.length; cont ++){
			if(cont % 2 == 0){
				n.add(this.parser.parseNumber(operationItems[cont]));
			}else{
				Operator o = this.parser.parseOperator(operationItems[cont]);
				this.compareOperatorWithTopOfStack(o);
				op.add(o);
			}
		}
		// Check if the last one was Multiply or Divide to calculate it
		this.compareOperatorWithTopOfStack(Operator.NOT_FOUND);
		this.calculateOutstandingOperations();
		//round number with 2 decimals
		if(n.size() > 0){
			return Utils.round(n.get(0), 2);
		}else{
			throw new WrongFormatException();
		}
		
	}
	
	private void compareOperatorWithTopOfStack(Operator operator) throws WrongFormatException{
		if(op.size() > 0){
			Operator lastOperator = op.get(op.size()-1);
				if((lastOperator == Operator.MULTIPLY || lastOperator == Operator.DIVIDE) && (operator == Operator.ADD || operator == Operator.SUBTRACT || operator == Operator.NOT_FOUND)){
					Double n1 = n.remove(n.size() -1);
					Double n2 = n.remove(n.size() -1);
					switch(lastOperator){
						case MULTIPLY:
							n.add(n2 * n1);
							break;
						case DIVIDE:
							n.add(n2 / n1);
							break;
						default:
							break;
					}
					
					op.remove(lastOperator);
					this.compareOperatorWithTopOfStack(operator);
				}
				
			}	
	}
	
	private void calculateOutstandingOperations() throws WrongFormatException{
		if(op.size() > 0){
			for(int cont = 0 ; cont < this.op.size(); cont ++){
				Double n1 = null;
				Double n2 = null;
				try{
					n1 = n.remove(0);
					n2 = n.remove(0);
				}catch (IndexOutOfBoundsException e) {
					throw new WrongFormatException();
				}
				
				switch(op.get(cont)){
				case ADD:
					n.add(n1 + n2);
					break;
				case SUBTRACT:
					n.add(n1 - n2);
					break;
				default:
					break;
				}
			}
		}
	}
	/**
	 * 
	 * Enum about types of operations
	 *
	 */
	public enum Operator{
		ADD, SUBTRACT, MULTIPLY, DIVIDE, NOT_FOUND
	}
	
}

