package ex12;

import java.util.ArrayList;

public class DecoratableList {
	public static void main(String[] args) {
		ArrayList array = new ArrayList<Decoratable>();
		array.add(new Star("aaa"));
		array.add(new Box("aaa"));
		array.add(new List("aaa",3));
		//array.decorate();
	}
}
