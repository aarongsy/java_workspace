package drawlinedouble;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.*;

public class FrameTestBase extends JFrame {

    public static void main(String args[]) {
        FrameTestBase t = new FrameTestBase();
        t.add(new JComponent() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                double[] x = new double[]{
                		-2.0,-0.5,5.0,2.0,0.0,0.2};
                double[] y = new double[]{-2.0,0.0,0.0,0.5,5.0,3.0};
                double[] x1= new double[512];
                double[] y1= new double[512];
                double[] x2= new double[512];
                double[] y2= new double[512];
                double[] m = new double[]{5,  0 ,-2};
                double[] n = new double[]{0,5,-2};
                double[] m1= new double[512];
                double[] n1 = new double[512];
                int vertices = 5;//size - 1
                int guard = 3;
                double multi = 20.0;
                double multi2 = 2.0;
                double mover = 20.0;
                Font myFont = new Font ("Courier New",1,10);
                
                for (int i = 0; i < vertices; i++){
                	 x1[i] = (x[i]+mover) * multi;
                	 x2[i] = (x[i+1]+mover) * multi;
                	 y1[i] = (y[i]+mover)  * multi;
                	 y2[i] = (y[i+1]+mover) * multi;
                	 
                	// y1[i] = (y[i]+mover)  * multi2;
                	 //y2[i] = (y[i+1]+mover) * multi2;
                	 
                	//System.out.println(i);
                	//System.out.println(x1+","+y1);
                	Shape l = new Line2D.Double(x1[i],y1[i],x2[i],y2[i]);
                	//Shape l = new Line2D.Double(1,2,3,4);
        			g2.setColor(Color.black);
        	        g2.draw(l);
        	        g2.fill(l);
        	        g2.setColor(Color.red);
        	        String str =Integer.toString(i);
        	        g2.setFont(myFont);
                    g2.drawString(str, (float)x1[i],(float) y1[i]);
        		 }
               /* for (int i = 0; i< guard;i++)
                {
                	m1[i]=(m[i]+mover)*multi;
                	n1[i]=(n[i]+mover)*multi;
                 Ellipse2D.Double j = new Ellipse2D.Double(m1[i],n1[i], 3, 3);
                 g2.setColor(Color.black);
                 g2.fill(j);
                }*/
                
            }
        });

        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setSize(2000, 2000);
        t.setVisible(true);
    }
}