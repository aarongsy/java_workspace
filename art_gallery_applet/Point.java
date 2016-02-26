
class Point {
    int x;
    int y;
    int color = -1;
    int index = -1;
    boolean guard = false;
    
    public Point(int xc,int yc){x=xc;y=yc;}
    int getColor()
    {
        return color;
    }
    /**
     * This method was created by a SmartGuide.
     * @return int
     */
    public int getIndex() {
        return index;
    }
    int getX()
    {return x;}
    int getY()
    {return y;}
    boolean setColor(int c)
    {
        if (color == -1) {color = c; return true;}
        else
        {
            color = -1;
            return false;
        }
        return false;
    }
    /**
     * This method was created by a SmartGuide.
     * @param i int
     */
    public void setIndex(int i) {
        index = i;
        return;
    }
}