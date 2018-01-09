package com.org.pricingEngine.model;

import java.util.List;

public class Input {
	
	private int numberOfProducts;
	private int numberofSurveyedPrices;
	private List<String> products;
	private List<String> surveys;
	
	public int getNumberOfProducts() {
		return numberOfProducts;
	}
	public void setNumberOfProducts(int numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}
	public int getNumberofSurveyedPrices() {
		return numberofSurveyedPrices;
	}
	public void setNumberofSurveyedPrices(int numberofSurveyedPrices) {
		this.numberofSurveyedPrices = numberofSurveyedPrices;
	}
	public List<String> getProducts() {
		return products;
	}
	public void setProducts(List<String> products) {
		this.products = products;
	}
	public List<String> getSurveys() {
		return surveys;
	}
	public void setSurveys(List<String> surveys) {
		this.surveys = surveys;
	}
	
	

}
