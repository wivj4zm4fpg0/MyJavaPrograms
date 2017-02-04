package ex5;

import java.util.ArrayList;

public class Deck {
	public static void main(String[] args) {
		ArrayList<Card> array = new ArrayList<Card>();
		int m = 0;
		int n = 0;
		while (m < 4) {
			Card aDeck = new Card(m,n);
			array.add(aDeck);
			n++;
			if(n == 12){
				m++;
				n = 0;
			}
		}
		for(Card aDeck:array){
			aDeck.show();
		}
	}
}
