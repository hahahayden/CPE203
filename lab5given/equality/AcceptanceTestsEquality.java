import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.rules.*;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import java.util.Arrays;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AcceptanceTestsEquality
{
   @Test
   public void test01_CorseSectionEqual_01()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void test01a_CorseSectionEqualSignature()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final Object two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void test02_CorseSectionEqual_02()
   {
      final CourseSection one = new CourseSection(null, "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection(null, "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void test03_CorseSectionEqual_03()
   {
      final CourseSection one = new CourseSection(null, null, 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection(null, null, 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void test04_CorseSectionEqual_04()
   {
      final CourseSection one = new CourseSection("CSC", null, 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", null, 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void test05_CorseSectionEqual_05()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         null, LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         null, LocalTime.of(11, 0));

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void test06_CorseSectionEqual_06()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), null);
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), null);

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void test07_CorseSectionEqual_07()
   {
      final CourseSection one = new CourseSection(null, "203", 35,
         LocalTime.of(9, 40), null);
      final CourseSection two = new CourseSection(null, "203", 35,
         LocalTime.of(9, 40), null);

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void test08_CorseSectionEqual_08()
   {
      final CourseSection one = new CourseSection(null, null, 35,
         null, null);
      final CourseSection two = new CourseSection(null, null, 35,
         null, null);

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void test09_CorseSectionNotEqual_01()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("XXX", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test10_CorseSectionNotEqual_02()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "XXX", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test11_CorseSectionNotEqual_03()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 9999,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test12_CorseSectionNotEqual_04()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(1, 40), LocalTime.of(11, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test13_CorseSectionNotEqual_05()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 1), LocalTime.of(11, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test14_CorseSectionNotEqual_06()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(1, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test15_CorseSectionNotEqual_07()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 1));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test16_CorseSectionNotEqual_08()
   {
      final CourseSection one = new CourseSection(null, "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test17_CorseSectionNotEqual_09()
   {
      final CourseSection one = new CourseSection("CSC", null, 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test18_CorseSectionNotEqual_10()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         null, LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test19_CorseSectionNotEqual_11()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), null);
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test20_CorseSectionNotEqual_12()
   {
      final CourseSection one = new CourseSection(null, "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection(null, null, 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test21_CorseSectionNotEqual_13()
   {
      final CourseSection one = new CourseSection(null, null, 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection(null, null, 35,
         null, LocalTime.of(11, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test22_CorseSectionNotEqual_14()
   {
      final CourseSection one = new CourseSection(null, null, 35,
         null, LocalTime.of(11, 0));
      final CourseSection two = new CourseSection(null, null, 35,
         null, null);

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void test23_CorseSectionNotEqual_14()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertFalse(one.equals(null));
   }

   @Test
   public void test23a_CorseSectionNotEqual_14()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertFalse(one.equals(new Object()));
   }

   @Test
   public void test24_CorseSectionHashEqual_01()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void test25_CorseSectionHashEqual_02()
   {
      final CourseSection one = new CourseSection(null, "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection(null, "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void test26_CorseSectionHashEqual_03()
   {
      final CourseSection one = new CourseSection("CSC", null, 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", null, 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void test27_CorseSectionHashEqual_04()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         null, LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         null, LocalTime.of(11, 0));

      assertEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void test28_CorseSectionHashEqual_05()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), null);
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), null);

      assertEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void test29_CorseSectionHashEqual_06()
   {
      final CourseSection one = new CourseSection(null, null, 35,
         null, null);
      final CourseSection two = new CourseSection(null, null, 35,
         null, null);

      assertEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void test30_CorseSectionHashNotEqual_01()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("XXX", "203", 34,
         LocalTime.of(9, 10), LocalTime.of(10, 0));

      assertNotEquals(one.hashCode(), two.hashCode());
   }

  @Test
   public void test31_CorseSectionHashNotEqual_02()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "XXX", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));

      assertNotEquals(one.hashCode(), two.hashCode());
   }

  @Test
   public void test32_CorseSectionHashNotEqual_03()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(5, 10), LocalTime.of(10, 0));

      assertNotEquals(one.hashCode(), two.hashCode());
   }

  @Test
   public void test33_CorseSectionHashNotEqual_04()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 5));

      assertNotEquals(one.hashCode(), two.hashCode());
   }

  @Test
   public void test34_CorseSectionHashNotEqual_05()
   {
      final CourseSection one = new CourseSection(null, "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));

      assertNotEquals(one.hashCode(), two.hashCode());
   }

  @Test
   public void test35_CorseSectionHashNotEqual_06()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         null, LocalTime.of(10, 0));

      assertNotEquals(one.hashCode(), two.hashCode());
   }

  @Test
   public void test36_CorseSectionHashNotEqual_076()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 999,
         LocalTime.of(9, 10), LocalTime.of(10, 0));

      assertNotEquals(one.hashCode(), two.hashCode());
   }

  @Test
   public void test37_CorseSectionHashNotEqual_08()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection(null, null, 35,
         null, null);

      assertNotEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void test38_StudentEqual_01()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1;
      Object s2;
      
      coursesOne.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesOne.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesOne.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));
         
      coursesTwo.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesTwo.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesTwo.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));

      s1 = new Student("Workman", "Julie", 43, coursesOne);
      s2 = new Student("Workman", "Julie", 43, coursesTwo);
      
      assertTrue(s1.equals(s2));
      assertTrue(s2.equals(s1));
   }
   
   @Test
   public void test39_StudentEqual_02()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      coursesOne.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesOne.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesOne.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));
         
      coursesTwo.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesTwo.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesTwo.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));

      s1 = new Student(null, "Julie", 43, coursesOne);
      s2 = new Student(null, "Julie", 43, coursesTwo);
      
      assertTrue(s1.equals(s2));
      assertTrue(s2.equals(s1));
   }
   
   @Test
   public void test40_StudentEqual_03()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      coursesOne.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesOne.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesOne.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));
         
      coursesTwo.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesTwo.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesTwo.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));

      s1 = new Student("Workman", null, 43, coursesOne);
      s2 = new Student("Workman", null, 43, coursesTwo);
      
      assertTrue(s1.equals(s2));
      assertTrue(s2.equals(s1));
   }
   
   @Test
   public void test41_StudentEqual_04()
   {

      Student s1, s2;

      s1 = new Student("Workman", "Julie", 43, null);
      s2 = new Student("Workman", "Julie", 43, null);
      
      assertTrue(s1.equals(s2));
      assertTrue(s2.equals(s1));
   }
   
   @Test
   public void test42_StudentEqual_05()
   {
      Student s1, s2;

      s1 = new Student(null, null, 43, null);
      s2 = new Student(null, null, 43, null);
      
      assertTrue(s1.equals(s2));
      assertTrue(s2.equals(s1));
   }
   
   @Test
   public void test43_StudentEqual_06()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      coursesOne.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesOne.add(null);
      coursesOne.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));
         
      coursesTwo.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesTwo.add(null);
      coursesTwo.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));

      s1 = new Student("Workman", "Julie", 43, coursesOne);
      s2 = new Student("Workman", "Julie", 43, coursesTwo);
      
      assertTrue(s1.equals(s2));
      assertTrue(s2.equals(s1));
   }
   
   @Test
   public void test44_StudentNotEqual_01()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      s1 = new Student("Workman", "Julie", 43, coursesOne);
      s2 = new Student("XXX", "Julie", 43, coursesTwo);
      
      assertNotEquals(s1, s2);
      assertNotEquals(s2, s1);
   }
   
   @Test
   public void test45_StudentNotEqual_02()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      s1 = new Student("Workman", "Julie", 43, coursesOne);
      s2 = new Student("Workman", "XXX", 43, coursesTwo);
      
      assertNotEquals(s1, s2);
      assertNotEquals(s2, s1);
   }
   
   @Test
   public void test46_StudentNotEqual_03()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      s1 = new Student("Workman", "Julie", 43, coursesOne);
      s2 = new Student("Workman", "Julie", 999, coursesTwo);
      
      assertNotEquals(s1, s2);
      assertNotEquals(s2, s1);
   }
   
   @Test
   public void test47_StudentNotEqual_04()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      s1 = new Student(null, "Julie", 43, coursesOne);
      s2 = new Student("Workman", "Julie", 43, coursesTwo);
      
      assertNotEquals(s1, s2);
      assertNotEquals(s2, s1);
   }
   
   @Test
   public void test48_StudentNotEqual_05()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      s1 = new Student("Workman", null, 43, coursesOne);
      s2 = new Student("Workman", "Julie", 43, coursesTwo);
      
      assertNotEquals(s1, s2);
      assertNotEquals(s2, s1);
   }
   
   @Test
   public void test49_StudentNotEqual_06()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      s1 = new Student("Workman", "Julie", 43, null);
      s2 = new Student("Workman", "Julie", 43, coursesTwo);
      
      assertNotEquals(s1, s2);
      assertNotEquals(s2, s1);
   }
   
   @Test
   public void test50_StudentNotEqual_07()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      s1 = new Student(null, null, 43, coursesOne);
      s2 = new Student(null, "Julie", 43, coursesTwo);
      
      assertNotEquals(s1, s2);
      assertNotEquals(s2, s1);
   }
   
   @Test
   public void test51_StudentNotEqual_08()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      coursesOne.add(new CourseSection("CSC", "XXX", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesOne.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesOne.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));
         
      coursesTwo.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesTwo.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesTwo.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));

      s1 = new Student("Workman", "Julie", 43, coursesOne);
      s2 = new Student("Workman", "Julie", 43, coursesTwo);
      
      assertNotEquals(s1, s2);
      assertNotEquals(s2, s1);
   }
   
   // Student hashCode
   @Test
   public void test52_StudentHashEqual_01()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      coursesOne.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesOne.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesOne.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));
         
      coursesTwo.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesTwo.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesTwo.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));

      s1 = new Student("Workman", "Julie", 43, coursesOne);
      s2 = new Student("Workman", "Julie", 43, coursesTwo);
      
      assertEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test53_StudentHashEqual_02()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;

      s1 = new Student(null, "Julie", 43, coursesOne);
      s2 = new Student(null, "Julie", 43, coursesTwo);
      
      assertEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test54_StudentHashEqual_03()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;

      s1 = new Student("Workman", null, 43, coursesOne);
      s2 = new Student("Workman", null, 43, coursesTwo);
      
      assertEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test55_StudentHashEqual_04()
   {
      Student s1, s2;

      s1 = new Student("Workman", "Julie", 43, null);
      s2 = new Student("Workman", "Julie", 43, null);
      
      assertEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test56_StudentHashEqual_05()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;

      s1 = new Student(null, null, 43, null);
      s2 = new Student(null, null, 43, null);
      
      assertEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test57_StudentHashNotEqual_01()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;

      s1 = new Student("Workman", "Julie", 43, coursesOne);
      s2 = new Student("Julie", "Workman", 43, coursesTwo);
      
      assertNotEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test58_StudentHashNotEqual_02()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;

      s1 = new Student("XXX", "Workman", 43, coursesOne);
      s2 = new Student("Julie", "Workman", 43, coursesTwo);
      
      assertNotEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test59_StudentHashNotEqual_03()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;

      s1 = new Student("Julie", "XXX", 43, coursesOne);
      s2 = new Student("Julie", "Workman", 43, coursesTwo);
      
      assertNotEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test60_StudentHashNotEqual_04()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;

      s1 = new Student("Julie", "Workman", 99, coursesOne);
      s2 = new Student("Julie", "Workman", 43, coursesTwo);
      
      assertNotEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test61_StudentHashNotEqual_05()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;

      s1 = new Student(null, "Workman", 99, coursesOne);
      s2 = new Student("Julie", "Workman", 43, coursesTwo);
      
      assertNotEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test62_StudentHashNotEqual_06()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;

      s1 = new Student("Julie", null, 99, coursesOne);
      s2 = new Student("Julie", "Workman", 43, coursesTwo);
      
      assertNotEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test63_StudentHashNotEqual_07()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;

      s1 = new Student("Julie", "Workman", 99, null);
      s2 = new Student("Julie", "Workman", 43, coursesTwo);
      
      assertNotEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test64_StudentHashNotEqual_08()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;

      s1 = new Student(null, null, 99, coursesOne);
      s2 = new Student(null, "Workman", 43, coursesTwo);
      
      assertNotEquals(s1.hashCode(), s2.hashCode());
   }
   
   @Test
   public void test65_StudentHashNotEqual_09()
   {
      List<CourseSection> coursesOne = new ArrayList<>();
      List<CourseSection> coursesTwo = new ArrayList<>();
      Student s1, s2;
      
      coursesOne.add(new CourseSection("XXX", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesOne.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesOne.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));
         
      coursesTwo.add(new CourseSection("CSC", "123", 33,
         LocalTime.of(9, 10), LocalTime.of(10, 0)));
      coursesTwo.add(new CourseSection("CSC", "123", 36,
         LocalTime.of(11, 10), LocalTime.of(12, 0)));
      coursesTwo.add(new CourseSection("CSC", "203", 36,
         LocalTime.of(1, 10), LocalTime.of(2, 0)));

      s1 = new Student("Workman", "Julie", 43, coursesOne);
      s2 = new Student("Julie", "Workman", 43, coursesTwo);
      
      assertNotEquals(s1.hashCode(), s2.hashCode());
   }
}