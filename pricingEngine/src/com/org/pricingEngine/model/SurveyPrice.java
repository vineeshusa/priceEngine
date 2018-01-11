package com.org.pricingEngine.model;

public class SurveyPrice {
	
	private String productName;
	private String Competetor;
	private double price;
	
	public SurveyPrice(){}
	
	public SurveyPrice (String productName, double price) {
		this.price = price;
		this.productName = productName;
	}
	
	public SurveyPrice (double price) {
		this.price = price;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompetetor() {
		return Competetor;
	}
	public void setCompetetor(String competetor) {
		Competetor = competetor;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
