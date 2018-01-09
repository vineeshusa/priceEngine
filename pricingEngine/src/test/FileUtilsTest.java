package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.org.pricingEngine.model.Input;
import com.org.pricingEngine.util.FileUtils;

public class FileUtilsTest {

	@Test
	public void readFromFileTest() {
		FileUtils fileutils = new FileUtils();
		Input input = fileutils.readFromFile("C://test/input.txt");
		assertEquals(2, input.getNumberOfProducts());
		assertEquals(4, input.getNumberofSurveyedPrices());
		assertEquals(2, input.getProducts().size());
		assertEquals(4, input.getSurveys().size());
	}

}
