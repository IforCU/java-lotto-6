package lotto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LottoCheck {
    private final List<Integer>[] lottoPickNumbers; // 로또 구매 값
    private final List<Integer> lottoNumber; // 로또 뽑기 값
    private final Integer[] lottoResult = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};
    private final Integer[] lottoPrice = new Integer[]{0, 0, 0, 5000, 50000, 1500000, 30000000, 2000000000};

    public LottoCheck(List<Integer>[] lottoPickNumbers, List<Integer> lottoNumber) {
        this.lottoPickNumbers = lottoPickNumbers;
        this.lottoNumber = lottoNumber;
    }

    // 로또 값 비교
    public void lottoCompare() {
        for (List<Integer> lotto : lottoPickNumbers) {
            lottoResult[lottoCount(lotto, lottoNumber)]++;
        }
    }

    // 결과 값 출력
    public void lottoOutput() {
        System.out.println("당첨통계\n---");
        System.out.println(place5th(lottoResult[3]));
        System.out.println(place4th(lottoResult[4]));
        System.out.println(place3th(lottoResult[5]));
        System.out.println(place2th(lottoResult[6]));
        System.out.println(place1th(lottoResult[7]));
        System.out.println(profit());
    }

    // 로또 결과 값 전달.
    private int lottoCount(List<Integer> lottoPickNumbers, List<Integer> lottoNumber) {
        int i = 0;
        for (Integer number : lottoNumber) {
            if (lottoPickNumbers.contains(number)) i++;
        }
        if (!(lottoBonus(lottoPickNumbers, lottoNumber))) i++;
        return i;
    }

    // 보너스 값 유무 확인.
    private boolean lottoBonus(List<Integer> lottoPickNumbers, List<Integer> lottoNumber) {
        return lottoPickNumbers.contains(pop(lottoNumber));
    }

    //리스트 pop 구현
    private int pop(List<Integer> number) {
        return number.get(number.size() - 1);
    }

    //3개 일치
    private String place5th(int number) {
        return "3개 일치 (5,000원) - " + number + "개";
    }

    //4개 일치
    private String place4th(int number) {
        return "4개 일치 (50,000원) - " + number + "개";
    }

    //5개 일치
    private String place3th(int number) {
        return "5개 일치 (1,500,000원) - " + number + "개";
    }

    //5개 + 보너스 일치
    private String place2th(int number) {
        return "5개 일치, 보너스 볼 일치 (30,000,000원) - " + number + "개";
    }

    //6개 일치
    private String place1th(int number) {
        return "6개 일치 (2,000,000,000원) - " + number + "개";
    }

    //수익률 리턴
    private String profit() {
        long sum = 0;
        double profit = 0;
        for (int i = 3; i <= 7; i++) {
            sum += (long) lottoResult[i] * lottoPrice[i];
        }
        long money;
        money = lottoPickNumbers.length * 1000L;
        profit = (sum / money) * 100;
        return "총 수익률은 " + String.format("%.1f", profit) + "입니다.";
    }
}
