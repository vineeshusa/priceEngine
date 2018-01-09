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
		
		
		/*Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of products:: ");
		try {
			int numberOfProducts = sc.nextInt();
			System.out.println("Enter products:: ");
			List <String> products = new ArrayList<> ();
			while (numberOfProducts-- > -1) {
				String productString = sc.nextLine();
				products.add(productString);
			}
			
			products.forEach(c -> System.out.println(c));
			
			System.out.println("Enter the number of survey prices:: ");
			
			int numberofSurveyedPrices = sc.nextInt();
			
			List <String> surveys = new ArrayList<> ();
			
			while (numberofSurveyedPrices-- > -1) {
				String surveyString = sc.nextLine();
				surveys.add(surveyString);
			}
			System.out.println("Processing started ");
			Input input = new Input();
			input.setNumberOfProducts(numberOfProducts);
			input.setNumberofSurveyedPrices(numberofSurveyedPrices);
			input.setProducts(products);
			input.setSurveys(surveys);
			
			PricingEngine priceEngine = new PricingEngineImpl();
			Output output = priceEngine.recomendPrices(input);
			
			for (Double price: output.getPrices()) {
				System.out.println(price); 
			}
		
		} catch (Exception ex) {
			if (ex instanceof InputMismatchException) {
				System.out.println("Error! Input mismath. Run again with a valid entry");
			}
		}*/
		

	
	}

}
