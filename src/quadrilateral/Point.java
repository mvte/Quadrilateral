/* 1/6/20
 * Point Class
 * Jan Marzan
 * Period 1
 */
package quadrilateral;

import java.text.DecimalFormat;

public class Point {
	
	private double x;
	private double y;
	
	public DecimalFormat df = new DecimalFormat("0.##");
	
	public Point() {
		x = 0; 
		y = 0;
	}
	
	public Point(double a, double b) {
		x = a;
		y = b;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void changeX(double xVel) {
		x += xVel;
	}
	
	public void changeY(double yVel) {
		y += yVel;
	}
	
	public double distanceTo(Point p) {
		return Math.sqrt(Math.pow(p.getX()-this.getX(), 2) + Math.pow(p.getY()-this.getY(), 2));
	}
	
	public boolean equals(Point p) {
		return Math.abs(this.distanceTo(p)) < 0.001;
	}
	
	public String toString() {
		return "(" + df.format(x) + ", " + df.format(y) + ")";
	}
	

}

