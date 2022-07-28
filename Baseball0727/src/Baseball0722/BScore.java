package Baseball0722;

public class BScore {
	private int score;		// B팀 해당 이닝 득점수
							
	private int out;
	
	public int getOut() {
		return out;
	}
	public void setOut(int out) {
		this.out = out;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score += score;
	}
}
