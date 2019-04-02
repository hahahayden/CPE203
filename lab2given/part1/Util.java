import java.lang.Math;
public class Util {
    public static double perimeter (Circle inputCircle){
        return 2*Math.PI*inputCircle.getRadius();

    }
    public static double perimeter (Rectangle inputRectangle){
        Point topLeft=inputRectangle.getTopLeft();
        Point bottomRight=inputRectangle.getBottomRight();
        return (2*(bottomRight.getX()-topLeft.getX())+(2*(topLeft.getY()-bottomRight.getY())));


    }
    public static double perimeter (Polygon inputPolygon) {
        Point next;
        double perimeter = 0;
        for (int i = 0; i < inputPolygon.getPoints().size(); i++) {
            Point current = inputPolygon.getPoints().get(i);
            if (i < inputPolygon.getPoints().size() - 1) {
                next = inputPolygon.getPoints().get(i + 1);

            } else {
                next = inputPolygon.getPoints().get(0);  //connect to the first Point to form a shape
            }

            double distance = Math.pow(Math.pow(next.getX() - current.getY(), 2) + Math.pow(next.getY() - current.getX(), 2), 0.5);
            perimeter += distance;
        }

        return perimeter;
    }
}
