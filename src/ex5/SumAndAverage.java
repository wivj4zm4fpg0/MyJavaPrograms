package ex5;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SumAndAverage {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		System.out.println("整数値を入力してください。");
		int a = 0;  //合計
		float b = 0;//平均
		try {
			while (scanner.hasNext()) {
				int line = scanner.nextInt();
				array.add(line);
				a += line;
				b++;
			}
			System.out.println("入力された数値は、");
			for (Integer line: array) {
				System.out.println(line);
			}
			System.out.println("合計は、");
			System.out.println(a);
			System.out.println("平均は、");
			System.out.println(a/b);
		} catch (InputMismatchException e) {
	        System.out.println("型が違います: " + e);
	    }
	}
}
