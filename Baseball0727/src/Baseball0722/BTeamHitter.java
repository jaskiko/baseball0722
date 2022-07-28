package Baseball0722;

public class BTeamHitter {
	private int num[] = new int[] {1,2,3,4,5,6,7,8,9}; // 타자번호
	
	private String hitter[] = new String[] {"동일", "동이", "동삼", "동사", "동오", "동육", "동칠", "동팔", "동구"};	// 타자 이름
	
	private double rate[] = new double[] 
//			{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };	// 타율
			{0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5 };	// 타율
//			{0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3 };	// 타율
//			{0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2 };	// 타율
//			{0, 0, 0, 0, 0, 0, 0, 0, 0 };	// 타율 : 0		

	private int order;

	public int[] getNum() {
		return num;
	}

	public String[] getHitter() {
		return hitter;
	}

	public double[] getRate() {
		return rate;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
