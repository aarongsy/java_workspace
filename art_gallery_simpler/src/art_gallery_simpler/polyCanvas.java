package art_gallery_simpler;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.*;

import art_gallery_simpler.triangulate;
import art_gallery_simpler.Point;
import art_gallery_simpler.Polygon;
import art_gallery_simpler.diagonalSet;
import art_gallery_simpler.edge;
import art_gallery_simpler.ColorSet;
import javax.swing.*;
import java.util.*; 
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/** A canvas to get an input polygon and to draw a polygon */
class polyCanvas extends Canvas {
    private        Point first;
    private        double prevX;
    private        double prevY = -1;
    public         String S;
    public double [] data = new double[2000];
    Polygon p = new Polygon(1000);
    diagonalSet d;
    boolean intersected = false;
    boolean triangulation = true;
    boolean guards = true;
    boolean colors = true;
    public String lineTXT = null;  
    triangulate t = new triangulate();
    
    /**
     * This method was created by a SmartGuide.
     * @param x1 int
     * @param y1 int
     * @param x2 int
     * @param y2 int
     */
    public void closePolygon(double x1,double y1,double x2,double y2) {
        /*Graphics g = getGraphics();
        g.setColor(Color.red);
        g.drawLine(x1,y1,x2,y2);*/
    	Graphics g = getGraphics();
    	Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
            Shape l = new Line2D.Double(x1,y1,x2,y2);
            g2.setColor(Color.red);
            g2.draw(l);
        return;
    }
    public void drawEdge(double x1,double y1,double x2, double y2)
    {
        /*Graphics g = getGraphics();
        g.setColor(Color.red);
        g.drawLine(x1,y1,x2,y2);
        drawpoint(x1,y1);
        drawpoint(x2,y2);*/
    	Graphics g = getGraphics();
    	Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
            Shape l = new Line2D.Double(x1,y1,x2,y2);
            g2.setColor(Color.red);
            g2.draw(l);
            drawpoint(x1,y1);
            drawpoint(x2,y2);
    }
    public void drawEdge(edge e)
    {
        /*Graphics g = getGraphics();
        g.setColor(Color.green);
        g.drawLine(e.getStart().getX(),e.getStart().getY(),e.getEnd().getX(),e.getEnd().getY());
        drawpoint(e.getStart().getX(),e.getStart().getY());*/
    	Graphics g = getGraphics();
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    	   Shape l = new Line2D.Double(e.getStart().getX(),e.getStart().getY(),e.getEnd().getX(),e.getEnd().getY());
    	   g2.draw(l);
    	   drawpoint(e.getStart().getX(),e.getStart().getY());
    }
    /**
     * This method was created by a SmartGuide.
     * @param p1 Point
     * @param p2 Point
     */
    public void drawEdge(Point p1,Point p2) {
        {
        	//两点之间连线
            /*Graphics g = getGraphics();
            g.setColor(Color.red);
            g.drawLine(p1.x,p1.y,p2.x,p2.y);
            drawpoint(p1,3);
            drawpoint(p2,3);*/
        	Graphics g = getGraphics();
        	Graphics2D g2 = (Graphics2D) g;
        	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        	Shape l = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
        	g2.setColor(Color.red);
        	g2.draw(l);
        	drawpoint(p1,3);
        	drawpoint(p2,3);
        }
        return;
    }
    public void drawpoint(double x,double y)
    {
        /*Graphics g = getGraphics();
        g.setColor(Color.red);
        g.fillOval(x-5,y-5,10,10);//画一个实心圆
        g.setColor(Color.black);
        g.drawOval(x-5,y-5,10,10);//画一个空心圆*/
    	Graphics g = getGraphics();
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    	Ellipse2D.Double l = new Ellipse2D.Double(x-5,y-5,10,10);
        g2.setColor(Color.red);
        g2.fill(l); //画空心圆
        g2.setColor(Color.black);
    	g2.draw(l);//画实心圆
    
    }
    public void drawpoint(Point p,int color)
    {
    	Graphics g = getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        // no colors
        if (p.color<=3 && colors)
        {
            if (p.color == 0)
                {g2.setColor(Color.yellow);
                 //System.out.printf("guard");
                }
            else if (p.color == 1)
                {g2.setColor(Color.blue);}
            else if (p.color == 2)
                {g2.setColor(Color.white);}
            
            double x= p.getX()-5;
            double y= p.getY()-5;
            //draw and fullfill circle to highlight
            //画一个圆圈
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        	Ellipse2D.Double l = new Ellipse2D.Double(x,y,10,10);
            g2.fill(l);
            g2.setColor(Color.black);
            //画一个黑色的圆环
            g2.draw(l);;
        }
        if ((p.color == -1) || !colors)
        { 	
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
    	    Ellipse2D.Double l = new Ellipse2D.Double(p.getX()-5,p.getY()-5,10,10);
            g2.setColor(Color.red);
            g2.fill(l);
            g2.setColor(Color.black);
            g2.draw(l);
        }
        if (p.guard  && guards)
        {
        	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
    	    Ellipse2D.Double l = new Ellipse2D.Double(p.getX()-5,p.getY()-5,10,10);
    	    Ellipse2D.Double j = new Ellipse2D.Double(p.getX()-6,p.getY()-6,12,12);
            g2.setColor(Color.black);
            g2.draw(l);;
            g2.setColor(Color.white);
            g2.draw(j);
            //System.out.printf("("+p.getX()+","+p.getY()+")"+",");
            //System.out.printf("guard1");
        }
    }
    
