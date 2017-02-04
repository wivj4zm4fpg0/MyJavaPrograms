package ex1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IntArrayListTest {
	   public static void main(String[] args) {
		      Scanner scanner = new Scanner(System.in);
		      // 整数を要素とするArrayListの宣言と生成
		      ArrayList<Integer> intList = new ArrayList<Integer>();
		      try {
		         System.out.println("整数値を入力してください。整数以外を入力するまで繰り返します。");
		         // 入力部分
		         // ここを作る
		         while(true){
		        	 try{
		        		 String n = scanner.next();
		        		 int val = Integer.parseInt(n);
		        		 intList.add(val);
		        	 } catch (NumberFormatException e) {
		        		 break;
		        	 }
		         }

		      } catch (InputMismatchException e) {
		         System.out.println("入力が完了しました。データの数は" + intList.size());
		      }
		      // 入力された値を表示する
		      System.out.println("入力された値は");
		      // ここを作る
		      float sum = 0;
		      for(int i = 0;i < intList.size();i++){
		    	  System.out.println(intList.get(i));
		    	  sum += intList.get(i);
		      }

		      // 合計を表示する
		      // ここを作る
		      System.out.println("合計された値は\n" + sum);

		      // 平均を表示する
		      // ここを作る
		      System.out.println("平均された値は\n" + sum / intList.size());

		      // scannerを閉じる
		      scanner.close();
	   }
}
