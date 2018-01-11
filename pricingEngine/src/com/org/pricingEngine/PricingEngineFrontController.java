package com.org.pricingEngine;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.org.pricingEngine.model.Input;
import com.org.pricingEngine.model.Output;
import com.org.pricingEngine.service.PricingEngine;
import com.org.pricingEngine.service.impl.PricingEngineImpl;
import com.org.pricingEngine.util.FileUtils;

public class PricingEngineFrontController {
	
	public static void main(String [] args){
		
		
		FileUtils fileUtils = new FileUtils();
		Input input = null;
		if (args.length == 0) {
			//hard coded
			String fileName = "C://test/input.txt";
			input = fileUtils.readFromFile(fileName);
		} else {
			input = fileUtils.readFromFile(args[0]);
		}
		PricingEngine priceEngine = new PricingEngineImpl();
		Output output = priceEngine.recomendPrices(input);
		
		for (Double price: output.getPrices()) {
			System.out.println(price); 
		}
	}

}
