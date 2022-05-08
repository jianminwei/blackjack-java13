package jianmin.bj;

import java.util.HashMap;

public class StrategyTrainingCards {
	int whatKindOfHand;
	int playerCard1;
	int playerCard2;
	
	private static HashMap<String, StrategyTrainingCards> playerHandCardsMap = new HashMap<>();


	StrategyTrainingCards(int whatKindOfHand, int playerCard1, int playerCard2) {
		this.whatKindOfHand = whatKindOfHand;
		this.playerCard1 = playerCard1;
		this.playerCard2 = playerCard2;
	}
	
	public String toString() {
		return "Card1:" + playerCard1 + " Card2:" + playerCard2 ;
	}
	
	static {
		playerHandCardsMap.put("Hard-Hand,8", new StrategyTrainingCards(MapUtil.HARD_HAND, 5, 3));
		playerHandCardsMap.put("Hard-Hand,9", new StrategyTrainingCards(MapUtil.HARD_HAND, 5, 4));
		playerHandCardsMap.put("Hard-Hand,10", new StrategyTrainingCards(MapUtil.HARD_HAND, 6, 4));
		playerHandCardsMap.put("Hard-Hand,11", new StrategyTrainingCards(MapUtil.HARD_HAND, 7, 4));
		playerHandCardsMap.put("Hard-Hand,12", new StrategyTrainingCards(MapUtil.HARD_HAND, 7, 5));
		playerHandCardsMap.put("Hard-Hand,13", new StrategyTrainingCards(MapUtil.HARD_HAND, 7, 6));
		playerHandCardsMap.put("Hard-Hand,14", new StrategyTrainingCards(MapUtil.HARD_HAND, 8, 6));
		playerHandCardsMap.put("Hard-Hand,15", new StrategyTrainingCards(MapUtil.HARD_HAND, 9, 6));
		playerHandCardsMap.put("Hard-Hand,16", new StrategyTrainingCards(MapUtil.HARD_HAND, 9, 7));
		playerHandCardsMap.put("Hard-Hand,17", new StrategyTrainingCards(MapUtil.HARD_HAND, 9, 8));

		playerHandCardsMap.put("Split-Hand,4", new StrategyTrainingCards(MapUtil.SPLIT_HAND, 2, 2));
		playerHandCardsMap.put("Split-Hand,6", new StrategyTrainingCards(MapUtil.SPLIT_HAND, 3, 3));
		playerHandCardsMap.put("Split-Hand,8", new StrategyTrainingCards(MapUtil.SPLIT_HAND, 4, 4));
		playerHandCardsMap.put("Split-Hand,10", new StrategyTrainingCards(MapUtil.SPLIT_HAND, 5, 5));
		playerHandCardsMap.put("Split-Hand,12", new StrategyTrainingCards(MapUtil.SPLIT_HAND, 6, 6));
		playerHandCardsMap.put("Split-Hand,14", new StrategyTrainingCards(MapUtil.SPLIT_HAND, 7, 7));
		playerHandCardsMap.put("Split-Hand,16", new StrategyTrainingCards(MapUtil.SPLIT_HAND, 8, 8));
		playerHandCardsMap.put("Split-Hand,18", new StrategyTrainingCards(MapUtil.SPLIT_HAND, 9, 9));
		playerHandCardsMap.put("Split-Hand,20", new StrategyTrainingCards(MapUtil.SPLIT_HAND, 10, 10));
	
		playerHandCardsMap.put("Late-Surrender-Hand,14", new StrategyTrainingCards(MapUtil.LATE_SURRENDER_HAND, 10, 4));
		playerHandCardsMap.put("Late-Surrender-Hand,15", new StrategyTrainingCards(MapUtil.LATE_SURRENDER_HAND, 10, 5));
		playerHandCardsMap.put("Late-Surrender-Hand,16", new StrategyTrainingCards(MapUtil.LATE_SURRENDER_HAND, 10, 6));

		playerHandCardsMap.put("Soft-Hand,13", new StrategyTrainingCards(MapUtil.SOFT_HAND, 1, 2));
		playerHandCardsMap.put("Soft-Hand,14", new StrategyTrainingCards(MapUtil.SOFT_HAND, 1, 3));
		playerHandCardsMap.put("Soft-Hand,15", new StrategyTrainingCards(MapUtil.SOFT_HAND, 1, 4));
		playerHandCardsMap.put("Soft-Hand,16", new StrategyTrainingCards(MapUtil.SOFT_HAND, 1, 5));
		playerHandCardsMap.put("Soft-Hand,17", new StrategyTrainingCards(MapUtil.SOFT_HAND, 1, 6));
		playerHandCardsMap.put("Soft-Hand,18", new StrategyTrainingCards(MapUtil.SOFT_HAND, 1, 7));
		playerHandCardsMap.put("Soft-Hand,19", new StrategyTrainingCards(MapUtil.SOFT_HAND, 1, 8));
		playerHandCardsMap.put("Soft-Hand,20", new StrategyTrainingCards(MapUtil.SOFT_HAND, 1, 9));
	}
	
	public static StrategyTrainingCards getPlayerHandCards(String playerHand) {
		return playerHandCardsMap.get(playerHand);
	}
}
