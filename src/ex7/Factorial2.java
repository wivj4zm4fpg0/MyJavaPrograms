package ex7;

public class Factorial2 {
    public static int factorial(int x) {
        // 再帰呼び出しを使ってここを作る
        int y;
        if (x == 0) {
            return 1;
        } else {
            y = factorial(x - 1);
            return x * y;
        }

    }

    public static void main(String[] args) {
        System.out.println("再帰を使って階乗を求めます。");
        for (int i = 0; i < 6; i++) {
            System.out.println(i + "の階乗は" + factorial(i));
        }
    }
}
