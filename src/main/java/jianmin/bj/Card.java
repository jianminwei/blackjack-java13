package jianmin.bj;
import java.net.URL;

public class Card {
    private int rank;
    private String suit;
    private boolean isFaceUp;
    private int value;
    
    //this property is used to identify the initial
    //card sequence when a card shoe is initialized.
    //It can be used to uniquely identify a card in a shoe
    //even after the shoe is shuffled.
    private int cardShoeInitialSequence;
    
    public int getCardShoeInitialSequence() {
		return cardShoeInitialSequence;
	}

	public void setCardShoeInitialSequence(int cardShoeInitialSequence) {
		this.cardShoeInitialSequence = cardShoeInitialSequence;
	}

	//for animation purpose.
	private int x, y;
	private int final_x, final_y;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getFinal_x() {
		return final_x;
	}

	public void setFinal_x(int final_x) {
		this.final_x = final_x;
	}

	public int getFinal_y() {
		return final_y;
	}

	public void setFinal_y(int final_y) {
		this.final_y = final_y;
	}

	public void moveCard() {
    	if(x > final_x) { x -= 32; }
        if(y < final_y) {y += 4;}
 	}
	
	public boolean movedToTarget() {
        if (x <= final_x && y >= final_y) {
        	return true;
        } else {
        	return false;
        }
	}
    
	@Override
	public String toString() {
		return "   Card [suite=" + this.suit + ", rank=" + this.rank + ", value=" + this.value + "]";
	}
	
	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	

    /**
     * Constructs a Card object
     * @param newRank rank of the card
     * @param newSuit suit of the card
     */
    public Card(int newRank, String newSuit)
    {
        rank = newRank;
        suit = newSuit;
        isFaceUp = true;
        
		if (newRank >= 10) {
			this.value = 10;
		} else {
			this.value = newRank;
		}
		
		//this.name = newSuit + newRank;
    }

    /**
     *
     * @return rank of the Card
     */
    public int getRank()
    {
        return rank;
    }

    /**
     *
     * @return suit of the Card
     */
    public String getSuit()
    {
        return suit;
    }


    /**
     * @return true if face up; false otherwise
     */
    public boolean isFaceUp()
    {
        return isFaceUp;
    }

    /**
     * Turns the card face up
     */
    public void turnUp()
    {
        isFaceUp = true;
    }

    /**
     * Turns the card face down
     */
    public void turnDown()
    {
        isFaceUp = false;
    }

    /**
     *Finds appropriate file name for png corresponding to card based
     * on suit and rank
     *
     * @return name of the project path (under src) for the appropriate Card
     */
    public String getImagePath()
    {
        if (!isFaceUp)return "cards/images/blue_back.png"; 
        if (rank == 10) return "cards/images/T" + suit + ".png";
        if (rank == 11) return "cards/images/J" + suit + ".png";
        if (rank == 12) return "cards/images/Q" + suit + ".png";
        if (rank == 13) return "cards/images/K" + suit + ".png";
        if (rank == 1) return "cards/images/A" + suit + ".png";
        return "cards/images/" + rank + suit + ".png";
    }
    
    public URL getImageURL()
    {
       return this.getClass().getClassLoader().getResource(getImagePath());
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Card card = new Card(12, "S");

		System.out.println(card.toString());
		System.out.println("Rank:" + card.rank);
		System.out.println("Suit:" + card.suit);
		System.out.println("Value:" + card.value);
		card.turnDown();
		System.out.println("Face down file Name:" + card.getImagePath());
		card.turnUp();
		System.out.println("Face up file Name:" + card.getImagePath());

	}
}
