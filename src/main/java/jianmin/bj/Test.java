package jianmin.bj;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double s = 0;
		for(int n=1; n <= 27; n++) {
			s = s + (n - 1) * (1 / (double )(365 - (n-1)));
		}
		
		System.out.println(s);

	}

}
