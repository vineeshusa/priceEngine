package com.org.pricingEngine.strategy;

public class LowSupplyLowDemandStrategy implements Strategy {

	@Override
	public Double applyPriceStrategy(Double price) {
		// TODO Auto-generated method stub
		double tenPercent = price*10/100;
		return price + tenPercent;
	}

}
