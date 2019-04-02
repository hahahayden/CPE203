import java.awt.Color;
import java.awt.Point;
public class Circle implements Shape{
    private double radius;
    private Point center;
    private Color color;
    public Circle(double radius,Point center, Color color){
        this.radius=radius;
        this.center=center;
        this.color=color;
    }
    public double getRadius(){
        return this.radius;
    }
    public void setRadius(double radius){
        this.radius=radius;
    }
    public Point getCenter(){
        return this.center;

    }
    public Color getColor(){
        return this.color;
    }
    public boolean equals(Object other){
        if (other==this){
            return true;
        }
        if (other==null){
            return false;
        }
        if (this.getClass()!=other.getClass()){
            return false;
        }
        Circle otherCircle=(Circle)other;
        return this.radius==otherCircle.getRadius() && this.center.equals(otherCircle.getCenter()) && this.color.equals(otherCircle.getColor());
    }

    public void setColor(Color color) { this.color = color; }
    public double getArea() { return (Math.PI*Math.pow(radius, 2)); }
    public void translate(Point point) { center.setLocation(center.getX() + point.getX(), center.getY() + point.getY()); }
    public double getPerimeter() { return (2*Math.PI*radius); }
}
