package ex5;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class StackByLinkedList {
  public static void main(String[] args) {
    LinkedList<String> stack = new LinkedList<String>();

    stack.push("Alice");
    System.out.println("Aliceを追加\n" + "スタックの中身 = " + stack);
    stack.push("Bob");
    System.out.println("\nBobを追加\n" + "スタックの中身 = " + stack);
    stack.push("Chris");
    System.out.println("\nChrisを追加\n" + "スタックの中身 = " + stack);
    stack.push("Diana");
    System.out.println("\nDianaを追加\n" + "スタックの中身 = " + stack);
    stack.push("Elmo");
    System.out.println("\nElmoを追加\n" + "スタックの中身 = " + stack);
    stack.push("Fred");
    System.out.println("\nFredを追加\n" + "スタックの中身 = " + stack);

    try {
      while (true) {
        String name = stack.remove();
        System.out.println("\n" + name + "を削除");
        System.out.println("スタックの中身 = " + stack);
      }
    } catch (NoSuchElementException e) {
      System.out.println("\nスタックが空です。");
    }
  }

}
