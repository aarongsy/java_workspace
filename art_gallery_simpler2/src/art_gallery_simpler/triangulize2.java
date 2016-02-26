package art_gallery_simpler;

import art_gallery_simpler.ColorSet;
import art_gallery_simpler.diagonalSet;
import art_gallery_simpler.edge;
import java.awt.*;
import art_gallery_simpler.Point;
import art_gallery_simpler.Polygon;
import art_gallery_simpler.triangulate;

public class triangulize2 extends java.applet.Applet {
    private      polyCanvas pC;
    Button reset;
    Button undo;
    Checkbox colors;
    Checkbox triangulation;
    Checkbox guards;
    
    /**
     * This method was created by a SmartGuide.
     * @return boolean
     * @param evt java.awt.Event
     * @param obj java.lang.Object
     */
    public boolean action(Event evt,Object obj) {
        if (evt.target.equals(reset))
        {
            pC.reset();
            return true;
        }
        else if (evt.target.equals(undo))
        {
            pC.undo();
            return true;
        }
        else if (evt.target.equals(triangulation))
        {
            pC.seeTriangulation();
            return true;
        }
        
        else if (evt.target.equals(colors))
        {
            pC.seeColors();
            return true;
        }
        else if (evt.target.equals(guards))
        {
            pC.seeGuards();
            return true;
        }
        
        return false;
    }
    public void init()
    {
        showStatus("The Art Gallery Problem");
        setLayout(new BorderLayout());
        pC = new polyCanvas();
        pC.resize(4500,450);
        pC.setBackground(Color.gray);
        add("Center",pC);
        Panel p = new Panel();
        p.setLayout(new FlowLayout());
        reset = new Button("Reset");
        undo = new Button("Undo");
        p.add(undo);
        p.add(reset);
        Label showme = new Label("Show");
        colors = new Checkbox("Colors");
        triangulation = new Checkbox("Triangulation");
        guards = new Checkbox("Guards");
        p.add(showme);
        p.add(triangulation);
        p.add(colors);
        p.add(guards);
        colors.setState(true);
        guards.setState(true);
        triangulation.setState(true);
        add("South",p);
    }
}