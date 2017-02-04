package ex9;

public class SampleEllipseCircle {
	public static void main(String[] args) {
		// 面積
		Ellipse e = new Ellipse(4.0, 2.0);
		System.out.println("ellipse: " + e);
		System.out.println("  area = " + e.getArea());
		// Circle クラスが定義できたら、以下の3行を実行
		Circle c = new Circle(4.0);
		System.out.println("circle: " + c);
		System.out.println("  area = " + c.getArea());

		// 変形
		// Ellipse クラスの setMajorAxis を用意したら以下の4行を実行
		e.setMajorAxis(8.0);	// 楕円を変形
		System.out.println("ellipse: " + e);
		c.setMajorAxis(8.0);	// 円を変形
		System.out.println("circle: " + c);
	}
}