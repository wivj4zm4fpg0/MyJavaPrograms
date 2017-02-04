package ex3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SelectionSort {
  private final int n = 50000;
  private int[] array = new int[n];

  public SelectionSort(String filename) {
    // ここを作る
    // ファイル名を引数とする
    // ファイルを開いて全て読み込んで配列arrayに入れる
	  try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			for(int i = 0;i < n;i++) {
				line = reader.readLine();
				array[i] = Integer.parseInt(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
  }
  public void sort() {
    // ここを作る
    // 選択ソートを実装する
    // 配列arrayの中身をソートする
	  int list = array.length;
	  for(int i = 0;i < list - 1;i++){
		  int lowest = i;
		  int lowkey = array[i];
		  for(int j = i + 1;j < list;j++){
			  if(array[j] < lowkey){
				  lowest = j;
				  lowkey = array[j];
			  }
		  }
		  int temp = array[i];
		  array[i] = array[lowest];
		  array[lowest] = temp;
	  }
  }
  public void output(String filename) {
    // ここを作る
    // ファイル名を引数とする
    // 配列arrayをファイルに出力する
    // 1行に1レコード
	    // 1行に1レコード
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filename));
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
				for(int i = 0;i < n;i++) {
					writer.println(array[i]);
				}
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
  }

  public static void main(String[] args) {
    String file1 = "rand2.txt";
    String file2 = "result_ex3-2_rand2.txt";

    SelectionSort ss = new SelectionSort(file1);
    ss.sort();
    ss.output(file2);
  }
}
