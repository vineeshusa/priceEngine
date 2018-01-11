package com.org.pricingEngine.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.org.pricingEngine.model.SurveyPrice;

public class LowPriceFilter implements Filter {

	@Override
	public List<Double> filter(List<Double> prices) {
		List <Double> removablePrices = new ArrayList<> ();
		OptionalDouble avg = prices.stream().mapToDouble(p->p).average();
		//OptionalDouble avg = surveyPrices.stream().mapToDouble(p ->p.getPrice()).average();
		//List<Double> prices = surveyPrices.stream().map (p -> p.getPrice()).collect(Collectors.toList());
		List<Double> filteredList = new ArrayList<>();
		for (Double price : prices) {
			if (price > avg.getAsDouble()/2) {
				filteredList.add(price);
			}
		}
/*		
		for (Double price: removablePrices) {
			
			filteredList = surveyPrices.stream().filter(removeIfPriceEqual(price)).collect(Collectors.toList());
			
		}*/
		return filteredList;
	}
	
/*	private Predicate<SurveyPrice> removeIfPriceEqual(Double price) {
		return p->p.getPrice() != price;
	}*/

}
