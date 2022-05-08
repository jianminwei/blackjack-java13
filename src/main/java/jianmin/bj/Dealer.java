package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Dealer {
	Logger logger = LogManager.getLogger(Dealer.class);

	private Hand hand = new Hand();
	
	public ArrayList<Card> getCards() {
		return this.hand.getCards();
	}

	public void receiveACard(Card c) {
		
		hand.receiveACard(c);

		if(hand.cardCount() == 2) {
			c.turnDown();
		} else {
			c.turnUp();
		}
	}
	
	/**
	 * This method is used by GUI
	 */
	public void turnFaceDownCardUp() {
		//this.cards.get(1).turnUp();
		this.hand.getSecondCard().turnUp();
	}
	
	public Card receiveNonBlackJack2ndCard(CardShoe cardShoe) {
		
		Card c;

		c = cardShoe.getNextCard();
		
		if (this.getFaceCard().getValue() == 1) {
			while(c.getValue() == 10 ) {
				c = cardShoe.getNextCard();
			}
			
		} else if (this.getFaceCard().getValue() == 10 ) {
			while(c.getValue() == 1 ) {
				c = cardShoe.getNextCard();
			}
		} 

		return c;
	}
	
	public Card getFaceCard() {
		if(!hand.isEmpty()) {
			return hand.getFirstCard();
		}else {
			return null;
		}
	}
	
	public int getFaceCardValue() {
		if(!hand.isEmpty()) {
			return hand.getFirstCard().getValue();
		}else {
			return 0;
		}
	}

	public void displayCards() {
		hand.displayCards();
	}

	public void clearHands() {
		hand.clearHands();
	}
	
	public boolean isBust() {
		return hand.score() > 21;
	}
	
	public boolean isBlackJack() {
		return hand.isBlackJack();
	}	
	
	//To dealer, there is no initial softhand or after hit softhand.
	//As long as a soft hand, it is a soft hand.
	public boolean isSofthand() {
		return (hand.isInitialSofthand() || hand.isAfterHitSofthand());
	}		

	public int score() {
        return hand.score();
	}
}
