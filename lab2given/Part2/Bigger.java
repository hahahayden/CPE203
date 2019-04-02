import java.util.*;
public class Bigger {
    public static double whichIsBigger(Rectangle rect, Circle circ, Polygon poly){
        double p1= rect.perimeter();
        double p2= circ.perimeter();
        double p3= poly.perimeter();

        ArrayList<Double> lst= new ArrayList<Double> ();
        lst.add(p1);
        lst.add(p2);
        lst.add(p3);

        double max= Collections.max(lst);
        return max;
    }
}
