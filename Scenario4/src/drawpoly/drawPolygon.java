package drawpoly;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.*;

import java.lang.String;
import java.util.Scanner;

public class drawPolygon extends JFrame {
	static double [] inputX = new double[512];
	static double [] inputY = new double[512];
	static int vertices = 66;
	static int multiplier = 15;
	static double [] x = new double[512];
	static double [] y = new double[512];
	

	
	public static void main(String args[]) {
		System.out.println("1");
		//getInput();
		//multiplyCoordinates();
		System.out.println("2");
		
        drawPolygon t = new drawPolygon();
        t.add(new JComponent() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
        		g.translate(500, 500);
        		
        		
        		for (int n = 1; n < vertices; n++){
        			g2.draw(new Line2D.Double(x[n-1], y[n-1], x[n], y[n]));
        			Ellipse2D.Double l = new Ellipse2D.Double(x[n-1]-4, y[n-1]-4, 8, 8);
        			g2.setColor(Color.black);
        	        g2.fill(l);
        		}
        		
        		Shape l = new Line2D.Double(x[0], y[0], x[vertices-1], y[vertices-1]);
        		//g2.draw(new Line2D.Double(x[0], y[0], x[vertices-1], y[vertices-1]));
        		Ellipse2D.Double p = new Ellipse2D.Double(x[vertices-1]-4, y[vertices-1]-4, 8, 8);
                g2.setColor(Color.blue);
                g2.fill(p);
                g2.fill(l);
                
            }
        });

        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setSize(400, 400);
        t.setVisible(true);
    }

	public static void getInput()
	{
		int n = 0;
		Scanner scan = new Scanner(System.in);
		
		while (n < vertices)
		{
			inputX[n] = scan.nextDouble();
			inputY[n] = scan.nextDouble();
			n++;
		}
	}
	
	public static void multiplyCoordinates(){
		for (int n = 1; n <= vertices; n++){
			x[n-1] = inputX[n-1] * multiplier;
			y[n-1] = inputY[n-1] * multiplier;
			System.out.println(n-1 + " (" + inputX[n-1] + ", " + inputY[n-1] + ")");
		}
	}
	
}