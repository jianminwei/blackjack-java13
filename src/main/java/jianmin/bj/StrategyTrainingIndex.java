package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class StrategyTrainingIndex implements StrategyTraining{
	Logger logger = LogManager.getLogger(StrategyTrainingIndex.class);
	private static Random rnd = new Random();
	private static HashMap<Integer, ArrayList<StrategyTrainingHand>> testHandsExclusiveMap = new HashMap<>();
	private static HashMap<Integer, ArrayList<StrategyTrainingHand>> testHandsInclusiveMap = new HashMap<>();

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
		logger.info("Random Index Strategy Training Hand, level: " + selectLevel + ", isInclusive: " + isInclusive);

		if(isInclusive) {
			return testHandsInclusiveMap.get(selectLevel).get(rnd.nextInt(testHandsInclusiveMap.get(selectLevel).size()));
		} else {
			return testHandsExclusiveMap.get(selectLevel).get(rnd.nextInt(testHandsExclusiveMap.get(selectLevel).size()));
		}
	}

	//Below static block create entire test hands for all
	//Override Strategies
	static {
		//Initialize 5 level's ArrayList for testHands
		testHandsExclusiveMap.put(1, new ArrayList<StrategyTrainingHand>());
		testHandsExclusiveMap.put(2, new ArrayList<StrategyTrainingHand>());
		testHandsExclusiveMap.put(3, new ArrayList<StrategyTrainingHand>());
		testHandsExclusiveMap.put(4, new ArrayList<StrategyTrainingHand>());
		testHandsExclusiveMap.put(5, new ArrayList<StrategyTrainingHand>());

		for(String key : Strategy.trueCountOverrideMap.keySet()) {
			char v[] = Strategy.trueCountOverrideMap.get(key);
			List<String> keyParts = Arrays.asList(key.split(","));
			
			//Format, Split-Hand,20,6 : >=+2P:V:1 1831
			String playerHand = keyParts.get(0) + "," + keyParts.get(1);
			int dealerCard = Integer.parseInt(keyParts.get(2));
			int selectLevel = Integer.parseInt(Character.toString(v[8]));
			int trueCount = Integer.parseInt(Character.toString(v[2]) + Character.toString(v[3]));
			
			//Only generate test hand for the 'V' verified.
			if (v[6] != 'U') {
				int trueCount2 = 0;
				if (v[0] == '>' && v[1] == '=') {
					trueCount2 = trueCount - 1;
				}else if (v[0] == '<' && v[1] == '=') {
					trueCount2 = trueCount + 1;
				};
				
				StrategyTrainingCards testPlayerHand = StrategyTrainingCards.getPlayerHandCards(playerHand);
				//StrategyTestHand testHand1 = new StrategyTestHand(MapUtil.HARD_HAND, 9, 7, 10, -3);
				StrategyTrainingHand testHand1 = new StrategyTrainingHand(testPlayerHand.whatKindOfHand, testPlayerHand.playerCard1, testPlayerHand.playerCard2, dealerCard, trueCount);
				StrategyTrainingHand testHand2 = new StrategyTrainingHand(testPlayerHand.whatKindOfHand, testPlayerHand.playerCard1, testPlayerHand.playerCard2, dealerCard, trueCount2);
				
				testHandsExclusiveMap.get(selectLevel).add(testHand1);
				testHandsExclusiveMap.get(selectLevel).add(testHand2);
			}
		}
		
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
	
	/**
	 * 
	 * @param selectLevel
	 * @return StrategyTestHand
	 * 
	 * This method first get a random test key within the Override Strategy level.
	 */
	public static String getRandomKey(int selectLevel, boolean isInclusive) {
		List<String> keys = new ArrayList<> ();
		
		for(String key : Strategy.trueCountOverrideMap.keySet()) {
			char v[] = Strategy.trueCountOverrideMap.get(key);
			int level = Integer.parseInt(Character.toString(v[8]));
			
			if(isInclusive) {
				if(level <= selectLevel) {
					keys.add(key);
				}
			} else {
				if(level == selectLevel) {
					keys.add(key);
				}
			}
		}
		
		//Get a random key within the selected keys
		return  keys.get(rnd.nextInt(keys.size()));
	}
	
	/**
	 * 
	 * @param selectLevel
	 * @return StrategyTestHand
	 * 
	 * This is another method to generate a StrategyTestHand within a selected level.
	 */
	public static StrategyTrainingHand getAHand2(int selectLevel, boolean isInclusive) {
		//Get a random key within the selected level
		String key =  getRandomKey(selectLevel, isInclusive);

		char v[] = Strategy.trueCountOverrideMap.get(key);
		List<String> keyParts = Arrays.asList(key.split(","));
		
		String playerHand = keyParts.get(0) + "," + keyParts.get(1);
		int dealerCard = Integer.parseInt(keyParts.get(2));
		int trueCount = Integer.parseInt(Character.toString(v[2]) + Character.toString(v[3]));
		
		int trueCount1 = 0, trueCount2 = 0;
		if (v[0] == '>' && v[1] == '=') {
			trueCount1 = trueCount - 1;
			trueCount2 = trueCount;
		}else if (v[0] == '<' && v[1] == '=') {
			trueCount1 = trueCount + 1;
			trueCount2 = trueCount;
		};
		
		//select one of the 2 true counts
		int selectedTrueCount = rnd.nextInt(2) == 0 ? trueCount1 : trueCount2;
		
		StrategyTrainingCards testPlayerHand = StrategyTrainingCards.getPlayerHandCards(playerHand);
		return new StrategyTrainingHand(testPlayerHand.whatKindOfHand, testPlayerHand.playerCard1, testPlayerHand.playerCard2, dealerCard, selectedTrueCount);
	}
	
}

