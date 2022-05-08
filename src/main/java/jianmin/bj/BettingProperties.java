package jianmin.bj;

public class BettingProperties {
	public String strategyName;
	public double initialBankRoll=0;
	public double baseBetAmount=0;
	public double currentBalance=0;
	public CardShoe cardShoe=null;
	public double goal=0;
	public double maxLooseAmount=0;
	public boolean isGoalPlay=false;
	
	public double getNet() {
		return this.currentBalance - initialBankRoll;
	}
	
	public double getCurrentBalance() {
		return  currentBalance;
	}
	
	public double getInitialBankRoll() {
		return initialBankRoll;
	}

}
