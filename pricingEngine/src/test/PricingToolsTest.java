package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
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
	public void parseInputTest() {


		PricingTools pricingTools = new PricingTools ();
		Map<String, Object> processParams = pricingTools.parseInput(input);
		List<Product> products = (List<Product>)processParams.get("products");
		List<SurveyPrice> surveyPrices = (List<SurveyPrice>)processParams.get("surveyPrices");
		assertEquals(2, products.size());
		assertEquals(6, surveyPrices.size());
		
	}

}
