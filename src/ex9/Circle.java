package ex9;

public class Circle {
	private double axis;

	public Circle(double axis) {
		this.axis = axis;
	}
	public double getRadius() {
		return axis;
	}
	public double getArea() {
		return axis * axis * Math.PI;
	}
	public String toString() {
		return "[" + axis + "," + axis + "]";
	}
	public void setMajorAxis(double majorAxis){
		axis = majorAxis;
	}
	public void setMinorAxis(double minorAxis) {
		axis = minorAxis;;
	}
}
