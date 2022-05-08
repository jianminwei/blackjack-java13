package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetFlatStrategy implements Betting {
	Logger logger = LogManager.getLogger(BetFlatStrategy.class);
	
	BettingProperties bp;
	
	//don't allow default construct being used.
	private BetFlatStrategy() {
		super();
	}
	
	public BetFlatStrategy(double initialBankRoll, double flatBetAmount) {
		super();
		
		bp = new BettingProperties();
		bp.strategyName = "Flat-Bet";
		bp.initialBankRoll = initialBankRoll;
		bp.currentBalance = initialBankRoll;
		bp.baseBetAmount = flatBetAmount;
		
	}

	@Override
	public BettingProperties getBettingProperties() {
		return bp;
	}
	
	@Override
	public double bet() {
		
		bp.currentBalance -= bp.baseBetAmount;
		logger.trace("Trace::FlatBetting bet:" + bp.baseBetAmount + ", balance:" + bp.currentBalance);
		return bp.baseBetAmount;
	}
}
