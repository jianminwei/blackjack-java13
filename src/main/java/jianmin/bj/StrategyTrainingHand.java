package jianmin.bj;

public  class StrategyTrainingHand {
	private int whatHand;
	private int card1Value;
	private int card2Value;
	private int dealerFaceCard;
	private int trueCount;
	
	public int getCard1Value() {
		return card1Value;
	}

	public int getCard2Value() {
		return card2Value;
	}

	public int getDealerFaceCard() {
		return dealerFaceCard;
	}

	public int getTrueCount() {
		return trueCount;
	}

	public StrategyTrainingHand(int whatHand, int card1Value, int card2Value, int dealerFaceCard, int trueCount) {
		this.whatHand = whatHand;
		this.card1Value = card1Value;
		this.card2Value = card2Value;
		this.dealerFaceCard = dealerFaceCard;
		this.trueCount = trueCount;
	}
	
	public String toString() {
		return "Card1:" + card1Value + " Card2:" + card2Value 
				+ " dealerFaceCard:" + dealerFaceCard + " trueCount:" + trueCount;
	}
}