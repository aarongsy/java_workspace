
class Polygon {
    public       int size = 0;
    Point[] VSet;
    int[][] adjList = new int[100][100];
    public     boolean closed = false;
    boolean clockwise = true;
    
    public Polygon(int max){VSet = new Point[max];}
    public Polygon(Polygon p)
    {
        VSet = new Point[100];
        
        for (;size<p.size;size++)
        {
            VSet[size] = new Point(p.getPnt(size).getX(),p.getPnt(size).getY());
            VSet[size].setIndex(size);
            closed = true;
        }
    }
    /**
     * This method was created by a SmartGuide.
     * @param d diagonalSet
     */
    public void addDiagonals (diagonalSet d) {
        edge tmp;
        for (int i=0;i<d.size;i++)
        {
            tmp = d.getDiagonal(i);
            link(tmp.getStart().getIndex(),tmp.getEnd().getIndex());
        }
        return;
    }
    void addPnt(Point p)
    {
        if (clockwise)
        {
            if (size !=0)
            {
                link(size,size-1);
                if (size != 2) unlink(size-1,0);
                link(size,0);
            }
            p.setIndex(size);
            VSet[size] = p;
        }
        
        else
        {
            for (int i = size;i>0;i--)
            {
                VSet[i] = VSet[i-1];
                VSet[i].index = i;
            }
            link(size,size-1);
            link(size,0);
            unlink(size-1,0);
            VSet[0] = p;
            VSet[0].index = 0;
        }
        
        size++;
    }
    /**
     * This method was created by a SmartGuide.
     * @return int
     */
    public int area() {
        int i;
        int currentSum = 0;
        for (i=0;i<size-1;i++)
        {
            currentSum += (VSet[i].x * VSet[i+1].y)-(VSet[i].y*VSet[i+1].x);
        }
        currentSum += (VSet[size-1].x * VSet[0].y)-(VSet[size-1].y*VSet[0].x);
        return currentSum;
    }
    /**
     * This method was created by a SmartGuide.
     * @return boolean
     * @param v int
     */
    public boolean areNeighbors(int v1,int v2) {
        
        return (adjList[v1][v2]==1);
    }
    /**
     * This method was created by a SmartGuide.
     */
    public void close() {
        closed = true;
        return;
    }
    Point getPnt(int i)
    {return VSet[i];
    }
    /**
     * This method was created by a SmartGuide.
     * @param a int
     * @param b int
     */
    public void link(int a,int b) {
        adjList[a][b]=1;
        adjList[b][a]=1;
        return;
    }
    /**
     * This method was created by a SmartGuide.
     * @param pnt Point
     */
    public void remove(int index,diagonalSet d) {
        edge diag;;
        int j;
        
        if (!clockwise)
        {
            for (j =0;j<size-1;j++)
            {
                VSet[j] = VSet[j+1];
                VSet[j].index = j;
            }
            
        }
        
        
        size--;
        
        // remove diagonals
        if (closed)
        {
            for (j= d.size-1;j>=0;j--)
            {
                diag = d.getDiagonal(j);
                unlink(diag.getStart().getIndex(),diag.getEnd().getIndex());
            }
            // reset the colors and remove guards
            
            for (j = 0;j<size;j++)
            {
                VSet[j].setColor(-1);
                VSet[j].guard = false;
            }
            d.size = 0;
        }
        
        link(size-1,0);
        unlink(0,size);	
        
        closed = false; // open the polygon
        return;
    }
    void removeVertex(int i)
    {
        int k;
        
        for (k=i; k<size-1;k++)
        {
            VSet[k] = VSet[k+1];
        }
        size--;
    }
    
    /**
     * This method was created by a SmartGuide.
     */
    public void reverse() {
        // 
        int i,j;
        Point tmp;
        if (size == 1) return;
        else
            for (i=0,j=size-1;i<j;i++,j--)
            {
                tmp = VSet[i];
                VSet[i] = VSet[j];
                VSet[j]=tmp;
                VSet[i].index = i;
                VSet[j].index = j;
            }
        clockwise = !clockwise;
        return;
    }
    /**
     * This method was created by a SmartGuide.
     * @param a int
     * @param b int
     */
    public void unlink(int a,int b) {
        adjList[a][b]=0;
        adjList[b][a]=0;
        return;
    }
}