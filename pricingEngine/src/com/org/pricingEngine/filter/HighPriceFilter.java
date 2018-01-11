package com.org.pricingEngine.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.org.pricingEngine.model.SurveyPrice;

public class HighPriceFilter implements Filter {

	@Override
	public List<Double> filter(List<Double> prices) {
		List <Double> removablePrices = new ArrayList<> ();
		OptionalDouble avg = prices.stream().mapToDouble(p->p).average();
		List<Double> filteredList = new ArrayList<>();
		for (Double price : prices) {
			if (price < avg.getAsDouble()*3/2) {
				filteredList.add(price);
			}
		}
		return filteredList;
	}
}
