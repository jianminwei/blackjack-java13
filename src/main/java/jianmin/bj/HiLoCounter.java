package jianmin.bj;

public class HiLoCounter implements CardCounter {

	private int shoeCount = 0;

	@Override
	public void count(Card c) {
		if(c.getValue() >= 2 && c.getValue() <= 6) {
			shoeCount++;
		}else if (c.getValue() == 1 || c.getValue() >= 10) {
			shoeCount--;
		}
	}
	
	@Override
	public int getCount() {
		return this.shoeCount;
	}

	@Override
	public void clearCount() {
		this.shoeCount = 0;
	}

}
