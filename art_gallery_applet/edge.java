
class edge {
    Point strt;
    Point nd;
    Point cutoff = null;
    
    public edge(Point p1,Point p2)
    {
        strt = p1;
        nd = p2;
    }
    public edge(Point p1,Point p2,Point cut)
    {
        strt = p1;
        nd = p2;
        cutoff = cut;
    }
    Point getCutPnt()
    {
        return cutoff;
    }
    Point getEnd()
    {
        return nd;
    }
    Point getStart()
    {
        return strt;
    }
}