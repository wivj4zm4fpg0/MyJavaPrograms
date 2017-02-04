package ex11;

public class Swallow extends Bird implements FlyingObject {
	public void fly() {
		System.out.println("Swallowが飛びます。");
	}
	public void pick() {
		System.out.println("つつきます。");
	}
}
