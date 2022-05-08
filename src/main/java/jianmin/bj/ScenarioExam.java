package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScenarioExam {

	Logger logger = LogManager.getLogger(ScenarioExam.class);

	private Player player = new Player();
	private Dealer dealer = new Dealer();
	private CardShoe cardShoe = new CardShoe();
	private BlackJackUtil bjUtil = new BlackJackUtil();
	private Betting betting;
	
	//this property is for testing late surrender only
	//to ensure the dealer get a "non-blackjack" 2nd card.
	private boolean isLateSurrenderTest = false;
	
	public boolean isLateSurrenderTest() {
		return isLateSurrenderTest;
	}

	public void setLateSurrenderTest(boolean isLateSurrenderTest) {
		this.isLateSurrenderTest = isLateSurrenderTest;
	}

	//this property is for Scenario Testing purpose.
	//It's just for testing not splitting A
	public void setCanSplitA(boolean canSplitA) {
		this.player.getStrategy().setCanSplitA(canSplitA); 
	}

	public void setBetting(Betting betting) {
		this.betting = betting;
	}
	
	public CardShoe getCardShoe() {
		return this.cardShoe;
	}

	public Player getPlayer() {
		return player;
	}

	public BlackJackUtil getBlackJackUtil() {
		return bjUtil;
	}

	public void play1Round(int[] cards, int dealerFaceCard) {

		player.getHand1().clearHands();
		dealer.clearHands();

		for (int i = 0; i < cards.length; i++) {
			player.getHand1().receiveACard(cardShoe.getTheCard(cards[i]));
		}

		dealer.receiveACard(cardShoe.getTheCard(dealerFaceCard));
		
		//We need to shuffle the shoe here because the previous 3 or 4 cards
		//are all selected from the shoe. It will make the shoe local unbalanced without shuffle.
		cardShoe.shuffle();
		
		//Control the Hi-Lo position
		if(cardShoe.getEnableTrueCountPosition()) {
			cardShoe.moveToTrueCountPosition(cardShoe.getTrueCountPosition());
		}
		
		//Bet
		player.getHand1().setBet(betting.bet());
		
		//If it is in "Late Surrender Testing",
		//don't let dealer get BlackJack.
		if (isLateSurrenderTest()) {
			dealer.receiveACard(dealer.receiveNonBlackJack2ndCard(cardShoe));
		}else {
			dealer.receiveACard(cardShoe.getNextCard());
		}
		
		Player.playTheGame(player.getHand1(), player, dealer, cardShoe, betting, player.getStrategy(), bjUtil);
	}

	public void playNTimes(int[] cards, int playerValue, int dealerFaceCard, int nTimes, int whatKindHand) {

		int i;
		for (i = 0; i < nTimes; i++) {
			logger.info("\n======== Game: " + (i + 1) + " ========");

			play1Round(cards, dealerFaceCard);
			//reconcile((i+1) * 2);
		}

		bjUtil.displayTotalGameResult(cards, playerValue, dealerFaceCard, nTimes, whatKindHand, this.cardShoe, this.player);
	}
	
	private void reconcile(int gameNumber){
		if(bjUtil.getTotal() != gameNumber) {
			logger.trace("Trace::Settler RECONCILIATION ERROR, bjUtil Total: " + bjUtil.getTotal() + 
					"Games Played:" + gameNumber);

		}
	}

	public static void main(String[] args) {

		ScenarioExam s = new ScenarioExam();
		
		s.cardShoe.initializeShoe(8);
		s.getCardShoe().setEnableTrueCountPosition(true);
		s.getCardShoe().setTrueCountPosition(4);
		
		s.getPlayer().getStrategy().setCanEarlySurrender(false);
		s.getPlayer().getStrategy().setCanLateSurrender(false);
		
		s.getPlayer().getStrategy().setCanSurrenderOverride(false);
		s.getPlayer().getStrategy().setCanHardHandOverride(false);
		s.getPlayer().getStrategy().setCanSplitHandOverride(false);
		s.getPlayer().getStrategy().setCanSoftHandOverride(false);
		s.getPlayer().getStrategy().setStrategyOverrideTrueCountAdjust(1);
		s.getPlayer().getStrategy().setStrategyOverrideSelectLevel(2);

		BetCounterStrategy betting = new BetCounterStrategy(50000, 50);
		betting.setGoal(betting.getBettingProperties(), 400);
		betting.setMaxLooseAmount(betting.getBettingProperties(), -2000);
		betting.setCardShoe(betting.getBettingProperties(), s.getCardShoe());
		betting.setGoalPlay(betting.getBettingProperties(), false);
		s.setBetting(betting);
		
		s.getCardShoe().setBettingPenetrationAdjust(1);
		
		int[] cards = new int[2];
		cards[0] = 5;
		cards[1] = 6;

		s.player.getStrategy().setStrategy(11, 1, 'D', MapUtil.HARD_HAND);
		s.playNTimes(cards, 11, 1, 1000000, MapUtil.HARD_HAND);
		
		s.getBlackJackUtil().displayResult();
	}
}
