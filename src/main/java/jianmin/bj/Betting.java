package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Betting {
	Logger logger = LogManager.getLogger(Betting.class);
	
	public double bet();
	
	public BettingProperties getBettingProperties();
	
	default double betMultiplier() {
		return 1.0;
	}
	
	
	default void setCardShoe(BettingProperties bp, CardShoe cardShoe) {
		bp.cardShoe = cardShoe;
	}
	
	default CardShoe getCardShoe(BettingProperties bp) {
		return bp.cardShoe;
	}
	
	default double bet(BettingProperties bp, double betAmount) {
		
		bp.currentBalance -= betAmount;
		logger.trace("Trace::" + bp.strategyName + " bet specific amount:" + betAmount + ", balance:" + bp.currentBalance);
		return betAmount;
	}
	
    default double returnBet(BettingProperties bp, double betAmount) {
		
		bp.currentBalance += betAmount;
		logger.trace("Trace::" + bp.strategyName + " return bet amount:" + betAmount + ", balance:" + bp.currentBalance);
		return betAmount;
	}

	default void setMaxLooseAmount(BettingProperties bp, double maxLooseAmount) {
		bp.maxLooseAmount = maxLooseAmount;
	}
	
	default double doubleOrSplitBet(BettingProperties bp, double betAmount)
	 {
		bp.currentBalance -= betAmount;
		logger.trace("Trace::" + bp.strategyName + " double or split bet:" + betAmount + ", balance:" + bp.currentBalance);

		return betAmount;
	}
	
	default void win(BettingProperties bp, double winAmount, String forWhat) {
		bp.currentBalance += winAmount;
		logger.trace("Trace::" + bp.strategyName + " " + forWhat + ":" + winAmount  +  ", balance:"+ bp.currentBalance);
	}
	
	default double getCurrentBalance(BettingProperties bp) {
		return  bp.currentBalance;
	}
	
	default double getGoal(BettingProperties bp) {
		return  bp.goal;
	}
	
	default double getBaseBetAmount(BettingProperties bp) {
		return  bp.baseBetAmount;
	}
	
	default double getMaxLooseAmount(BettingProperties bp) {
		return  bp.maxLooseAmount;
	}
	
	default double getInitialBankRoll(BettingProperties bp) {
		return bp.initialBankRoll;
	}
	
	default double getNet(BettingProperties bp) {
		return bp.currentBalance - bp.initialBankRoll;
	}
	
	default String getBetStrategyName(BettingProperties bp) {
		return bp.strategyName;
	}
	
	default boolean achievedGoal(BettingProperties bp) {
		return bp.getNet() >= bp.goal;
	}
	
	default boolean isGoalPlay(BettingProperties bp) {
		return bp.isGoalPlay;
	}
	
	default void setGoalPlay(BettingProperties bp, boolean isGoalPlay) {
		bp.isGoalPlay = isGoalPlay;
	}
	
	default void setGoal(BettingProperties bp, double goal) {
		bp.goal = goal;
	}
	
	default boolean isLostMaxAmount(BettingProperties bp) {
		return bp.getNet() < bp.maxLooseAmount;
	}
	
	default void displaySummaryResult(BettingProperties bp) {
		System.out.println("\n" + getBetStrategyName(bp) + " Betting Strategy Results::");
		System.out.println("Original bank roll: $" + bp.getInitialBankRoll());
		System.out.println("Base Bet Size: $" + bp.baseBetAmount);
		System.out.println("Remaining balance: $" + bp.getCurrentBalance());
		System.out.println("Net: $" + bp.getNet());
		System.out.println("Percentage: " + 100.00 * bp.getNet() / bp.getInitialBankRoll());
	}

}
