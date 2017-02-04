package ex12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BinarySearch {
    private final int n = 9999;
    private int[] table = new int[n];

    public BinarySearch(String filename) {
        try {
            // ここを作る
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                table[i] = Integer.parseInt(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(filename + "が見つかりません。");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void search(int key) {
        // ここを作る
        // 二分探索でkeyを探す
        int low = 0;
        int high = n - 1;
        int i = 0;

        while (low <= high) {
            i++;
            int mid = (low + high) / 2;
            if (key == table[mid]) {
                System.out.println(i + "回の探索で" + key + "が見つかりました");
                return;
            } else if (key < table[mid])
                high = mid - 1;
            else
                low = mid + 1;
        }
        System.out.println(i + "回探索しましたが" + key + "は見つかりませんでした");
    }

    public void show() {
        // ここを作る
        // 表の要素を全て表示する
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + "=" + table[i]);
        }
    }

    public static void main(String[] args) {
        String filename = "sorted.txt";
        BinarySearch table = new BinarySearch(filename);

        table.show();
        System.out.println("key=1000を探索");
        table.search(1000);
        System.out.println("key=9412を探索");
        table.search(9412);
        System.out.println("key=2を探索");
        table.search(2);
        System.out.println("key=10000を探索");
        table.search(10000);
    }
}

