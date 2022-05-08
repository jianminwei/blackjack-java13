package jianmin.bj;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardShoeTest {
	
	@Test
	public void getTheCard() {
	    CardShoe shoe = new CardShoe();
	    shoe.initializeShoe(8);
	    Card c = shoe.getTheCard(3);
	    assertEquals(3, c.getValue());
	  }

}
