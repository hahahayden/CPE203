import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AcceptanceTestsCalculator
{
   public static final double DELTA = 0.00001;
   private Bindings bindings = new VariableBindings();
   
   @Before
   public void init() 
   {
      bindings.addBinding("x", 2.5);
      bindings.addBinding("y", 10);
      bindings.addBinding("bob", -7);
   }

   @Test
   public void test01_AddExpressionEvaluate()
   {
      AddExpression exp = new AddExpression(new IdentifierExpression("x"),
                                               new IdentifierExpression("y"));

      assertEquals(12.5, exp.evaluate(bindings), DELTA);
   }

   @Test
   public void test02_AddExpressionEvaluate()
   {
      BinaryExpression exp = new AddExpression(new IdentifierExpression("y"),
                                               new IdentifierExpression("bob"));

      assertEquals(3.0, exp.evaluate(bindings), DELTA);
   }

   @Test
   public void test03_AddExpressionToString()
   {
      BinaryExpression exp = new AddExpression(new IdentifierExpression("x"),
                                               new IdentifierExpression("y"));

      assertEquals("(x + y)", exp.toString());
   }

   @Test
   public void test04_AddExpressionToString()
   {
      BinaryExpression exp = new AddExpression(new IdentifierExpression("y"),
                                               new IdentifierExpression("bob"));

      assertEquals("(y + bob)", exp.toString());
   }

   @Test
   public void test05_SubtractExpressionEvaluate()
   {
      BinaryExpression exp = new SubtractExpression(new IdentifierExpression("x"),
                                                    new IdentifierExpression("y"));

      assertEquals(-7.5, exp.evaluate(bindings), DELTA);
   }

   @Test
   public void test06_SubtractExpressionEvaluate()
   {
      BinaryExpression exp = new SubtractExpression(new IdentifierExpression("y"),
                                                    new IdentifierExpression("bob"));

      assertEquals(17.0, exp.evaluate(bindings), DELTA);
   }

   @Test
   public void test07_SubtractExpressionToString()
   {
      BinaryExpression exp = new SubtractExpression(new IdentifierExpression("x"),
                                                    new IdentifierExpression("y"));

      assertEquals("(x - y)", exp.toString());
   }

   @Test
   public void test08_SubtractExpressionToString()
   {
      BinaryExpression exp = new SubtractExpression(new IdentifierExpression("y"),
                                                    new IdentifierExpression("bob"));

      assertEquals("(y - bob)", exp.toString());
   }

   @Test
   public void test09_MultiplyExpressionEvaluate()
   {
      BinaryExpression exp = new MultiplyExpression(new IdentifierExpression("x"),
                                                    new IdentifierExpression("y"));

      assertEquals(25.0, exp.evaluate(bindings), DELTA);
   }

   @Test
   public void test10_MultiplyExpressionEvaluate()
   {
      BinaryExpression exp = new MultiplyExpression(new IdentifierExpression("y"),
                                                    new IdentifierExpression("bob"));

      assertEquals(-70.0, exp.evaluate(bindings), DELTA);
   }

   @Test
   public void test11_MultiplyExpressionToString()
   {
      BinaryExpression exp = new MultiplyExpression(new IdentifierExpression("x"),
                                                    new IdentifierExpression("y"));

      assertEquals("(x * y)", exp.toString());
   }

   @Test
   public void test12_MultiplyExpressionToString()
   {
      BinaryExpression exp = new MultiplyExpression(new IdentifierExpression("y"),
                                                    new IdentifierExpression("bob"));

      assertEquals("(y * bob)", exp.toString());
   }

   @Test
   public void test13_DivideExpressionEvaluate()
   {
      BinaryExpression exp = new DivideExpression(new IdentifierExpression("y"),
                                                  new IdentifierExpression("x"));

      assertEquals(4.0, exp.evaluate(bindings), DELTA);
   }

   @Test
   public void test14_DivideExpressionEvaluate()
   {
      BinaryExpression exp = new DivideExpression(new IdentifierExpression("bob"),
                                                  new IdentifierExpression("y"));

      assertEquals(-.7, exp.evaluate(bindings), DELTA);
   }

   @Test
   public void test15_DivideExpressionToString()
   {
      BinaryExpression exp = new DivideExpression(new IdentifierExpression("x"),
                                                  new IdentifierExpression("y"));

      assertEquals("(x / y)", exp.toString());
   }

   @Test
   public void test16_DivideExpressionToString()
   {
      BinaryExpression exp = new DivideExpression(new IdentifierExpression("y"),
                                                  new IdentifierExpression("bob"));

      assertEquals("(y / bob)", exp.toString());
   }

   @Test
   public void test17_CombinationExpressionEvaluate()
   {
      BinaryExpression exp = new AddExpression(new IdentifierExpression("bob"),
                                               new IdentifierExpression("y"));
      BinaryExpression exp2 = new SubtractExpression(exp,
                                                     new IdentifierExpression("x"));

      assertEquals(.5, exp2.evaluate(bindings), DELTA);
   }

   @Test
   public void test18_CombinationExpressionToString()
   {
      BinaryExpression exp = new AddExpression(new IdentifierExpression("bob"),
                                               new IdentifierExpression("y"));
      BinaryExpression exp2 = new SubtractExpression(exp,
                                                     new IdentifierExpression("x"));

      assertEquals("((bob + y) - x)", exp2.toString());
   }

   @Test
   public void test19_CombinationExpressionEvaluate()
   {
      BinaryExpression exp = new AddExpression(new IdentifierExpression("x"),
                                               new IdentifierExpression("y"));
      BinaryExpression exp2 = new SubtractExpression(exp,
                                                     new IdentifierExpression("bob"));

      assertEquals(19.5, exp2.evaluate(bindings), DELTA);
   }

   @Test
   public void test20_CombinationExpressionToString()
   {
      BinaryExpression exp = new AddExpression(new IdentifierExpression("y"),
                                               new IdentifierExpression("bob"));
      BinaryExpression exp2 = new SubtractExpression(new IdentifierExpression("x"),
                                                     exp);

      assertEquals("(x - (y + bob))", exp2.toString());
   }

   @Test
   public void test21_testAddExpressionImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "applyOperator");

      final List<Class> expectedMethodReturns = Arrays.asList(
         double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[][] {new Class[] {Double.class, Double.class}});

      final List<String> expectedAbstractMethods = Arrays.asList();

      final List<String> expectedProtectedMethods = Arrays.asList(
         "applyOperator");

      verifyImplSpecifics(AddExpression.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters,
         expectedAbstractMethods, expectedProtectedMethods);
   }

   @Test
   public void test22_testSubtractExpressionImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "applyOperator");

      final List<Class> expectedMethodReturns = Arrays.asList(
         double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[][] {new Class[] {Double.class, Double.class}});

      final List<String> expectedAbstractMethods = Arrays.asList();

      final List<String> expectedProtectedMethods = Arrays.asList(
         "applyOperator");

      verifyImplSpecifics(SubtractExpression.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters,
         expectedAbstractMethods, expectedProtectedMethods);
   }

   @Test
   public void test23_testMultiplyExpressionImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "applyOperator");

      final List<Class> expectedMethodReturns = Arrays.asList(
         double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[][] {new Class[] {Double.class, Double.class}});

      final List<String> expectedAbstractMethods = Arrays.asList();

      final List<String> expectedProtectedMethods = Arrays.asList(
         "applyOperator");

      verifyImplSpecifics(MultiplyExpression.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters,
         expectedAbstractMethods, expectedProtectedMethods);
   }

   @Test
   public void test24_testDivideExpressionImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "applyOperator");

      final List<Class> expectedMethodReturns = Arrays.asList(
         double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[][] {new Class[] {Double.class, Double.class}});

      final List<String> expectedAbstractMethods = Arrays.asList();

      final List<String> expectedProtectedMethods = Arrays.asList(
         "applyOperator");

      verifyImplSpecifics(DivideExpression.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters,
         expectedAbstractMethods, expectedProtectedMethods);
   }

   @Test
   public void test25_testBinaryExpressionImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "applyOperator", "toString", "evaluate");

      final List<Class> expectedMethodReturns = Arrays.asList(
         double.class, String.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[] {Double.class, Double.class}, new Class[0],
         new Class[] {Bindings.class});

      final List<String> expectedAbstractMethods = Arrays.asList(
         "applyOperator");

      final List<String> expectedProtectedMethods = Arrays.asList(
         "applyOperator");

      verifyImplSpecifics(BinaryExpression.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters,
         expectedAbstractMethods, expectedProtectedMethods);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters,
      final List<String> expectedAbstractMethods,
      final List<String> expectedProtectedMethods)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, clazz.getFields().length);

      // public and protected methods
      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()) ||
                         Modifier.isProtected(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Unexpected number of methods",
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

      // abstract methods
      final List<Method> abstractMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isAbstract(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Incorrect number of abstract methods",
                   expectedAbstractMethods.size(),
                   abstractMethods.size());

      // protected methods
      final List<Method> protectedMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isProtected(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Incorrect number of protected methods",
                   expectedProtectedMethods.size(),
                   protectedMethods.size());
   }

}