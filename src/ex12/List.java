package ex12;

public class List implements Decoratable {
	private String str;
	private int n;

	public List(String str, int n) {
		this.str = str;
		this.n = n;
	}
	public void decorate() {
		while(n > 0) {
			System.out.println("・" + str + "!");
			n--;
		}
	}
}
