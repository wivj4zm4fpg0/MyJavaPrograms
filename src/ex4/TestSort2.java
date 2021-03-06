package ex4;

import ex3.BubbleSort;
import ex3.InsertionSort;
import ex3.SelectionSort;

public class TestSort2 {
	  public static void test(String filename){
		    long start, stop;

		    System.out.println(filename + "のソート");
		    start = System.currentTimeMillis();
		    BubbleSort bs = new BubbleSort(filename);
		    bs.sort();
		    stop = System.currentTimeMillis();
		 	System.out.println("  バブルソート：" + (stop - start) + "[ms]");

		    start = System.currentTimeMillis();
		    SelectionSort ss = new SelectionSort(filename);
		    ss.sort();
		    stop = System.currentTimeMillis();
		 	System.out.println("    選択ソート：" + (stop - start) + "[ms]");

		    start = System.currentTimeMillis();
		    InsertionSort is = new InsertionSort(filename);
		    is.sort();
		    stop = System.currentTimeMillis();
		 	System.out.println("    挿入ソート：" + (stop - start) + "[ms]");

		    start = System.currentTimeMillis();
		    QuickSort qs = new QuickSort(filename);
		    qs.sort();
		    stop = System.currentTimeMillis();
		 	System.out.println("クイックソート：" + (stop - start) + "[ms]");

		    start = System.currentTimeMillis();
		    MergeSort ms = new MergeSort(filename);
		    ms.sort();
		    stop = System.currentTimeMillis();
		 	System.out.println("  マージソート：" + (stop - start) + "[ms]\n");
		  }
  public static void main(String[] args) {
    String file1 = "rand2.txt";
    String file2 = "reverse2.txt";
    String file3 = "sorted2.txt";
    String file4 = "same2.txt";

    // ここを作る
    // 各ファイルに対して，各ソートアルゴリズムを実行する
    // 各アルゴリズムでソートする際に，その処理時間を測定する
    test(file1);
    test(file2);
    test(file3);
    test(file4);
  }
}
