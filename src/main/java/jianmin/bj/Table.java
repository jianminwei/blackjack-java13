package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Table {

	Logger logger = LogManager.getLogger(Table.class);

	private Player player = new Player();
	private Dealer dealer = new Dealer();
	private CardShoe cardShoe = new CardShoe();
	private BlackJackUtil bjUtil = new BlackJackUtil();
	private Betting betting;
	private StrategyTraining strategyTraining;
	private boolean isGoalTesting = false;
	private boolean isGuiStrategyTraining = false;
	private boolean isGuiInclusiveTrainingHands = true;
	
	public StrategyTraining getStrategyTraining() {
		return strategyTraining;
	}

	public void setStrategyTraining(StrategyTraining strategyTest) {
		this.strategyTraining = strategyTest;
	}

	public boolean isGuiInclusiveTrainingHands() {
		return isGuiInclusiveTrainingHands;
	}

	public void setGuiInclusiveTrainingHands(boolean isGuiInclusiveTrainingHands) {
		this.isGuiInclusiveTrainingHands = isGuiInclusiveTrainingHands;
	}

	public boolean isGuiStrategyTraining() {
		return isGuiStrategyTraining;
	}

	public void setGuiStrategyTraining(boolean isGuiStrategyTraining) {
		this.isGuiStrategyTraining = isGuiStrategyTraining;
	}

	private BlackJackDisplay display;
	
	public void setDisplay(BlackJackDisplay display) {
		this.display = display;
	}
	
	public boolean isGoalTesting() {
		return isGoalTesting;
	}

	public void setGoalTesting(boolean isGoalTesting) {
		this.isGoalTesting = isGoalTesting;
	}

	public Player getPlayer() {
		return player;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public BlackJackUtil getBjUtil() {
		return bjUtil;
	}

	public Betting getBetting() {
		return betting;
	}
	
	public void setBetting(Betting betting) {
		this.betting = betting;
	}

	public CardShoe getCardShoe() {
		return this.cardShoe;
	}

	public BlackJackUtil getBlackJackUtil() {
		return this.bjUtil;
	}

	public void playNTimes(int n, GoalProperties gp) {
		int gamePlayed=0;
		
		for (int i = 0; i < n; i++) {
			logger.info("\n======== Game: " + (i + 1) + " ========");
			play1Round();
			// reconcileMoney();
			
			gamePlayed++;
			
			if(betting.isGoalPlay(betting.getBettingProperties())) {
				if(betting.achievedGoal(betting.getBettingProperties())) {
					gp.addCurrentBalance( betting.getNet(betting.getBettingProperties()));
					gp.incrementGoalAchievedCount();
					
					if(this.isGoalTesting()) {
						return;
					} else {
						System.out.println("\nGoal Achieved");
						break;
					}
				}
				
				if(betting.isLostMaxAmount(betting.getBettingProperties())) {
					gp.addCurrentBalance( betting.getNet(betting.getBettingProperties()));
					gp.incrementMaxLooseCount();

					if(this.isGoalTesting()) {
						return;
					} else {
						System.out.println("\nLost Max Amount");
						break;
					}

				}
			}
		}
		
		if(!this.isGoalTesting()) {
			bjUtil.displayTotalGameResultByTitle(getTitle(gamePlayed));;
		}

	}

	public void play1Round() {

		player.getHand1().clearHands();
		dealer.clearHands();

		if (cardShoe.getEnableTrueCountPosition()) {
			cardShoe.moveToTrueCountPosition(cardShoe.getTrueCountPosition());
		}
		
		//Bet
		player.getHand1().setBet(betting.bet());

		player.getHand1().receiveACard(cardShoe.getNextCard());
		dealer.receiveACard(cardShoe.getNextCard());
		player.getHand1().receiveACard(cardShoe.getNextCard());
		dealer.receiveACard(cardShoe.getNextCard());

		Player.playTheGame(player.getHand1(), player, dealer, cardShoe, betting, player.getStrategy(), bjUtil);
	}
	
	/**
	 * This method is used by GUI
	 */
	public void deal() {
		player.getHand1().clearHands();
		dealer.clearHands();
		
		//logger.info("\n\n <<< NEW GAME >>>");
		
		//Trace shoe counts
		if(!this.isGuiStrategyTraining()) {
			this.getCardShoe().traceCardShoeCount();
		}

		if (isGuiStrategyTraining()) {
			StrategyTrainingHand testHand = 
					this.getStrategyTraining().getAHand(
					this.getPlayer().getStrategy().getStrategyOverrideSelectLevel(),
					this.isGuiInclusiveTrainingHands());
			player.getHand1().receiveACard(cardShoe.getTheCard(testHand.getCard1Value()));
			player.getHand1().receiveACard(cardShoe.getTheCard(testHand.getCard2Value()));
			
			//Note: The rational of moving to the true count position is that to really
			//mimic the real playing situation. 
			cardShoe.moveToTrueCountPosition(testHand.getTrueCount());
			
			//Note: this check is to make sure the card is not in
			//player's hand because of the True count move.
			Card c = cardShoe.getTheCard(testHand.getDealerFaceCard());
			while(player.getHand1().isCardOnHand(c)) {
				c = cardShoe.getTheCard(testHand.getDealerFaceCard());
			}
			dealer.receiveACard(c);	
			
			//For strategy training, don't let dealer get BlackJack.
			c = cardShoe.getNonBlackJack2ndCard(dealer.getFaceCard());
			while(player.getHand1().isCardOnHand(c)) {
				c = cardShoe.getNonBlackJack2ndCard(dealer.getFaceCard());
			}
			dealer.receiveACard(c);
			cardShoe.moveToTrueCountPosition(testHand.getTrueCount());
		} else {
			if (cardShoe.getEnableTrueCountPosition()) {
				cardShoe.moveToTrueCountPosition(cardShoe.getTrueCountPosition());
			}
	
			player.getHand1().receiveACard(cardShoe.getNextCard());
			dealer.receiveACard(cardShoe.getNextCard());
			player.getHand1().receiveACard(cardShoe.getNextCard());
			dealer.receiveACard(cardShoe.getNextCard());
		}
	}
	
	/**
	 * This method is used by GUI
	 */
	public void playerHit(Hand hand) {
		hand.receiveACard(cardShoe.getNextCard());
	}
	
	/**
	 * This method is used by GUI
	 */
	public void dealerDrawCards() {
		while (this.dealer.score() < 17) {
			logger.info("Dealer Score " + this.dealer.score() + ", still need to hit");
			Card c = this.cardShoe.getNextCard();
			logger.info("Dealer got card: " + c.getValue());
			this.dealer.receiveACard(c);
		}
		//logger.info("Dealer Score " + this.dealer.score() + ", no need to hit anymore");		
	}
	
	public String getTitle(int nTimes) {

		String sTrueCountClause = "";
		if (this.cardShoe.getEnableTrueCountPosition()) {
			sTrueCountClause = " with TrueCount Position Enabled At: " + this.cardShoe.getTrueCountPosition();
		} else {
			sTrueCountClause = " with TrueCount Position Disabled";
		}

		return "\n====== Total " + nTimes + " games Played " + sTrueCountClause + " ======";
	}


	private void reconcileMoney() {
		if (bjUtil.getNet() != (betting.getCurrentBalance(betting.getBettingProperties())
				- betting.getInitialBankRoll(betting.getBettingProperties()))) {
			logger.trace("Trace::Settler RECONCILIATION ERROR, bjUtil Amt: " + bjUtil.getNet() + "Betting Amt:"
					+ (betting.getCurrentBalance(betting.getBettingProperties())
							- betting.getInitialBankRoll(betting.getBettingProperties())));

			logger.trace(
					"Trace::Betting Current Balance: " + betting.getCurrentBalance(betting.getBettingProperties()));
		}
	}

	public static void main(String[] args) {

		Table t = new Table();
		GoalProperties gp = new GoalProperties();
		t.setGoalTesting(false);
		
		t.getCardShoe().initializeShoe(8);

		t.getCardShoe().setEnableTrueCountPosition(false);
		t.getCardShoe().setTrueCountPosition(1);
		
		t.getPlayer().getStrategy().setCanEarlySurrender(false);
		t.getPlayer().getStrategy().setCanLateSurrender(true);

		t.getPlayer().getStrategy().setCanSurrenderOverride(true);
		t.getPlayer().getStrategy().setCanHardHandOverride(true);
		t.getPlayer().getStrategy().setCanSplitHandOverride(true);
		t.getPlayer().getStrategy().setCanSoftHandOverride(true);
		
		t.getPlayer().getStrategy().setStrategyOverrideTrueCountAdjust(0);
		t.getPlayer().getStrategy().setStrategyOverrideSelectLevel(5);
		
		BetCounterStrategy betting = new BetCounterStrategy(50000, 50);
		//BetFlatStrategy betting = new BetFlatStrategy(50000, 50);
		
		betting.setGoalPlay(betting.getBettingProperties(), false);
		betting.setGoal(betting.getBettingProperties(), 1000);
		betting.setMaxLooseAmount(betting.getBettingProperties(), -1000);
		betting.setCardShoe(betting.getBettingProperties(), t.getCardShoe());
		
		t.setBetting(betting);
		t.getCardShoe().setBettingPenetrationAdjust(1);

		t.playNTimes(1000000, gp);

		betting.displaySummaryResult(betting.getBettingProperties());
		t.getPlayer().getStrategy().printStrategyOverrideHistogram();
	}
}
