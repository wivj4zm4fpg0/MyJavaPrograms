package ex6;

import lesson03.sample1.Node;

public class Numbers {
	  public static void main(String args[])
	  {
		Node no1 = new Node("One");
		no1.setNext(null);

		Node no2 = new Node("Two");
		no2.setNext(no1);

		Node no3 = new Node("Three");
		no3.setNext(no2);

		Node no4 = new Node("Four");
		no4.setNext(no3);

		for(Node n = no4; n != null; n = n.getNext()) {
		    System.out.println(n);
		}
	  }

}
