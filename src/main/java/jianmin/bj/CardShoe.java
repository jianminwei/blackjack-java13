package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/*
 * Note: don't use Card Count and Remove Cards functions together.
 * The card count won't be accurate if using remove cards and shuffle;
 */
public class CardShoe {
	Logger logger = LogManager.getLogger(CardShoe.class);
	private Deck[] decks;
	private Card[] cards;
	private int nextCard = 0;
	private int totalNumberOfCards = 0;
	private int numberOfDecks = 0;
	private CardCounter cardCounter;
	private int numberOfShuffles = 0;
	private boolean enableTrueCountPosition = false;
	private double trueCountPosition = 0;
	
	private double penetrationAdjust;
	
	// removedCardsBoundry is for testing remove
	// a specific card from the shoe to see the effect.
	private int removedCardsBoundry = 0;

	public int getUsedCardCount() {
		return this.nextCard; 
	}
	
	public int getNumberOfDecks() {
		return this.numberOfDecks;
	}

	public void setEnableTrueCountPosition(boolean enableTrueCountPosition) {
		this.enableTrueCountPosition = enableTrueCountPosition;
	}

	public boolean getEnableTrueCountPosition() {
		return this.enableTrueCountPosition;
	}

	public double getTrueCountPosition() {
		return trueCountPosition;
	}

	public void setTrueCountPosition(double trueCountPosition) {
		this.trueCountPosition = trueCountPosition;
	}

	public void setBettingPenetrationAdjust(double penetrationAdjust) {
		this.penetrationAdjust = penetrationAdjust;
	}
	
	public double betMultiplier() {
		double pen_adjust;
		
		pen_adjust = penetrationAdjust < this.getPenetration() ? penetrationAdjust : this.getPenetration() ;
		
		return this.cardCounter.getCount() / pen_adjust;
	}
	
	public double getTrueCount() {
		return this.cardCounter.getCount() / this.getPenetration();
	}
	
	public int getRunningCount() {
		return this.cardCounter.getCount();
	}

	public int getFloorTrueCount() {
		if (this.getTrueCount() >= 0) {
			return (int) Math.floor(this.getTrueCount());
		} else {
			//Note: When it's exact count, like -1, -2, -3 etc, the "+1"
			//logic won't work. To solve this problem, add "-0.01" to the 
			//true count value.
		
			return (int) (Math.floor(this.getTrueCount() - 0.01) + 1);
		}
	}

	public double getPenetration() {
		return (double) (totalNumberOfCards - nextCard) / 52;
	}
	
	public void initializeShoe(int numberOfdecks) {

		this.decks = new Deck[numberOfdecks];
		this.cardCounter = new HiLoCounter();
		this.totalNumberOfCards = numberOfdecks * 52;
		this.numberOfDecks = numberOfdecks;

		// initialize the decks
		for (int i = 0; i < decks.length; i++) {
			Deck d = new Deck();
			d.initCard();
			decks[i] = d;
		}

		// initialize the shoe
		this.cards = new Card[numberOfdecks * 52];

		int i = 0;
		for (Deck d : this.decks) {
			for (Card c : d.getCard()) {
				//give this card a unique identifier in this shoe
				//even when the shoe is shuffled later.
				c.setCardShoeInitialSequence(i);
				cards[i++] = c;
			}
		}

		shuffle();
	}

	public void traceCardShoeState() {
		logger.trace("<<<<<<<  Card Shoe Condition::");
		logger.trace("         Number of decks -> " + numberOfDecks);
		logger.trace("         Next Card Position -> " + nextCard);
		logger.trace("         Penetration -> " + getPenetration());
		logger.trace("         True Count -> " + getTrueCount());
		logger.trace("         Number of shuffles -> " + numberOfShuffles + "     >>>>>>\n");
	}
	
	public void traceCardShoeCount() {
		logger.trace("Shoe running count: " + getRunningCount() + ", True Count:" + getTrueCount());
	}

	public void moveToTrueCountPosition(double trueCount) {
		Card c;
		if (trueCount >= 0) {
			while (this.getTrueCount() < trueCount || this.getTrueCount() >= (trueCount + 1)) {
				c = this.getNextCard();
			}
		} else {
			while (this.getTrueCount() > trueCount || this.getTrueCount() <= (trueCount - 1)) {
				c = this.getNextCard();
			}
		}

		//traceCardShoeState();
	}

