package com.org.pricingEngine.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.org.pricingEngine.model.Input;

public class FileUtils {
	
	public Input readFromFile (String fileName) {
		
		//File file = new File (filePath);
		FileReader fr = null;
		BufferedReader br = null;
		String sCurrentLine = "test";
		int numberOfProducts = 0;
		int numberOfSurveys = 0;
		List <String> products = new ArrayList<> ();
		List <String> surveyPrices = new ArrayList<> ();
		Input input = new Input ();
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			int line = 1;
			boolean isAllProductsAdded = false;
			while ((sCurrentLine = br.readLine()) != null) {
				
				if (line == 1 ) {
					numberOfProducts = new Integer(sCurrentLine).intValue();
				}else {
					if (products.size() == numberOfProducts) {
						if (numberOfSurveys == 0) {
							numberOfSurveys = new Integer(sCurrentLine).intValue();
						} else {
							surveyPrices.add(sCurrentLine);
						}
					} else {
						if (products.size() < numberOfProducts )
							products.add(sCurrentLine);
					}
					
				}
				line++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		
		input.setNumberOfProducts(numberOfProducts);
		input.setNumberofSurveyedPrices(numberOfSurveys);
		input.setProducts(products);
		input.setSurveys(surveyPrices);
		return input;
	}

}
