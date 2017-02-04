package ex5;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class QueueByLinkedList
{
  public static void main(String args[])
  {
    Queue<String> queue = new LinkedList<String>();

    queue.add("Alice");
    System.out.println("Aliceを追加\n" + "キューの中身 = " + queue);
    queue.add("Bob");
    System.out.println("\nBobを追加\n" + "キューの中身 = " + queue);
    queue.add("Chris");
    System.out.println("\nChrisを追加\n" + "キューの中身 = " + queue);
    queue.add("Diana");
    System.out.println("\nDianaを追加\n" + "キューの中身 = " + queue);
    queue.add("Elmo");
    System.out.println("\nElmoを追加\n" + "キューの中身 = " + queue);
    queue.add("Fred");
    System.out.println("\nFredを追加\n" + "キューの中身 = " + queue);

    try
    {
      while(true)
      {
        String name = queue.remove();
        System.out.println("\n" + name + "を削除");
        System.out.println("キューの中身 = " + queue);
      }
    }
    catch(NoSuchElementException e)
    {
      System.out.println("\nキューが空です。");
    }
  }
}
