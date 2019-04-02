import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.lang.Math;

class SimpleList {
   public static List<Integer> squareAll(List<Integer> values) {
      List<Integer> newValues = new LinkedList<Integer>();

      /*
       * TO DO: The output list, newValues, should hold as its elements the square of
       * the corresponding element in the input list.
       *
       * Write a loop to add the square of each element from the input list into the
       * output list. Use a "foreach".
       */

      for (int c : values) {

         int e=(int)Math.pow(c,2);
         newValues.add(e);
      }

      return newValues;
   }

//   public static void main(String[] args) {
//      List<Integer> input = new LinkedList<Integer>(Arrays.asList(new Integer[] { 1, 2, 3 }));
//      System.out.println(SimpleList.squareAll(input));
//   }
}
