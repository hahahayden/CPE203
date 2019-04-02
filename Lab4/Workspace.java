import java.awt.Point;
import java.awt.Color;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
public class Workspace
{
   private List<Shape> shapes;
   public Workspace(){
       this.shapes=new ArrayList<>();
   }
   public void addShape(Shape shape){
       shapes.add(shape);
   }
   public Shape get(int index){
       return shapes.get(index);
   }

   public int size(){
       return shapes.size();
   }
    public List<Circle>getCircles(){
       List <Circle> circleList=new ArrayList<>();
       for (Shape i: shapes){
           if (i instanceof Circle){
               circleList.add((Circle)i);
           }
       }
       return circleList;
    }

    public List<Rectangle> getRectangles(){
        List <Rectangle> rectanglesList=new ArrayList<>();
        for (Shape i: shapes){
            if (i instanceof Rectangle){
                rectanglesList.add((Rectangle) i);
            }
        }
        return rectanglesList;
    }
    public List<Triangle> getTriangles(){
        List <Triangle> trianglesList=new ArrayList<>();
        for (Shape i: shapes){
            if (i instanceof Triangle){
                trianglesList.add((Triangle)i);
            }
        }
        return trianglesList;
    }

    public List<Shape>getShapesByColor(Color color){
       List <Shape> shapeColor=new ArrayList<>();
       for (Shape i: shapes){
           if (i.getColor()==color){
               shapeColor.add(i);
           }
       }
       return shapeColor;
    }
    public double getAreaOfAllShapes()
    {
        double result = 0;
        for(Shape i : shapes)
        {
            result += i.getArea();
        }
        return result;
    }

    //gets perimeter of all shapes in list
    public double getPerimeterOfAllShapes()
    {
        double result = 0;
        for(Shape i : shapes)
        {
            result += i.getPerimeter();
        }
        return result;
    }

}
