package jianmin.bj;

import org.junit.Test;


public class SplitStrategyTest {
	
	@Test
	public void strategyTest() {
		Strategy strategy = new Strategy();
		
		Hand h = new Hand();
		CardShoe shoe = new CardShoe();
		shoe.initializeShoe(8);

		//A, A
		h.receiveACard(shoe.getTheCard(1));
		h.receiveACard(shoe.getTheCard(1));
		
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 2));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 3));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 4));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 5));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 6));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 7));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 8));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 9));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 10));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 1));
//
//		//2, 2
//		h.clearHands();
//		h.receiveACard(shoe.getTheCard(2));
//		h.receiveACard(shoe.getTheCard(2));
//		
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 2));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 3));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 4));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 5));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 6));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 7));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 8));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 9));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 10));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 1));
//		
//		//3, 3
//		h.clearHands();
//		h.receiveACard(shoe.getTheCard(3));
//		h.receiveACard(shoe.getTheCard(3));
//		
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 2));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 3));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 4));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 5));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 6));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 7));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 8));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 9));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 10));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 1));
//
//		//4, 4
//		h.clearHands();
//		h.receiveACard(shoe.getTheCard(4));
//		h.receiveACard(shoe.getTheCard(4));
//		
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 2));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 3));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 4));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 5));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 6));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 7));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 8));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 9));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 10));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 1));
//		
//		//5, 5
//		h.clearHands();
//		h.receiveACard(shoe.getTheCard(5));
//		h.receiveACard(shoe.getTheCard(5));
//		
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 2));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 3));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 4));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 5));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 6));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 7));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 8));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 9));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 10));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 1));
//		
//		//6, 6
//		h.clearHands();
//		h.receiveACard(shoe.getTheCard(6));
//		h.receiveACard(shoe.getTheCard(6));
//		
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 2));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 3));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 4));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 5));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 6));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 7));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 8));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 9));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 10));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 1));
//		
//		//7, 7
//		h.clearHands();
//		h.receiveACard(shoe.getTheCard(7));
//		h.receiveACard(shoe.getTheCard(7));
//		
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 2));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 3));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 4));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 5));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 6));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 7));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 8));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 9));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 10));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 1));
//		
//		//8, 8
//		h.clearHands();
//		h.receiveACard(shoe.getTheCard(8));
//		h.receiveACard(shoe.getTheCard(8));
//		
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 2));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 3));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 4));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 5));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 6));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 7));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 8));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 9));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 10));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 1));
//		
//		//9, 9
//		h.clearHands();
//		h.receiveACard(shoe.getTheCard(9));
//		h.receiveACard(shoe.getTheCard(9));
//		
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 2));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 3));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 4));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 5));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 6));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 7));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 8));
//		assertEquals(strategy.SPLIT, strategy.splitStrategy(h, 9));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 10));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 1));
//		
//		//10, 10
//		h.clearHands();
//		h.receiveACard(shoe.getTheCard(10));
//		h.receiveACard(shoe.getTheCard(10));
//		
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 2));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 3));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 4));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 5));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 6));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 7));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 8));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 9));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 10));
//		assertEquals(strategy.NOT_SPLIT, strategy.splitStrategy(h, 1));
		
	}

}
