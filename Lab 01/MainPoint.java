import java.lang.Math;

public class MainPoint {
    public static void main(String[] args) {
        Point p = new Point(4.2, .8);
        Point p2 = new Point(4.1, -1.0);
        Point p3 = new Point(-5.0, 2.0);
        Point p4 = new Point(-2.0, 2.0);

        double radius = p.getRadius();
        double radius2 = p2.getRadius();
        double radius3 = p3.getRadius();
        double radius4 = p4.getRadius();

        // System.out.println(
        // "radius:" + radius + " radius2:" + radius2 + " radius 3:" + radius3 + "
        // radius4:" + radius4);
        // double max1= max()
        // double largestDistance= Math.max(radius, (Math.max(radius2, Math.max(radius3,
        // radius4))));

        // System.out.println( "the largest distance is: " + largestDistance+ "which
        // corresponds to Point: "+ )
        Point pointArray[] = new Point[5];
        pointArray[0] = p;
        pointArray[1] = p2;
        pointArray[2] = p3;
        pointArray[3] = p4;
        double radiusArray[] = new double[5];
        radiusArray[0] = radius;
        radiusArray[1] = radius2;
        radiusArray[2] = radius3;
        radiusArray[3] = radius4;
        double radiusMax = 0;
        int index = 0;
        for (int i = 0; i < pointArray.length; i++) {
            if (radiusArray[i] > radiusMax) {
                radiusMax = radiusArray[i];
                index = i;
            }
        }
        System.out.println("the largest distance is: " + radiusMax + " which corresponds to Point: ("
                + pointArray[index].getX() + "," + pointArray[index].getY() + ")");
        Point rot = p.rotate90();
        Point rot2 = p2.rotate90();
        Point rot3 = p3.rotate90();
        Point rot4 = p4.rotate90();
        System.out.println("Point:" + p.getX() + " " + p.getY() + " Rotated by 90 degrees would be:" + rot.getX() + " "
                + rot.getY());
        System.out.println("Point:" + p2.getX() + " " + p2.getY() + " Rotated by 90 degrees would be:" + rot2.getX()
                + " " + rot2.getY());
        System.out.println("Point:" + p3.getX() + " " + p3.getY() + " Rotated by 90 degrees would be:" + rot3.getX()
                + " " + rot3.getY());
        System.out.println("Point:" + p4.getX() + " " + p4.getY() + " Rotated by 90 degrees would be:" + rot4.getX()
                + " " + rot4.getY());
    }
}