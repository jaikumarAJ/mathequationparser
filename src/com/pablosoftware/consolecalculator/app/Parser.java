package com.pablosoftware.consolecalculator.app;

import java.util.HashMap;

import com.pablosoftware.consolecalculator.app.Operations.Operator;

public class Parser {

	private HashMap<String, Operations.Operator> operationsMap;
	private HashMap<String, Double> numbersMap;
	
	public Parser(){
		operationsMap = new HashMap<String,Operations.Operator>();
		numbersMap = new HashMap<String,Double>();
		
		operationsMap.put("add", Operations.Operator.ADD);
		operationsMap.put("plus", Operations.Operator.ADD);
		operationsMap.put("subtract", Operations.Operator.SUBTRACT);
		operationsMap.put("minus", Operations.Operator.SUBTRACT);
		operationsMap.put("less", Operations.Operator.SUBTRACT);
		operationsMap.put("multiply-by", Operations.Operator.MULTIPLY);
		operationsMap.put("times", Operations.Operator.MULTIPLY);
		operationsMap.put("divide-by", Operations.Operator.DIVIDE);
		operationsMap.put("over", Operations.Operator.DIVIDE);
		
		numbersMap.put("zero", 0.0);
		numbersMap.put("one", 1.0);
		numbersMap.put("two", 2.0);
		numbersMap.put("three", 3.0);
		numbersMap.put("four", 4.0);
		numbersMap.put("five", 5.0);
		numbersMap.put("six", 6.0);
		numbersMap.put("seven", 7.0);
		numbersMap.put("eight", 8.0);
		numbersMap.put("nine", 9.0);
		
	}
	
	
	public double parseNumber(String numberString){
		Double number = numbersMap.get(numberString);
		if(number != null)
			return number;
		else
			return -1;
		
	}
	
	public Operations.Operator parseOperator(String operatorString){
		Operations.Operator operator = operationsMap.get(operatorString);
		if(operator != null)
			return operator;
		else
			return Operations.Operator.NOT_FOUND;
		
	}
	
	public boolean isAddOrSubtract(Operations.Operator operator){
		return (operator == Operator.ADD || operator == Operator.SUBTRACT);
	}
	
	
	
	
}
