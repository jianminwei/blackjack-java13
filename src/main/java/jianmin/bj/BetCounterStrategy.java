package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetCounterStrategy implements Betting {
	Logger logger = LogManager.getLogger(BetCounterStrategy.class);
	BettingProperties bp;
	
	//don't allow default construct being used.
	private BetCounterStrategy() {
		super();
	}
	
	public BetCounterStrategy(double initialBankRoll, double baseBetAmount) {
		super();
		bp = new BettingProperties();
		bp.strategyName = "TrueCount-Bet";
		bp.initialBankRoll = initialBankRoll;
		bp.currentBalance = initialBankRoll;
		bp.baseBetAmount = baseBetAmount;
	}
	
	@Override
	public BettingProperties getBettingProperties() {
		return bp;
	}

	@Override
	public double bet() {
		double betAmount = 0;
		betAmount = bp.baseBetAmount * betMultiplier();
		bp.currentBalance -= betAmount;
		logger.trace("Trace::TrueCount Bet Multiplier:" + betMultiplier() + ", True-count:" + bp.cardShoe.getTrueCount());
		logger.trace("Trace::TrueCount Bet:" + betAmount + ", balance:" + bp.currentBalance );
		return betAmount;
	}
	
	@Override 
	public double betMultiplier() {
		double multiplier = 0;
		
		double shoeBetMultiplier = bp.cardShoe.betMultiplier();
		
		//Below betting schedule is tested. It is easy to remember, and
		//manageable at casinos. Use $50 as base, max goes to $600. When count
		//is negative, use half the base, $25. This is normally above most of the
		//minimum bet levels. When count is positive, every 5 count increase bet 
		//4 times until max 12 times.
		
		if(shoeBetMultiplier >= 20 ) {
			multiplier = 12;
		}else if(shoeBetMultiplier >= 15 && shoeBetMultiplier < 20) {
			multiplier = 12;
		}else if(shoeBetMultiplier >= 10 && shoeBetMultiplier < 15) {
			multiplier = 8;
		}else if(shoeBetMultiplier >= 5 && shoeBetMultiplier < 10) {
			multiplier = 4;
		}else if(shoeBetMultiplier >= 0 && shoeBetMultiplier < 5) {
			multiplier = 1;
		}else if (shoeBetMultiplier >= -5 && shoeBetMultiplier < 0){
			multiplier = 0.5;
		}else if (shoeBetMultiplier < -5 ){
			multiplier = 0.5;
		}
		
		//logger.trace("Trace::TrueCount Bet Multiplier:" + multiplier + ", True-count:" + bp.cardShoe.getTrueCount());
		return multiplier;
	}
	
	
	public double betMultiplier_orig() {
		double multiplier = 0;
		
		double shoeBetMultiplier = bp.cardShoe.betMultiplier();
		
		if(shoeBetMultiplier >= 1 ) {
			multiplier = Math.floor(shoeBetMultiplier);
		}else if (shoeBetMultiplier >= -3 && shoeBetMultiplier < 1){
			multiplier = 1.0;
		}else if (shoeBetMultiplier >= -6 && shoeBetMultiplier < -3){
			multiplier = 0.5;
		}else if (shoeBetMultiplier >= -9 && shoeBetMultiplier < -6){
			multiplier = 0.25;
		}else if (shoeBetMultiplier < -9){
			multiplier = 0.1;
		}
		
		logger.trace("Trace::TrueCount Bet Multiplier:" + multiplier + ", True-count:" + bp.cardShoe.getTrueCount());
		return multiplier;
	}
	
	public double bet_orig() {
		double betAmount = 0;
		
		
		/** Original bet logic
		if(bp.cardShoe.getTrueCount() >= 1 ) {
			betAmount = bp.flatBetAmount * Math.floor(bp.cardShoe.getTrueCount());
		} else {
			betAmount = bp.cardShoe.getTrueCount() <= -2.0 ? Math.floor(bp.flatBetAmount/ (-1 * Math.floor(bp.cardShoe.getTrueCount()))) : bp.flatBetAmount;
		}
		*/
		
		if(bp.cardShoe.getTrueCount() >= 1 ) {
			betAmount = bp.baseBetAmount * Math.floor(bp.cardShoe.getTrueCount());
		} else {
			betAmount = bp.cardShoe.getTrueCount() <= -2.0 ? bp.baseBetAmount/ 2 : bp.baseBetAmount;
		}
		
		bp.currentBalance -= betAmount;
		logger.trace("Trace::TrueCount Bet:" + betAmount + ", balance:" + bp.currentBalance + ", True-count:" + bp.cardShoe.getTrueCount());
		return betAmount;
	}

}
