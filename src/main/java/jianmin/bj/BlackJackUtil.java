package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class BlackJackUtil {
	Logger logger = LogManager.getLogger(BlackJackUtil.class);

	int playerWin = 0;
	int playerDoubleWin = 0;
	int playerDoubleLoose = 0;
	int playerDoublePush = 0;
	int dealerWin = 0;
	int push = 0;
	int playerSurrender = 0;
	int playerBust = 0;
	int playerBustDealerBlackjack = 0;
	int playerDoubleLooseDealerBlackjack = 0;
	int dealerBust = 0;
	int playerBlackJackWin = 0;
	int playerBlackJackPush = 0;
	int dealerBlackJackWin = 0;
	int other;
	int penetrationTestingNumber = 1;

	private HashMap<String, Double> resultMap = new HashMap<>();

	public void displayResult() {

		System.out.println("\n\n====Summary result====");
		for (String key : resultMap.keySet()) {
			System.out.println(key + resultMap.get(key));
		}
	}

	public void clearResult() {
		this.resultMap.clear();
	}

	public void clearGameCounts() {
		this.playerWin = 0;
		this.playerDoubleWin = 0;
		this.playerDoubleLoose = 0;
		this.playerDoublePush = 0;
		this.dealerWin = 0;
		this.push = 0;
		this.playerSurrender = 0;
		this.playerBust = 0;
		this.playerBustDealerBlackjack = 0;
		this.playerDoubleLooseDealerBlackjack = 0;
		this.dealerBust = 0;
		this.playerBlackJackWin = 0;
		this.playerBlackJackPush = 0;
		this.dealerBlackJackWin = 0;
		this.other = 0;

	}

	public void displayGameResult(Hand hand, Dealer dealer, int finalValue) {
		logger.info("Game Result->" + MapUtil.mapToString(finalValue));
		logger.info("Player Score: " + hand.score());
		logger.info("Dealer Score: " + dealer.score());
		logger.info("\nPlayer Cards: ");
		hand.displayCards();
		logger.info("Dealer Cards: ");
		dealer.displayCards();
	}

	public int getTotal() {
		return playerWin + playerDoubleWin + playerDoubleLoose + playerDoublePush + playerSurrender + dealerWin + push
				+ dealerBust + playerBust + playerBlackJackWin + playerBlackJackPush + dealerBlackJackWin
				+ playerBustDealerBlackjack + playerDoubleLooseDealerBlackjack;
	}

	public double getSumTotalWin() {
		return (double) (playerWin + playerDoubleWin * 2.0 + dealerBust + playerBlackJackWin * 1.5);
	}

	public double getSumTotalLose() {
		return (double) (playerDoubleLoose * 2.0 + playerSurrender * 0.5 + dealerWin + playerBust + dealerBlackJackWin
				+ playerBustDealerBlackjack + playerDoubleLooseDealerBlackjack * 2);
	}

	public double getNet() {
		return getSumTotalWin() - getSumTotalLose();
	}

	public double getPercentage() {
		return (getSumTotalWin() - getSumTotalLose()) * 100.00 / getTotal();
	}

	public void recordGame(int whoWin) {

		if (whoWin == MapUtil.PLAYER_WIN) {
			playerWin += 1;
		} else if (whoWin == MapUtil.PLAYER_DOUBLE_WIN) {
			playerDoubleWin++;
		} else if (whoWin == MapUtil.PLAYER_DOUBLE_LOOSE) {
			playerDoubleLoose++;
		} else if (whoWin == MapUtil.PLAYER_DOUBLE_PUSH) {
			playerDoublePush++;
		} else if (whoWin == MapUtil.PLAYER_SURRENDER) {
			playerSurrender++;
		} else if (whoWin == MapUtil.DEALER_WIN) {
			dealerWin++;
		} else if (whoWin == MapUtil.PUSH) {
			push++;
		} else if (whoWin == MapUtil.DEALER_BUST) {
			dealerBust++;
		} else if (whoWin == MapUtil.PLAYER_BUST) {
			playerBust++;
		} else if (whoWin == MapUtil.PLAYER_BLACKJACK_WIN) {
			playerBlackJackWin++;
		} else if (whoWin == MapUtil.PLAYER_BLACKJACK_PUSH) {
			playerBlackJackPush++;
		} else if (whoWin == MapUtil.DEALER_BLACKJACK_WIN) {
			dealerBlackJackWin++;
		} else if (whoWin == MapUtil.PLAYER_BUST_DEALER_BLACKJACK) {
			playerBustDealerBlackjack++;
		} else if (whoWin == MapUtil.PLAYER_DOUBLE_LOOSE_DEALER_BLACKJACK) {
			playerDoubleLooseDealerBlackjack++;
		} else {
			other++;
			System.out.println("OTHER DETECTED");
			System.out.println("whoWin" + whoWin);
		}

	}

	public String getTitle(int playerValue, int dealerFaceCard, int nTimes, int whatKindHand, CardShoe cardShoe,
			Player player) {
		String sTrueCountClause = "";
		if (cardShoe.getEnableTrueCountPosition()) {
			sTrueCountClause = " with TrueCount Position Enabled At: " + cardShoe.getTrueCountPosition();
		} else {
			sTrueCountClause = " with TrueCount Position Disabled";
		}

		return "\n==== " + nTimes + " Player " + playerValue + " to Dealer " + dealerFaceCard + " "
				+ player.getStrategy().getStrategyString(playerValue, dealerFaceCard, whatKindHand) + " "
				+ MapUtil.mapToString(whatKindHand) + " games played " + sTrueCountClause + " ====";
	}

	public void recordResult(int[] cards, int playerValue, int dealerFaceCard, int whatKindHand, Player player) {

		String pv = "";

		// If it's a pair of A, use "AA" as hash key.
		if (playerValue == 12 && cards[0] == 1) {
			pv = "AA";
		} else {
			pv = Integer.toString(playerValue);
		}

		String key = pv + "," + player.getStrategy().getStrategyString(playerValue, dealerFaceCard, whatKindHand) + ","
				+ dealerFaceCard + ",";

		this.resultMap.put(key, getNet());
	}

	public void displayTotalGameResult(int[] cards, int playerValue, int dealerFaceCard, int nTimes, int whatKindHand,
			CardShoe cardShoe, Player player) {
		int total = getTotal();

		recordResult(cards, playerValue, dealerFaceCard, whatKindHand, player);

		String title = getTitle(playerValue, dealerFaceCard, nTimes, whatKindHand, cardShoe, player);

		System.out.println(title);

		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Win: ", playerWin, " :Percentage: ",
				(double) playerWin / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Win (Dealer Bust): ", dealerBust,
				" :Percentage: ", (double) dealerBust / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Double Win: ", playerDoubleWin, " :Percentage: ",
				(double) playerDoubleWin / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Double Loose: ", playerDoubleLoose,
				" :Percentage: ", (double) playerDoubleLoose / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Double Push: ", playerDoublePush,
				" :Percentage: ", (double) playerDoublePush / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Blackjack Win: ", playerBlackJackWin,
				" :Percentage: ", (double) playerBlackJackWin / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Blackjack Push: ", playerBlackJackPush,
				" :Percentage: ", (double) playerBlackJackPush / total));

		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Surrender: ", playerSurrender, " :Percentage: ",
				(double) playerSurrender / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Dealer Win: ", dealerWin, " :Percentage: ",
				(double) dealerWin / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Dealer Win (Player Bust): ", playerBust,
				" :Percentage: ", (double) playerBust / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Dealer Win (Player Bust & Dealer Blackjack): ",
				playerBustDealerBlackjack, " :Percentage: ", (double) playerBustDealerBlackjack / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Dealer Win (Player Double & Dealer Blackjack): ",
				playerDoubleLooseDealerBlackjack, " :Percentage: ", (double) playerDoubleLooseDealerBlackjack / total));

		System.out.println(String.format("%-48s%-10d%-15s%f", "Dealer Blackjack Win: ", dealerBlackJackWin,
				" :Percentage: ", (double) dealerBlackJackWin / total));

		System.out.println(String.format("%-48s%-10d%-15s%f", "Push: ", push, " :Percentage: ", (double) push / total));

		// System.out.println("\n");
		System.out.println(String.format("%20s%18f", "Total Games Played: ", (double) total));
		System.out.println(String.format("%20s%18f", "Total $ Wins: ", getSumTotalWin()));
		System.out.println(String.format("%20s%18f", "Total $ Losses: ", getSumTotalLose()));
		System.out.println(String.format("%20s%18f", "$ Net: ", getNet()));
		System.out.println(String.format("%20s%18f", "$ Percentage: ", getPercentage()));
	}

	public void displayTotalGameResultByTitle(String title) {
		int total = getTotal();
		
		System.out.println(title);

		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Win: ", playerWin, " :Percentage: ",
				(double) playerWin / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Win (Dealer Bust): ", dealerBust,
				" :Percentage: ", (double) dealerBust / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Double Win: ", playerDoubleWin, " :Percentage: ",
				(double) playerDoubleWin / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Double Loose: ", playerDoubleLoose,
				" :Percentage: ", (double) playerDoubleLoose / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Double Push: ", playerDoublePush,
				" :Percentage: ", (double) playerDoublePush / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Blackjack Win: ", playerBlackJackWin,
				" :Percentage: ", (double) playerBlackJackWin / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Blackjack Push: ", playerBlackJackPush,
				" :Percentage: ", (double) playerBlackJackPush / total));

		System.out.println(String.format("%-48s%-10d%-15s%f", "Player Surrender: ", playerSurrender, " :Percentage: ",
				(double) playerSurrender / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Dealer Win: ", dealerWin, " :Percentage: ",
				(double) dealerWin / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Dealer Win (Player Bust): ", playerBust,
				" :Percentage: ", (double) playerBust / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Dealer Win (Player Bust & Dealer Blackjack): ",
				playerBustDealerBlackjack, " :Percentage: ", (double) playerBustDealerBlackjack / total));
		System.out.println(String.format("%-48s%-10d%-15s%f", "Dealer Win (Player Double & Dealer Blackjack): ",
				playerDoubleLooseDealerBlackjack, " :Percentage: ", (double) playerDoubleLooseDealerBlackjack / total));

		System.out.println(String.format("%-48s%-10d%-15s%f", "Dealer Blackjack Win: ", dealerBlackJackWin,
				" :Percentage: ", (double) dealerBlackJackWin / total));

		System.out.println(String.format("%-48s%-10d%-15s%f", "Push: ", push, " :Percentage: ", (double) push / total));

		// System.out.println("\n");
		System.out.println(String.format("%20s%18f", "Total Games Played: ", (double) getTotal()));
		System.out.println(String.format("%20s%18f", "Total $ Wins: ", getSumTotalWin()));
		System.out.println(String.format("%20s%18f", "Total $ Losses: ", getSumTotalLose()));
		System.out
				.println(String.format("%20s%18f", "$ Net: ", getNet()) + " [" + getNet() * 50 + " if played $50 flat bet]");
		System.out.println(String.format("%20s%18f", "$ Percentage: ", getPercentage()));
	}
}
