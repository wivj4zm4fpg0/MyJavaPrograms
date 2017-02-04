package ex4;

public class MyPoint
{
	private int x;
	private int y;
	public MyPoint(int x,int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public double getR(){
		return Math.sqrt(x * x + y * y);
	}
	public double getTheta(){
		return Math.toDegrees(Math.atan2(x, y));
	}
	public double distance(MyPoint anotherPoint){
		return Math.sqrt((anotherPoint.x - x) * (anotherPoint.x - x) +
				         (anotherPoint.y - y) * (anotherPoint.y - y));
	}
	public String toString() {
    	return "a MyPoint(" + getX() + ", " + getY() + ")";
    }
	public boolean equals(MyPoint aPoint) {
		boolean ans;
		ans = (getX() == aPoint.getX()) && (getY() == aPoint.getY());
		return ans;
	}
}