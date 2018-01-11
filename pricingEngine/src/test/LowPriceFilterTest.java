package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.org.pricingEngine.filter.Filter;
import com.org.pricingEngine.filter.LowPriceFilter;

public class LowPriceFilterTest {

	@Test
	public void testLowPriceFilter() {
		
		Filter lowPriceFilter = new  LowPriceFilter(); 
		List<Double> prices = Arrays.asList(1.0,502.0,503.0,500.0);
		List<Double> filteredPrices = lowPriceFilter.filter(prices);
		List<Double> expectedPrices =  Arrays.asList(502.0,503.0,500.0);
		assertEquals(3, filteredPrices.size());
		assertTrue(filteredPrices.containsAll(expectedPrices));
	}

}
