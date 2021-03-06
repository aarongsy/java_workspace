package drawcircle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.*;
import java.util.Vector;

import javax.swing.*;

import drawlinedouble.FrameTestBase;


public class drawcircle extends JFrame {

    public static void main(String args[]) {
        FrameTestBase t = new FrameTestBase();
        t.add(new JComponent() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                    Ellipse2D.Double l = new Ellipse2D.Double(0.5, 0.5, 50, 50);
                    Ellipse2D.Double j = new Ellipse2D.Double(0.5, 0.5, 60, 60);
                    g2.setColor(Color.red);
                    g2.draw(j);
                    g2.setColor(Color.black);
                    g2.fill(l);
            }
        });

        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setSize(400, 400);
        t.setVisible(true);
    }
}
