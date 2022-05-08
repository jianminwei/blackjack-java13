package jianmin.bj;

public class Deck {
	private Card[] card = new Card[52];

	public Card[] getCard() {
		return card;
	}

	public void setCard(Card[] card) {
		this.card = card;
	}

	public void initCard() {
		int k = 0;

		for (int i = 1; i <= 13; i++) {
			card[k++] = new Card(i, "S");
		}

		for (int i = 1; i <= 13; i++) {
			card[k++] = new Card(i, "H" );
		}

		for (int i = 1; i <= 13; i++) {
			card[k++] = new Card(i, "C");
		}

		for (int i = 1; i <= 13; i++) {
			card[k++] = new Card(i, "D");
		}
	}

	@Override
	public String toString() {

		String s = "";

		for (Card c : this.getCard()) {
			s = s + c.toString() + "\n";
		}
		return s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Deck d = new Deck();

		d.initCard();

		System.out.println(d.toString());

	}

}
