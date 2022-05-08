package jianmin.bj;

public class BlackJack {
	public static void main(String[] args) {
		new BlackJack();
	}

	private Table t;
	private BlackJackDisplay display;
 
	public BlackJack() {

		this.t = new Table();
		
		t.getCardShoe().initializeShoe(8); 
		
		GoalProperties gp = new GoalProperties();
		t.setGoalTesting(false);
		
		t.getCardShoe().initializeShoe(8);

		
		//Strategy Section
		t.getCardShoe().setEnableTrueCountPosition(false);
		t.getCardShoe().setTrueCountPosition(-3);
		
		t.getPlayer().getStrategy().setCanEarlySurrender(false);
		t.getPlayer().getStrategy().setCanLateSurrender(true);

		t.getPlayer().getStrategy().setCanSurrenderOverride(true);
		t.getPlayer().getStrategy().setCanHardHandOverride(true);
		t.getPlayer().getStrategy().setCanSplitHandOverride(true);
		t.getPlayer().getStrategy().setCanSoftHandOverride(true);
		
		t.getPlayer().getStrategy().setStrategyOverrideTrueCountAdjust(0);
		t.getPlayer().getStrategy().setStrategyOverrideSelectLevel(5);
		
		//t.setGuiStrategyTraining(true);
		//t.setStrategyTraining(new StrategyTrainingIndex());
		//t.setGuiInclusiveTrainingHands(false);

		//Betting section
		BetCounterStrategy betting = new BetCounterStrategy(72404.5, 50);
		//BetFlatStrategy betting = new BetFlatStrategy(50000, 50);
		
		betting.setGoalPlay(betting.getBettingProperties(), false);
		betting.setGoal(betting.getBettingProperties(), 1000);
		betting.setMaxLooseAmount(betting.getBettingProperties(), -1000);
		betting.setCardShoe(betting.getBettingProperties(), t.getCardShoe());
		
		t.getCardShoe().setBettingPenetrationAdjust(1);
		
		t.setBetting(betting);
		
		this.display = new BlackJackDisplay(this);
		t.setDisplay(display);
	}
	
	public Table getTable() {
		return this.t;
	}
}