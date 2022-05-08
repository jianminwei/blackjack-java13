package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Strategy {
	static Logger logger = LogManager.getLogger(Strategy.class);

	// this property is for Scenario Testing purpose.
	// It's just for testing not splitting A
	private boolean canSplitA = true;
	private boolean canEarlySurrender = false;
	private boolean canLateSurrender = true;
	private boolean canSurrenderOverride = false;
	private boolean canHardHandOverride = false;
	private boolean canSplitHandOverride = false;
	private boolean canSoftHandOverride = false;
	
	public boolean canSoftHandOverride() {
		return canSoftHandOverride;
	}

	public void setCanSoftHandOverride(boolean canSoftHandOverride) {
		this.canSoftHandOverride = canSoftHandOverride;
	}

	public boolean canSplitHandOverride() {
		return canSplitHandOverride;
	}

	public void setCanSplitHandOverride(boolean canSplitHandOverride) {
		this.canSplitHandOverride = canSplitHandOverride;
	}

	public boolean canHardHandOverride() {
		return canHardHandOverride;
	}

	public void setCanHardHandOverride(boolean canHardHandOverride) {
		this.canHardHandOverride = canHardHandOverride;
	}

	public boolean canSurrenderOverride() {
		return canSurrenderOverride;
	}

	public void setCanSurrenderOverride(boolean canSurrenderOverride) {
		this.canSurrenderOverride = canSurrenderOverride;
	}

	public boolean canEarlySurrender() {
		return canEarlySurrender;
	}

	public void setCanEarlySurrender(boolean canEarlySurrender) {
		this.canEarlySurrender = canEarlySurrender;
	}

	public boolean canLateSurrender() {
		return canLateSurrender;
	}

	public void setCanLateSurrender(boolean canLateSurrender) {
		this.canLateSurrender = canLateSurrender;
	}

	public boolean canSplitA() {
		return canSplitA;
	}

	public void setCanSplitA(boolean canSplitA) {
		this.canSplitA = canSplitA;
	}

	static final int STAY = 0;
	static final int HIT = 1;
	static final int DOUBLE = 2;
	static final int SURRENDER = 3;
	static final int SPLIT = 4;
	static final int NOT_SPLIT = 5;
	static final int NOT_SURRENDER = 6;
	static final int UNKNOWN = -1;

	static HashMap<Integer, String> decisionMap = new HashMap<>();

	static {
		decisionMap.put(STAY, "STAY");
		decisionMap.put(HIT, "HIT");
		decisionMap.put(DOUBLE, "DOUBLE");
		decisionMap.put(SURRENDER, "SURRENDER");
		decisionMap.put(SPLIT, "SPLIT");
		decisionMap.put(NOT_SPLIT, "NOT_SPLIT");
		decisionMap.put(NOT_SURRENDER, "NOT_SURRENDER");
		decisionMap.put(UNKNOWN, "UNKNOWN");
	}

	public static String decisionToString(int decision) {
		return decisionMap.get(decision);
	}

	public static int ruleCharToDecision(char ruleChar) {
		int decision = UNKNOWN;

		if (ruleChar == 'H') {
			decision = HIT;
		} else if (ruleChar == 'S') {
			decision = STAY;
		} else if (ruleChar == 'D') {
			decision = DOUBLE;
		} else if (ruleChar == 'G') {
			decision = SURRENDER;
		} else if (ruleChar == 'P') {
			decision = SPLIT;
		} else if (ruleChar == 'N') {
			decision = NOT_SPLIT;
		} else if (ruleChar == 'Z') {
			decision = NOT_SURRENDER;
		}

		return decision;
	}

	/*
	 * This is combined setter for setting all strategies.
	 */
	public void setStrategy(int playerValue, int dealerFaceCard, char ruleChar, int whatStrategy) {
		int hashMapIndex = dealerFaceCard == 1 ? 9 : dealerFaceCard - 2;

		if (whatStrategy == MapUtil.HARD_HAND) {
			hardHandStategyMap.get(playerValue)[hashMapIndex] = ruleChar;
		} else if (whatStrategy == MapUtil.SOFT_HAND) {
			softHandStategyMap.get(playerValue)[hashMapIndex] = ruleChar;
		} else if (whatStrategy == MapUtil.AFTER_HIT_SOFT_HAND) {
			afterHitSoftHandStategyMap.get(playerValue)[hashMapIndex] = ruleChar;
		} else if (whatStrategy == MapUtil.SPLIT_HAND) {
			splitStategyMap.get(playerValue)[hashMapIndex] = ruleChar;
		} else if (whatStrategy == MapUtil.EARLY_SURRENDER_HAND) {
			earlySurrenderStategyMap.get(playerValue)[hashMapIndex] = ruleChar;
		} else if (whatStrategy == MapUtil.LATE_SURRENDER_HAND) {
			lateSurrenderStategyMap.get(playerValue)[hashMapIndex] = ruleChar;
		}
	}

	public char getStrategy(int playerValue, int dealerFaceCard, int whatStrategy) {
		int hashMapIndex = dealerFaceCard == 1 ? 9 : dealerFaceCard - 2;

		char strategy = 'X';

		if (whatStrategy == MapUtil.HARD_HAND) {
			strategy = hardHandStategyMap.get(playerValue)[hashMapIndex];
		} else if (whatStrategy == MapUtil.SOFT_HAND) {
			strategy = softHandStategyMap.get(playerValue)[hashMapIndex];
		} else if (whatStrategy == MapUtil.AFTER_HIT_SOFT_HAND) {
			strategy = afterHitSoftHandStategyMap.get(playerValue)[hashMapIndex];
		} else if (whatStrategy == MapUtil.SPLIT_HAND) {
			strategy = splitStategyMap.get(playerValue)[hashMapIndex];
		} else if (whatStrategy == MapUtil.EARLY_SURRENDER_HAND) {
			strategy = earlySurrenderStategyMap.get(playerValue)[hashMapIndex];
		} else if (whatStrategy == MapUtil.LATE_SURRENDER_HAND) {
			strategy = lateSurrenderStategyMap.get(playerValue)[hashMapIndex];
		}

		return strategy;
	}

	public String getStrategyString(int playerValue, int dealerFaceCard, int whatStrategy) {
		return decisionToString(ruleCharToDecision(getStrategy(playerValue, dealerFaceCard, whatStrategy)));
	}

	/*
	 * This is the Hard Hand Strategy Hash Map table.
	 */
	static HashMap<Integer, char[]> hardHandStategyMap = new HashMap<>();
	static {
		/*
		 * Hardhand rule Player Dealer Face Card Value 23456789TA -------
		 * ----------------- 3 HHHHHHHHHH 4 HHHHHHHHHH 5 HHHHHHHHHH 6 HHHHHHHHHH
		 * 7 HHHHHHHHHH 8 HHHHHHHHHH 9 HDDDDHHHHH 10 DDDDDDDDHH 11 DDDDDDDDHH 12
		 * HHSSSHHHHH 13 SSSSSHHHHH 14 SSSSSHHHHH 15 SSSSSHHHHH 16 SSSSSHHHHH 17
		 * SSSSSSSSSS 18 SSSSSSSSSS 19 SSSSSSSSSS 20 SSSSSSSSSS 21 SSSSSSSSSS
		 * 
		 * H = Hit S = Stand P = sPlit D = Double Down G = Give up (surrender if
		 * allowed,otherwise Hit) T = Ten-valued card
		 * 
		 */
		hardHandStategyMap.put(1,  "HHHHHHHHHH".toCharArray());
		hardHandStategyMap.put(2,  "HHHHHHHHHH".toCharArray());
		hardHandStategyMap.put(3,  "HHHHHHHHHH".toCharArray());
		hardHandStategyMap.put(4,  "HHHHHHHHHH".toCharArray());
		hardHandStategyMap.put(5,  "HHHHHHHHHH".toCharArray());
		hardHandStategyMap.put(6,  "HHHHHHHHHH".toCharArray());
		hardHandStategyMap.put(7,  "HHHHHHHHHH".toCharArray());
		hardHandStategyMap.put(8,  "HHHHHHHHHH".toCharArray());
		hardHandStategyMap.put(9,  "HDDDDHHHHH".toCharArray());
		hardHandStategyMap.put(10, "DDDDDDDDHH".toCharArray());
		hardHandStategyMap.put(11, "DDDDDDDDHH".toCharArray());
		hardHandStategyMap.put(12, "HHSSSHHHHH".toCharArray());
		hardHandStategyMap.put(13, "SSSSSHHHHH".toCharArray());
		hardHandStategyMap.put(14, "SSSSSHHHHH".toCharArray());
		hardHandStategyMap.put(15, "SSSSSHHHHH".toCharArray());
		hardHandStategyMap.put(16, "SSSSSHHHHH".toCharArray());
		hardHandStategyMap.put(17, "SSSSSSSSSS".toCharArray());
		hardHandStategyMap.put(18, "SSSSSSSSSS".toCharArray());
		hardHandStategyMap.put(19, "SSSSSSSSSS".toCharArray());
		hardHandStategyMap.put(20, "SSSSSSSSSS".toCharArray());
		hardHandStategyMap.put(21, "SSSSSSSSSS".toCharArray());
	}

	public int hardHandStrategy(CardShoe cardShoe, int playerValue, int dealerFaceCard, int playerCardCount) {
		int decision = UNKNOWN;

		logger.trace("Trace::hardHandStrategy-> player value:" + playerValue + " dealer FaceCard:" + dealerFaceCard);

		int hashMapIndex = dealerFaceCard == 1 ? 9 : dealerFaceCard - 2;

		// If player value greater than 21, use 21
		// because the strategy hash map goes only to 21
		playerValue = playerValue > 21 ? 21 : playerValue;

		char c = hardHandStategyMap.get(playerValue)[hashMapIndex];

		char cOverride = 'X';
		if (this.canHardHandOverride()) {
			cOverride = this.getStrategyOverride(MapUtil.HARD_HAND, cardShoe.getFloorTrueCount(), playerValue, dealerFaceCard);
		}
		c = cOverride == 'X' ? c : cOverride;

		// If player card count is not 2, it is not initial 2 cards.
		// It can't double or surrender
		if (c == 'D') {
			c = playerCardCount == 2 ? c : 'H';
		}

		decision = ruleCharToDecision(c);
		logger.trace("Trace::hardHandStrategy-> Decision:" + decisionToString(decision));

		return decision;
	}

	/*
	 * This is the Early Surrender Strategy Hash Map table.
	 */
	static HashMap<Integer, char[]> earlySurrenderStategyMap = new HashMap<>();
	static {
		/*
		 * Early Surrender rule Player Dealer Face Card Value 23456789TA -------
		 * ----------------- 3 HHHHHHHHHH 4 HHHHHHHHHH 5 HHHHHHHHHG 6 HHHHHHHHHG
		 * 7 HHHHHHHHHG 8 HHHHHHHHHH 9 HHHHHHHHHH 10 HHHHHHHHHH 11 HHHHHHHHHH 12
		 * HHHHHHHHHG 13 HHHHHHHHHG 14 HHHHHHHHGG 15 HHHHHHHHGG 16 HHHHHHHGGG 17
		 * SSSSSSSSSG 18 SSSSSSSSSS 19 SSSSSSSSSS 20 SSSSSSSSSS 21 SSSSSSSSSS
		 * 
		 * H = Hit S = Stand P = sPlit D = Double Down G = Give up (surrender if
		 * allowed,otherwise Hit) T = Ten-valued card
		 * 
		 */

		earlySurrenderStategyMap.put(1,  "HHHHHHHHHH".toCharArray());
		earlySurrenderStategyMap.put(2,  "HHHHHHHHHH".toCharArray());
		earlySurrenderStategyMap.put(3,  "HHHHHHHHHH".toCharArray());
		earlySurrenderStategyMap.put(4,  "HHHHHHHHHH".toCharArray());
		earlySurrenderStategyMap.put(5,  "HHHHHHHHHG".toCharArray());
		earlySurrenderStategyMap.put(6,  "HHHHHHHHHG".toCharArray());
		earlySurrenderStategyMap.put(7,  "HHHHHHHHHG".toCharArray());
		earlySurrenderStategyMap.put(8,  "HHHHHHHHHH".toCharArray());
		earlySurrenderStategyMap.put(9,  "HHHHHHHHHH".toCharArray());
		earlySurrenderStategyMap.put(10, "HHHHHHHHHH".toCharArray());
		earlySurrenderStategyMap.put(11, "HHHHHHHHHH".toCharArray());
		earlySurrenderStategyMap.put(12, "HHHHHHHHHG".toCharArray());
		earlySurrenderStategyMap.put(13, "HHHHHHHHHG".toCharArray());
		earlySurrenderStategyMap.put(14, "HHHHHHHHGG".toCharArray());
		earlySurrenderStategyMap.put(15, "HHHHHHHHGG".toCharArray());
		earlySurrenderStategyMap.put(16, "HHHHHHHGGG".toCharArray());
		earlySurrenderStategyMap.put(17, "SSSSSSSSSG".toCharArray());
		earlySurrenderStategyMap.put(18, "SSSSSSSSSS".toCharArray());
		earlySurrenderStategyMap.put(19, "SSSSSSSSSS".toCharArray());
		earlySurrenderStategyMap.put(20, "SSSSSSSSSS".toCharArray());
		earlySurrenderStategyMap.put(21, "SSSSSSSSSS".toCharArray());

	}

	/*
	 * This is the Early Surrender Strategy Hash Map table.
	 */
	static HashMap<Integer, char[]> lateSurrenderStategyMap = new HashMap<>();
	static {
		/*
		 * Early Surrender rule Player Dealer Face Card Value 23456789TA -------
		 * ----------------- 3 HHHHHHHHHH 4 HHHHHHHHHH 5 HHHHHHHHHH 6 HHHHHHHHHH
		 * 7 HHHHHHHHHH 8 HHHHHHHHHH 9 HHHHHHHHHH 10 HHHHHHHHHH 11 HHHHHHHHHH 12
		 * HHHHHHHHHH 13 HHHHHHHHHH 14 HHHHHHHHHH 15 HHHHHHHHGH 16 HHHHHHHGGG 17
		 * SSSSSSSSSS 18 SSSSSSSSSS 19 SSSSSSSSSS 20 SSSSSSSSSS 21 SSSSSSSSSS
		 * 
		 * H = Hit S = Stand P = sPlit D = Double Down G = Give up (surrender if
		 * allowed,otherwise Hit) T = Ten-valued card
		 * 
		 */

		lateSurrenderStategyMap.put(1,  "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(2,  "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(3,  "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(4,  "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(5,  "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(6,  "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(7,  "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(8,  "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(9,  "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(10, "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(11, "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(12, "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(13, "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(14, "HHHHHHHHHH".toCharArray());
		lateSurrenderStategyMap.put(15, "HHHHHHHHGH".toCharArray());
		lateSurrenderStategyMap.put(16, "HHHHHHHGGG".toCharArray());
		lateSurrenderStategyMap.put(17, "SSSSSSSSSS".toCharArray());
		lateSurrenderStategyMap.put(18, "SSSSSSSSSS".toCharArray());
		lateSurrenderStategyMap.put(19, "SSSSSSSSSS".toCharArray());
		lateSurrenderStategyMap.put(20, "SSSSSSSSSS".toCharArray());
		lateSurrenderStategyMap.put(21, "SSSSSSSSSS".toCharArray());

	}

	public int surrenderStrategy(CardShoe cardShoe, Hand hand, int dealerFaceCard, Dealer dealer) {
		int decision = UNKNOWN;
		char c = 'Z';

		if (hand.cardCount() != 2) { // passed initial 2 cards, can't surrender
			//logger.trace("Trace::Surrender Strategy-> player has " + hand.cardCount() + " cards, can not surrender");
			decision = NOT_SURRENDER;
		} else if (hand.isInitialSofthand()) {
			// logger.trace("Trace::Surrender Strategy-> player has Soft Hand,
			// no surrender");
			decision = NOT_SURRENDER;
		} else if (hand.isPairOf(8) && dealer.getFaceCardValue() == 9) {  
			// Player a pair of 8 not surrender to dealer 9
			decision = NOT_SURRENDER;
		}else { // Initial 2 cards state and not soft hand
			int hashMapIndex = dealerFaceCard == 1 ? 9 : dealerFaceCard - 2;
			int playerValue = hand.score();

			char cOverride = 'X';
			if (canEarlySurrender()) {
				c = earlySurrenderStategyMap.get(playerValue)[hashMapIndex];

				if (this.canSurrenderOverride()) {
					cOverride = this.getStrategyOverride(MapUtil.EARLY_SURRENDER_HAND, cardShoe.getFloorTrueCount(),
							playerValue, dealerFaceCard);
				}
			} else if (canLateSurrender() && !dealer.isBlackJack()) {
				//cardShoe.traceCardShoeState();
				c = lateSurrenderStategyMap.get(playerValue)[hashMapIndex];

				if (this.canSurrenderOverride()) {
					cOverride = this.getStrategyOverride(MapUtil.LATE_SURRENDER_HAND, cardShoe.getFloorTrueCount(),
							playerValue, dealerFaceCard);
				}
			}

			c = cOverride == 'X' ? c : cOverride;

			// if c is not surrender "G", convert to 'Z' not surrender
			// this is because our surrender map table has all other
			// values for not surrender
			c = c == 'G' ? c : 'Z';

			decision = ruleCharToDecision(c);
		}

		// below is just for displaying trace message
		if (canEarlySurrender() && c == 'G') {
			logger.trace("Trace::Early Surrender Strategy-> player value:" + hand.score() + " dealer FaceCard:"
					+ dealerFaceCard);
			logger.trace("Trace::Early Surrender Strategy-> Decision:" + decisionToString(decision));
		} else if (canLateSurrender() && !dealer.isBlackJack() && c == 'G') {
			logger.trace("Trace::Late Surrender Strategy-> player value:" + hand.score() + " dealer FaceCard:"
					+ dealerFaceCard);
			logger.trace("Trace::Late Surrender Strategy-> Decision:" + decisionToString(decision));
		}

		return decision;
	}

	/*
	 * This is the Soft Hand Strategy Hash Map table.
	 */
	static HashMap<Integer, char[]> softHandStategyMap = new HashMap<>();
	static {
		/*
		 * Softhand rule Player Dealer Face Card Value 23456789TA -------
		 * ----------------- 
		 * 12 HHHHHHHHHH //AA it should never be used when Split strategy used 
		 * 13 HHHHDHHHHH //A2 
		 * 14 HHHDDHHHHH //A3 
		 * 15 HHHDDHHHHH //A4 
		 * 16 HHDDDHHHHH //A5 
		 * 17 HDDDDHHHHH //A6 
		 * 18 SDDDDSSHHH //A7 
		 * 19 SSSSSSSSSS //A8 
		 * 20 SSSSSSSSSS //A9 
		 * 21 SSSSSSSSSS //A10
		 * 
		 * H = Hit S = Stand P = sPlit D = Double Down G = Give up (surrender if
		 * allowed,otherwise Hit) T = Ten-valued card
		 * 
		 */
		softHandStategyMap.put(12, "HHHHHHHHHH".toCharArray());
		softHandStategyMap.put(13, "HHHHDHHHHH".toCharArray()); //A2 double on 6
		softHandStategyMap.put(14, "HHHDDHHHHH".toCharArray()); //A3 double on 5,6
		softHandStategyMap.put(15, "HHHDDHHHHH".toCharArray()); //A4 double on 5,6
		softHandStategyMap.put(16, "HHDDDHHHHH".toCharArray()); //A5 double on 4,5,6
		softHandStategyMap.put(17, "HDDDDHHHHH".toCharArray()); //A6 double on 3,4,5,6
		softHandStategyMap.put(18, "SDDDDSSHHH".toCharArray()); //A7 double on 3,4,5,6; stay on 2,7,8;Hit on 9,10,A
		softHandStategyMap.put(19, "SSSSSSSSSS".toCharArray()); //A8 always stay
		softHandStategyMap.put(20, "SSSSSSSSSS".toCharArray());
		softHandStategyMap.put(21, "SSSSSSSSSS".toCharArray());
	}

	/*
	 * This is the After Hit Soft Hand Strategy Hash Map table.
	 */
	static HashMap<Integer, char[]> afterHitSoftHandStategyMap = new HashMap<>();
	static {
		/*
		 * Softhand rule Player Dealer Face Card Value 23456789TA -------
		 * ----------------- 3 HHHHHHHHHH 4 HHHHHHHHHH 5 HHHHHHHHHH 6 HHHHHHHHHH
		 * 7 HHHHHHHHHH 8 HHHHHHHHHH 9 HHHHHHHHHH 10 HHHHHHHHHH 11 HHHHHHHHHH 12
		 * HHHHHHHHHH 13 HHHHHHHHHH 14 HHHHHHHHHH 15 HHHHHHHHHH //A4 16
		 * HHHHHHHHHH //A5 17 HHHHHHHHHH //A6 18 SSSSSSSHHH //A7 19 SSSSSSSSSS
		 * //A8 20 SSSSSSSSSS //A9 21 SSSSSSSSSS //A10
		 * 
		 * H = Hit S = Stand P = sPlit D = Double Down G = Give up (surrender if
		 * allowed,otherwise Hit) T = Ten-valued card
		 * 
		 */
		afterHitSoftHandStategyMap.put(1,  "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(2,  "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(3,  "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(4,  "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(5,  "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(6,  "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(7,  "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(8,  "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(9,  "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(10, "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(11, "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(12, "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(13, "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(14, "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(15, "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(16, "HHHHHHHHHH".toCharArray());
		afterHitSoftHandStategyMap.put(17, "HHHHHHHHHH".toCharArray());  //Always hit when after-hit 17
		afterHitSoftHandStategyMap.put(18, "SSSSSSSHHH".toCharArray());  //hit on 9,10 A when after-hit 18
		afterHitSoftHandStategyMap.put(19, "SSSSSSSSSS".toCharArray());
		afterHitSoftHandStategyMap.put(20, "SSSSSSSSSS".toCharArray());
		afterHitSoftHandStategyMap.put(21, "SSSSSSSSSS".toCharArray());
	}

	public int softHandStrategy(CardShoe cardShoe, int playerValue, int dealerFaceCard, int playerCardCount) {
		int decision = UNKNOWN;

		int hashMapIndex = dealerFaceCard == 1 ? 9 : dealerFaceCard - 2;

		// If player value greater than 21, use 21
		// because the strategy hash map goes only to 21
		playerValue = playerValue > 21 ? 21 : playerValue;

		char c = 'X';
		char cOverride = 'X';
		
		if (playerCardCount > 2) { // afterhit softhand
			logger.trace("Trace::AfterHit softHandStrategy-> player value:" + playerValue + " dealer FaceCard:"
					+ dealerFaceCard);
			c = afterHitSoftHandStategyMap.get(playerValue)[hashMapIndex];
		} else { // Initial softhand
			logger.trace("Trace::Initial softHandStrategy-> player value:" + playerValue + " dealer FaceCard:"
					+ dealerFaceCard);
			c = softHandStategyMap.get(playerValue)[hashMapIndex];
			
			if (this.canSoftHandOverride()) {
				cOverride = this.getStrategyOverride(MapUtil.SOFT_HAND, cardShoe.getFloorTrueCount(), playerValue, dealerFaceCard);
			}
			c = cOverride == 'X' ? c : cOverride;
		}

		decision = ruleCharToDecision(c);

		logger.trace("Trace::softHandStrategy-> Decision:" + decisionToString(decision));

		return decision;
	}

	/*
	 * This is the Soft Hand Strategy Hash Map table.
	 */
	static HashMap<Integer, char[]> splitStategyMap = new HashMap<>();
	static {
		/*
		 * Split rule Player Dealer Face Card Value 23456789TA -------
		 * ----------------- 
		 * 4 PPPPPPHHHH //22 
		 * 6 PPPPPPHHHH //33 
		 * 8 HHHPPHHHHH //44 
		 * 10 HHHHHHHHHH //55 
		 * 12 PPPPPHHHHH //66 or AA 
		 * 14 PPPPPPHHHH //77
		 * 16 PPPPPPPPPP //88 
		 * 18 PPPPPSPPSS //99 
		 * 20 SSSSSSSSSS //10 10
		 * 
		 * P = sPlit D = Double Down G = Give up (surrender if allowed,otherwise
		 * Hit) T = Ten-valued card
		 * 
		 */
		splitStategyMap.put(4,  "PPPPPPHHHH".toCharArray()); //2 2 split 2 to 7
		splitStategyMap.put(6,  "PPPPPPPHHH".toCharArray()); //3 3 split 2 to 8
		splitStategyMap.put(8,  "HHHPPHHHHH".toCharArray()); //4 4 split 5,6
		splitStategyMap.put(10, "HHHHHHHHHH".toCharArray()); //5 5 never split
		splitStategyMap.put(12, "PPPPPHHHHH".toCharArray()); //6 6 split 2 to 6
		splitStategyMap.put(14, "PPPPPPHHHH".toCharArray()); //7 7 split 2 to 7, just like 2 2
		splitStategyMap.put(16, "PPPPPPPPGG".toCharArray()); //8 8 split up to 9. Surrender on 10 A
		splitStategyMap.put(18, "PPPPPSPPSS".toCharArray()); //9 9 split on all except 7,10,A
		splitStategyMap.put(20, "SSSSSSSSSS".toCharArray());
	}

	public int splitStrategy(CardShoe cardShoe, Hand hand, int dealerFaceCard) {
		int decision = UNKNOWN;
		logger.trace("Trace::Split Strategy-> player value:" + hand.score() + " dealer FaceCard:" + dealerFaceCard);

		char c = 'X';

		if (hand.cardCount() != 2 || !hand.isPair()) { // passed initial 2
														// cards, can't split
			logger.trace("Trace::Split Strategy-> player has " + hand.cardCount() + ", can not split");
			decision = NOT_SPLIT;
		} else { // Initial 2 cards state, and is a pair.
			int hashMapIndex = dealerFaceCard == 1 ? 9 : dealerFaceCard - 2;
			int playerValue = hand.score();

			c = splitStategyMap.get(playerValue)[hashMapIndex];

			// AA or 66 (due to the way our scoring logic is)
			if (playerValue == 12 && hand.getFirstCard().getValue() == 1 && canSplitA()) { 
				c = 'P';
			}
			
			char cOverride = 'X';
			if (this.canSplitHandOverride()) {
				cOverride = this.getStrategyOverride(MapUtil.SPLIT_HAND, cardShoe.getFloorTrueCount(), playerValue, dealerFaceCard);
			}
			c = cOverride == 'X' ? c : cOverride;

			// if c is not split, convert to 'N' not split
			// this is because our split map table has all other
			// values for not split
			c = c == 'P' ? c : 'N';

			decision = ruleCharToDecision(c);
		}
		logger.trace("Trace::Split Strategy-> Decision:" + decisionToString(decision));

		return decision;
	}

	private HashMap<String, Integer> strategyOverrideHistogram = new HashMap<>();
	
	public HashMap<String, Integer> getStrategyOverrideHistogram() {
		return this.strategyOverrideHistogram;
	}
	
	public void printStrategyOverrideHistogram() {
        ArrayList<String> keys =  new ArrayList<>(this.strategyOverrideHistogram.keySet());
        Collections.sort(keys);
        
        System.out.println("\nStrategy Override Histogram:");
        for (String key: keys) {
            System.out.printf("%s %d\n", key, this.strategyOverrideHistogram.get(key));
        }
    }
	
	public char getStrategyOverride(int whatKindOfHand, int floorTrueCount, int playerValue, int dealerFaceCard) {
		char ruleChar = 'X';
		
		String key = MapUtil.mapToString(whatKindOfHand) + "," + playerValue + ","	+ dealerFaceCard;

		if (trueCountOverrideMap.get(key) != null) {
			ruleChar = getTrueCountQualifiedRuleChar(floorTrueCount, trueCountOverrideMap.get(key));
			
			if (ruleChar != 'X') {
				logger.trace("Trace::True Count Strategy Override-> " + MapUtil.mapToString(whatKindOfHand)
					+ ", Floor True Count: " + floorTrueCount);
				logger.trace("Trace::True Count Strategy Override-> player value:" + playerValue + " dealer FaceCard:"
					+ dealerFaceCard + " Override Decision: " + ruleChar);
				
				//track the override usage using the histogram
				//Use String.valueOf(charArray) to convert Char array back to String.
				String histoKey = key + " : " + String.valueOf(trueCountOverrideMap.get(key));
				if (strategyOverrideHistogram.containsKey(histoKey)) {
					strategyOverrideHistogram.put(histoKey, strategyOverrideHistogram.get(histoKey) + 1);
	            } else {
	            	strategyOverrideHistogram.put(histoKey, 1);
	            }
			}
		}
		return ruleChar;
	}
	
	/**
	This property is added for adjusting the TrueCount override level.
	When the TrueCount override just start, the benefit may not be big.
	This adjustment is for delay the override more to get the bigger benefit.
	 */
	private int strategyOverrideTrueCountAdjust = 0;
	
	public void setStrategyOverrideTrueCountAdjust(int strategyOverrideTrueCountAdjust) {
		this.strategyOverrideTrueCountAdjust = strategyOverrideTrueCountAdjust;
	}
	
	
	/**
	 * This property is used for selecting the TrueCount override levels
	 * defined at the end of override rule, level 1 - 5
	*/
	private int strategyOverrideSelectLevel = 5;
	
	public int getStrategyOverrideSelectLevel() {
		return strategyOverrideSelectLevel;
	}

	public void setStrategyOverrideSelectLevel(int strategyOverrideSelectLevel) {
		this.strategyOverrideSelectLevel = strategyOverrideSelectLevel;
	}
	
	public char getTrueCountQualifiedRuleChar(int trueCount, char[] v) {
		char ruleChar = 'X';
		
		String sTrueCount;
		String sOverrideLevel;

		if (v.length < 9 ) {
			return ruleChar;
		} else {
			sTrueCount=Character.toString(v[2]) + Character.toString(v[3]);
			sOverrideLevel = Character.toString(v[8]);
		}
		
		if(Integer.parseInt(sOverrideLevel) <= strategyOverrideSelectLevel ) {
			if (v[0] == '>' && v[1] == '=') {
				ruleChar = trueCount >= Integer.parseInt(sTrueCount) + strategyOverrideTrueCountAdjust ? v[4] : 'X';
			}else if (v[0] == '<' && v[1] == '=') {
				ruleChar = trueCount <= Integer.parseInt(sTrueCount) - strategyOverrideTrueCountAdjust ? v[4] : 'X';
			}else if (v[0] == '=' && v[1] == '=') {
				ruleChar = trueCount == Integer.parseInt(sTrueCount) ? v[4] : 'X';
			}
		}

		return ruleChar;
	}
	
	static HashMap<String, char[]> trueCountOverrideMap = new HashMap<>();
	static {
		/*
		 * 
		 * Definition of the Map
		 * 
		 * Key: WhatKindOfHand,PlayerValue,DealerFaceCard 
		 * 
		 * Value: Decision Character array
		 *   char position 1 - 2: >= | <= | ==
		 *   char position 3: + | -
		 *   char position 4: True Count absolutely value, like 2, 3
		 *   char position 5: Decision Character, like G, H, S, D
		 *   char position 6: fixed char ':' as a separator
		 *   char position 7: V -- for Verify. This is used by the GUI Training Test Hands generator to signal
		 *                         generate test hand or not. V -- generate. U -- not generate.
		 *                    U -- for not generate.
		 *   char position 8: fixed char ':' as a separator
		 *   char position 9: 1 - 5 as level.
		 *                    Level 1 -- Above 1000 hit in histogram
		 *                    Level 2 -- Above 500 and below 1000 hit in histogram
		 *                    Level 3 -- Above 100 and below 500 hit in histogram
		 *                    Level 4 -- Above 50 and below 100 hit in histogram
		 *                    Level 5 -- Below 50 hit in histogram
		 * 
		 * For example:
		 * 
		 * "Hard-Hand,12,3", ">=-2G"
		 * 
		 * We use this 1 Map to store all TrueCount Override rule for Hard Hard,
		 * Soft Hand, After-Hit Soft Hand, Split.
		 * 
		 * 'Z' -- Not Surrender 'G' -- Surrender 'N' -- Not Split 'P' -- Split
		 * 'H' -- Hit 'S' -- Stay 'D' -- Double
		 * 
		 */
		
		//Late Surrender override Map
		//Late Surrender begins when True Count start with -3 (at 16-10)
		//There is no Late Surrender when True Count below -4
		//
		//      8   9   10    A
		// -- ---- --- ---- ----
		// 14           +3
		// 15      +3    0   +2
		// 16  +5   0   -3   -1
		// 
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.LATE_SURRENDER_HAND)) + ",15" + ",1",  ">=+2G:V:3".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.LATE_SURRENDER_HAND)) + ",15" + ",9",  ">=+3G:V:3".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.LATE_SURRENDER_HAND)) + ",14" + ",10", ">=+3G:V:1".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.LATE_SURRENDER_HAND)) + ",16" + ",8",  ">=+5G:V:4".toCharArray());
		
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.LATE_SURRENDER_HAND)) + ",16" + ",9",  "<=-1H:V:1".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.LATE_SURRENDER_HAND)) + ",15" + ",10", "<=-1H:V:1".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.LATE_SURRENDER_HAND)) + ",16" + ",1",  "<=-2H:V:2".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.LATE_SURRENDER_HAND)) + ",16" + ",10", "<=-4H:V:2".toCharArray());
		
		//split hand strategy override
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",20" + ",5",  ">=+2P:V:1".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",20" + ",6",  ">=+2P:V:1".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",8"  + ",4",  ">=+3P:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",4"  + ",8",  ">=+4P:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",18" + ",7",  ">=+4P:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",20" + ",4",  ">=+4P:V:3".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",20" + ",3",  ">=+6P:V:3".toCharArray());
		
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",8"  + ",5",  "<=-1H:V:4".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",6"  + ",2",  "<=-2H:V:4".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",12" + ",2",  "<=-2H:V:3".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",8"  + ",6",  "<=-3H:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",6"  + ",3",  "<=-4H:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",6"  + ",8",  "<=-4H:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",18" + ",2",  "<=-4S:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",12" + ",3",  "<=-5H:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",18" + ",3",  "<=-5S:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",6"  + ",4",  "<=-6H:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SPLIT_HAND)) + ",18" + ",4",  "<=-6S:V:5".toCharArray());
		
		//Hard Hand Double Override
		//       2      3      4      5      6      7      8      9     10      A
		//     
		//  8                        +5D    +2D
		//  9   +1D    -1D    -3D    -4D      D    +4D
		// 10     D      D      D      D      D      D    -5D    -1D    
		// 11     D      D      D      D      D      D      D    -5D    +3D
		//	
		
		//Hard Hand Strategy Override
		//       2      3      4      5      6      7      8      9     10      A
		//    ------ ------ ------- ------ ------ ------ ------ ------ ------ ------
		//  8                        +5D    +2D
		//  9   +1D    -2H    -4H    -5H           +4D
		// 10                                             -6H    -2H    +3D
		// 11                                                    -6H
		// 12   +3S    +2S    -1H    -2H    -2H
		// 13   -1H    -3H    -4H    -6H    -6H
		// 14   -5H    -6H        
     
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",9"  + ",2",  ">=+1D:V:1".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",12" + ",3",  ">=+2S:V:1".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",8"  + ",6",  ">=+2D:V:3".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",12" + ",2",  ">=+3S:V:2".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",11" + ",10", ">=+3D:V:1".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",9"  + ",7",  ">=+4D:V:3".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",8"  + ",5",  ">=+5D:V:4".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",16" + ",10", ">=+1S:U:1".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",15" + ",10", ">=+4S:U:2".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",16" + ",9",  ">=+5S:U:2".toCharArray());
		
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",12"  + ",4",  "<=-1H:V:1".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",13"  + ",2",  "<=-1H:V:1".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",12"  + ",5",  "<=-2H:V:2".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",12"  + ",6",  "<=-2H:V:2".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",9"   + ",3",  "<=-2H:V:3".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",10"  + ",9",  "<=-2H:V:3".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",13"  + ",3",  "<=-3H:V:2".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",9"   + ",4",  "<=-4H:V:4".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",13"  + ",4",  "<=-4H:V:3".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",9"   + ",5",  "<=-5H:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",14"  + ",2",  "<=-5H:V:3".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",10"  + ",8",  "<=-6H:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",11"  + ",9",  "<=-6H:V:5".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",13"  + ",5",  "<=-6H:V:4".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",13"  + ",6",  "<=-6H:V:4".toCharArray());
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.HARD_HAND)) + ",14"  + ",3",  "<=-6H:V:4".toCharArray());
		
		//Softhand strategy override
		//Soft Hand Double Override
		//       2      3      4      5      6      7      8      9     10      A
		//     
		// A2                 +4D    +1D    -1D
		// A3                 +3D    -1D    -5D     
		// A4                 +1D    -4D      D           
		// A5          +3D    -4D      D      D       
		// A6          -4D      D      D      D
		// A7   +1D    -3D    -5D      D      D
		// A8
		// A9
		
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",13"  + ",5",  ">=+1D:V:3".toCharArray()); //A2
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",15"  + ",4",  ">=+1D:V:3".toCharArray()); //A4
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",18"  + ",2",  ">=+1D:V:3".toCharArray()); //A7
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",18"  + ",1",  ">=+1S:V:3".toCharArray()); //A7
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",19"  + ",6",  ">=+1D:V:3".toCharArray()); //A8
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",19"  + ",5",  ">=+2D:V:3".toCharArray()); //A8
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",14"  + ",4",  ">=+3D:V:4".toCharArray()); //A3
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",16"  + ",3",  ">=+3D:V:4".toCharArray()); //A5
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",13"  + ",4",  ">=+4D:V:5".toCharArray()); //A2
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",19"  + ",4",  ">=+4D:V:4".toCharArray()); //A8
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",20"  + ",6",  ">=+5D:V:5".toCharArray()); //A9
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",19"  + ",3",  ">=+6D:V:5".toCharArray()); //A8
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",20"  + ",5",  ">=+6D:V:5".toCharArray()); //A9
		
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",13"  + ",6",  "<=-2H:V:3".toCharArray()); //A2
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",14"  + ",5",  "<=-2H:V:3".toCharArray()); //A3
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",18"  + ",3",  "<=-4S:V:5".toCharArray()); //A7
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",15"  + ",5",  "<=-5H:V:5".toCharArray()); //A7
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",16"  + ",4",  "<=-5H:V:5".toCharArray()); //A7
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",17"  + ",3",  "<=-5H:V:5".toCharArray()); //A7
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",14"  + ",6",  "<=-6H:V:5".toCharArray()); //A3
		trueCountOverrideMap.put(MapUtil.mapToString((MapUtil.SOFT_HAND)) + ",18"  + ",4",  "<=-6S:V:5".toCharArray()); //A7
	}
	

	public static void main(String[] args) {

		Strategy s = new Strategy();

//		System.out.println(s.getStrategyOverride(MapUtil.LATE_SURRENDER_HAND, 2, 15, 1));
//		System.out.println(s.getStrategyOverride(MapUtil.LATE_SURRENDER_HAND, 1, 15, 1));
//		
//		System.out.println(s.getStrategyOverride(MapUtil.LATE_SURRENDER_HAND, 3, 15, 9));
//		System.out.println(s.getStrategyOverride(MapUtil.LATE_SURRENDER_HAND, 3, 14, 10));
//		System.out.println(s.getStrategyOverride(MapUtil.LATE_SURRENDER_HAND, 5, 16, 8));
//		
//		System.out.println(s.getStrategyOverride(MapUtil.LATE_SURRENDER_HAND, -1, 16, 9));
//		System.out.println(s.getStrategyOverride(MapUtil.LATE_SURRENDER_HAND, -1, 15, 10));
//		
//		System.out.println(s.getStrategyOverride(MapUtil.LATE_SURRENDER_HAND, -2, 16, 1));
//		System.out.println(s.getStrategyOverride(MapUtil.LATE_SURRENDER_HAND, -5, 16, 10));
//		
//		System.out.println(s.getStrategyOverride(MapUtil.HARD_HAND, 1, 9, 2));
//		
//		System.out.println(s.getStrategyOverride(MapUtil.SPLIT_HAND, -1, 8, 5));
//		System.out.println(s.getStrategyOverride(MapUtil.SPLIT_HAND, -2, 6, 2));
//		System.out.println(s.getStrategyOverride(MapUtil.SPLIT_HAND, -2, 12, 2));
//		System.out.println(s.getStrategyOverride(MapUtil.SPLIT_HAND, -3, 8, 6));
//		System.out.println(s.getStrategyOverride(MapUtil.SPLIT_HAND, -4, 6, 3));
//		System.out.println(s.getStrategyOverride(MapUtil.SPLIT_HAND, -4, 6, 8));
//		System.out.println(s.getStrategyOverride(MapUtil.SPLIT_HAND, -4, 18, 2));
//		System.out.println(s.getStrategyOverride(MapUtil.SPLIT_HAND, -5, 12, 3));
//		System.out.println(s.getStrategyOverride(MapUtil.SPLIT_HAND, -5, 18, 3));
//		System.out.println(s.getStrategyOverride(MapUtil.SPLIT_HAND, -6, 6, 4));
//		System.out.println(s.getStrategyOverride(MapUtil.SPLIT_HAND, -6, 18, 4));
		
		CardShoe shoe = new CardShoe();
		shoe.initializeShoe(8);
		Hand hand = new Hand();
		Card c1 = new Card(1, "S");
		Card c2 = new Card(8, "S");
		hand.receiveACard(c1);
		hand.receiveACard(c2);
		shoe.moveToTrueCountPosition(0);
		
		s.softHandStrategy(shoe, 19, 1, 2);
		System.out.println(s.softHandStrategy(shoe, 19, 1, 2));
	}

}