	public void shuffle() {
		Random rnd = new Random();

		// when shuffle, reset the nextCard pointer.
		nextCard = removedCardsBoundry;

		// reset the card count
		this.cardCounter.clearCount();

		// record the shuffle
		numberOfShuffles++;

		for (int i = cards.length - 1; i > nextCard; i--) {
			int index = nextCard + rnd.nextInt(i + 1 - nextCard);

			// simple swap
			Card c = cards[index];
			cards[index] = cards[i];
			cards[i] = c;
		}

	}

	public Card getNextCard() {

		if (shoeRemainPct() < 0.25) {
			shuffle();
			
			//if the shoe true count position is enabled, move to the position
			if(this.getEnableTrueCountPosition()) {
				this.moveToTrueCountPosition(this.getTrueCountPosition());
			}
		}

		Card c = cards[nextCard];
		nextCard++;

		// count the card
		cardCounter.count(c);

		return c;
	}
	
	//This method is used by GUI for strategy training.
	//It can be used to prevent dealer get a BlackJack.
	public Card getNonBlackJack2ndCard(Card firstCard) {
		
		Card c;

		c = this.getNextCard();
		
		if (firstCard.getValue() == 1) {
			while(c.getValue() == 10 ) {
				c = this.getNextCard();
			}
			
		} else if (firstCard.getValue() == 10 ) {
			while(c.getValue() == 1 ) {
				c = this.getNextCard();
			}
		} 

		return c;
	}
	
	public Card getTheCard(int cardValue) {
		Card c;

		shuffle();
		
		int i = nextCard;
		while (i < totalNumberOfCards) {
			if (cards[i].getValue() == cardValue) {
				c = cards[i];
				cards[i] = cards[nextCard];
				cards[nextCard] = c;
				break;
			}
			i++;
		}

		return getNextCard();
	}


	public void removeCard(int cardValue) {
		// shuffle ensure to reset the nextCard value to removedCardsBoundry
		this.shuffle();
		this.cards[removedCardsBoundry] = this.getTheCard(cardValue);
		this.removedCardsBoundry++;
	}

	public void removeCards(int cardValue, int howMany) {
		for (int i = 0; i < howMany; i++) {
			this.removeCard(cardValue);
		}
	}

	public void unRemoveCards() {
		this.removedCardsBoundry = 0;
		this.shuffle();
	}

	public Card[] getScoredCards(int score) {
		Card[] cs = new Card[2];

		Card firstCard = getNextCard();

		while (firstCard.getValue() == 1 || firstCard.getValue() > score - 2) {
			firstCard = getNextCard();
		}

		Card secondCard = getTheCard(score - firstCard.getValue());

		cs[0] = firstCard;
		cs[1] = secondCard;

		return cs;
	}

	public double shoeRemainPct() {
		return (double) (totalNumberOfCards - nextCard) / totalNumberOfCards;
	}

	public static void main(String[] args) {

		CardShoe cardShoe = new CardShoe();
		cardShoe.initializeShoe(8);
		// Card c;
		//
		// cardShoe.shuffle();
		// System.out.println("\n----After Shuffle----\n" );
		// int i = 1;
		// for (Card c1: cardShoe.cards) {
		// System.out.println("Card:" + i++ + " " + c1.toString());
		// }

		cardShoe.shuffle();
		// cardShoe.getTheCard(10);
		// cardShoe.getTheCard(10);
		// System.out.println(cardShoe.getTrueCount());
		// cardShoe.getTheCard(3);
		// System.out.println(cardShoe.getTrueCount());

		cardShoe.moveToTrueCountPosition(1);
		System.out.println("true count: " + cardShoe.getTrueCount() + ", floor true count:" + cardShoe.getFloorTrueCount());
		cardShoe.moveToTrueCountPosition(2);
		System.out.println("true count: " + cardShoe.getTrueCount() + ", floor true count:" + cardShoe.getFloorTrueCount());
		
		cardShoe.moveToTrueCountPosition(-1);
		System.out.println("true count: " + cardShoe.getTrueCount() + ", floor true count:" + cardShoe.getFloorTrueCount());
		cardShoe.moveToTrueCountPosition(-2);
		System.out.println("true count: " + cardShoe.getTrueCount() + ", floor true count:" + cardShoe.getFloorTrueCount());

	}

}
