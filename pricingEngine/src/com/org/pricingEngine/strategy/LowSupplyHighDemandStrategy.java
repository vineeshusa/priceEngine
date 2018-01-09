package com.org.pricingEngine.strategy;

public class LowSupplyHighDemandStrategy implements Strategy {

	@Override
	public Double applyPriceStrategy(Double price) {
		// TODO Auto-generated method stub
		double fivePercent = price*5/100;
		return price + fivePercent;
	}

}
