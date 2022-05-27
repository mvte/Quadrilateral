package quadrilateral;

/* 1/6/20
 * Quadrilateral Class
 * Jan Marzan
 * Period 1
 */

import java.text.DecimalFormat;

public class Quadrilateral {
	/*Points are created in clockwise order such that:
	 * A is top left
	 * B is top right
	 * C is bottom right
	 * D is bottom left
	 */
	private Point A;
	private Point B;
	private Point C;
	private Point D;
	private String type;
	
	//default constructor
	public Quadrilateral(){
		genA();
		genB();
		genC();
		genD();
		type = classify();
		// assign value to type using classify method
	}
	
	// overloaded constructor
	/*Creates a quadrilateral based upon the String type passed as a 
	 * parameter. type: Parallelogram, Rhombus, Rectangle, Square and Trapezoid
	 * EXTRA CREDIT: kite
	 * Use classify method to initialize type. 
	 */
	public Quadrilateral(String str){
		if(str.equalsIgnoreCase("Parallelogram")) {
			initParallelogram();
		}else if(str.equalsIgnoreCase("Rectangle")) {
			initRectangle();
		}else if(str.equalsIgnoreCase("Square")) {
			initSquare();
		}else if(str.equalsIgnoreCase("Rhombus")) {
			initRhombus();
		}else if(str.equalsIgnoreCase("Trapezoid")) {
			initTrapezoid();
		}else {
			genA();
			genB();
			genC();
			genD();
		}
		
		type = classify();
			
	}

	//random point with x ->[00-90], y->[0,90], x and y are multiples of 10
	private void genA(){
		int x = (int)(Math.random()*10)*10;
		int y = (int)(Math.random()*10)*10;
		A = new Point(x, y);
	}
	
	//random point with x ->[110-200], y->[0,90], x and y are multiples of 10
	private void genB(){
		int x = (int)(Math.random()*10)*10 + 110;
		int y = (int)(Math.random()*10)*10;
		B = new Point(x, y);
	}
	
	//random point with x ->[110-200], y->[110,200], x and y are multiples of 10
	private void genC(){
		int x = (int)(Math.random()*10)*10 + 110;
		int y = (int)(Math.random()*10)*10 + 110;
		C = new Point(x, y);
	}
	
	//random point with x ->[0-90], y->[110,200], x and y are multiples of 10
	private void genD(){
		int x = (int)(Math.random()*10)*10;
		int y = (int)(Math.random()*10)*10 + 110;
		D = new Point(x, y);
	}
	
	public Point getA(){
		return A;
	}
	
	public Point getB(){
		return B;
	}
	
	public Point getC(){
		return C;
	}
	
	public Point getD(){
		return D;
	}
	
	
	/* returns true or false based upon if quadrilateral is a parallelogram 
	 * with all equal side and all equal angles. 
	 */
	public boolean isSquare(){
		return isRectangle() && Math.abs(sideLength(A,B)-(sideLength(B,C))) < 0.1;
	}
	
	/* returns true or false based upon if quadrilateral is a 
	 * parallelogram. There are many sufficient conditions to prove that
	 * shape is a parallelogram. You can decide to choose any you like. 
	 */
	public boolean isParallelogram(){
		return Math.abs(sideSlope(A,B)-(sideSlope(C,D))) < 0.1 && Math.abs(sideSlope(A,D)-(sideSlope(B,C))) < 0.1;
	}
	
	/* returns true or false based upon if quadrilateral 
	 * is a quadrilaeral with all 90 degree angles
	 */
	public boolean isRectangle(){
		Line AB = new Line(A,B);
		Line BC = new Line(B,C);
		return isParallelogram() && (AB.isPerpendicular(BC));
	}
	
	/* returns true or false based upon if quadrilateral
	 * is a parallelogram with all equal sides
	 */
	public boolean isRhombus(){
		Line AC = new Line(A,C);
		Line BD = new Line(B,D);
		return (AC.isPerpendicular(BD));
	}
	
	/* return true or false based upon if quadrilateral
	 * is a trapezoid with a single pair of parallel sides
	 */
	public boolean isTrapezoid() {
		return (Math.abs(sideSlope(A,B)-sideSlope(C,D))<0.01 && Math.abs(sideSlope(B,C)-sideSlope(A,D))>0.01)
				|| (Math.abs(sideSlope(A,B)-sideSlope(C,D))>0.01 && Math.abs(sideSlope(B,C)-sideSlope(A,D))<0.01);
	}
	
	/* returns area of the quadrilateral, in order to do this
	 * you may want to check this link: 
	 * https://www.youtube.com/watch?v=JVZud7ZBEKg
	 * OR You may want to use Heron's Formula, in this case
	 * you will treat quadrilateral as two triangles and find length
	 * of the diagonal. 
	 */
	public double area(){
		double s1 = (A.distanceTo(B) + B.distanceTo(C) + C.distanceTo(A)) / 2;
		double s2 = (A.distanceTo(D) + D.distanceTo(C) + C.distanceTo(A)) / 2;

		double a1 = Math.sqrt((s1 * (s1 - A.distanceTo(B)) * (s1 - B.distanceTo(C)) * (s1 - C.distanceTo(A))));
		double a2 = Math.sqrt((s2 * (s2 - A.distanceTo(D)) * (s2 - D.distanceTo(C)) * (s2 - C.distanceTo(A))));

		return a1 + a2;
	}
	
