package com.org.pricingEngine.model;

import java.util.List;

public class Product {
	
	private String name;
	private String supplyParam;
	private String demandParam;
	private List<SurveyPrice> surveyPrices;
	private double price;
	
	public Product(){}
	
	public Product(String name, String supplyParam, String demandParam) {
		this.name = name;
		this.supplyParam = supplyParam;
		this.demandParam = demandParam;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSupplyParam() {
		return supplyParam;
	}
	public void setSupplyParam(String supplyParam) {
		this.supplyParam = supplyParam;
	}
	public String getDemandParam() {
		return demandParam;
	}
	public void setDemandParam(String demandParam) {
		this.demandParam = demandParam;
	}
	public List<SurveyPrice> getSurveyPrices() {
		return surveyPrices;
	}
	public void setSurveyPrices(List<SurveyPrice> surveyPrices) {
		this.surveyPrices = surveyPrices;
	}

}
