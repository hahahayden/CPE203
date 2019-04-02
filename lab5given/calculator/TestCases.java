//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//import java.util.Arrays;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.time.LocalTime;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import org.junit.*;
//import org.junit.rules.*;
//import org.junit.runners.MethodSorters;
//import org.junit.FixMethodOrder;
//
//import org.junit.Test;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class TestCases
//{
//   public static final double DELTA = 0.00001;
//   private Bindings bindings = new VariableBindings();
//
//   @Before
//   public void init()
//   {
//      bindings.addBinding("x", 2.5);
//      bindings.addBinding("y", 10);
//   }
//
//   @Test
//   public void test01_AddExpressionEvaluate()
//   {
//      IdentifierExpression x = new IdentifierExpression("x");
//      IdentifierExpression y = new IdentifierExpression("y");
//      AddExpression add = new AddExpression(x, y,"+");
//
//      assertEquals(12.5, add.evaluate(bindings), DELTA);
//   }
//
//   @Test
//   public void test02_AddExpressionToString()
//   {
//      IdentifierExpression x = new IdentifierExpression("x");
//      IdentifierExpression y = new IdentifierExpression("y");
//      AddExpression add = new AddExpression(x,y,"+");
//
//      assertEquals("(x + y)", add.toString());
//   }
//   @Test
//   public void test03_AddExpressionEvaluate()
//   {
//      IdentifierExpression x = new IdentifierExpression("x");
//      IdentifierExpression y = new IdentifierExpression("y");
//      SubtractExpression subtract = new SubtractExpression(x, y,"-");
//
//      assertEquals(-7.5, subtract.evaluate(bindings), DELTA);
//   }
//
//   @Test
//   public void test04_AddExpressionToString()
//   {
//      IdentifierExpression x = new IdentifierExpression("x");
//      IdentifierExpression y = new IdentifierExpression("y");
//      SubtractExpression subtract = new SubtractExpression(x,y,"-");
//
//      assertEquals("(x - y)", subtract.toString());
//   }
//   @Test
//   public void test05_MultExpressionEvaluate()
//   {
//      IdentifierExpression x = new IdentifierExpression("x");
//      IdentifierExpression y = new IdentifierExpression("y");
//      MultiplyExpression mult = new MultiplyExpression(x, y,"*");
//
//      assertEquals(25, mult.evaluate(bindings), DELTA);
//   }
//
//   @Test
//   public void test06_AddExpressionToString()
//   {
//      IdentifierExpression x = new IdentifierExpression("x");
//      IdentifierExpression y = new IdentifierExpression("y");
//      MultiplyExpression mult = new MultiplyExpression(x,y,"*");
//
//      assertEquals("(x * y)", mult.toString());
//   }
//
//   @Test
//   public void test07_AddExpressionEvaluate()
//   {
//      IdentifierExpression x = new IdentifierExpression("x");
//      IdentifierExpression y = new IdentifierExpression("y");
//      DivideExpression div = new DivideExpression(x, y,"-");
//
//      assertEquals(.25, div.evaluate(bindings), DELTA);
//   }
//
//   @Test
//   public void test08_AddExpressionToString()
//   {
//      IdentifierExpression x = new IdentifierExpression("x");
//      IdentifierExpression y = new IdentifierExpression("y");
//      DivideExpression subtract = new DivideExpression(x,y,"/");
//
//      assertEquals("(x / y)", subtract.toString());
//   }
//}