	//returns perimeter of a quadrilateral
	public double perimeter(){
		return sideLength(A, B) + sideLength(B, C) + sideLength(C, D) + sideLength(D, A);
	}
	
	
	// returns slope of the line formed by points L and N
	public double sideSlope(Point L, Point N){
		Line l = new Line(L, N);
		return l.getSlope();
	}
	
	// returns length of the line with end points in parameter
	public double sideLength(Point L, Point N){
		return L.distanceTo(N);
	}
	
	
	/*Classify quadrilateral as parallelogram, rectangle, rhombus, 
	 * square or trapezoid. write code below for this method
	 */
	private String classify(){
		String result="";

		if(isSquare())
			result = "Square";
		else if(isRectangle())
			result = "Rectangle";
		else if(isRhombus())
			result = "Rhombus";
		else if(isParallelogram())
			result = "Parallelogram";
		else if(isTrapezoid())
			result = "Trapezoid";
		else
			result = "Quadrilateral";

		return result;
	}
	
	public String getType(){
		return type;
	}
	
	
	/*should return coordinates of 4 corners
	 * Area: 
	 * Perimeter:
	 * Type:
	 */
	public String toString(){
		DecimalFormat df = new DecimalFormat("0.00");
		return "Vertices | A: " + A +" | B: " + B + " | C: " + C +" | D: " + D + " | "
				+ "\nAB = " + df.format(sideLength(A,B)) + " | Slope AB = " + df.format(sideSlope(A,B))
				+ "\nBC = " + df.format(sideLength(B,C)) + " | Slope BC = " + df.format(sideSlope(B,C))
				+ "\nArea = " + df.format(area())
				+ "\nPerimeter = " + df.format(perimeter())
				+ "\n" + type.toUpperCase();
				
	}
	
	private void initParallelogram() {
		//generates a parellelogram
		//point d is found by taking the opposite of the transformation of points A and B and applying it to C.
		genA();
		genB();
		genC();
		D = new Point(A.getX() - B.getX() + C.getX(), A.getY() - B.getY() + C.getY());
	}
	
	private void initRectangle() {
		//generates a rectangle
		int cy, cx = (int)(Math.random()*10)*10 + 110;	//respective x and y coordinates for point c
		int loop = 0;		//to find if a point cannot be found in the respective restrictions
		genA();
		genB();
		if(A.getY()==B.getY() || A.getX()==B.getX()) {	//due to a division by zero error, this creates a non-oblique rectangle without division
			genC();
			B = new Point(C.getX(), A.getY());
			D = new Point(A.getX(), C.getY());
		}else {											//creates an oblique rectangle if first two points aren't perfectly horizontal
			Line n = new Line(B, -1/sideSlope(A,B));	
			while(loop < 1000) {
				if(n.evaluate(cx)>=110 && n.evaluate(cx)<=200)	//finds a random C point in the restrictions on the line perpendicular to AB
					break;
				else
					cx = (int)(Math.random()*10)*10 + 110;
				loop++;
			}
			cy = (int)n.evaluate(cx);
			C = new Point(cx, cy);
			D = new Point(A.getX() - B.getX() + C.getX(), A.getY() - B.getY() + C.getY());
		}
	}
	
	private void initSquare() {
		//generates a square
		genA();
		genC();
		Point mdpt = new Point((A.getX()+C.getX())/2.0, (A.getY()+C.getY())/2.0); //midpoint of diagonal
		Point pot  = new Point((A.getX()-C.getX())/2.0, (A.getY()-C.getY())/2.0); //point of transformation
		
		B = new Point(mdpt.getX()-pot.getY(), mdpt.getY()+pot.getX());
		D = new Point(mdpt.getX()+pot.getY(), mdpt.getY()-pot.getX());
	}
	
	private void initRhombus() {
		//generates a rhombus
		genA();
		genC();
		Point mdpt = new Point((A.getX()+C.getX())/2.0, (A.getY()+C.getY())/2.0); 	//midpoint of AC

		Line n = new Line(mdpt,-1/sideSlope(A,C));	//line perpendicular 
		double range = 200-mdpt.getX();	//denotes range for a random x variable

		int randomX = (int)(Math.random()*range)+1;	//produces a random yet valid x value  
		B = new Point(mdpt.getX()+randomX, n.evaluate(mdpt.getX()+randomX));
		D = new Point(mdpt.getX()-randomX, n.evaluate(mdpt.getX()-randomX));
	}
	
	private void initTrapezoid() {
		//generates a trapezoid
		genA();
		genB();
		genC();
		int antiLoop = 0; 	//for use in the case that no point can be generated within the restrictions
		Line n = new Line(C, sideSlope(A,B));	//line of slope AB and containing point c
		double dx = (int)(Math.random()*10)*10;
		double dy = n.evaluate(dx);
		while(!(dy >= 110 || dy <= 200) || antiLoop < 1000) { //generates a point on this line that falls within restrictions for D 
			dx = (int)(Math.random()*10)*10;
			dy = n.evaluate(dx);
			antiLoop++;
		}
		D = new Point(dx, dy);
	}
}
