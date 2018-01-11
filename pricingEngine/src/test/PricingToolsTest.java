package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.org.pricingEngine.model.Input;
import com.org.pricingEngine.model.Product;
import com.org.pricingEngine.model.SurveyPrice;
import com.org.pricingEngine.service.helper.PricingTools;

public class PricingToolsTest {
	
	Input input = null;
	List <SurveyPrice> surveyPrices = null;
	
	
	@Before
	public void beforeEachTest() {
		input = new Input();
		input.setNumberOfProducts(2);
		input.setNumberofSurveyedPrices(6);
		List <String> products = Arrays.asList("mp3 H H", "ssd H L");
		List <String> surveys = Arrays.asList("mp3 X 3.0", "mp3 Y 3.0", "mp3 Z 2.0", "ssd X 2.0", "ssd X 5.0", "ssd X 5.0");
		input.setProducts(products);
		input.setSurveys(surveys);
	}
	
	@Test
	public void getBasePriceTest() {
		
		List<Double> prices = Arrays.asList(10.0,9.0,9.0,9.0,9.0,9.0,10.0,10.0);
		PricingTools pricingTools = new PricingTools ();
		double price = pricingTools.getBasePrice(prices);
		assertEquals(9.0, price, 0.0);
		
	}
	
	@Test
	public void applyBasePriceTest() {
		Map<String, List<Double>> prodNamePricesMap = new HashMap<>();
		prodNamePricesMap.put("mp3", Arrays.asList(10.0,9.0,9.0,9.0,9.0,9.0,10.0,10.0));
		prodNamePricesMap.put("ssd", Arrays.asList(9.0,9.0,10.0,10.0));
		prodNamePricesMap.put("cd", Arrays.asList(9.0,8.0,7.0,6.0));
		PricingTools pricingTools = new PricingTools ();
		Map<String, Double> productNamePrice = pricingTools.applyBasePrice(prodNamePricesMap);
		assertEquals(9.0, productNamePrice.get("mp3"), 0.0);
		assertEquals(9.0, productNamePrice.get("ssd"), 0.0);
		assertEquals(6.0, productNamePrice.get("cd"), 0.0);

	}
	
	@Test
	public void applyStrategyTest() {
		List <Product> products = Arrays.asList(
					new Product("mp3", "H", "L" ),
					new Product("ssd", "L", "H" )
				);
		Map<String, Double> productNameToPriceMap = new HashMap<String, Double> ();
		productNameToPriceMap.put("mp3", 100.0);
		productNameToPriceMap.put("ssd", 1000.0);
		
		PricingTools pricingTools = new PricingTools ();
		List<Product> resultProducts = pricingTools.applyStrategy(products, productNameToPriceMap);
		double mp3price = resultProducts
				.stream().filter(p->p.getName()
						.equals("mp3"))
				.findFirst()
				.get()
				.getPrice();
		double ssdprice = resultProducts
				.stream().filter(p->p.getName()
						.equals("ssd"))
				.findFirst()
				.get()
				.getPrice();
		
		assertEquals(95.0, mp3price, 0.0);
		assertEquals(1050.0, ssdprice, 0.0);
		
	}

	
		
	@Test
	public void filterOutLowPricesTest () {
		
		surveyPrices = Arrays.asList(
					new SurveyPrice("mp3",3.0),
					new SurveyPrice("mp3",300.0),
					new SurveyPrice("mp3",301.0),
					new SurveyPrice("mp3",302.0),
					new SurveyPrice("ssd",3.0),
					new SurveyPrice("ssd",300.0),
					new SurveyPrice("ssd",3.0),
					new SurveyPrice("ssd",3.0)
				);
		
		List <Product> products = Arrays.asList(
				new Product("mp3", "H", "L" ),
				new Product("ssd", "L", "H" )
			);
		
		PricingTools pricingTools = new PricingTools ();
		Map<String, List<Double>> filteredMap = pricingTools.applyFilter(products, surveyPrices);
		assertEquals(3, filteredMap.get("mp3").size());
		assertEquals(3, filteredMap.get("ssd").size());
	}


	
	@Test
	public void parseInputTest() {


		PricingTools pricingTools = new PricingTools ();
		Map<String, Object> processParams = pricingTools.parseInput(input);
		List<Product> products = (List<Product>)processParams.get("products");
		List<SurveyPrice> surveyPrices = (List<SurveyPrice>)processParams.get("surveyPrices");
		assertEquals(2, products.size());
		assertEquals(6, surveyPrices.size());
		
	}

}
