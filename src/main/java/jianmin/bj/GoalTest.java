package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoalTest {

	Logger logger = LogManager.getLogger(Table.class);

	public static void main(String[] args) {
		
		GoalProperties gp = new GoalProperties();
		
		int gamesPlayed=0;
		for (int i = 0; i < 1000; i++) {
			
			gamesPlayed++;
			
			Table t = new Table();
			t.setGoalTesting(true);
			
			t.getCardShoe().initializeShoe(8);

			t.getPlayer().getStrategy().setCanEarlySurrender(false);
			t.getPlayer().getStrategy().setCanLateSurrender(true);

			t.getCardShoe().setEnableTrueCountPosition(false);
			t.getCardShoe().setTrueCountPosition(2);
			
			t.getPlayer().getStrategy().setCanSurrenderOverride(true);
			t.getPlayer().getStrategy().setCanHardHandOverride(true);
			t.getPlayer().getStrategy().setCanSplitHandOverride(true);
			t.getPlayer().getStrategy().setCanSoftHandOverride(true);
			
			t.getPlayer().getStrategy().setStrategyOverrideTrueCountAdjust(0);
			t.getPlayer().getStrategy().setStrategyOverrideSelectLevel(6);

			BetCounterStrategy betting = new BetCounterStrategy(50000, 50);
			//BetFlatStrategy betting = new BetFlatStrategy(50000, 50);
			
			betting.setGoalPlay(betting.getBettingProperties(), true);
			betting.setGoal(betting.getBettingProperties(), 2500);
			betting.setMaxLooseAmount(betting.getBettingProperties(), -2500);
			betting.setCardShoe(betting.getBettingProperties(), t.getCardShoe());
			
			t.setBetting(betting);

			t.playNTimes(1000000, gp);
			
			if (i==0) {
				gp.setGoal(betting.getGoal(betting.getBettingProperties()));
				gp.setMaxLooseAmount(betting.getMaxLooseAmount(betting.getBettingProperties()));
				gp.setFlatBetAmount(betting.getBaseBetAmount(betting.getBettingProperties()));
			}
			
		}
		
		System.out.println("Goal $:" + gp.getGoal());
		System.out.println("Max Loose Amount $:" + gp.getMaxLooseAmount());
		System.out.println("Bet Base Amount $:" + gp.getFlatBetAmount());
		System.out.println("Total Times Played:" + gamesPlayed);
		System.out.println("Goal Achieved Count:" + gp.getGoalAchievedCount());
		System.out.println("Max Loose Count:" + gp.getMaxLooseCount());
		System.out.println("Net Win $: " + gp.getCurrentBalance());
	}
}
