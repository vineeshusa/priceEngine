package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.org.pricingEngine.strategy.HighSupplyLowDemandStrategy;
import com.org.pricingEngine.strategy.Strategy;

public class HighSupplyLowDemandStrategyTest {

	@Test
	public void highSupplyLowDemandStrategyTest() {
		Strategy highSupplyLowDemandStrategy = new HighSupplyLowDemandStrategy();
		double price = highSupplyLowDemandStrategy.applyPriceStrategy(100.00);
		assertEquals(95.0, price, 0.0);
	}

}
