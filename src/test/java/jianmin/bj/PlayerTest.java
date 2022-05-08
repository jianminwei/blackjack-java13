package jianmin.bj;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
	@Test
	public void handTest() {
		Player player = new Player();
		Hand hand = player.getHand1();
		Hand anotherHand;
		CardShoe shoe = new CardShoe();
		shoe.initializeShoe(8);
		
		shoe.shuffle();
		
		hand.receiveACard(shoe.getTheCard(1));
		hand.receiveACard(shoe.getTheCard(2));
		
	    assertEquals(true, hand.isInitialSofthand());
	    assertEquals(false, hand.isAfterHitSofthand());
	    assertEquals(13, hand.score());
	    assertEquals(false, hand.isBust());
	    
	    hand.receiveACard(shoe.getTheCard(1));
	    assertEquals(false, hand.isInitialSofthand());
	    assertEquals(true, hand.isAfterHitSofthand());
	    assertEquals(14, hand.score());
	    assertEquals(false, hand.isBust());
	    
	    hand.receiveACard(shoe.getTheCard(4));
	    assertEquals(false, hand.isInitialSofthand());
	    assertEquals(true, hand.isAfterHitSofthand());
	    assertEquals(18, hand.score());
	    assertEquals(false, hand.isBust());
	    
		hand.receiveACard(shoe.getTheCard(1));
	    assertEquals(false, hand.isInitialSofthand());
	    assertEquals(true, hand.isAfterHitSofthand());
	    assertEquals(19, hand.score());
	    assertEquals(false, hand.isBust());
	    
		hand.receiveACard(shoe.getTheCard(5));
	    assertEquals(false, hand.isInitialSofthand());
	    assertEquals(false, hand.isAfterHitSofthand());
	    assertEquals(14, hand.score());
	    assertEquals(false, hand.isBust());
	    
		hand.receiveACard(shoe.getTheCard(1));
	    assertEquals(false, hand.isInitialSofthand());
	    assertEquals(false, hand.isAfterHitSofthand());
	    assertEquals(15, hand.score());
	    assertEquals(false, hand.isBust());
	    
	    hand.receiveACard(shoe.getTheCard(6));
	    assertEquals(false, hand.isInitialSofthand());
	    assertEquals(false, hand.isAfterHitSofthand());
	    assertEquals(21, hand.score());
	    assertEquals(false, hand.isBust());
	    
	    hand.receiveACard(shoe.getTheCard(2));
	    assertEquals(false, hand.isInitialSofthand());
	    assertEquals(false, hand.isAfterHitSofthand());
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
		
	    //Softhand test
		shoe = new CardShoe();
		shoe.initializeShoe(8);
		shoe.shuffle();
		
		hand.clearHands();
		hand.receiveACard(shoe.getTheCard(1));
		hand.receiveACard(shoe.getTheCard(2));
		assertEquals(true, hand.isInitialSofthand());
		assertEquals(13, hand.score());
		
		
		hand.clearHands();
		hand.receiveACard(shoe.getTheCard(1));
		hand.receiveACard(shoe.getTheCard(9));
		assertEquals(true, hand.isInitialSofthand());
		assertEquals(20, hand.score());
		
		//A softhand become AfterHit softhand after a hit
		hand.clearHands();
		hand.receiveACard(shoe.getTheCard(1));
		hand.receiveACard(shoe.getTheCard(3));
		hand.receiveACard(shoe.getTheCard(4));
		assertEquals(false, hand.isInitialSofthand());
		assertEquals(true, hand.isAfterHitSofthand());
		assertEquals(18, hand.score());
		
		//A AfterHit softhand can remain as AfterHit softhand after a hit
		//depend on the card get hit
		hand.clearHands();
		hand.receiveACard(shoe.getTheCard(1));
		hand.receiveACard(shoe.getTheCard(3));
		hand.receiveACard(shoe.getTheCard(4));
		hand.receiveACard(shoe.getTheCard(2));
		assertEquals(false, hand.isInitialSofthand());
		assertEquals(true, hand.isAfterHitSofthand());
		assertEquals(20, hand.score());
		
		//A AfterHit softhand can become hardhand after a hit
		//depend on the card get hit
		hand.clearHands();
		hand.receiveACard(shoe.getTheCard(1));
		hand.receiveACard(shoe.getTheCard(3));
		hand.receiveACard(shoe.getTheCard(4));
		hand.receiveACard(shoe.getTheCard(5));
		assertEquals(false, hand.isInitialSofthand());
		assertEquals(false, hand.isAfterHitSofthand());
		assertEquals(13, hand.score());
		
		
		//Split test
		hand.clearHands();
		hand.receiveACard(shoe.getTheCard(4));
		hand.receiveACard(shoe.getTheCard(4));
		
		anotherHand = hand.splitHand();
		
		hand.receiveACard(shoe.getTheCard(1));
		assertEquals(true, hand.isInitialSofthand());
		assertEquals(false, hand.isAfterHitSofthand());
		assertEquals(15, hand.score());
		assertEquals(2, hand.cardCount());
		
		anotherHand.receiveACard(shoe.getTheCard(4));
		assertEquals(false, anotherHand.isInitialSofthand());
		assertEquals(false, anotherHand.isAfterHitSofthand());
		assertEquals(8, anotherHand.score());
		assertEquals(2, anotherHand.cardCount());	
		assertEquals(true, anotherHand.isPair());
		
		anotherHand.receiveACard(shoe.getTheCard(4));
		assertEquals(false, anotherHand.isPair());

	    
	  }

}
