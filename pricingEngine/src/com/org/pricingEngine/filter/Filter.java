package com.org.pricingEngine.filter;

import java.util.List;

import com.org.pricingEngine.model.SurveyPrice;

public interface Filter {
	
	public List<Double> filter (List<Double> prices);
}
