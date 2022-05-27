package quadrilateral;

//import java.applet.Applet;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.util.Scanner;
/*
* DO NOT EDIT STUDENT VERSION
*/

public class QuadrilateralRunner {
	static PolygonApplet applet;
	static Scanner read = new Scanner(System.in);
	
	public static void main(String[] args) {
		String in;
		Quadrilateral Q;
		while(true) {
			System.out.println("Press enter to randomly generate another quadrilateral. Alternatively, you can type in the name of the quadrilateral you would like to generate. Type quit to quit");
			in = read.nextLine();
			
			if(in.equalsIgnoreCase("quit"))
				break;
			
			Q = new Quadrilateral(in.toUpperCase());
			System.out.println(Q);
			displayQuad(Q.getA(), Q.getB(), Q.getC(), Q.getD());
		}
		
		
//		Quadrilateral Q = new Quadrilateral("RECTANGLE");
//		System.out.println(Q);
//		displayQuad(Q.getA(), Q.getB(), Q.getC(), Q.getD());

	}
	
	/*
	 *  This methods accepts 4 points as a paramenter and uses this to
	 *  draw quadrilateral.  
	 */
	public static void displayQuad(Point A, Point B, Point C, Point D){
		JFrame frame = new JFrame();
		frame.setSize(400, 300);
		int x1 = (int)A.getX();
		int x2 = (int)B.getX();
		int x3 = (int)C.getX();
		int x4 = (int)D.getX();
		
		int y1 = (int)A.getY();
		int y2 = (int)B.getY();
		int y3 = (int)C.getY(); 
		int y4 = (int)D.getY();

		applet = new PolygonApplet(new int[] {x1, x2, x3, x4 },
				new int[] { y1, y2, y3, y4 });
		int R = (int)((Math.random())*256);
		int G = (int)((Math.random())*256);
		int Bb = (int)((Math.random())*256);
		
		applet.setForeground(new Color(R, G, Bb, 40));
		frame.getContentPane().add(applet);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				applet.stop();
				applet.destroy();
			}
		});

		frame.setVisible(true);
		applet.init();
		applet.start();
		
	}

}


