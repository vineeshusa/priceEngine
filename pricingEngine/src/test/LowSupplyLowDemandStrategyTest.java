package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.org.pricingEngine.strategy.LowSupplyHighDemandStrategy;
import com.org.pricingEngine.strategy.LowSupplyLowDemandStrategy;
import com.org.pricingEngine.strategy.Strategy;

public class LowSupplyLowDemandStrategyTest {

	@Test
	public void lowSupplyLowDemandStrategyTest() {
		Strategy lowSupplyLowDemandStrategy = new LowSupplyLowDemandStrategy();
		double price = lowSupplyLowDemandStrategy.applyPriceStrategy(100.00);
		assertEquals(110.0, price, 0.0);
	}

}
