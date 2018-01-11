package com.org.pricingEngine.service.impl;

import java.util.List;
import java.util.Map;

import com.org.pricingEngine.filter.Filter;
import com.org.pricingEngine.model.Input;
import com.org.pricingEngine.model.Output;
import com.org.pricingEngine.model.Product;
import com.org.pricingEngine.model.SurveyPrice;
import com.org.pricingEngine.service.PricingEngine;
import com.org.pricingEngine.service.helper.PricingTools;

public class PricingEngineImpl implements PricingEngine {

	@Override
	public Output recomendPrices(Input input) {
		// TODO Auto-generated method stub
		
		//TODO Singleton
		PricingTools pricingTools = new PricingTools();
		Map <String, Object> processParam = pricingTools.parseInput(input);
		
		//Filtering
		List<SurveyPrice> surveyPrices = (List <SurveyPrice>)processParam.get("surveyPrices");
		List<Product> products = (List <Product>)processParam.get("products");
		Map<String, List<Double>> productPriceMap = pricingTools.applyFilter(products, surveyPrices);
		
		//identify the base price of the product
		Map<String, Double> productNameToPriceMap = pricingTools.applyBasePrice(productPriceMap);
		
		//Apply Strategy to get the final Price 
		pricingTools.applyStrategy(products, productNameToPriceMap);
		
		Output output = new Output ();
		List <Double> prices = pricingTools.generateOutput(products);
		output.setPrices(prices);
		return output;
	}

}
