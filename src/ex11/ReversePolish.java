package ex11;

import java.util.*;

import static java.lang.Math.abs;

public class ReversePolish {
    private static Deque<String> stack = new LinkedList<>();
    private String label;
    private ReversePolish left;
    private ReversePolish right;

    private ReversePolish(String label, ReversePolish left, ReversePolish right) {
        this.label = label;
        this.left = left;
        this.right = right;
    }

    public void printNode() {
        printNode("");
    }

    public void printNode(String prefix) {
        if (right != null) {
            right.printNode(prefix + "\t");
        }
        System.out.println(prefix + this);
        if (left != null) {
            left.printNode(prefix + "\t");
        }
    }

    public String toString() {
        return "[" + label + "]";
    }

    private int result() {
        // ここを作る
        // スタックに結果が残っているはず
        return Integer.parseInt(stack.pop());
    }

    private void calc(String label) {
        // ここを作る
        // なぞったノードで演算を行う
        // ノードのlabelが演算子であれば・・・
        switch (label) {
            case "+":
                stack.push(String.valueOf(Integer.parseInt(stack.pop()) + Integer.parseInt(stack.pop())));
                break;
            case "-":
                stack.push(String.valueOf(abs(Integer.parseInt(stack.pop()) - Integer.parseInt(stack.pop()))));
                break;
            case "*":
                stack.push(String.valueOf(Integer.parseInt(stack.pop()) * Integer.parseInt(stack.pop())));
                break;
            case "/":
                stack.push(String.valueOf(Integer.parseInt(stack.pop()) / Integer.parseInt(stack.pop())));
                break;
            // ノードのlabelが数字であれば・・・
            default:
                stack.push(label);
        }
        // Java7以降ならswitch文が便利、スタックも有効に使おう
    }

    private void traversePostorder(ReversePolish n) {
        // ここを作る（帰りがけ順のなぞりでcalc()を呼び出す）
        if (n == null) {
            return;
        }
        traversePostorder(n.left);
        traversePostorder(n.right);
        calc(n.label);
    }

    public static void main(String[] args) {
        ReversePolish tree =
                new ReversePolish("*",
                        new ReversePolish("+",
                                new ReversePolish("3", null, null),
                                new ReversePolish("5", null, null)),
                        new ReversePolish("-",
                                new ReversePolish("6", null, null),
                                new ReversePolish("2", null, null)));
        System.out.println("数式の木");
        tree.printNode();
        tree.traversePostorder(tree);
        System.out.println("計算結果");
        System.out.println(tree.result());
    }
}