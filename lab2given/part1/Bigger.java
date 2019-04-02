import java.util.*;
public class Bigger {
    public static double whichIsBigger(Rectangle rect, Circle circ, Polygon poly){
        double p1= Util.perimeter(rect);
        double p2= Util.perimeter(circ);
        double p3= Util.perimeter(poly);

        ArrayList<Double> lst= new ArrayList<Double> ();
        lst.add(p1);
        lst.add(p2);
        lst.add(p3);

        double max= Collections.max(lst);
        return max;
    }
}
