package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Hand {
	Logger logger = LogManager.getLogger(Hand.class);

	private ArrayList<Card> cards = new ArrayList<Card>();
	private boolean softHand = false;
	private boolean splitedAceHand = false;
	
	//this property is for tracking splitted hand since no Blackjack after split
	private boolean splitedHand = false;
	
	private double bet = 0;
	
	//this is for GUI BlackJack to
	//keep track of player last decision
	private int lastHandDecision;
	
	public int getLastHandDecision() {
		return lastHandDecision;
	}

	public void setLastHandDecision(int lastHandDecision) {
		this.lastHandDecision = lastHandDecision;
	}
	
	//This method is used by GUI deal() method. Sometimes
	//the card used by player hand could be dealt again to dealer
	//because of the True count position move. this is to prevent that.
	public boolean isCardOnHand(Card card) {
		for(Card c: cards) {
			if (c.getCardShoeInitialSequence() == card.getCardShoeInitialSequence()) {
				return true;
			}
		}
		
		return false;
	}
	
	//this is used to chain the splitted hand.
	//the child hand need parent hand to set the bet.
	private Hand parentHand = null;
	
	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}
	
	//this tableBet is used for GUI BlackJack only.
	//When placing bets at the table, only when the card dealing starts, 
	//commit and move this money to bet.
	public void commitTableBet(double tableBet) {
		this.bet = tableBet;
		logger.trace("Trace:: committed bet amount:" + this.bet );
	}
	
	public double getBet() {
		return this.bet;
	}
	
	public void receiveACard(Card c) {
		cards.add(c);

		if (c.getValue() == 1) {
			this.softHand = true;
		}
	}
	
	public boolean isEmpty() {
		return cards.size() == 0;
	}
	
	public Card getFirstCard() {
		return cards.get(0);
	}
	
	public Card getSecondCard() {
		return cards.get(1);
	}
	
	public boolean isSplitedAceHand() {
		return splitedAceHand;
	}
	
	public void setSplitedAceHand(boolean splitedAceHand) {
		this.splitedAceHand = splitedAceHand;
	}
	
	public Hand getParentHand() {
		return parentHand;
	}

	public void setParentHand(Hand parentHand) {
		this.parentHand = parentHand;
	}

	public boolean isBlackJack() {
		//if the hand is from split, no blackjack
		if (this.splitedHand) {
			return false;
		}
		
		if (score() == 21 && cards.size() ==2) {
			return true;
		}else {
			return false;
		}
	}	
	
	public boolean isInitialSofthand() {
	
		if (softHand && cards.size() ==2) {
			return true;
		}else {
			return false;
		}
	}	
	
	public boolean isAfterHitSofthand() {
		//it needs to do the same score logic first to get the 
		//softHandCount11Used and softHandUnCount11Used state, and to determine
		//is it a After-Hit softhand
		int score = 0;
		boolean softHandCount11Used = false;
		boolean softHandUnCount11Used = false;

		for (Card c : cards) {
			int v = c.getValue();

			if (v == 1) {
				if (score >= 11 ) {
					v = 1;
				}else {
					v = 11;
					softHandCount11Used = true;					
				}
			} 

			score += v;

			if (score > 21 && softHandCount11Used && !softHandUnCount11Used) {
				score = score - 10;
				softHandUnCount11Used = true;
			}
		}
		
		if (softHand && cards.size() > 2 && softHandCount11Used && !softHandUnCount11Used) {
			return true;
		}else {
			return false;
		}
	}		
	
	public boolean isBust() {
		return score() > 21;
	}
	
	public int cardCount() {
		return cards.size();
	}
	
	public boolean isPair() {
		if (cards.size() == 2) {
			return cards.get(0).getValue() == cards.get(1).getValue();
		}else {
			return false;
		}
	}
	
	public boolean isPairOfAce() {
		if (this.isPair() && cards.get(0).getValue() == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isPairOf(int rank) {
		if (this.isPair() && cards.get(0).getValue() == rank) {
			return true;
		}else {
			return false;
		}
	}

	public Hand splitHand() {
		Hand anotherHand = new Hand();

		anotherHand.receiveACard(this.cards.remove(1));
		
		//if the hand is a pair of Ace
		if(cards.get(0).getValue() == 1) {
			setSplitedAceHand(true);
			anotherHand.setSplitedAceHand(true);
		}
		
		anotherHand.setParentHand(this);
		
		//set the splithand property
		this.splitedHand = true;
		anotherHand.splitedHand = true;

		return anotherHand;
	}

	public void displayCards() {
		for (int i = 0; i < cards.size(); i++) {
			logger.info(cards.get(i).toString());
		}
	}

	public void clearHands() {
		cards.clear();
		// score = 0;
		softHand = false;
		splitedAceHand = false;
		splitedHand = false;
		bet = 0;
	}

	public int score() {

		int score = 0;
		boolean softHandCount11Used = false;
		boolean softHandUnCount11Used = false;

		for (Card c : cards) {
			int v = c.getValue();

			if (v == 1) {
				if (score >= 11 ) {
					v = 1;
				}else {
					v = 11;
					softHandCount11Used = true;					
				}
			} 

			score += v;

			if (score > 21 && softHandCount11Used && !softHandUnCount11Used) {
				score = score - 10;
				softHandUnCount11Used = true;
			}
		}
		
		return score;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hand hand = new Hand();
		CardShoe shoe = new CardShoe();
		shoe.initializeShoe(8);
		
		shoe.shuffle();
		
		hand.receiveACard(shoe.getTheCard(1));
		hand.receiveACard(shoe.getTheCard(2));

		hand.displayCards();
		hand.logger.info("Bust: " +  hand.isBust());
		hand.logger.info("\nScore: " +  hand.score());

		
		hand.receiveACard(shoe.getTheCard(1));
		hand.displayCards();
		hand.logger.info("\nScore: " +  hand.score());
		
		hand.receiveACard(shoe.getTheCard(4));
		hand.displayCards();
		hand.logger.info("\nScore: " +  hand.score());
		
		hand.receiveACard(shoe.getTheCard(1));
		hand.displayCards();
		hand.logger.info("\nScore: " +  hand.score());
		
		hand.receiveACard(shoe.getTheCard(5));
		hand.displayCards();
		hand.logger.info("\nScore: " +  hand.score());
		
		hand.receiveACard(shoe.getTheCard(1));
		hand.displayCards();
		hand.logger.info("\nScore: " +  hand.score());
		
		hand.receiveACard(shoe.getTheCard(1));
		hand.displayCards();
		hand.logger.info("\nScore: " +  hand.score());

		hand.receiveACard(shoe.getTheCard(5));
		hand.displayCards();
		hand.logger.info("\nScore: " +  hand.score());
	}

}