    public void read() 
    {
    	System.out.println("read");
        // when mouse down store prevX,Y when mouse up drawline prevX,prevY,x,y
    	String lineTXT = null;   
    	try {   String encoding = "GBK"; // 字符编码(可解决中文乱码问题 )   
	      File file = new File("/Users/mac/Desktop/questionCoo");
	      if (file.isFile() && file.exists()) {  
	    	  InputStreamReader read1 = new InputStreamReader(   
	                    new FileInputStream(file), encoding);   
           BufferedReader bufferedReader = new BufferedReader(read1);  
           while ((lineTXT = bufferedReader.readLine()) != null ){
	           //if (evt.id == Event.MOUSE_DOWN && !p.closed)
        	   //如果按下鼠标并且多面体不闭合
        	      System.out.println(lineTXT.toString().trim()); 
	    		 String S1= lineTXT.replace(",",""); 	//delete ","
	     		 String S2= S1.replace("(", ""); 	//delete "("
  	   		     String S3= S2.replace(")", ""); 		//delete ")"
	   	 	     S = S3;
	     		StringTokenizer Token =new StringTokenizer(S); //token

		   		int tokenNum = Token.countTokens(); 
		   		for(int i=0; i<(tokenNum/2.0);i++){ 
		   			data[i] = Double.parseDouble(Token.nextToken());
		   			data[i+1] = Double.parseDouble(Token.nextToken());
		   			i++;
		   		    //double y = Double.parseDouble(Token.nextToken());
		   		     System.out.println(data[i]);
		   		    //System.out.println(y); 
		   		    
		   		} read1.close();}}
               else{System.out.println("找不到指定的文件！");   
		         }   
			   } catch (Exception e) {   
			          System.out.println("读取文件内容操作出错");   
			          e.printStackTrace();   
			     }   
		 }
		   		    
		   		public void inputevent(){  
		   			read();
		   			double readx=0.0;
		   			double ready=0.0; 
		   			for (int i=0;i<=data.length;i++)
		   			{
		   				readx=data[i];
		   				ready=data[i+1];
		   				i++;
		   				System.out.print(readx);
		   				System.out.print(ready);
		   		    if(!p.closed){
                      if (prevY != -1) // not the first point
                      {
                    	  if (!((Math.abs(first.getX() -readx)<=3 && (Math.abs(first.getY() - ready) <=3))) && p.size <200)
                       {
                          if (noIntersection(readx,ready,prevX,prevY))
                       {
                          drawEdge(readx,ready,prevX,prevY);
                          p.addPnt(new Point(readx,ready));
                          drawpoint(readx,ready);
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
                        //System.out.printf("("+first.x+","+first.y+")"+",");
                        repaint();
                    }
                    else intersected = true;
                }
            }
            else
            {
                first = new Point(readx,ready);
                p.addPnt(first);
                drawpoint(readx,ready);
            }
            if (!intersected)
            {
                prevX = readx;
                prevY = ready;
                
            }
            //return true;
            }//first if	   		      
        //return false;
    }}
		
           
    /**
     * This method was created by a SmartGuide.
     * @return boolean
     * @param x1 int
     * @param y1 int
     * @param x2 int
     * @param y2 int
     */
    public boolean noIntersection(double x1,double y1,double x2,double y2) {
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
    public boolean noIntersection2(double x1,double y1,double x2,double y2) {
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