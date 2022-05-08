package jianmin.bj;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HardHandStrategyTest {
	@Test
	public void strategyTest() {
		Strategy strategy = new Strategy();
		CardShoe cardShoe = new CardShoe();

		//////////////////////////////////////////
		// Hardhand strategy test

		// above 17
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 2, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 18, 3, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 19, 4, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 19, 5, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 21, 6, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 7, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 18, 8, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 19, 9, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 20, 10, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 21, 1, 3));
		
		//17		
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 2, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 3, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 4, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 5, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 6, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 7, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 8, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 9, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 10, 2));
		//assertEquals(strategy.SURRENDER, strategy.hardHandStrategy(cardShoe, 17, 1, 2));
		
		//17 not initial stage
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 17, 1, 3));

		// 16
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 16, 2, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 16, 3, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 16, 4, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 16, 5, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 16, 6, 3));

		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 16, 7, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 16, 8, 3));

		//assertEquals(Strategy.SURRENDER, strategy.hardHandStrategy(cardShoe, 16, 9, 2));
		//assertEquals(Strategy.SURRENDER, strategy.hardHandStrategy(cardShoe, 16, 10, 2));
		//assertEquals(Strategy.SURRENDER, strategy.hardHandStrategy(cardShoe, 16, 1, 2));
		
		
		//not initial state
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 16, 9, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 16, 10, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 16, 1, 3));


 		// 15
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 15, 2, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 15, 3, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 15, 4, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 15, 5, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 15, 6, 3));

		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 15, 7, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 15, 8, 3));

		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 15, 9, 2));
		//assertEquals(Strategy.SURRENDER, strategy.hardHandStrategy(cardShoe, 15, 10, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 15, 10, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 15, 1, 2));

		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 15, 10, 3));

		// 14
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 14, 2, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 14, 3, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 14, 4, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 14, 5, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 14, 6, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 14, 7, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 14, 8, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 14, 9, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 14, 10, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 14, 1, 2));

		// 13
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 13, 2, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 13, 3, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 13, 4, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 13, 5, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 13, 6, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 13, 7, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 13, 8, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 13, 9, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 13, 10, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 13, 1, 2));

		// 12
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 2, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 3, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 4, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 5, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 6, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 7, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 8, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 9, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 10, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 1, 2));

		//strategy.setCan12HitOn2(false);
		strategy.setStrategy(12, 2, 'S', MapUtil.HARD_HAND);
		
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 2, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 3, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 4, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 5, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 6, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 7, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 8, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 9, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 10, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 1, 2));

		//strategy.setCan12HitOn3(false);
		strategy.setStrategy(12, 3, 'S', MapUtil.HARD_HAND);
		
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 2, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 3, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 4, 2));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 5, 3));
		assertEquals(Strategy.STAY, strategy.hardHandStrategy(cardShoe, 12, 6, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 7, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 8, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 9, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 10, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 12, 1, 2));

		// 11
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 11, 2, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 11, 3, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 11, 4, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 11, 5, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 11, 6, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 11, 7, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 11, 8, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 11, 9, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 10, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 1, 2));

		// 11, not initial stage
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 2, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 3, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 4, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 5, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 6, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 7, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 8, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 9, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 10, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 11, 1, 3));

		// 10
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 10, 2, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 10, 3, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 10, 4, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 10, 5, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 10, 6, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 10, 7, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 10, 8, 2));
		assertEquals(Strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 10, 9, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 10, 2));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 1, 2));
		
		// 10
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 2, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 3, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 4, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 5, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 6, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 7, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 8, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 9, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 10, 3));
		assertEquals(Strategy.HIT, strategy.hardHandStrategy(cardShoe, 10, 1, 3));
		

		// 9
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 2, 2));
		assertEquals(strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 9, 3, 2));
		assertEquals(strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 9, 4, 2));
		assertEquals(strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 9, 5, 2));
		assertEquals(strategy.DOUBLE, strategy.hardHandStrategy(cardShoe, 9, 6, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 7, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 8, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 9, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 10, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 1, 2));
		
		// 9, not initial stage
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 2, 3));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 3, 3));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 4, 3));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 5, 3));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 6, 3));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 7, 3));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 8, 3));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 9, 3));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 10, 3));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 9, 1, 3));

		// 8 and below
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 8, 2, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 8, 3, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 8, 4, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 8, 5, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 8, 6, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 8, 7, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 8, 8, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 8, 9, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 8, 10, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 8, 1, 2));

		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 7, 2, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 7, 3, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 7, 4, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 7, 5, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 7, 6, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 7, 7, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 7, 8, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 7, 9, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 7, 10, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 7, 1, 2));

		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 6, 2, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 6, 3, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 6, 4, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 6, 5, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 6, 6, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 6, 7, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 6, 8, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 6, 9, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 6, 10, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 6, 1, 2));

		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 5, 2, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 5, 3, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 5, 4, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 5, 5, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 5, 6, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 5, 7, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 5, 8, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 5, 9, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 5, 10, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 5, 1, 2));

		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 4, 2, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 4, 3, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 4, 4, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 4, 5, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 4, 6, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 4, 7, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 4, 8, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 4, 9, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 4, 10, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 4, 1, 2));

		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 3, 2, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 3, 3, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 3, 4, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 3, 5, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 3, 6, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 3, 7, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 3, 8, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 3, 9, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 3, 10, 2));
		assertEquals(strategy.HIT, strategy.hardHandStrategy(cardShoe, 3, 1, 2));
	}

}
