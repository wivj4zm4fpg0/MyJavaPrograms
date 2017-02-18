package compiler

import java.io.*;
import java.util.*;

import static java.lang.Math.ceil;

// トークンを表す抽象クラス
// トークン間の比較のために Comparable インタフェースを実装
abstract class Token implements Comparable<Token> {
    // トークンの文字列表現
    private String string;
    // トークンの種類
    int kind;
    // トークンの種類を表す定数
    // 値は演算子順位行列の添字と対応していれば何でもよく、順不同
    static final int START = 0;  // 始
    static final int SUM = 1;    // +
    static final int SUB = 2;    // -
    static final int MUL = 3;    // *
    static final int DIV = 4;    // /
    static final int LPAREN = 5; // (
    static final int RPAREN = 6; // )
    static final int OPERAND = 7;// 識別子(変数)または定数
    static final int END = 8;    // 終
    static final int POWER = 9;  // ^
    // 演算子順位行列
    // トークンを追加する場合には、対応する行と列の両方を追加
    private static final int order[][] = { // [左][右]
            // 始  +  -  *  /  (  ) 識別子 終 <-右  左
            {9, 1, 1, 1, 1, 1, 9, 1, 0, 1},       // 始
            {9, -1, -1, 1, 1, 1, -1, 1, -1, 1},   // +
            {9, -1, -1, 1, 1, 1, -1, 1, -1, 1},   // -
            {9, -1, -1, -1, -1, 1, -1, 1, -1, 1}, // *
            {9, -1, -1, -1, -1, 1, -1, 1, -1, 1}, // /
            {9, 1, 1, 1, 1, 1, 0, 1, 9, 1},       // (
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9},       // )
            {9, -1, -1, -1, -1, 9, -1, 9, -1, -1},// 識別子
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9},       // 終
            {9, -1, -1, -1, -1, 1, -1, 1, -1, -1},// ^
    };

    // コンストラクタ
    Token(String string) {
        this.string = string;
    }

    public String toString() {
        return string;
    }

    int getKind() {
        return kind;
    }

    // トークン間の比較
    public int compareTo(Token anotherToken) {
        return order[this.kind][anotherToken.getKind()];
    }
}


// Token のサブクラスのインスタンスを生成するための Factory クラス
class TokenFactory {
    public static Token newInstance(String string) {
        Token instance;
        if (string.equals("`") || string.equals(";") || string.equals("=")) {
            instance = new SpecialSymbol(string);
        } else if (string.equals("+")) {
            instance = new Sum(string);
        } else if (string.equals("-")) {
            instance = new Sub(string);
        } else if (string.equals("*")) {
            instance = new Mul(string);
        } else if (string.equals("/")) {
            instance = new Div(string);
        } else if (string.equals("(")) {
            instance = new LParen(string);
        } else if (string.equals(")")) {
            instance = new RParen(string);
        } else if (string.equals("^")) {
            instance = new Power(string);
        } else if (string.matches("\\p{all}+")) { // すべての数字
            instance = new IntConstant(string);
        } else { // 識別子(変数)
            instance = new Identifier(string);
        }
        return instance;
    }
}


// 特殊記号 ` ; =
class SpecialSymbol extends Token {
    SpecialSymbol(String string) {
        super(string);
        if (string.equals("`")) kind = START;
        else if (string.equals(";")) kind = END;
        // "=" は扱わないので何もしない
    }
}

// 2項演算子の抽象クラス。2引数の calc メソッドを持つ
abstract class BinaryOperator extends Token {
    BinaryOperator(String string) {
        super(string);
    }

    public abstract double calc(double operand1, double operand2);
}

// 2項演算子 +
class Sum extends BinaryOperator {
    Sum(String string) {
        super(string);
        kind = SUM;
    }

    public double calc(double operand1, double operand2) {
        return operand1 + operand2;
    }
}

// 2項演算子 -
class Sub extends BinaryOperator {
    Sub(String string) {
        super(string);
        kind = SUB;
    }

    public double calc(double operand1, double operand2) {
        return operand1 - operand2;
    }
}

// 2項演算子 *
class Mul extends BinaryOperator {
    Mul(String string) {
        super(string);
        kind = MUL;
    }

    public double calc(double operand1, double operand2) {
        return operand1 * operand2;
    }
}

// 2項演算子 /
class Div extends BinaryOperator {
    Div(String string) {
        super(string);
        kind = DIV;
    }

