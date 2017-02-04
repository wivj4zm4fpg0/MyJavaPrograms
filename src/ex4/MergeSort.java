package ex4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MergeSort {
    private final int n = 50000;
    private int[] a = new int[n];

    MergeSort(String filename) {
        // ここを作る
        // ファイル名を引数とする
        // ファイルを開いて全て読み込んで配列aに入れる
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                a[i] = Integer.parseInt(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mergeSort(int low, int high) {
        // ここを作る
        // マージソートを実装する
        // 配列aの中身をソートする
        int[] b = new int[a.length];
        if (low >= high) {
            System.out.println("OK");
            return;
        }
        int mid = (low + high) / 2;
        mergeSort(low, mid);
        mergeSort(mid + 1, high);
        System.arraycopy(a, low, b, low, mid - low + 1);
        for (int i = mid + 1, j = high; i <= high; i++, j--) {
            b[i] = a[j];
        }
        int i = low;
        int j = high;
        for (int k = low; k <= high; k++) {
            if (b[i] <= b[j]) {
                a[k] = b[i++];
            } else {
                a[k] = b[j--];
            }
        }
    }

    public void sort() {
        mergeSort(0, a.length - 1);
    }

    private void output(String filename) {
        // ここを作る
        // ファイル名を引数とする
        // 配列aをファイルに出力する
        // 1行に1レコード
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            for (int i = 0; i < n; i++) {
                writer.println(a[i]);
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String file1 = "rand2.txt";
        String file2 = "result_ex4-2_rand2.txt";
        MergeSort ms = new MergeSort(file1);
        ms.sort();
        ms.output(file2);
    }
}
