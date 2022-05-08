package jianmin.bj;

import java.util.HashMap;

public class MapUtil {
	//Used as signaling No Split game
	public final static int NO_SPLIT = -1000;
	
	//Used as signaling Not Surrender game
	public final static int NO_SURRENDER = -2000;
	
	//Used for identifying what kind of hand it is
	//200 section
	public final static int HARD_HAND = 200;
	public final static int SOFT_HAND = 201;
	public final static int AFTER_HIT_SOFT_HAND = 202;
	public final static int SPLIT_HAND = 203;
	public final static int EARLY_SURRENDER_HAND = 204;
	public final static int LATE_SURRENDER_HAND = 205;
	
	//Player Decision Section
	//100 section
	public static final int DECISION_SURRENDER = 100;
	public static final int DECISION_STAY = 101;
	public static final int DECISION_DOUBLE = 102;
	public static final int DECISION_HIT = 103;
	public static final int DECISION_SPLIT = 104;
	
	//Game Results Section
	//1 - 100 section
	public static final int PLAYER_WIN = 1;
	public static final int PLAYER_DOUBLE_WIN = 2;
	public static final int PLAYER_DOUBLE_LOOSE = 3;
	public static final int PLAYER_DOUBLE_PUSH = 4;
	public static final int PLAYER_BLACKJACK_WIN = 5;
	public static final int PLAYER_BLACKJACK_PUSH = 6;
	public static final int DEALER_WIN = 7;
	public static final int DEALER_BLACKJACK_WIN = 8;
	public static final int PUSH = 9;
	public static final int PLAYER_SURRENDER = 10;
	public static final int PLAYER_BUST = 11;
	public static final int PLAYER_BUST_DEALER_BLACKJACK = 12;
	public static final int PLAYER_DOUBLE_LOOSE_DEALER_BLACKJACK = 13;
	public static final int DEALER_BUST = 14;
	public static final int UNKNOWN = -1;

	public static HashMap<Integer, String> resultMap = new HashMap<>();
	
	static {
		resultMap.put(PLAYER_WIN, "PLAYER_WIN");
		resultMap.put(PLAYER_DOUBLE_WIN, "PLAYER_DOUBLE_WIN");
		resultMap.put(PLAYER_DOUBLE_LOOSE, "PLAYER_DOUBLE_LOOSE");
		resultMap.put(PLAYER_DOUBLE_PUSH, "PLAYER_DOUBLE_PUSH");
		resultMap.put(PLAYER_BLACKJACK_WIN, "PLAYER_BLACKJACK_WIN");
		resultMap.put(PLAYER_BLACKJACK_PUSH, "PLAYER_BLACKJACK_PUSH");		
		resultMap.put(DEALER_WIN, "DEALER_WIN");
		resultMap.put(DEALER_BLACKJACK_WIN, "DEALER_BLACKJACK_WIN");
		resultMap.put(PUSH, "PUSH");
		resultMap.put(PLAYER_SURRENDER, "PLAYER_SURRENDER");
		resultMap.put(PLAYER_BUST, "PLAYER_BUST");
		resultMap.put(PLAYER_BUST_DEALER_BLACKJACK, "PLAYER_BUST_DEALER_BLACKJACK");
		resultMap.put(PLAYER_DOUBLE_LOOSE_DEALER_BLACKJACK, "PLAYER_DOUBLE_LOOSE_DEALER_BLACKJACK");
		resultMap.put(DEALER_BUST, "DEALER_BUST");
		resultMap.put(UNKNOWN, "UNKNOWN");
		resultMap.put(HARD_HAND, "Hard-Hand");
		resultMap.put(SOFT_HAND, "Soft-Hand");
		resultMap.put(AFTER_HIT_SOFT_HAND, "After-Hit-Soft-Hand");
		resultMap.put(SPLIT_HAND, "Split-Hand");
		resultMap.put(EARLY_SURRENDER_HAND, "Early-Surrender-Hand");
		resultMap.put(LATE_SURRENDER_HAND, "Late-Surrender-Hand");
	}
	
	public static String mapToString(int result) {
		return resultMap.get(result);
	}
	
}
