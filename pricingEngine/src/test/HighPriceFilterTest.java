package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.org.pricingEngine.filter.Filter;
import com.org.pricingEngine.filter.HighPriceFilter;
import com.org.pricingEngine.model.Product;
import com.org.pricingEngine.model.SurveyPrice;

public class HighPriceFilterTest {

	@Test
	public void testHighPriceFilter() {
		
		Filter highPriceFilter = new  HighPriceFilter(); 
		List<Double> prices = Arrays.asList(1.0,2.0,3.0,500.0);
		List<Double> filteredPrices = highPriceFilter.filter(prices);
		List<Double> expectedPrices =  Arrays.asList(1.0,2.0,3.0);
		assertEquals(3, filteredPrices.size());
		assertTrue(filteredPrices.containsAll(expectedPrices));
	}

}
