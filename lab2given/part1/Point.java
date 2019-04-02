import java.lang.Math;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getRadius() {
        double square = Math.pow(this.x, 2) + Math.pow(this.y, 2);
        double radius = Math.sqrt(square);
        return radius;
    }

    public Point rotate90() {
        double newX = -(this.y);
        double newY = (this.x);
        Point pointNew = new Point(newX, newY);
        return pointNew;
    }

    // public static void main(String[] args) {
    // Point p = new Point(4.2, .8);
    // Point p2 = new Point(4.1, -1.0);
    // Point p3 = new Point(-5.0, 2.0);
    // Point p4 = new Point(-2.0, 2.0);

    // double radius = p.getRadius();
    // double radius2 = p2.getRadius();
    // double radius3 = p3.getRadius();
    // double radius4 = p4.getRadius();

    // System.out.println(
    // "radius:" + radius + " radius2:" + radius2 + " radius 3:" + radius3 + "
    // radius4:" + radius4);

    // Point rot = p.rotate90();
    // Point rot2 = p2.rotate90();
    // Point rot3 = p3.rotate90();
    // Point rot4 = p4.rotate90();
    // System.out.println("Rotate90: (" + rot.x + " " + rot.y + ") Rotate90:(" +
    // rot2.x + " " + rot2.y
    // + ") Rotate90:(" + rot3.x + " " + rot3.y + " ) Rotate90:(" + rot4.x + " " +
    // rot4.y + ")");

    // }
}
