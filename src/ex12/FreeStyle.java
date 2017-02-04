package ex12;

public class FreeStyle {
	public static void main(String[] args) {
		Decoratable number, name, name2, address;
		number = new Star("15FI057");
		name = new Box("Satoru Saika");
		name2 = new Box("雑賀智");
		address = new List("15fi057@im.dendai.ac.jp", 3);
		number.decorate();
		name.decorate();
		name2.decorate();
		address.decorate();
	}

}
