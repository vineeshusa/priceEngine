package com.org.pricingEngine.service.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.org.pricingEngine.filter.Filter;
import com.org.pricingEngine.filter.HighPriceFilter;
import com.org.pricingEngine.filter.LowPriceFilter;
import com.org.pricingEngine.model.Input;
import com.org.pricingEngine.model.Product;
import com.org.pricingEngine.model.SurveyPrice;
import com.org.pricingEngine.strategy.HighSupplyHighDemandStrategy;
import com.org.pricingEngine.strategy.HighSupplyLowDemandStrategy;
import com.org.pricingEngine.strategy.LowSupplyHighDemandStrategy;
import com.org.pricingEngine.strategy.LowSupplyLowDemandStrategy;
import com.org.pricingEngine.strategy.Strategy;

public class PricingTools {
	
	public Map<String, Object> parseInput (Input input) {
		List<Product> products = new ArrayList <> ();
		List<SurveyPrice> surveyPrices = new ArrayList <> ();
		Map <String, Object> processParams = new HashMap<> ();
		
		
		input.getProducts().forEach(prodString -> {
			String [] prodStrArr = prodString.split("\\s+");
			Product product = new Product();
			product.setName(prodStrArr[0]);
			product.setSupplyParam(prodStrArr[1]);
			product.setDemandParam(prodStrArr[2]);
			products.add(product);
		});
		
		input.getSurveys().forEach(surString -> {
			String [] surStrArr = surString.split("\\s+");
			SurveyPrice surveyPrice = new SurveyPrice();
			surveyPrice.setProductName(surStrArr[0]);
			surveyPrice.setCompetetor(surStrArr[1]);
			surveyPrice.setPrice(new Double(surStrArr[2]).doubleValue());
			surveyPrices.add(surveyPrice);
		});
		
		processParams.put("products", products);
		processParams.put("surveyPrices", surveyPrices);
		
		return processParams;
	}
	
	public Map<String, List<Double>> applyFilter (List <Product> products, List <SurveyPrice> surveyPrices) {
		
		Map<String, List<Double>> productPriceMap = new HashMap<> ();
		for (Product product:  products) {
			List<Double> prices = surveyPrices.stream()
					.filter(sp -> sp.getProductName().equalsIgnoreCase(product.getName()))
					.map(p->p.getPrice())
					.collect(Collectors.toList());
			for (Filter filter: getFilters()) {
				prices = filter.filter(prices);
			}
			productPriceMap.put(product.getName(), prices);
		}
		return productPriceMap;
	}
	
	public double getBasePrice(List<Double> prices) {
		Map<Double,Long> priceCount = 
				prices.stream()
			           .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		Optional<Long> maxOccurance = priceCount.values().stream().reduce(Long::max);
		List<Double> maxRepeatedPrices = new ArrayList<> ();
		
		priceCount.forEach((k,v)-> {
			
			if (v == maxOccurance.get()) {
				maxRepeatedPrices.add(k);
			}
			
		});
		
		Optional<Double> price = maxRepeatedPrices.stream().reduce(Double::min);
		return price.get();
	}
	
	public Map<String, Double> applyBasePrice(Map<String, List<Double>> productPriceMap) {
		
		Map <String, Double> productNameToPriceMap = new HashMap<> ();
		
		for (String productName : productPriceMap.keySet()) {
			List<Double> prices = productPriceMap.get(productName);
			double basePrice = getBasePrice(prices);
			productNameToPriceMap.put(productName, basePrice);
			
			
		}
		return productNameToPriceMap;
	}
	
	public List<Product> applyStrategy(List <Product> products, Map<String, Double> productNameToPriceMap) {
		
		for (Product product: products) {
			Strategy strategy = getStrategy(product.getSupplyParam()+product.getDemandParam());
			Double price = strategy.applyPriceStrategy(productNameToPriceMap.get(product.getName()));
			product.setPrice(price);
		}
		return products;
	}
	
	public Strategy getStrategy(String strategyStr) {
		
		Strategy strategy = null;
		switch (strategyStr) {
			case "LL" : strategy = new LowSupplyLowDemandStrategy(); break ;
			case "LH" : strategy = new LowSupplyHighDemandStrategy(); break ;
			case "HL" : strategy = new HighSupplyLowDemandStrategy(); break ;
			case "HH" : strategy = new HighSupplyHighDemandStrategy(); break ;
			default : strategy = new HighSupplyHighDemandStrategy(); break ;
		}
		return strategy;
		
	}
	
	public List<Double> generateOutput(List <Product> products) {
		List <Double> list = products.stream().map(p -> p.getPrice()).collect(Collectors.toList());
		return list;
	}
	
	public List<Filter> getFilters(){
		List<Filter> filters = Arrays.asList(
									 new HighPriceFilter(),
									 new LowPriceFilter()
								);
		return filters;
	}
	
}
