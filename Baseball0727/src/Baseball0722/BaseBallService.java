package Baseball0722;

import java.util.List;

public interface BaseBallService {
	int menu();	// 공수선택 메소드(BaseBallMain switch case문)
	
	void attack(List<AScore> asco, ATeamHitter athh, AScore aOut, Base base);	// 공격 메소드
	
	void defense(List<BScore> bsco, BTeamHitter btth, BScore bOut, Base base);	// 수비 메소드
	
	public void sunGong(List<AScore> asco, List<BScore> bsco, ATeamHitter athh, BTeamHitter btth, BScore bOut, AScore aOut);
	
	public void huGong(List<AScore> asco, List<BScore> bsco, ATeamHitter athh, BTeamHitter btth, BScore bOut, AScore aOut);
	
	void hit(ATeamHitter[] ath, ATeamHitter athh, AScore aOut, Base base);
	
	void pass(ATeamHitter[] ath, ATeamHitter athh, AScore aOut, Base base);
	
	void strTrw(BTeamHitter[] bth, BTeamHitter bthh, BScore bOut, Base base);

	void ballTrw(BTeamHitter[] bth, BTeamHitter bthh, BScore bOut, Base base);
}
