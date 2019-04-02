import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import java.awt.Color;
import java.awt.Point;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class AcceptanceTests
{
   public static final double DELTA = 0.00001;

   private Workspace ws = new Workspace();
   private Workspace wsEmpty = new Workspace();
   private final Circle c1 = new Circle(5.678, new Point(2,3), Color.BLACK);
   private final Circle c2 = new Circle(11.1, new Point(0,0), Color.BLUE);
   private final Circle c3 = new Circle(25, new Point(-8,-4), Color.RED);
   private final Triangle t1 = new Triangle(new Point(0,0), new Point(3,0), new Point(2,-4), Color.RED);
   private final Triangle t2 = new Triangle(new Point(-2,11), new Point(14,3), new Point(8,9), Color.BLACK);
   private final Rectangle r1 = new Rectangle(1.234, 5.678, new Point(2,3), Color.RED);

   @Before
   public void setup()
   {
      ws.addShape(c1);
      ws.addShape(t1);
      ws.addShape(c2);
      ws.addShape(r1);
      ws.addShape(t2);
      ws.addShape(c3);
   }

   @Test
   public void test00_testCircleImplSpecifics()
           throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
              "getColor", "setColor", "getArea", "getPerimeter", "translate",
              "getRadius", "setRadius", "getCenter", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
              Color.class, void.class, double.class, double.class, void.class,
              double.class, void.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
              new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
              new Class[0], new Class[] {double.class}, new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Circle.class, expectedMethodNames,
              expectedMethodReturns, expectedMethodParameters);
   }

   @Test (timeout = 5000)
   public void test01_testCircleGetArea()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(101.2839543, c.getArea(), DELTA);
   }

   @Test (timeout = 5000)
   public void test02_testCircleGetPerimeter()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(35.6759261, c.getPerimeter(), DELTA);
   }

   @Test (timeout = 5000)
   public void test03_CircleTranslate()
   {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);

      c.translate(new Point(-7,10));

      assertEquals(new Point(-5,13), c.getCenter());
   }

   @Test (timeout = 5000)
   public void test04_CircleGetColor()
   {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);

      assertEquals(new Color(0), c.getColor());
   }

   @Test (timeout = 5000)
   public void test05_CircleEqualsNull()
   {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);

      assertNotEquals("Should not be equal to null.", c, null);
   }

   @Test (timeout = 5000)
   public void test06_CircleEqualsTriangle()
   {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);

      assertNotEquals("Should not be equal to a Triangle.", c, t1);
   }

   @Test (timeout = 5000)
   public void test07_CircleEquals()
   {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);
      Circle c2 = new Circle(5.678, new Point(2,3), Color.RED);

      assertNotEquals("Two unequal Circles.", c, c2);
   }

   @Test (timeout = 5000)
   public void test08_CircleEquals()
   {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);
      Circle c2 = new Circle(5.678, new Point(2,3), Color.BLACK);

      assertEquals("Two equal Circles.", c, c2);
   }

   @Test
   public void test09_testRectangleImplSpecifics()
           throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
              "getColor", "setColor", "getArea", "getPerimeter", "translate",
              "getWidth", "setWidth", "getHeight", "setHeight", "getTopLeft", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
              Color.class, void.class, double.class, double.class, void.class,
              double.class, void.class, double.class, void.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
              new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
              new Class[0], new Class[] {double.class}, new Class[0], new Class[] {double.class},
              new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
              expectedMethodReturns, expectedMethodParameters);
   }

   @Test (timeout = 5000)
   public void test10_testRectangleGetArea()
   {
      Rectangle r = new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK);

      assertEquals(7.006652, r.getArea(), DELTA);
   }

   @Test (timeout = 5000)
   public void test11_testRectangleGetPerimeter()
   {
      Rectangle r = new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK);

      assertEquals(13.824, r.getPerimeter(), DELTA);
   }

   @Test (timeout = 5000)
   public void test12_testRectangleTranslate()
   {
      Rectangle r = new Rectangle(1.234, 5.678, new Point(2,3), Color.BLACK);

      r.translate(new Point(4,-5));

      assertEquals(new Point(6,-2), r.getTopLeft());
   }

   @Test (timeout = 5000)
   public void test13_RectangleEqualsNull()
   {
      Rectangle r = new Rectangle(1.234, 5.678, new Point(2,3), Color.BLACK);

      assertNotEquals("Should not be equal to null.", r, null);
   }

   @Test (timeout = 5000)
   public void test14_RectangleEqualsTriangle()
   {
      Rectangle r = new Rectangle(1.234, 5.678, new Point(2,3), Color.BLACK);

      assertNotEquals("Should not be equal to a Triangle.", r, t1);
   }

   @Test (timeout = 5000)
   public void test15_RectanlgeEquals()
   {
      Rectangle r = new Rectangle(1.234, 5.678, new Point(2,3), Color.BLACK);
      Rectangle r2 = new Rectangle(1.234, 5.679, new Point(2,3), Color.BLACK);

      assertNotEquals("Two unequal Rectangles.", r, r2);
   }

   @Test (timeout = 5000)
   public void test16_RectangleEquals()
   {
      Rectangle r = new Rectangle(1.234, 5.678, new Point(2,3), Color.BLACK);
      Rectangle r2 = new Rectangle(1.234, 5.678, new Point(2,3), Color.BLACK);

      assertEquals("Two equal Rectangles.", r, r2);
   }

   @Test
   public void test17_testTriangleImplSpecifics()
           throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
              "getColor", "setColor", "getArea", "getPerimeter", "translate",
              "getVertexA", "getVertexB", "getVertexC", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
              Color.class, void.class, double.class, double.class, void.class,
              Point.class, Point.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
              new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
              new Class[0], new Class[0], new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Triangle.class, expectedMethodNames,
              expectedMethodReturns, expectedMethodParameters);
   }

   @Test (timeout = 5000)
   public void test18_testTriangleGetArea()
   {
      Triangle t = new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), Color.BLACK);

      assertEquals(6.0, t.getArea(), DELTA);
   }

   @Test (timeout = 5000)
   public void test19_testTriangleGetPerimeter()
   {
      Triangle t = new Triangle(new Point(0,0), new Point(0,3), new Point(4,0), Color.BLACK);

      assertEquals(12.0, t.getPerimeter(), DELTA);
   }

   @Test (timeout = 5000)
   public void test20_testTriangleTranslate()
   {
      Triangle t = new Triangle(new Point(0,0), new Point(0,3), new Point(4,0), Color.BLACK);

      t.translate(new Point(3,-1));

      assertEquals(new Point(3,-1), t.getVertexA());
      assertEquals(new Point(3,2), t.getVertexB());
      assertEquals(new Point(7,-1), t.getVertexC());
   }

   @Test (timeout = 5000)
   public void test21_TriangleEqualsNull()
   {
      Triangle t = new Triangle(new Point(0,0), new Point(0,3), new Point(4,0), Color.BLACK);

      assertNotEquals("Should not be equal to null.", t, null);
   }

   @Test (timeout = 5000)
   public void test22_TriangleEqualsCircle()
   {
      Triangle t = new Triangle(new Point(0,0), new Point(0,3), new Point(4,0), Color.BLACK);

      assertNotEquals("Should not be equal to a Circle.", t, c1);
   }

   @Test (timeout = 5000)
   public void test23_TrianlgeEquals()
   {
      Triangle t = new Triangle(new Point(0,0), new Point(0,3), new Point(4,0), Color.BLACK);
      Triangle t2 = new Triangle(new Point(0,0), new Point(1,3), new Point(4,0), Color.BLACK);

      assertNotEquals("Two unequal Triangles.", t, t2);
   }

   @Test (timeout = 5000)
   public void test24_TriangleEquals()
   {
      Triangle t = new Triangle(new Point(0,0), new Point(0,3), new Point(4,0), Color.BLACK);
      Triangle t2 = new Triangle(new Point(0,0), new Point(0,3), new Point(4,0), Color.BLACK);

      assertEquals("Two equal Triangles.", t, t2);
   }

   @Test (timeout = 5000)
   public void test25_testWorkSpaceSize()
   {
      assertEquals("Workspace with six shapes.", 6, ws.size());
   }

   @Test (timeout = 5000)
   public void test26_testWorkSpaceSize()
   {
      assertEquals("Workspace with no shapes.", 0, wsEmpty.size());
   }

   @Test (timeout = 5000)
   public void test27_testWorkSpaceGetCircles()
   {
      List<Circle> circles = new ArrayList<Circle>();

      circles.add(c1);
      circles.add(c2);
      circles.add(c3);

      // use HashSet so order doesn't matter
      assertEquals("Workspace with many Circles.",
              new HashSet<>(circles), new HashSet<>(ws.getCircles()));
   }

   @Test (timeout = 5000)
   public void test28_testWorkSpaceGetCircles2()
   {
      List<Circle> circles = new ArrayList<Circle>();

      // use HashSet so order doesn't matter
      assertEquals("Workspace with no shapes.",
              new HashSet<>(circles),
              new HashSet<>(wsEmpty.getCircles()));
   }

   @Test (timeout = 5000)
   public void test29_testWorkSpaceGetTriangles()
   {
      List<Triangle> triangles = new ArrayList<Triangle>();

      triangles.add(t1);
      triangles.add(t2);

      assertEquals("Workspace with several Triangles.",
              new HashSet<>(triangles),
              new HashSet<>(ws.getTriangles()));
   }

   @Test (timeout = 5000)
   public void test30_testWorkSpaceGetTriangles2()
   {
      List<Triangle> triangles = new ArrayList<Triangle>();

      assertEquals("WorkSpace with no shapes.",
              new HashSet<>(triangles),
              new HashSet<>(wsEmpty.getTriangles()));
   }

   @Test (timeout = 5000)
   public void test31_testWorkSpaceGetRectangles()
   {
      List<Rectangle> rectangles = new ArrayList<Rectangle>();

      rectangles.add(r1);

      assertEquals("WorkSpace with one Rectangle.",
              new HashSet<>(rectangles),
              new HashSet<>(ws.getRectangles()));
   }

   @Test (timeout = 5000)
   public void test32_testWorkSpaceGetRectangles2()
   {
      List<Rectangle> rectangles = new ArrayList<Rectangle>();

      Workspace ws2 = new Workspace();
      ws2.addShape(new Circle(3.4, new Point(0,0), Color.BLUE));

      assertEquals("WorkSpace with no Rectangles, but with other shapes.",
              new HashSet<>(rectangles),
              new HashSet<>(ws2.getRectangles()));
   }

   @Test (timeout = 5000)
   public void test33_testWorkSpaceGetShapesByColor()
   {
      List<Shape> shapes = new ArrayList<Shape>();

      shapes.add(c1);
      shapes.add(t2);

      assertEquals(new HashSet<>(shapes), new HashSet<>(ws.getShapesByColor(Color.BLACK)));
   }

   @Test (timeout = 5000)
   public void test34_testWorkSpaceGetShapesByColor2()
   {
      List<Shape> shapes = new ArrayList<Shape>();

      shapes.add(c3);
      shapes.add(t1);
      shapes.add(r1);

      assertEquals(new HashSet<>(shapes), new HashSet<>(ws.getShapesByColor(Color.RED)));
   }

   @Test (timeout = 5000)
   public void test35_testWorkSpaceGetShapesByColor3()
   {
      List<Shape> shapes = new ArrayList<Shape>();

      assertEquals("Empty WorkSpace.",
              new HashSet<>(shapes),
              new HashSet<>(wsEmpty.getShapesByColor(Color.RED)));
   }

   @Test (timeout = 5000)
   public void test36_testWorkSpaceGetAreaOfAllShapes()
   {
      assertEquals(2488.86164575, ws.getAreaOfAllShapes(), DELTA);
   }

   @Test (timeout = 5000)
   public void test37_testWorkSpaceGetAreaOfAllShapes2()
   {
      Workspace ws = new Workspace();

      assertEquals(0.0, ws.getAreaOfAllShapes(), DELTA);
   }

   @Test (timeout = 5000)
   public void test38_testWorkSpaceGetPerimeterOfAllShapes()
   {
      assertEquals(324.490021565, ws.getPerimeterOfAllShapes(), DELTA);
   }

   @Test (timeout = 5000)
   public void test39_testWorkSpaceGetPerimeterOfAllShapes2()
   {
      Workspace ws = new Workspace();

      assertEquals(0.0, ws.getPerimeterOfAllShapes(), DELTA);
   }

   private static void verifyImplSpecifics(
           final Class<?> clazz,
           final List<String> expectedMethodNames,
           final List<Class> expectedMethodReturns,
           final List<Class[]> expectedMethodParameters)
           throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
              0, clazz.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
              clazz.getDeclaredMethods())
              .filter(m -> Modifier.isPublic(m.getModifiers()))
              .collect(Collectors.toList());

      assertEquals("Unexpected number of public methods",
              expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration",
              expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
              expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
                 expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }


}