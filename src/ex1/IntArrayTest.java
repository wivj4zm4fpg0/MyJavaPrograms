package ex1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntArrayTest {
	   public static void main(String[] args) {
		      Scanner scanner = new Scanner(System.in);
		      int[] numbers = null; // 整数型の配列を入れる変数宣言
		      System.out.println("配列の大きさを入れてください。");
		      try {
		         int n = scanner.nextInt(); // 個数の入力
		         if (n <= 0) {
		            System.out.println("ゼロより大きな値を入れてください。");
		            System.exit(0);
		         }
		         // 配列の確保
		         // ここを作る
		         numbers = new int[n];

		         System.out.println("整数値を" + n + "個入れてください。");
		         // 配列に値を入力
		         // ここを作る
		         for(int i = 0;i < n;i++){
		             int b = scanner.nextInt();
		        	 numbers[i] = b;
		         }

		      } catch (InputMismatchException e) {
		         System.out.println("数値の形式が違います。");
		         e.printStackTrace();
		         System.exit(0);
		      }
		      // 入力された値を表示
		      System.out.println("入力された値は");
		      // ここを作る タブ文字は \t を使うとよい
		      float sum = 0;
		      for(int i = 0;i < numbers.length;i++){
		      System.out.println(numbers[i]);
		      sum += numbers[i];
		      }

		      // 合計の表示
		      // ここを作る
		      System.out.println("合計の値は\n" + sum);

		      // 平均の表示
		      // ここを作る
		      System.out.println("平均の値は\n" + sum / numbers.length);

		      // scannerを閉じる
		      scanner.close();
		   }

}