    public double calc(double operand1, double operand2) {
        return operand1 / operand2;
    }
}

// 左括弧
class LParen extends Token {
    LParen(String string) {
        super(string);
        kind = LPAREN;
    }
}

// 右括弧
class RParen extends Token {
    RParen(String string) {
        super(string);
        kind = RPAREN;
    }
}

// 整数定数
class IntConstant extends Token {
    private double value;

    IntConstant(String string) {
        super(string);
        try {
            value = Double.parseDouble(string);
        } catch (Exception e) {
            System.err.println(e);
        }
        kind = OPERAND;
    }

    double intValue() {
        return value;
    }
}

// 識別子(変数名)
class Identifier extends Token {
    Identifier(String string) {
        super(string);
        kind = OPERAND;
    }
}

// 累乗
class Power extends BinaryOperator {
    Power(String string) {
        super(string);
        kind = POWER;
    }

    public double calc(double operand1, double operand2) {
        return Math.pow(operand1, operand2);
    }
}

// プログラム本体
class Calcurator {
    void Calculate() {
        System.out.println("式の値を求めます (Ctrl-d で終了)");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            System.out.print("式を入力してください: ");
            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                break;
            }
            if (line == null) // 入力終了 (Ctrl-d)
                break;
            // 文字列 line を Token に分解しリストに
            LinkedList<Token> inputList = stringToTokenList(line);
            // 逆ポーランド記法に変換
            LinkedList<Token> reversePolishList = reverse(inputList);
            // 逆ポーランド記法に変換した結果を表示
            // 逆ポーランド記法の式の値を計算
            Double value = calculate(reversePolishList);
            // 式の値を表示
            System.out.println("   value  : " + value);
            System.out.println("消費税込み: " + (int) ceil((double) value * 1.08) + "円");
        }
    }

    // 入力された文字列からトークンのリストを生成
    private LinkedList<Token> stringToTokenList(String string) {
        LinkedList<Token> inputList = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(string, " `;=()+-*/^", true);
        while (st.hasMoreTokens()) {
            String tokenString = st.nextToken();
            if (!tokenString.equals(" "))
                inputList.add(TokenFactory.newInstance(tokenString));
        }
        return inputList;
    }

    // 入力されたトークンのリストから逆ポーランド記法のリストを生成
    private LinkedList<Token> reverse(LinkedList<Token> inputList) {
        LinkedList<Token> reversePolishList = new LinkedList<>();
        inputList.add(TokenFactory.newInstance(";")); // 終了記号
        Stack<Token> tokenStack = new Stack<>();
        tokenStack.push(TokenFactory.newInstance("`")); // 開始記号
        do {
            Token a = inputList.poll();  // 先頭を取り出す
            if (a.getKind() == Token.OPERAND) {
                // オペランド(定数か変数)だったらすぐに出力
                reversePolishList.add(a);
            } else {
                Token s = tokenStack.peek();
                while (s.compareTo(a) == -1) {
                    // a よりも優先度の高い演算子がスタックにあったら出力
                    reversePolishList.add(tokenStack.pop());
                    s = tokenStack.peek();
                }
                if (s.compareTo(a) == 1) {
                    // a より優先度の低い演算子がスタックの先頭だったら
                    // a をスタックに積む
                    tokenStack.push(a);
                } else if (s.compareTo(a) == 0) {
                    tokenStack.pop(); // 取り出して捨てる
                }
            }
        } while (!tokenStack.empty());
        return reversePolishList;
    }

    // 逆ポーランド記法のトークンリストを演算
    private Double calculate(List<Token> reversePolishList) {
        // スタックを用意 (とりあえず中身はInteger)
        // 中身は Integer だがオートボクシングにより int のように使える
        Stack<Double> stack = new Stack<>();
        for (Token token : reversePolishList) {
            // 現在のスタックを表示
            // 2項演算子だったら、前に数値が2つ push されているはずなので、
            // それらを pop して演算し、結果の値を push する
            if (token instanceof BinaryOperator) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                stack.push(((BinaryOperator) token).calc(operand1, operand2));
            } else if (token instanceof IntConstant) { // 数値
                // 文字列から数値に変換して push
                stack.push(((IntConstant) token).intValue());
            }
        }
        // 最終的に stack には計算された値1つが入っている
        return stack.pop();
    }
}

public class Compiler_15FI057 {
    public static void main(String[] args) {
        Calcurator calcurator = new Calcurator();
        calcurator.Calculate();
    }
}

