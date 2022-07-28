package Baseball0722;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BaseBallServiceImpl implements BaseBallService {
   Scanner sc = new Scanner(System.in);
   Random rand = new Random();
   Base base = new Base();

   int strike = 0;
   int ball = 0;
   int out = 0;
   int score = 0;

   @Override
   public int menu() {
      // TODO Auto-generated method stub
      System.out.println("1. 선수비");
      System.out.println("2. 선공격");
      System.out.print("선택 : ");

      return sc.nextInt();
   }

   @Override
   public void attack(List<AScore> asco, ATeamHitter athh, AScore aOut, Base base) {
      // TODO Auto-generated method stub
      // a팀 타자 배열객체 생성
      ATeamHitter[] ath = new ATeamHitter[9];

      base.setFirst(0);
      base.setSecond(0);
      base.setThird(0);
      base.setHome(0);

      boolean attackInning = true;
      while(attackInning) {

         ath[athh.getOrder()]=new ATeamHitter();
         AScore ascore = new AScore();
         System.out.println();
         System.out.println("===== 경기장 =====");
         System.out.println("1루 상태 : "+base.getFirst()+" 2루 상태 : "+base.getSecond()+" 3루 상태 : "+base.getThird());
         System.out.println("홈 상태(이번 점수) : "+ score);
         System.out.println("=====================================");
         System.out.println(ath[athh.getOrder()].getNum()[athh.getOrder()]+"번  "+ath[athh.getOrder()].getHitter()[athh.getOrder()]+" 선수 타석에 나왔습니다. 이 선수의 타율="+ath[athh.getOrder()].getRate()[athh.getOrder()]);

         System.out.println("1. 친다");
         System.out.println("2. 안친다");
         System.out.print("선택 : ");

         int select = sc.nextInt();
         switch(select) {
         case 1:
            hit(ath, athh, aOut, base);
            break;
         case 2:
            pass(ath, athh, aOut, base);
            break;
         default :
            System.out.println("1과 2 중에서 골라주세요.");
         }

         if(aOut.getOut()==3) {      // 3 아웃되면 ATeamInning 클래스 종료
            aOut.setOut(0);
            base.setFirst(0);
            base.setSecond(0);
            base.setThird(0);
            base.setHome(0);
            athh.setOrder(athh.getOrder());
            ascore.setScore(score);
            asco.add(ascore);
            score = 0;

            attackInning = false;
         }
      }
   }

   @Override
   public void defense(List<BScore> bsco, BTeamHitter bthh, BScore bOut, Base base) {
      // TODO Auto-generated method stub
      // a팀 타자 배열객체 생성
      BTeamHitter[] bth = new BTeamHitter[9];

      base.setFirst(0);
      base.setSecond(0);
      base.setThird(0);
      base.setHome(0);

      boolean defenseInning = true;
      while(defenseInning) {

         bth[bthh.getOrder()]=new BTeamHitter();
         BScore bscore = new BScore();

         System.out.println("===== 경기장 =====");
         System.out.println("1루 상태 : "+base.getFirst()+" 2루 상태 : "+base.getSecond()+" 3루 상태 : "+base.getThird());
         System.out.println("홈 상태(이번 점수) : "+ score);
         System.out.println("=====================================");
         System.out.println(bth[bthh.getOrder()].getNum()[bthh.getOrder()]+"번  "+bth[bthh.getOrder()].getHitter()[bthh.getOrder()]+" 선수 타석에 나왔습니다. 이 선수의 타율="+bth[bthh.getOrder()].getRate()[bthh.getOrder()]);

         System.out.println("1. 스트라이크로 던진다");
         System.out.println("2. 볼로 던진다");
         System.out.print("선택 : ");

         int select = sc.nextInt();
         switch(select) {
         case 1:
            strTrw(bth, bthh, bOut, base);
            break;
         case 2:
            ballTrw(bth, bthh, bOut, base);
            break;
         default :
            System.out.println("1과 2 중에서 골라주세요.");
         }

         if(bOut.getOut()==3) {      // 3 아웃되면 BTeamInning 클래스 종료
            bOut.setOut(0);
            base.setFirst(0);
            base.setSecond(0);
            base.setThird(0);
            base.setHome(0);
            bthh.setOrder(bthh.getOrder());
            bscore.setScore(score);
            bsco.add(bscore);
            score = 0;

            defenseInning = false;
         }
      }
   }

   @Override
   public void sunGong(List<AScore> asco, List<BScore> bsco,ATeamHitter athh, BTeamHitter bthh, BScore bOut,  AScore aOut) {
      // TODO Auto-generated method stub
      int cnt=0; // 회차 카운트용

      for(int j = 1; j < 10 ;j++) {

         cnt++; // 포문 들어오기시작하면 1회차부터 계속 값이 더해짐.
         System.out.println("\n" + cnt + "회 입니다.\n");
         int aTotal=0; // 리스트값에서 총점 불러오기위한 변수
         int bTotal=0; // 리스트값에서 총점 불러오기위한 변수



         attack(asco, athh, aOut, base);
         System.out.println("공격 끝");

         // 여기부터 회차별 점수판
         int aScoreList=0; // 리스트에서 회차별 점수를 불러오기위한 변수
         int bScoreList=0;
         System.out.println("현재까지 점수");
         System.out.println("##A팀점수##");
         for(AScore a : asco) {            // 각 회차별 점수나열
            aScoreList++;
            System.out.println(aScoreList+"회 : "+a.getScore());
         }
         System.out.println("##B팀점수##");
         for(BScore b : bsco) {
            bScoreList++;
            System.out.println(bScoreList+"회 : "+b.getScore());
         }
         // 여기까지 회차별 점수판


         for(int k=0;k<asco.size();k++) {    // asco에서 총점을 aTotal에 저장
            AScore a = asco.get(k);
            aTotal += a.getScore();
         }
         for(int k=0;k<bsco.size();k++) {   // bsco에서 총점을 bTotal에 저장
            BScore b = bsco.get(k);
            bTotal += b.getScore();
         }

         if(j==9) {         // j가 1~9까지 진행함.

            if(aTotal>bTotal) {
               System.out.println(cnt+"회초 A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"점이므로 "+cnt+"회말 진행합니다.");
               aTotal=0;
               bTotal=0;      // 9회말 진행하면 9회말에서 리스트 총점 다시 저장해야하므로 초기화
            }
            else if(aTotal<bTotal) {
               System.out.println("A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 B팀이 승리.\n게임종료");
               return;
            }
            else if(aTotal==bTotal) {
               System.out.println(cnt+"회초 A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 동점이므로 "+cnt+"회말 진행합니다.");
               aTotal=0;
               bTotal=0;      // 9회말 진행하면 9회말에서 리스트 총점 다시 저장해야하므로 초기화
            }
         }
         
         defense(bsco, bthh, bOut, base);

         // 여기부터 회차별 점수판
         aScoreList=0; // 리스트에서 회차별 점수를 불러오기위한 변수
         bScoreList=0;
         System.out.println("현재까지 점수");
         System.out.println("##A팀점수##");
         for(AScore a : asco) {            // 각 회차별 점수나열
            aScoreList++;
            System.out.println(aScoreList+"회 : "+a.getScore());
         }
         System.out.println("##B팀점수##");
         for(BScore b : bsco) {
            bScoreList++;
            System.out.println(bScoreList+"회 : "+b.getScore());
         }
         // 여기까지 회차별 점수판


         for(int k=0;k<asco.size();k++) {    // asco에서 총점을 aTotal에 저장
            AScore a = asco.get(k);
            aTotal += a.getScore();
         }
         for(int k=0;k<bsco.size();k++) {   // bsco에서 총점을 bTotal에 저장
            BScore b = bsco.get(k);
            bTotal += b.getScore();
         }

         if(j==9) {         // j가 1~9까지 진행함.

            if(aTotal>bTotal) {
               System.out.println("A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 A팀이 승리.\n게임종료");
               return;
            }
            else if(aTotal<bTotal) {
               System.out.println("A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 B팀이 승리.\n게임종료");
               return;
            }
            else if(aTotal==bTotal && cnt==12) {
               System.out.println("12회까지 모두 종료되었습니다.");
               System.out.println("A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 게임이 종료됩니다.");
               return;
            }
            else if(aTotal==bTotal) {
               System.out.println(cnt+"회말 A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 동점이므로 "+(cnt+1)+"회 진행합니다.");

               j--; // j가 9여서 포문이 종료되야하는데 연장전진행해야하므로 j를 8로 만들어줌.
            }
         }
      }
   }

   @Override
   public void huGong(List<AScore> asco, List<BScore> bsco,ATeamHitter athh, BTeamHitter bthh, BScore bOut,  AScore aOut) {
      int cnt=0; // 회차 카운트용

      for(int j = 1; j < 10 ; j++) {

         cnt++; // 포문 들어오기시작하면 1회차부터 계속 값이 더해짐.
         System.out.println("\n" + cnt + "회 입니다.");
         int aTotal=0; // 리스트값에서 총점 불러오기위한 변수
         int bTotal=0; // 리스트값에서 총점 불러오기위한 변수


         defense(bsco, bthh, bOut, base);

         System.out.println("수비 끝");

         // 여기부터 회차별 점수판
         int aScoreList=0; // 리스트에서 회차별 점수를 불러오기위한 변수
         int bScoreList=0;
         System.out.println("현재까지 점수");
         System.out.println("##A팀점수##");
         for(AScore a : asco) {            // 각 회차별 점수나열
            aScoreList++;
            System.out.println(aScoreList+"회 : "+a.getScore());
         }
         System.out.println("##B팀점수##");
         for(BScore b : bsco) {
            bScoreList++;
            System.out.println(bScoreList+"회 : "+b.getScore());
         }
         // 여기까지 회차별 점수판


         for(int k=0;k<asco.size();k++) {    // asco에서 총점을 aTotal에 저장
            AScore a = asco.get(k);
            aTotal += a.getScore();
         }
         for(int k=0;k<bsco.size();k++) {   // bsco에서 총점을 bTotal에 저장
            BScore b = bsco.get(k);
            bTotal += b.getScore();
         }

         if(j==9) {         // j가 1~9까지 진행함.

            if(aTotal<bTotal) {
               System.out.println(cnt+"회초 A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"점이므로 "+cnt+"회말 진행합니다.");
               aTotal=0;
               bTotal=0;      // 9회말 진행하면 9회말에서 리스트 총점 다시 저장해야하므로 초기화
            }
            else if(aTotal>bTotal) {
               System.out.println("A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 A팀이 승리.\n게임종료");
               return;
            }
            else if(aTotal==bTotal) {
               System.out.println(cnt+"회초 A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 동점이므로 "+cnt+"회말 진행합니다.");
               aTotal=0;
               bTotal=0;      // 9회말 진행하면 9회말에서 리스트 총점 다시 저장해야하므로 초기화
            }
         }

         attack(asco, athh, aOut, base);

         // 여기부터 회차별 점수판
         aScoreList=0; // 리스트에서 회차별 점수를 불러오기위한 변수
         bScoreList=0;
         System.out.println("현재까지 점수");
         System.out.println("##A팀점수##");
         for(AScore a : asco) {            // 각 회차별 점수나열
            aScoreList++;
            System.out.println(aScoreList+"회 : "+a.getScore());
         }
         System.out.println("##B팀점수##");
         for(BScore b : bsco) {
            bScoreList++;
            System.out.println(bScoreList+"회 : "+b.getScore());
         }
         // 여기까지 회차별 점수판


         for(int k=0;k<asco.size();k++) {    // asco에서 총점을 aTotal에 저장
            AScore a = asco.get(k);
            aTotal += a.getScore();
         }
         for(int k=0;k<bsco.size();k++) {   // bsco에서 총점을 bTotal에 저장
            BScore b = bsco.get(k);
            bTotal += b.getScore();
         }

         if(j==9) {         // j가 1~9까지 진행함.

            if(aTotal>bTotal) {
               System.out.println("A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 A팀이 승리.\n게임종료");
               return;
            }
            else if(aTotal<bTotal) {
               System.out.println("A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 B팀이 승리.\n게임종료");
               return;
            }
            else if(aTotal==bTotal && cnt==12) {
               System.out.println("12회까지 모두 종료되었습니다.");
               System.out.println("A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 게임이 종료됩니다.");
               return;
            }
            else if(aTotal==bTotal) {
               System.out.println(cnt+"회말 A팀 총점수 "+aTotal+"점, B팀 총점수 "+bTotal+"으로 동점이므로 "+(cnt+1)+"회차 진행합니다.");

               j--; // j가 9여서 포문이 종료되야하는데 연장전진행해야하므로 j를 8로 만들어줌.
            }
         }
      }
   }


   @Override
   public void hit(ATeamHitter[] ath, ATeamHitter athh, AScore aOut, Base base) {
      double hitRate = ath[athh.getOrder()].getRate()[athh.getOrder()]*0.5*100 ;    // 타격 성공율
      int prob = rand.nextInt(50)+1;         // 컴퓨터의 랜덤값
      // i++ 인 경우 : 아웃 || 안타 || 홈런

      if(hitRate < prob) {   // 스트라이크인 경우
         if(strike == 0 || strike == 1) { // 기존 스트라이크가 0 or 1인 경우
            strike ++;
            System.out.println("스트라이크입니다!");
            System.out.println(strike + " 스트라이크");
            System.out.println(ball + " 볼");
            System.out.println(aOut.getOut() + " 아웃");
         } else if (strike == 2) {   // 기존 스트라이크가 2인 경우
            out++;               // 아웃카운트 +1
            System.out.println("스트라이크입니다!");
            System.out.println("삼진 아웃입니다!");
            aOut.setOut(aOut.getOut()+1);   // 아웃카운트 +1
            System.out.println(aOut.getOut() + " 아웃");
            strike = 0;
            ball = 0;

            if(athh.getOrder() != 8) {
               athh.setOrder(athh.getOrder()+1);
            } else {
               athh.setOrder(0);
            }

            System.out.println("다음타자 순서 : " + (athh.getOrder()+1) + "\n");   // 인덱스 번호이므로
         }

      } else if(hitRate > prob) {   // 안타인 경우   - 홈런인 경우는 따로 if문
         strike = 0;
         ball = 0;
         System.out.println("안타입니다!");
         System.out.println(aOut.getOut() + " 아웃");
         if (base.getFirst()!=1) {   // 1루에 주자가 없다면
            base.setFirst(1);      // 1루에 주자 배치

            if(athh.getOrder() != 8) {
               athh.setOrder(athh.getOrder()+1);
            } else {
               athh.setOrder(0);
            }
         } else {                  // 1루에 주자가 있고,
            if(base.getSecond()!=1) {   // 2루에 주자가 없다면
               base.setSecond(1);      // 2루에 주자 배치
            } else {                  // 2루에 주자가 있고,
               if(base.getThird()!=1) {   // 3루에 주자가 없다면
                  base.setThird(1);      // 3루에 주자 배치
               } else {            // 1,2,3루 모두 주자가 있다면
                  score++;         // 점수 ++
               }
            }

            if(athh.getOrder() != 8) {
               athh.setOrder(athh.getOrder()+1);
            } else {
               athh.setOrder(0);
            }
         }
      } else if(hitRate == prob) {   // 홈런인 경우
         score += 1 + (base.getFirst()+base.getSecond()+base.getThird());
         System.out.println(1 + (base.getFirst()+base.getSecond()+base.getThird()) + "점 홈런입니다");
         base.setFirst(0);
         base.setSecond(0);
         base.setThird(0);
         strike = 0;
         ball = 0;

         if(athh.getOrder() != 8) {
            athh.setOrder(athh.getOrder()+1);
         } else {
            athh.setOrder(0);
         }
      } else {
         System.out.println("오류");
      }

   }

   @Override
   public void pass(ATeamHitter[] ath, ATeamHitter athh, AScore aOut, Base base) {
      // TODO Auto-generated method stub
      int probH = rand.nextInt(20)+1;      // 타자 랜덤값
      int probP = rand.nextInt(20)+1;      // 투수 랜덤값

      if(probH < probP) {   // 스트라이크인 경우
         if(strike == 0 || strike == 1) { // 기존 스트라이크가 0 or 1인 경우
            strike ++;
            System.out.println("스트라이크입니다!");
            System.out.println(strike + "스트라이크");
            System.out.println(ball + "볼");
            System.out.println(aOut.getOut() + " 아웃");
         } else if (strike == 2) {   // 기존 스트라이크가 2인 경우
            out++;               // 아웃카운트 +1
            strike = 0;            // 타자가 아웃되었으니 스트라이크, 볼 0으로 초기화
            ball = 0;
            System.out.println("스트라이크입니다!");
            System.out.println("삼진 아웃입니다!");
            aOut.setOut(aOut.getOut()+1);   // 아웃카운트 +1
            System.out.println(aOut.getOut() + " 아웃");

            if(athh.getOrder() != 8) {
               athh.setOrder(athh.getOrder()+1);
            } else {
               athh.setOrder(0);
            }

            System.out.println("다음타자 순서 : " + (athh.getOrder()+1) + "\n");   // 인덱스 번호이므로
         }
      } else if(probH >= probP) {   // 볼인 경우
         if (ball == 0 || ball == 1 || ball == 2){
            ball++;
            System.out.println("볼입니다!");
            System.out.println(strike + "스트라이크");
            System.out.println(ball + "볼");
            System.out.println(aOut.getOut() + " 아웃");
         } else if(ball == 3) {   // 포볼인 경우
            System.out.println("볼입니다!");
            System.out.println("4볼로 진루입니다!");
            System.out.println(aOut.getOut() + " 아웃");
            strike = 0;               // 타자가 진루했으니 스트라이크, 볼 0으로 초기화
            ball = 0;
            if (base.getFirst()!=1) {   // 1루에 주자가 없다면
               base.setFirst(1);      // 1루에 주자 배치

               if(athh.getOrder() != 8) {
                  athh.setOrder(athh.getOrder()+1);
               } else {
                  athh.setOrder(0);
               }
            } else {                        // 1루에 주자가 있고,
               if(base.getSecond()!=1) {   // 2루에 주자가 없다면
                  base.setSecond(1);      // 2루에 주자 배치

            } else {                        // 2루에 주자가 있고,
                  if(base.getThird()!=1) {   // 3루에 주자가 없다면
                     base.setThird(1);      // 3루에 주자 배치

                  } else {            // 1,2,3루 모두 주자가 있다면
                     score++;         // 점수 ++
                  }
               }

               if(athh.getOrder() != 8) {
                  athh.setOrder(athh.getOrder()+1);
               } else {
                  athh.setOrder(0);
               }
            }
         }
      }
   }

   @Override
   public void strTrw(BTeamHitter[] bth, BTeamHitter bthh, BScore bOut, Base base) {
      double hitRate = bth[bthh.getOrder()].getRate()[bthh.getOrder()]*0.3*100 ;    // 타격 성공율
      int prob = rand.nextInt(50)+1;                  // 유저의 랜덤값
      // i++ 인 경우 : 아웃 || 안타 || 홈런
      System.out.println("hitRate : " + hitRate);
      System.out.println("prob : " + prob);

      if(hitRate < prob) {   // 스트라이크인 경우
         if(strike == 0 || strike == 1) { // 기존 스트라이크가 0 or 1인 경우
            strike ++;
            System.out.println("스트라이크입니다!");
            System.out.println(strike + " 스트라이크");
            System.out.println(ball + " 볼");
            System.out.println(bOut.getOut() + " 아웃");
         } else if (strike == 2) {   // 기존 스트라이크가 2인 경우
            out++;               // 아웃카운트 +1
            System.out.println("스트라이크입니다!");
            System.out.println("삼진아웃입니다!");
            bOut.setOut(bOut.getOut()+1);   // 아웃카운트 +1
            System.out.println(bOut.getOut() + " 아웃");
            strike = 0;
            ball = 0;

            if(bthh.getOrder() != 8) {
               bthh.setOrder(bthh.getOrder()+1);
            } else {
               bthh.setOrder(0);
            }

            System.out.println("다음타자 순서 : " + (bthh.getOrder()+1) + "\n");   // 인덱스 번호이므로
         }

      } else if(hitRate > prob) {   // 안타인 경우   - 홈런인 경우는 따로 if문
         strike = 0;
         ball = 0;
         System.out.println("안타입니다!");
         System.out.println(bOut.getOut() + " 아웃");
         if (base.getFirst()!=1) {   // 1루에 주자가 없다면
            base.setFirst(1);      // 1루에 주자 배치

            if(bthh.getOrder() != 8) {
               bthh.setOrder(bthh.getOrder()+1);
            } else {
               bthh.setOrder(0);
            }
         } else {                  // 1루에 주자가 있고,
            if(base.getSecond()!=1) {   // 2루에 주자가 없다면
               base.setSecond(1);      // 2루에 주자 배치
            } else {                  // 2루에 주자가 있고,
               if(base.getThird()!=1) {   // 3루에 주자가 없다면
                  base.setThird(1);      // 3루에 주자 배치
               } else {            // 1,2,3루 모두 주자가 있다면
                  score++;         // 점수 ++
                  System.out.println("1점 추가합니다");
               }
            }

            if(bthh.getOrder() != 8) {
               bthh.setOrder(bthh.getOrder()+1);
            } else {
               bthh.setOrder(0);
            }
         }
      } else if(hitRate == prob) {   // 홈런인 경우
         
         score += 1 + (base.getFirst()+base.getSecond()+base.getThird());
         System.out.println(1 + (base.getFirst()+base.getSecond()+base.getThird()) + "점 홈런입니다.");
         base.setFirst(0);
         base.setSecond(0);
         base.setThird(0);
         strike = 0;
         ball = 0;

         if(bthh.getOrder() != 8) {
            bthh.setOrder(bthh.getOrder()+1);
         } else {
            bthh.setOrder(0);
         }
      } else {
         System.out.println("오류");
      }

   }

   @Override
   public void ballTrw(BTeamHitter[] bth, BTeamHitter bthh, BScore bOut, Base base) {
      // TODO Auto-generated method stub
      // 볼을 던졌을 때, 타자가 휘두를 확률, 휘두르지 않을 확률에 따라 경우의 수가 나뉨
      // 휘두를 확률 50%, 휘두르지 않을 확률 50%
      int prob = rand.nextInt(100)+1;   // 1~100까지의 임의의 수
      int half = 51; // 확률 기준점

      if(prob>=half) {   // 50%의 확률 - 타자가 휘둘렀을 때(스트라이크 처리)
         // 스트라이크인 경우
         if(strike == 0 || strike == 1) { // 기존 스트라이크가 0 or 1인 경우
            strike ++;
            System.out.println("스트라이크입니다!");
            System.out.println(strike + " 스트라이크");
            System.out.println(ball + " 볼");
            System.out.println(bOut.getOut() + " 아웃");
         } else if (strike == 2) {   // 기존 스트라이크가 2인 경우
            out++;               // 아웃카운트 +1
            System.out.println("스트라이크입니다!");
            System.out.println("삼진아웃입니다!");
            bOut.setOut(bOut.getOut()+1);   // 아웃카운트 +1
            System.out.println(bOut.getOut() + " 아웃");
            strike = 0;
            ball = 0;

            if(bthh.getOrder() != 8) {
               bthh.setOrder(bthh.getOrder()+1);
            } else {
               bthh.setOrder(0);
            }

            System.out.println("다음타자 순서 : " + (bthh.getOrder()+1) + "\n");   // 인덱스 번호이므로
         }
      } else {   // 나머지 50%의 확률 - 타자가 휘두르지 않았을 때(볼처리)
         // 볼인 경우
         if (ball == 0 || ball == 1 || ball == 2){
            ball++;
            System.out.println("볼입니다!");
            System.out.println(strike + "스트라이크");
            System.out.println(ball + "볼");
            System.out.println(bOut.getOut() + " 아웃");
         } else if(ball == 3) {   // 포볼인 경우
            System.out.println("볼입니다!");
            System.out.println("4볼로 진루입니다!");
            System.out.println(bOut.getOut() + " 아웃");
            strike = 0;               // 타자가 진루했으니 스트라이크, 볼 0으로 초기화
            ball = 0;
            if (base.getFirst()!=1) {   // 1루에 주자가 없다면
               base.setFirst(1);      // 1루에 주자 배치

               if(bthh.getOrder() != 8) {
                  bthh.setOrder(bthh.getOrder()+1);
               } else {
                  bthh.setOrder(0);
               }
            } else {                  // 1루에 주자가 있고,
               if(base.getSecond()!=1) {   // 2루에 주자가 없다면
                  base.setSecond(1);      // 2루에 주자 배치

               } else {                  // 2루에 주자가 있고,
                  if(base.getThird()!=1) {   // 3루에 주자가 없다면
                     base.setThird(1);      // 3루에 주자 배치

                  } else {            // 1,2,3루 모두 주자가 있다면
                     score++;         // 점수 ++
                     System.out.println("1점 추가합니다");
                  }
               }

               if(bthh.getOrder() != 8) {
                  bthh.setOrder(bthh.getOrder()+1);
               } else {
                  bthh.setOrder(0);
               }   // 다음 타자
            }
         }
      }
   }
}