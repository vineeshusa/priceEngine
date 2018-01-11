package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.org.pricingEngine.strategy.HighSupplyLowDemandStrategy;
import com.org.pricingEngine.strategy.LowSupplyHighDemandStrategy;
import com.org.pricingEngine.strategy.Strategy;

public class LowSupplyHighDemandStrategyTest {

	@Test
	public void lowSupplyHighDemandStrategyTest() {
		Strategy lowSupplyHighDemandStrategy = new LowSupplyHighDemandStrategy();
		double price = lowSupplyHighDemandStrategy.applyPriceStrategy(100.00);
		assertEquals(105.0, price, 0.0);
	}

}
