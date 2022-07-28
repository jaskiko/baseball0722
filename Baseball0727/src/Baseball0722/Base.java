package Baseball0722;

public class Base {
	
	private int first;	// 1루 베이스
	
	private int second;	// 2루 베이스
	
	private int third;	// 3루 베이스
	
	private int home;	// 홈 베이스

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getThird() {
		return third;
	}

	public void setThird(int third) {
		this.third = third;
	}

	public int getHome() {
		return home;
	}

	public void setHome(int home) {
		this.home += home;
	}
}
