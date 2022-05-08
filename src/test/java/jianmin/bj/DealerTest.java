package jianmin.bj;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DealerTest {
	@Test
	public void handTest() {
		Dealer hand = new Dealer();
		CardShoe shoe = new CardShoe();
		shoe.initializeShoe(8);
		
		shoe.shuffle();
		
		hand.receiveACard(shoe.getTheCard(1));
		hand.receiveACard(shoe.getTheCard(2));
		
	    assertEquals(true, hand.isSofthand());
	    assertEquals(13, hand.score());
	    assertEquals(false, hand.isBust());
	    
	    hand.receiveACard(shoe.getTheCard(1));
	    assertEquals(true, hand.isSofthand());
	    assertEquals(14, hand.score());
	    assertEquals(false, hand.isBust());
	    
	    hand.receiveACard(shoe.getTheCard(4));
	    assertEquals(true, hand.isSofthand());
	    assertEquals(18, hand.score());
	    assertEquals(false, hand.isBust());
	    
		hand.receiveACard(shoe.getTheCard(1));
	    assertEquals(true, hand.isSofthand());
	    assertEquals(19, hand.score());
	    assertEquals(false, hand.isBust());
	    
		hand.receiveACard(shoe.getTheCard(5));
	    assertEquals(false, hand.isSofthand());
	    assertEquals(14, hand.score());
	    assertEquals(false, hand.isBust());
	    
		hand.receiveACard(shoe.getTheCard(1));
	    assertEquals(false, hand.isSofthand());
	    assertEquals(15, hand.score());
	    assertEquals(false, hand.isBust());
	    
	    hand.receiveACard(shoe.getTheCard(6));
	    assertEquals(false, hand.isSofthand());
	    assertEquals(21, hand.score());
	    assertEquals(false, hand.isBust());
	    
	    hand.receiveACard(shoe.getTheCard(2));
	    assertEquals(false, hand.isSofthand());
	    assertEquals(23, hand.score());
	    assertEquals(true, hand.isBust());
	    
	    //BlackJack test
		shoe = new CardShoe();
		shoe.initializeShoe(8);
		shoe.shuffle();
		
		hand.clearHands();
		hand.receiveACard(shoe.getTheCard(1));
		hand.receiveACard(shoe.getTheCard(10));
		assertEquals(true, hand.isBlackJack());
	    
		hand.clearHands();
		hand.receiveACard(shoe.getTheCard(1));
		hand.receiveACard(shoe.getTheCard(9));
		assertEquals(false, hand.isBlackJack());
		
		hand.clearHands();
		hand.receiveACard(shoe.getTheCard(10));
		hand.receiveACard(shoe.getTheCard(10));
		assertEquals(false, hand.isBlackJack());
		
		hand.clearHands();
		hand.receiveACard(shoe.getTheCard(10));
		hand.receiveACard(shoe.getTheCard(5));
		hand.receiveACard(shoe.getTheCard(6));
		assertEquals(false, hand.isBlackJack());
		assertEquals(21, hand.score());
	    
	  }

}
