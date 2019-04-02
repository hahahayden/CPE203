
public class Circle{
    private Point center;
    private double radius;
    public Circle(Point center, double rad){

        this.center=center;
        this.radius=rad;
    }

    public Point getCenter(){
        return center;
    }
    public double getRadius(){
        return radius;
    }
}