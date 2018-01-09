package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.org.pricingEngine.filter.Filter;
import com.org.pricingEngine.filter.HighPriceFilter;
import com.org.pricingEngine.model.Product;
import com.org.pricingEngine.model.SurveyPrice;

public class HighPriceFilterTest {

	@Test
	public void test() {
		
		Filter highPriceFilter = new HighPriceFilter ();
		Product product = new Product();
		SurveyPrice surveyPrice = new SurveyPrice ();
		
		List <SurveyPrice> surveyPrices = new ArrayList <> ();
		//highPriceFilter.filter(surveyPrices);
	}

}
