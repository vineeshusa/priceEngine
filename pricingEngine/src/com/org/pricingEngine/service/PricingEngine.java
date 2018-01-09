package com.org.pricingEngine.service;

import com.org.pricingEngine.model.Input;
import com.org.pricingEngine.model.Output;

public interface PricingEngine {
	
	public Output recomendPrices(Input input);

}
