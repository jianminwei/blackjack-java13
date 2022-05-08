package jianmin.bj;

public class GoalProperties {
	public String strategyName;
	public double initialBankRoll=0;
	public double flatBetAmount=0;
	public double currentBalance=0;
	public double goal=0;
	public double maxLooseAmount=0;
	public int goalAchievedCount=0;
	public int maxLooseCount=0;
	
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	public double getInitialBankRoll() {
		return initialBankRoll;
	}
	public void setInitialBankRoll(double initialBankRoll) {
		this.initialBankRoll = initialBankRoll;
	}
	public double getFlatBetAmount() {
		return flatBetAmount;
	}
	public void setFlatBetAmount(double flatBetAmount) {
		this.flatBetAmount = flatBetAmount;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void addCurrentBalance(double amt) {
		this.currentBalance += amt;
	}
	public double getGoal() {
		return goal;
	}
	public void setGoal(double goal) {
		this.goal = goal;
	}
	public double getMaxLooseAmount() {
		return maxLooseAmount;
	}
	public void setMaxLooseAmount(double maxLooseAmount) {
		this.maxLooseAmount = maxLooseAmount;
	}
	public int getGoalAchievedCount() {
		return goalAchievedCount;
	}
	public void incrementGoalAchievedCount() {
		this.goalAchievedCount += 1;
	}
	public int getMaxLooseCount() {
		return maxLooseCount;
	}
	public void incrementMaxLooseCount() {
		this.maxLooseCount += 1;
	}

}
