
import java.awt.*;
import java.util.Vector;
import triangulate;
import Point;
import Polygon;
import diagonalSet;
import edge;
import ColorSet;
/** A canvas to get an input polygon and to draw a polygon */
class polyCanvas extends Canvas {
    private        Point first;
    private        int prevX;
    private        int prevY = -1;
    Polygon p = new Polygon(100);
    diagonalSet d;
    boolean intersected = false;
    boolean triangulation = true;
    boolean guards = true;
    boolean colors = true;
    triangulate t = new triangulate();
    
    /**
     * This method was created by a SmartGuide.
     * @param x1 int
     * @param y1 int
     * @param x2 int
     * @param y2 int
     */
    public void closePolygon(int x1,int y1,int x2,int y2) {
        Graphics g = getGraphics();
        g.setColor(Color.red);
        g.drawLine(x1,y1,x2,y2);
        return;
    }
    public void drawEdge(int x1,int y1,int x2, int y2)
    {
        Graphics g = getGraphics();
        g.setColor(Color.red);
        g.drawLine(x1,y1,x2,y2);
        drawpoint(x1,y1);
        drawpoint(x2,y2);
    }
    public void drawEdge(edge e)
    {
        Graphics g = getGraphics();
        g.setColor(Color.green);
        g.drawLine(e.getStart().getX(),e.getStart().getY(),e.getEnd().getX(),e.getEnd().getY());
        drawpoint(e.getStart().getX(),e.getStart().getY());
    }
    /**
     * This method was created by a SmartGuide.
     * @param p1 Point
     * @param p2 Point
     */
    public void drawEdge(Point p1,Point p2) {
        {
            Graphics g = getGraphics();
            g.setColor(Color.red);
            g.drawLine(p1.x,p1.y,p2.x,p2.y);
            drawpoint(p1,3);
            drawpoint(p2,3);
        }
        return;
    }
    public void drawpoint(int x,int y)
    {
        Graphics g = getGraphics();
        g.setColor(Color.red);
        g.fillOval(x-5,y-5,10,10);
        g.setColor(Color.black);
        g.drawOval(x-5,y-5,10,10);
    }
    public void drawpoint(Point p,int color)
    {
        
        Graphics g = getGraphics();
        // no colors
        if (p.color<=3 && colors)
        {
            if (p.color == 0)
                g.setColor(Color.yellow);
            else if (p.color == 1)
                g.setColor(Color.blue);
            else if (p.color == 2)
            {g.setColor(Color.white);}
            int x= p.getX()-5;
            int y= p.getY()-5;
            g.fillOval(x,y,10,10);
            g.setColor(Color.black);
            g.drawOval(x,y,10,10);
        }
        if ((p.color == -1) || !colors)
        {
            g.setColor(Color.red);
            g.fillOval(p.getX()-5,p.getY()-5,10,10);
            g.setColor(Color.black);
            g.drawOval(p.getX()-5,p.getY()-5,10,10);
        }
        if (p.guard  && guards)
        {
            g.setColor(Color.black);
            g.drawOval(p.getX()-5,p.getY()-5,10,10);
            g.setColor(Color.white);
            g.drawOval(p.getX()-6,p.getY()-6,12,12);
        }
        
    }
    public boolean handleEvent(Event evt)
    {
        // when mouse down store prevX,Y when mouse up drawline prevX,prevY,x,y
        
        if (evt.id == Event.MOUSE_DOWN && !p.closed)
        {
            if (prevY != -1) // not the first point
            {
                if (!((Math.abs(first.getX() -evt.x)<=3 && (Math.abs(first.getY() - evt.y) <=3))) && p.size <100)
                {
                    if (noIntersection(evt.x,evt.y,prevX,prevY))
                    {
                        drawEdge(evt.x,evt.y,prevX,prevY);
                        p.addPnt(new Point(evt.x,evt.y));
                        drawpoint(evt.x,evt.y);
                        intersected = false;
                    }
                    else {
                        intersected = true;
                    }
                }
                
                else
                {
                    if (noIntersection2(first.x,first.y,prevX,prevY))
                    {
                        drawEdge(first.x,first.y,prevX,prevY);
                        p.close();
                        if (p.clockwise && (p.area() < 0)) p.reverse();
                        else if (!p.clockwise && (p.area() < 0)) p.reverse();
                        Polygon pTmp = new Polygon(p);
                        d = t.triangulate(pTmp,getGraphics());
                        p.addDiagonals(d);
                        ColorSet CSet = t.color(d,p); 		// 3 color the polygon
                        CSet.setGuards();
                        repaint();
                    }
                    else intersected = true;
                }
            }
            else
            {
                first = new Point(evt.x,evt.y);
                p.addPnt(first);
                drawpoint(evt.x,evt.y);
            }
            if (!intersected)
            {
                prevX = evt.x;
                prevY = evt.y;
            }
            return true;
        }
        
        return false;
    }
    /**
     * This method was created by a SmartGuide.
     * @return boolean
     * @param x1 int
     * @param y1 int
     * @param x2 int
     * @param y2 int
     */
    public boolean noIntersection(int x1,int y1,int x2,int y2) {
        Point p1,p2;
        p1 = new Point(x1,y1);
        p2 = new Point(x2,y2);
        if (p.size <= 1) return true;
        if (p.clockwise)
            for (int i = 0;i<p.size-2;i++)
            {
                if (t.intersect(p1,p2,p.VSet[i],p.VSet[i+1])){
                    return false;
                }
            }
        else
            for (int i = p.size-1;i>1;i--)
            {
                if (t.intersect(p1,p2,p.VSet[i-1],p.VSet[i])){
                    return false;
                }
                
            }
        
        
        return true;
    }
    /**
     * This method was created by a SmartGuide.
     * @return boolean
     * @param x1 int
     * @param y1 int
     * @param x2 int
     * @param y2 int
     */
    public boolean noIntersection2(int x1,int y1,int x2,int y2) {
        Point p1,p2;
        p1 = new Point(x1,y1);
        p2 = new Point(x2,y2);
        if (p.size <= 1) return true;
        if (p.clockwise)
            for (int i = 1;i<p.size-2;i++)
            {
                if (t.intersect(p1,p2,p.VSet[i],p.VSet[i+1])){
                    return false;	
                }
            }
        else
            for (int i = p.size-2;i>1;i--)
            {
                if (t.intersect(p1,p2,p.VSet[i-1],p.VSet[i])){
                    return false;	
                }
                
            }
        
        
        return true;
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(0,0,450,450);
        if (p.size != 0) 
        {
            Point p1 = p.getPnt(0);
            Point p2;
            
            if ((p.closed) && triangulation)
            {
                
                for (int j=0;j<d.size;j++)
                {
                    drawEdge(d.getDiagonal(j));
                }
            }
            for (int i = 0;i<p.size;i++)
            {
                p2 = p.getPnt(i);
                drawEdge(p2,p1);
                p1 = p2;
            }
            if (p.closed)
            {
                p2 = p.getPnt(0);
                drawEdge(p2,p1);
            }
            
        }
    }
    public void reset()
    {
        prevY = -1;
        p = new Polygon(100);
        Graphics g = getGraphics();
        g.setColor(Color.gray);
        g.fillRect(0,0,450,450);
        intersected = false;
    }
    /**
     * This method was created by a SmartGuide.
     */
    public void seeColors() {
        colors = !colors;
        repaint();
        return;
    }
    /**
     * This method was created by a SmartGuide.
     */
    public void seeGuards() {
        guards = !guards;
        repaint();
        return;
    }
    /**
     * This method was created by a SmartGuide.
     */
    public void seeTriangulation() {
        triangulation = !triangulation;
        repaint();
        return;
    }
    /**
     * This method was created by a SmartGuide.
     */
    public void undo() {
        if (p.size <= 1) reset();
        else
        {
            p.remove(p.size,d);
            if (p.clockwise)
            {
                prevX = p.getPnt(p.size-1).getX();
                prevY = p.getPnt(p.size-1).getY();
            }
            else 
            {
                prevX = p.getPnt(0).getX();
                prevY = p.getPnt(0).getY();
            }
            repaint();
        }	
        return;
    }
}