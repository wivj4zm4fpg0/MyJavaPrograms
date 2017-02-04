package ex4;

public class Card {
    private int mark;
    private int number;

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
    public int color(){
    	switch(mark){
    	case 0:
    	case 2:
    		return BLACK;
    	case 1:
    	case 3:
    		return RED;
    	}
		return mark;
    }
    public String toString(){
        String[] a = {"S","H","C","D"};
        String[] b = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    	return "a Card("+ a[getMark()] + "," + b[number - 1] +")";
    }

    public static void main(String[] args) {
    	Card card1, card2, card3;
    	card1 = new Card(Card.SPADE, 1);
    	card2 = new Card(Card.DIAMOND, 2);
    	card3 = new Card(Card.SPADE, 13);

    	System.out.println(card1);
    	System.out.println(card2);
    	System.out.println(card3);
    }
}