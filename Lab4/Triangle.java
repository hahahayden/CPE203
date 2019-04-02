import java.awt.*;
public class Triangle implements Shape {
    private Point a;
    private Point b;
    private Point c;
    private Color color;

    public Triangle(Point a, Point b, Point c, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    public Point getVertexA() {
        return this.a;
    }

    public Point getVertexB() {
        return this.b;
    }

    public Point getVertexC() {
        return this.c;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Triangle otherTri = (Triangle) other;
        return this.a.equals(otherTri.getVertexA())&& this.b.equals(otherTri.getVertexB()) && this.c .equals( otherTri.getVertexC())&& this.color.equals(otherTri.getColor());
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getArea() {
        return Math.abs((a.getX() * (b.getY() - c.getY()) +
                b.getX() * (c.getY() - a.getY()) +
                c.getX() * (a.getY() - b.getY())) / 2);
    }

    public void translate(Point point) {
        a.setLocation(a.getX() + point.getX(), a.getY() + point.getY());
        b.setLocation(b.getX() + point.getX(), b.getY() + point.getY());
        c.setLocation(c.getX() + point.getX(), c.getY() + point.getY());
    }

    public double getPerimeter() {
        double AB = Math.pow(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2), 0.5);
        double BC = Math.pow(Math.pow(c.getX() - b.getX(), 2) + Math.pow(c.getY() - b.getY(), 2), 0.5);
        double AC = Math.pow(Math.pow(c.getX() - a.getX(), 2) + Math.pow(c.getY() - a.getY(), 2), 0.5);
        return AB + BC + AC;
    }

}
