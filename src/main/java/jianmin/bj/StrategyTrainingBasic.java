package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class StrategyTrainingBasic implements StrategyTraining{
	Logger logger = LogManager.getLogger(StrategyTrainingBasic.class);
	private static HashMap<Integer, ArrayList<StrategyTrainingHand>> testHandsExclusiveMap = new HashMap<>();
	private static HashMap<Integer, ArrayList<StrategyTrainingHand>> testHandsInclusiveMap = new HashMap<>();

	private static Random rnd = new Random();
	
	//return a random testHand for the selectLevel
	/**
	 * 
	 * @param selectLevel -- Override Strategy Level
	 * @param isInclusive -- Inclusive level selections. For example, if isInclusive is true and selectLevel is 2,
	 *                       the testHands will include Level2 and Level1.
	 * @return StrategyTestHand
	 */
	
	@Override
	public StrategyTrainingHand getAHand(int selectLevel, boolean isInclusive) {
		logger.info("Random Basic Strategy Training Hand, level: " + selectLevel + ", isInclusive: " + isInclusive);

		if(isInclusive) {
			return testHandsInclusiveMap.get(selectLevel).get(rnd.nextInt(testHandsInclusiveMap.get(selectLevel).size()));
		} else {
			return testHandsExclusiveMap.get(selectLevel).get(rnd.nextInt(testHandsExclusiveMap.get(selectLevel).size()));
		}
	}

	static {
		//Basic Strategy
		
		//Initialize 5 level's ArrayList for testHands
		testHandsExclusiveMap.put(1, new ArrayList<StrategyTrainingHand>());
		testHandsExclusiveMap.put(2, new ArrayList<StrategyTrainingHand>());
		testHandsExclusiveMap.put(3, new ArrayList<StrategyTrainingHand>());
		testHandsExclusiveMap.put(4, new ArrayList<StrategyTrainingHand>());
		testHandsExclusiveMap.put(5, new ArrayList<StrategyTrainingHand>());
		
		//HardHand
		for(int playerValue = 9; playerValue <= 17; playerValue++) {
			for(int dealerCard = 1; dealerCard <= 10; dealerCard++) {
				String playerHand = "Hard-Hand," + playerValue;
				StrategyTrainingCards playerCards = StrategyTrainingCards.getPlayerHandCards(playerHand);
				testHandsExclusiveMap.get(1).add(new StrategyTrainingHand(MapUtil.HARD_HAND, playerCards.playerCard1, playerCards.playerCard2, dealerCard, 0));
			}
		}
		
		//Soft Hand
		for(int playerValue = 13; playerValue <= 19; playerValue++) {
			for(int dealerCard = 1; dealerCard <= 10; dealerCard++) {
				String playerHand = "Soft-Hand," + playerValue;
				StrategyTrainingCards playerCards = StrategyTrainingCards.getPlayerHandCards(playerHand);
				testHandsExclusiveMap.get(2).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, playerCards.playerCard1, playerCards.playerCard2, dealerCard, 0));
			}
		}

		//Split Hand, "Split-Hand,4"
		for(int playerValue = 4; playerValue <= 18; playerValue += 2) {
			for(int dealerCard = 1; dealerCard <= 10; dealerCard++) {
				String playerHand = "Split-Hand," + playerValue;
				StrategyTrainingCards playerCards = StrategyTrainingCards.getPlayerHandCards(playerHand);
				testHandsExclusiveMap.get(3).add(new StrategyTrainingHand(MapUtil.SPLIT_HAND, playerCards.playerCard1, playerCards.playerCard2, dealerCard, 0));
			}
		}

		//Double Strategy. Note it is covered by Hard Hand and Softhand. Put it here just for doubling drills.
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 5, 4, 3, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 5, 4, 4, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 5, 4, 5, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 5, 4, 6, 0) ); 
		
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 6, 4, 2, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 6, 4, 3, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 6, 4, 4, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 6, 4, 5, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 6, 4, 6, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 6, 4, 7, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 6, 4, 8, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 6, 4, 9, 0) ); 
		
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 7, 4, 2, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 7, 4, 3, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 7, 4, 4, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 7, 4, 5, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 7, 4, 6, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 7, 4, 7, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 7, 4, 8, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.HARD_HAND, 7, 4, 9, 0) ); 
		
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 2, 6, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 3, 5, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 3, 6, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 4, 5, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 4, 6, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 5, 4, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 5, 5, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 5, 6, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 6, 3, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 6, 4, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 6, 5, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 6, 6, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 3, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 4, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 5, 0) ); 
		testHandsExclusiveMap.get(4).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 6, 0) ); 
		
		
		//Difficult ones. Split 9 and Soft 18 are the difficult ones. Put it here again for drills.
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SPLIT_HAND, 9, 9, 2, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SPLIT_HAND, 9, 9, 3, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SPLIT_HAND, 9, 9, 4, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SPLIT_HAND, 9, 9, 5, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SPLIT_HAND, 9, 9, 6, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SPLIT_HAND, 9, 9, 7, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SPLIT_HAND, 9, 9, 8, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SPLIT_HAND, 9, 9, 9, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SPLIT_HAND, 9, 9, 10, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SPLIT_HAND, 9, 9, 1, 0) ); 

		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 2, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 3, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 4, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 5, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 6, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 7, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 8, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 9, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 10, 0) ); 
		testHandsExclusiveMap.get(5).add(new StrategyTrainingHand(MapUtil.SOFT_HAND, 1, 7, 1, 0) ); 

		//Initialize the inclusive test hands map;
		testHandsInclusiveMap.put(1, testHandsExclusiveMap.get(1));
		
		ArrayList <StrategyTrainingHand> list2 = new ArrayList<>();
		list2.addAll(testHandsExclusiveMap.get(1));
		list2.addAll(testHandsExclusiveMap.get(2));
		testHandsInclusiveMap.put(2, list2);
		
		ArrayList <StrategyTrainingHand> list3 = new ArrayList<>();
		list3.addAll(testHandsExclusiveMap.get(1));
		list3.addAll(testHandsExclusiveMap.get(2));
		list3.addAll(testHandsExclusiveMap.get(3));
		testHandsInclusiveMap.put(3, list3);

		ArrayList <StrategyTrainingHand> list4 = new ArrayList<>();
		list4.addAll(testHandsExclusiveMap.get(1));
		list4.addAll(testHandsExclusiveMap.get(2));
		list4.addAll(testHandsExclusiveMap.get(3));
		list4.addAll(testHandsExclusiveMap.get(4));
		testHandsInclusiveMap.put(4, list4);	
		
		ArrayList <StrategyTrainingHand> list5 = new ArrayList<>();
		list5.addAll(testHandsExclusiveMap.get(1));
		list5.addAll(testHandsExclusiveMap.get(2));
		list5.addAll(testHandsExclusiveMap.get(3));
		list5.addAll(testHandsExclusiveMap.get(4));
		list5.addAll(testHandsExclusiveMap.get(5));
		testHandsInclusiveMap.put(5, list5);		
	}
}

