package ex4;

public class MyPointTest
{
    public static void main(String[] args) {
	MyPoint a, b, c, d;
	a = new MyPoint(6, 8);   // 1.
	b = new MyPoint(10, 10); // 2.
	c = new MyPoint(6, 8);   // 3.
	d = a;                   // 4.

	System.out.println("a は " + a);
	System.out.println("b は " + b);
	System.out.println("c は " + c);
	System.out.println("d は " + d);

	System.out.println("a equals b は "  + a.equals(b)); // 5.
	System.out.println("a equals c は "  + a.equals(c)); // 6.
	System.out.println("a equals d は "  + a.equals(d)); // 7.
	System.out.println("a == b は "  + (a == b)); // 8.
	System.out.println("a == c は "  + (a == c)); // 9.
	System.out.println("a == d は "  + (a == d)); // 10.
    }
}