package ex5;

public class Card {
    public int mark;
    public int number;

    public static final int SPADE = 0;
    public static final int HEART = 1;
    public static final int CLUB = 2;
    public static final int DIAMOND = 3;

    public static final int BLACK = 0;
    public static final int RED = 1;

    public Card(int m, int num) {
    	mark = m;
    	number = num;
    }
    public int getMark() {
    	return mark;
    }
    public int getNumber() {
    	return number;
    }
    public String toString(){
        String[] a = {"S","H","C","D"};
        String[] b = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    	return "a Card("+ a[getMark()] + "," + b[getNumber() - 1] +")";
    }
	public void show() {
        String[] c = {"S","H","C","D"};
        String[] d = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    	System.out.println("a Card("+ c[getMark()] + "," + d[getNumber() - 1] +")");
	}
}
