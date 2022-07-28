package Baseball0722;

public class ATeamHitter {
	private int num[] = new int[] {1,2,3,4,5,6,7,8,9}; // 타자번호
	
	private String hitter[] = new String[] {"일동","이동","삼동","사동","오동","육동","칠동","팔동","구동"};	// 타자이름
	
	private	double rate[] = new double[] 
//			{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };	// 타율 : 100%
//			{0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5 };	// 타율 : 50%
			{0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3 };	// 타율 : 30%
//			{0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2 };	// 타율
//			{0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };	// 타율
//			{0, 0, 0, 0, 0, 0, 0, 0, 0 };	// 타율 : 0		
	
	
	private int order;	// 타순

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int[] getNum() {
		return num;
	}

	public String[] getHitter() {
		return hitter;
	}

	public double[] getRate() {
		return rate;
	}

}
