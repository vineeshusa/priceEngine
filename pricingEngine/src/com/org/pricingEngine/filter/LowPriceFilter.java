package com.org.pricingEngine.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.org.pricingEngine.model.SurveyPrice;

public class LowPriceFilter implements Filter {

	@Override
	public List<SurveyPrice> filter(List<SurveyPrice> surveyPrices) {
		// TODO Auto-generated method stub
		//surveyPrices.stream().map(p ->p.getPrice()).collect(Collectors.toList()).stream().mapToDouble(a->a).average();
		List <Double> removablePrices = new ArrayList<> ();
		OptionalDouble avg = surveyPrices.stream().mapToDouble(p ->p.getPrice()).average();
		List<Double> prices = surveyPrices.stream().map (p -> p.getPrice()).collect(Collectors.toList());
		List<SurveyPrice> filteredList = null;
		for (Double price : prices) {
			if (price < avg.getAsDouble()/2) {
				removablePrices.add(price);
			}
		}
		
		for (Double price: removablePrices) {
			
			filteredList = surveyPrices.stream().filter(removeIfPriceEqual(price)).collect(Collectors.toList());
			
		}
		return filteredList;
	}
	
	private Predicate<SurveyPrice> removeIfPriceEqual(Double price) {
		return p->p.getPrice() != price;
	}

}
