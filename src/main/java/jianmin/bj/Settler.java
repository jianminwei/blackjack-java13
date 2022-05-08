package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Settler {
	static Logger logger = LogManager.getLogger(Settler.class);
	
	//A static service method to provide BlackJack settle service.
	public static int settle(int playerDecision, Hand playerHand, Dealer dlr, CardShoe shoe, Betting betting) {
		int playResult = MapUtil.UNKNOWN;
		
		// dealer draw cards under 17
		while (dlr.score() < 17) {
			logger.info("Dealer Score " + dlr.score() + ", still need to hit");
			Card c = shoe.getNextCard();
			logger.info("Dealer got card: " + c.getValue());
			dlr.receiveACard(c);
		}
		logger.info("Dealer Score " + dlr.score() + ", no need to hit anymore");		

		if (playerDecision != MapUtil.DECISION_SURRENDER && playerHand.score() <= 21) {

			if (playerDecision == MapUtil.DECISION_DOUBLE) {
				if (dlr.score() > 21) {
					playResult = MapUtil.PLAYER_DOUBLE_WIN;
				} else if (playerHand.score() > dlr.score()) {
					playResult = MapUtil.PLAYER_DOUBLE_WIN;
				} else if (playerHand.score() < dlr.score()) {
					playResult = MapUtil.PLAYER_DOUBLE_LOOSE;
				} else if (playerHand.score() == dlr.score()) {

					if (dlr.isBlackJack()) {
						//It should never come here if the strategy is not double on dealer
						//A and 10. But if you double on 10 (player 11), it will come here.
						playResult = MapUtil.PLAYER_DOUBLE_LOOSE_DEALER_BLACKJACK;
					} else {
						playResult = MapUtil.PLAYER_DOUBLE_PUSH;
					}
				}

			} else if (playerDecision == MapUtil.DECISION_STAY) {
				if (playerHand.isBlackJack() && !dlr.isBlackJack()) {
					playResult = MapUtil.PLAYER_BLACKJACK_WIN;
				} else if (playerHand.isBlackJack() && dlr.isBlackJack()) {
					playResult = MapUtil.PLAYER_BLACKJACK_PUSH;
				} else if (dlr.score() > 21) {
					playResult = MapUtil.DEALER_BUST;
				} else if (playerHand.score() > dlr.score()) {
					playResult = MapUtil.PLAYER_WIN;
				} else if (playerHand.score() < dlr.score()) {
					if (dlr.isBlackJack()) {
						playResult = MapUtil.DEALER_BLACKJACK_WIN;
					} else {
						playResult = MapUtil.DEALER_WIN;
					}

				} else if (playerHand.score() == dlr.score()) {
					if (dlr.isBlackJack()) {
						playResult = MapUtil.DEALER_BLACKJACK_WIN;
					} else {
						playResult = MapUtil.PUSH;
					}
				}

			} else if (playerDecision == MapUtil.DECISION_HIT) {
				if (dlr.isBlackJack()) {
					playResult = MapUtil.DEALER_BLACKJACK_WIN;
				} else if (dlr.score() > 21) {
					playResult = MapUtil.DEALER_BUST;
				} else if (playerHand.score() > dlr.score()) {
					playResult = MapUtil.PLAYER_WIN;
				} else if (playerHand.score() < dlr.score()) {
					playResult = MapUtil.DEALER_WIN;
				} else if (playerHand.score() == dlr.score()) {
					playResult = MapUtil.PUSH;
				}

			}

		} else { //player surrender or score > 21
			
			if (playerDecision == MapUtil.DECISION_SURRENDER) {
				playResult = MapUtil.PLAYER_SURRENDER;
			} else if (playerHand.score() > 21) {
				//In normal case, player double should never bust, but for scenario testing
				//I add this for testing doulbe on player value greater than 11. So there could 
				//be cases double and bust.
				if (playerDecision == MapUtil.DECISION_DOUBLE) {
					playResult = MapUtil.PLAYER_DOUBLE_LOOSE;
				}else if (dlr.isBlackJack() ) {
					playResult = MapUtil.PLAYER_BUST_DEALER_BLACKJACK;
				} else {
					playResult = MapUtil.PLAYER_BUST;
				}
			}
		}
		
		//for scenario testing, most of them don't do betting (betting == null),
		//so we skip the money settlement for them.
		if (betting != null) {
			settleMoney( playResult, betting, playerHand);
		}

		return playResult;
	}
	
	//A static service method to provide "Settle Money" service.
	private static void settleMoney(int playResult, Betting betting, Hand hand) {
		
		if (playResult == MapUtil.PLAYER_WIN) {
			//get the original bet, and the winning.
			betting.win(betting.getBettingProperties(),hand.getBet() * 2, "Player-Win");
		} else if (playResult == MapUtil.PLAYER_DOUBLE_WIN) {
			double doubleBet = betting.doubleOrSplitBet(betting.getBettingProperties(),hand.getBet());
			betting.win(betting.getBettingProperties(),(hand.getBet() + doubleBet) * 2, "Player-Double-Win");
		} else if (playResult == MapUtil.PLAYER_DOUBLE_LOOSE) {
			//pay the double bet
			betting.doubleOrSplitBet(betting.getBettingProperties(),hand.getBet());
			logger.trace("Trace::Player double loose: " + hand.getBet() * 2  +  ", balance:" + betting.getCurrentBalance(betting.getBettingProperties()));
		} else if (playResult == MapUtil.PLAYER_DOUBLE_PUSH) {
			//get the original bet back
			betting.win(betting.getBettingProperties(),hand.getBet(), "Double-Push, get original bet back");
		} else if (playResult == MapUtil.PLAYER_SURRENDER) {
			//get half of the original bet back
			betting.win(betting.getBettingProperties(),hand.getBet() * 0.5, "Player-Surrender, get half bet back");
		} else if (playResult == MapUtil.DEALER_WIN) {
			//no need to do anything, just loose the original bet
			logger.trace("Trace::Dealer Win: " + hand.getBet()  +  ", balance:" + betting.getCurrentBalance(betting.getBettingProperties()));
		} else if (playResult == MapUtil.PUSH) {
			//get the original bet back
			betting.win(betting.getBettingProperties(),hand.getBet(), "Push, get original bet back");
		} else if (playResult == MapUtil.DEALER_BUST) {
			betting.win(betting.getBettingProperties(),hand.getBet() * 2, "Dealer-Bust");
		} else if (playResult == MapUtil.PLAYER_BUST) {
			//no need to do anything, just loose the original bet
			logger.trace("Trace::Player Bust, Dealer Win: " + hand.getBet()  +  ", balance:" + betting.getCurrentBalance(betting.getBettingProperties()));
		} else if (playResult == MapUtil.PLAYER_BLACKJACK_WIN) {
			//get the original bet, plus 1 and half times winning
			betting.win(betting.getBettingProperties(),hand.getBet() + hand.getBet() * 1.5, "Player BlackJack-Win");
		} else if (playResult == MapUtil.PLAYER_BLACKJACK_PUSH) {
			//get the original bet back
			betting.win(betting.getBettingProperties(),hand.getBet(), "BlackJack-Push, get original bet back" );
		} else if (playResult == MapUtil.DEALER_BLACKJACK_WIN) {
			//no need to do anything, just loose the original bet
			logger.trace("Trace::Dealer BlackJack Win: " + hand.getBet()  +  ", balance:" + betting.getCurrentBalance(betting.getBettingProperties()));
		} else if (playResult == MapUtil.PLAYER_BUST_DEALER_BLACKJACK) {
			//no need to do anything, just loose the original bet
			logger.trace("Trace::Player Bust & Dealer BlackJack Win: " + hand.getBet()  +  ", balance:" + betting.getCurrentBalance(betting.getBettingProperties()));
		} else if (playResult == MapUtil.PLAYER_DOUBLE_LOOSE_DEALER_BLACKJACK) {
			//need to pay the double bet
			betting.doubleOrSplitBet(betting.getBettingProperties(),hand.getBet());
			logger.trace("Trace::Player double loose & Dealer BlackJack Win: " + hand.getBet()  +  ", balance:" + betting.getCurrentBalance(betting.getBettingProperties()));
		}
	}
}
