/* 1/6/20
 * Line Class
 * Jan Marzan
 * Period 1
 */

package quadrilateral;

public class Line {
	
	private double m;
	private double b;
	private double xInt;
	private boolean isHorizontal;
	private boolean isVertical;
	
	//y = x
	public Line() {
		m = 1;
		b = 0;
		isHorizontal = false;
		isVertical = false;
		xInt = 0;
	}
	
	//y = mx + b
	public Line(double slope, double yInt) {
		m = slope;
		b = yInt;
		isHorizontal = m==0;
		isVertical = false;
		if(!isHorizontal)
			xInt = -b/m;
	}
	
	//line given two points
	public Line(Point u, Point v) {
		isVertical = u.getX() == v.getX();
		if(isVertical) {
			xInt = u.getX();	
		}else {	
			m = (u.getY()-v.getY())/(u.getX()-v.getX());
			b = u.getY()-m*u.getX();
			isHorizontal = m==0;
			if(!isHorizontal)
				xInt = -b/m;
		}
	}
	
	//y - y1 = m(x - x1) + b
	public Line(Point p, double slope) {
		isVertical = false;
		m = slope;
		isHorizontal = m==0;
		b = (p.getY()-m*p.getX());
		if(!isHorizontal)
			xInt = -b/m;	
	}
	
	//Ax + Bx = C
	public Line(double x, double y, double z) {
		if(y==0) {
			isVertical = true;
			xInt = z/x;	//z/x;
		}else {
			m = -x/y;	//-x/y;
			isHorizontal = m==0;
			b = z/y;	//z/y;
			if(!isHorizontal)
				xInt = -b/m;
		}
	}
	
	public boolean isVertical() {
		return isVertical;
	}
	
	public boolean isHorizontal() {
		return isHorizontal;
	}
	
	public double getSlope() {
		return m;
	}
	
	public double getYint() {
		return b;
	}
	
	public double getXint() {
		return xInt;
	}
	
	public boolean equals(Line l) {
		//if slopes and intercepts are similar enough, it will return true
		//also returns true if both lines are vertical and the x ints are close enough
		return (Math.abs(this.getSlope() - l.getSlope()) < .01
				&& Math.abs(this.getYint() - l.getYint()) < .01)
				|| ((this.isVertical() && l.isVertical()) && this.getXint() - l.getXint() < .01);
	}
	
	public boolean isConsistent(Line l) {
		//a consistent system has at least one solution
		return this.intersection(l) != null || (this.isParallel(l) && this.getYint()==(l.getYint()));
	}
	
	public boolean isParallel(Line l) {
		return (Math.abs(this.getSlope() - l.getSlope()) < .01
				|| (this.isVertical() && l.isVertical())) && !this.equals(l);
	}
	
	public boolean isPerpendicular(Line l) {
		return (Math.abs(this.getSlope() - (-1 / l.getSlope())) < .01)
				|| (this.isHorizontal() && l.isVertical())
				|| (this.isVertical() && l.isHorizontal());
		}
	
	public Point intersection(Line l){
		//finds the the point of intersection between two lines
		//idk if ive taken into acct all cases tho
		double x, y;
		if(this.equals(l)) {
			return null;
		}else if(this.isParallel(l)) {
			return null;
		}else if(this.isPerpendicular(l) && (this.isVertical)) {
			return new Point(this.getXint(), l.getYint());
		}else if(this.isPerpendicular(l) && (this.isHorizontal)) {
			return new Point(l.getXint(), this.getYint());
		}else if(this.isVertical) {
			return new Point(xInt, l.evaluate(this.getXint()));
		}else if(l.isVertical) {
			return new Point(l.getXint(), this.evaluate(l.getXint()));
		}else {
			x = (l.getYint()-this.getYint())/(this.getSlope()-l.getSlope());
			y = evaluate(x);
			return new Point(x, y);
		}
	}
	
	public double evaluate(double x) {
		return x*m + b;
	}
	
	public double evaluateY(double y) {
		return(y-b)/m;
	}
	
	public String toString() {
		if(isVertical){
			return "x = " + xInt;
		}else if(m==0){
			return "y = " + b;
		}else if(m==1) {
			return "y = x + " + b;
		}else {
			if(b==0)
				return "y = " + m + "x";
			else if(b<0)
				return "y = " + m + "x - " + -b;
			else
				return "y = " + m + "x + " + b;
		}

	}
	
}

