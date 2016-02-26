package art_gallery_simpler;

class diagonalSet {
    edge[] dSet = new edge[200];
    public  int size = 0;
    
    public diagonalSet(){}
    void addDiagonal(Point i,Point j,Point cutOff)
    {
    	//add edge in dSet
        dSet[size]=new edge(i,j,cutOff);
        size++;
    }
    edge getDiagonal(int i)
    {
        return dSet[i];
    }
    int getSize()
    {
        return size;
    }
    int isInDiagSet(Point a, Point b)
    {
        for (int i = 0;i<size;i++)
        {
        	//判断whether it is an edge
            if (((dSet[i].getStart().getX() == a.getX()) &&
                 (dSet[i].getStart().getY() == a.getY()) &&
                 (dSet[i].getEnd().getX() == b.getX()) &&
                 (dSet[i].getEnd().getY() == b.getY()))
                ||
                ((dSet[i].getEnd().getX() == a.getX()) &&
                 (dSet[i].getEnd().getY() == a.getY()) &&
                 (dSet[i].getStart().getX() == b.getX()) &&
                 (dSet[i].getStart().getY() == b.getY())))
                return i;
        }
        return -1;
    }
    diagonalSet merge(diagonalSet d2)
    {
        int d2size = d2.getSize();
        int i = size;
        
        for (int j = 0;j<d2size;j++,i++,size++)
        {
            dSet[size] = d2.getDiagonal(j);
        }
        
        return this;
    }
}