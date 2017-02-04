package ex9;

public class Ellipse {
	private double majorAxis;
	private double minorAxis;

	public Ellipse(double majorAxis, double minorAxis){
		this.majorAxis = majorAxis;
		this.minorAxis = minorAxis;
	}
	public double getMajorAxis() {
		return majorAxis;
	}
	public double getMinorAxis() {
		return minorAxis;
	}
	public double getArea() {
		return majorAxis * minorAxis * Math.PI;
	}
	public String toString() {
		return "[" + majorAxis + "," + minorAxis + "]";
	}
	public  void setMajorAxis(double majorAxis){
		this.majorAxis = majorAxis;
	}
	public void setMinorAxis(double minorAxis) {
		this.minorAxis = minorAxis;
	}
}
