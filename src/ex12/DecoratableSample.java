package ex12;

public class DecoratableSample {
   public static void main(String[] args) {
      Decoratable star, box, list, box2;
      star = new Star("Hello Tokyo");
      box = new Box("Hello Japan");
      list = new List("Hello Universe", 3);
      box2 = new Box("日本語");
      star.decorate();
      box.decorate();
      list.decorate();
      box2.decorate();
   }
}