package com.org.pricingEngine.filter;

import java.util.List;

import com.org.pricingEngine.model.SurveyPrice;

public interface Filter {
	
	public List<SurveyPrice> filter (List<SurveyPrice> surveyPrices);
}
