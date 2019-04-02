import java.awt.*;
public class Rectangle implements Shape {
    private double width;
    private double height;
    private Point topLeft;
    private Color color;
    public Rectangle (double width, double height, Point topLeft, Color color) {
        this.width = width;
        this.height = height;
        this.topLeft = topLeft;
        this.color = color;
    }
    public double getWidth()
    {
        return this.width;
    }
    public void setWidth(double width){
        this.width=width;

    }

    public double getHeight(){
        return this.height;
    }
    public void setHeight(double height){
        this.height=height;
    }
    public Point getTopLeft(){
        return this.topLeft;

    }

    public Color getColor(){
        return this.color;
    }
    public void setColor(Color color){
        this.color=color;
    }

    public double getArea(){
        return (this.width*this.height);
    }
    public void translate(Point point){
        topLeft.setLocation(topLeft.getX()+point.getX(),topLeft.getY()+point.getY());

    }
    public double getPerimeter(){
        return ((2*this.width)+(2*this.height));
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
            Rectangle otherRect=(Rectangle)other;
            return this.width==otherRect.getWidth() && this.height==otherRect.getHeight() && this.topLeft.equals(otherRect.getTopLeft())&& this.color.equals(otherRect.getColor());
        }


}
