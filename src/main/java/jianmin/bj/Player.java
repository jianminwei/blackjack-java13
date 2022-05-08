package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Player {

	static Logger logger = LogManager.getLogger(Player.class);

	private Hand hand1 = new Hand();
	private Strategy strategy = new Strategy();

	public Hand getHand1() {
		return hand1;
	}
	
	public Strategy getStrategy() {
		return strategy;
	}
	
	//This method is a static service method. It really has nothing to do with a player instance.
	//I put it here because it is related to playing BJ games. Just think of a player has the playing
	//BJ game skills.
	public static void playTheGame(Hand hand, Player player, Dealer dealer, CardShoe cardShoe,
			Betting betting, Strategy strategy, BlackJackUtil bjUtil) {
		int playerDecision = -1;
		int finalValue = -1;

		// the hand can be from a split hand.
		// In that case, the first hand already bet.
		if (hand.getBet() == 0 && hand.getParentHand() != null) {
			double bet = hand.getParentHand().getBet();
			logger.trace("Trace::Splitted Hand Bet Amount:" + bet);
			hand.setBet(betting.bet(betting.getBettingProperties(), bet));
		}

		int strategyDecision = strategy.surrenderStrategy(cardShoe, hand, dealer.getFaceCard().getValue(), dealer);
		//cardShoe.traceCardShoeState();

		if (strategyDecision == Strategy.SURRENDER) {
			logger.trace("Trace::Player Decided to Surrender");

			finalValue = Settler.settle(MapUtil.DECISION_SURRENDER, hand, dealer, cardShoe, betting);
			bjUtil.recordGame(finalValue);
			bjUtil.displayGameResult(hand, dealer, finalValue);

		} else {

			if (hand.isPair()) { // pair
				if (strategy.splitStrategy(cardShoe, hand, dealer.getFaceCard().getValue()) == Strategy.SPLIT) {
					logger.trace("Trace::Split a pair of " + hand.getFirstCard().getValue() + ", dealer face card is "
							+ dealer.getFaceCard().getValue());

					Hand splittedHand = hand.splitHand();

					Card c = cardShoe.getNextCard();
					logger.trace("Trace::hand 1 got card:" + c.getValue());
					hand.receiveACard(c);

					c = cardShoe.getNextCard();
					logger.trace("Trace::hand 2 got card:" + c.getValue());
					splittedHand.receiveACard(c);

					// Hand 1 play the game
					playTheGame(hand, player, dealer, cardShoe, betting, strategy, bjUtil);

					logger.info("\n..........Playing Splitted Hand..........\n");

					// Splitted hand play the game
					playTheGame(splittedHand, player, dealer, cardShoe, betting, strategy, bjUtil);

				} else { // not split
					playerDecision = Player.playAHand(hand, dealer.getFaceCard().getValue(), cardShoe, strategy);
					finalValue = Settler.settle(playerDecision, hand, dealer, cardShoe, betting);
					bjUtil.recordGame(finalValue);
					bjUtil.displayGameResult(hand, dealer, finalValue);
				}
			} else { // not a pair
				playerDecision = Player.playAHand(hand, dealer.getFaceCard().getValue(), cardShoe, strategy);
				finalValue = Settler.settle(playerDecision, hand, dealer, cardShoe, betting);
				bjUtil.recordGame(finalValue);
				bjUtil.displayGameResult(hand, dealer, finalValue);
			}
		}
	}
	
	// A static method for playing a hand of BlackJack game
	// when the "hand" passed to this method, it is assumed
	// that the hand is already dealt with initial 2 cards.
	public static int playAHand(Hand hand, int dealerFaceCard, CardShoe cardShoe, Strategy strategy) {

		int return_value = -100;
		int strategyDecision = Strategy.UNKNOWN;
		
		if (hand.isSplitedAceHand()){
			logger.trace("Trace::Splitted Ace, already got a card " + hand.getSecondCard().getValue() + ", cant hit anymore");
			return MapUtil.DECISION_STAY;
		}

		if (hand.isInitialSofthand() || hand.isAfterHitSofthand()) {
			strategyDecision = strategy.softHandStrategy(cardShoe, hand.score(), dealerFaceCard, hand.cardCount());
		} else {
			strategyDecision = strategy.hardHandStrategy(cardShoe, hand.score(), dealerFaceCard, hand.cardCount());
		}
		
		if (strategyDecision == Strategy.STAY) {
			logger.trace("Trace::Player Decided to Stay");
			return_value = MapUtil.DECISION_STAY;
		} else if (strategyDecision == Strategy.DOUBLE) {
			logger.trace("Trace::Player Decided to double");
			Card card = cardShoe.getNextCard();
			hand.receiveACard(card);
			logger.trace("Trace::Player got card: " + card.getValue());
			return_value = MapUtil.DECISION_DOUBLE;

		} else if (strategyDecision == Strategy.HIT ){
			while (true) {
				logger.trace("Trace::Player Decided to hit");

				Card c = cardShoe.getNextCard();
				logger.trace("Trace::Player got card:" + c.getValue());
				hand.receiveACard(c);
				
				if (hand.isAfterHitSofthand()) {
					if (strategy.softHandStrategy(cardShoe, hand.score(), dealerFaceCard, hand.cardCount()) != Strategy.HIT) {
						logger.trace("Trace::Player stopped hitting");
						break;
					}
				} else { //the hand become a hardhand
					if (strategy.hardHandStrategy(cardShoe, hand.score(), dealerFaceCard, hand.cardCount()) != Strategy.HIT) {
						logger.trace("Trace::Player stopped hitting");
						break;
					}					
				}
			}
			return_value = MapUtil.DECISION_HIT;
		}

		return return_value;
	}
	
	//this method is created for the GUI game for verifying decision purpose.
	//It is a static service method. It really has nothing to do with a player instance.
	public static int getTheDecision(Hand hand, Player player, Dealer dealer, CardShoe cardShoe,
			Betting betting, Strategy strategy) {
		int playerDecision = -1;

		int strategyDecision = strategy.surrenderStrategy(cardShoe, hand, dealer.getFaceCard().getValue(), dealer);

		if (strategyDecision == Strategy.SURRENDER) {
			logger.trace("Trace::Strategy to Surrender");
			return Strategy.SURRENDER;
		} 
		
		if (hand.isPair() && hand.cardCount() == 2) { // pair
			if (strategy.splitStrategy(cardShoe, hand, dealer.getFaceCard().getValue()) == Strategy.SPLIT) {
				logger.trace("Trace::Strategy to Split a pair of " + hand.getFirstCard().getValue() + ", dealer face card is "
						+ dealer.getFaceCard().getValue());
				return Strategy.SPLIT;
			} 
		} 
		
		if (hand.isInitialSofthand() || hand.isAfterHitSofthand()) { //softhand
			if (hand.isSplitedAceHand() ){
				if (hand.getCards().size() == 2) {
					logger.trace("Trace::Splitted Ace, already got a card " + hand.getSecondCard().getValue() + ", cant hit anymore");
					return Strategy.STAY;
				}else if (hand.getCards().size() == 1) {
					logger.trace("Trace::Splitted Ace, can get 1 card " );
					return Strategy.HIT;
				} else {//it should never come here
				}
			} else { //normal softhad
			    return strategy.softHandStrategy(cardShoe, hand.score(), dealer.getFaceCard().getValue(), hand.cardCount());
			}
		}
		
		// If No Surrender, No Split, No Soft Hand play, it will come here for the hard hand play
		playerDecision = strategy.hardHandStrategy(cardShoe, hand.score(), dealer.getFaceCard().getValue(), hand.cardCount());
		
		return playerDecision;
	}

}
