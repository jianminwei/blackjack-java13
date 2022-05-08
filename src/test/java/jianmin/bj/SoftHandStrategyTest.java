package jianmin.bj;

import org.junit.Test;

public class SoftHandStrategyTest {
	@Test
	public void strategyTest() {
		Strategy strategy = new Strategy();
		
		//Initial Softhand Test
		
		//A, 2
//		assertEquals(strategy.HIT, strategy.softHandStrategy(13, 2, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(13, 3, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(13, 4, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(13, 5, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(13, 6, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(13, 7, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(13, 10, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(13, 1, 2));	
//		
//		
//		//A, 3 
//		assertEquals(strategy.HIT, strategy.softHandStrategy(14, 2, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(14, 3, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(14, 4, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(14, 5, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(14, 6, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(14, 7, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(14, 10, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(14, 1, 2));		
//
//		
//		//A, 4
//		assertEquals(strategy.HIT, strategy.softHandStrategy(15, 2, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(15, 3, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(15, 4, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(15, 5, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(15, 6, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(15, 7, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(15, 8, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(15, 9, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(15, 10, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(15, 1, 2));
//	
//		//A, 5 
//		assertEquals(strategy.HIT, strategy.softHandStrategy(16, 2, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(16, 3, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(16, 4, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(16, 5, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(16, 6, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(16, 7, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(16, 8, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(16, 9, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(16, 10, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(16, 1, 2));
//		
//		
//		//A6
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 2, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(17, 3, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(17, 4, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(17, 5, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(17, 6, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 7, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 8, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 9, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 10, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 1, 2));
//		
//		//A7
//		assertEquals(strategy.STAY, strategy.softHandStrategy(18, 2, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(18, 3, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(18, 4, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(18, 5, 2));
//		assertEquals(strategy.DOUBLE, strategy.softHandStrategy(18, 6, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(18, 7, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(18, 8, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(18, 9, 2));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(18, 10, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(18, 1, 2));
//
//		//A8
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 2, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 3, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 4, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 5, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 6, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 7, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 8, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 9, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 10, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 1, 2));
//		
//		//A9
//		assertEquals(strategy.STAY, strategy.softHandStrategy(20, 2, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(20, 3, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(20, 4, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(20, 5, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(20, 6, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(20, 7, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(20, 8, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(20, 9, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(20, 10, 2));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(20, 1, 2));
//		
//		
//		//===========================================
//		//After hit softhand test
//		
//		
//		//A6
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 2, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 3, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 4, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 5, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 6, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 7, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 8, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 9, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 10, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(17, 1, 3));	
//		
//
//		
//		//A7
//		assertEquals(strategy.STAY, strategy.softHandStrategy(18, 2, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(18, 3, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(18, 4, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(18, 5, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(18, 6, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(18, 7, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(18, 8, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(18, 9, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(18, 10, 3));
//		assertEquals(strategy.HIT, strategy.softHandStrategy(18, 1, 3));	
//		
//		//A8
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 2, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 3, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 4, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 5, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 6, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 7, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 8, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 9, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 10, 3));
//		assertEquals(strategy.STAY, strategy.softHandStrategy(19, 1, 3));
		
	}

}
