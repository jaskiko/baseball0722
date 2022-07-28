package Baseball0722;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseBallMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BaseBallService bbs = new BaseBallServiceImpl();
		List<AScore> asco = new ArrayList<AScore>();	// a팀 점수 List
		List<BScore> bsco = new ArrayList<BScore>();	// b팀 점수 List
		AScore aOut = new AScore();				// 아웃카운트
		BScore bOut = new BScore();
		ATeamHitter athh = new ATeamHitter();	// 타자 순서 save & load getOrder()
		BTeamHitter btth = new BTeamHitter();
		
		System.out.print("insert coin : ");
		sc.nextInt();
		System.out.println();
		
		int menu = bbs.menu();
		switch(menu){
		case 1:
			bbs.huGong(asco, bsco, athh, btth, bOut, aOut);
			break;
		case 2:
			bbs.sunGong(asco, bsco, athh, btth, bOut, aOut);
			break;
		default:
			System.out.println("잘못누르심");
		}
	}
}
