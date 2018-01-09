package com.org.pricingEngine.service.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
		
		/*for (Product product : products) {
			List<SurveyPrice> productBasedSurveyPrices = surveyPrices
															.stream()
															.filter(surveyPrice -> surveyPrice.getProductName()
																			.equalsIgnoreCase(product.getName())
															 )
															.collect(Collectors.toList());
			product.setSurveyPrices(productBasedSurveyPrices);
		}*/
		
		return processParams;
	}
	
	public List<SurveyPrice> applyFilter(List <SurveyPrice> surveyPrices) {
		
		List<SurveyPrice> filteredSurveyPrices = surveyPrices;
		for (Filter filter: getFilters()) {
			filteredSurveyPrices = filter.filter(surveyPrices);
		}
		
		return filteredSurveyPrices;
	}
	
	public List<Product> applyBasePrice(List <Product> products, List <SurveyPrice> surveyPrices) {
		
		for (Product product: products) {
			
			Map<Double, List<SurveyPrice>>  groupByPrice = surveyPrices
																	.stream()
																	.collect(Collectors.groupingBy(sp -> sp.getPrice()));
			
			TreeMap <Integer, List<Double>> countToPriceListMap = new TreeMap<>();
			groupByPrice.forEach((k, v) -> {
				int size = v.size();
				List <Double> priceList = null;
				if(countToPriceListMap.get(size) == null) {
					priceList = new ArrayList<>();
					priceList.add(k);
					countToPriceListMap.put(size, priceList);
				}else {
					countToPriceListMap.get(size).add(k);
				}
			});
			Optional<Double> maxCountAges = countToPriceListMap
					.get(countToPriceListMap.lastKey())
					.stream().min((s1, s2) -> s1.compareTo(s2));
			product.setPrice(maxCountAges.get());

		}
		return products;
	}
	
	public List<Product> applyStrategy(List <Product> products, List <SurveyPrice> surveyPrices) {
		
		for (Product product: products) {
			Strategy strategy = getStrategy(product.getSupplyParam()+product.getDemandParam());
			Double price = strategy.applyPriceStrategy(product.getPrice());
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
	
	public double findMostFrequentyOccuringPriceIfAny(List<SurveyPrice> surveyPrices){
		double mostFrequentPrice = 0.00;
		return mostFrequentPrice;
	}
	
	public List<Filter> getFilters(){
		List<Filter> filters = Arrays.asList(
									 new HighPriceFilter(),
									 new LowPriceFilter()
								);
		return filters;
	}
	
	/*public List<SupplyDemandPriceRecomender> getStrategies() {
		return null;
	}*/
	
	public int sum(int a, int b) {
		return a+b;
	}
	
	
	
	

}
