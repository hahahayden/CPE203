import java.lang.Math;
import java.util.Arrays;

class SimpleArray {
   public static int[] squareAll(int values[]) {
      /*
       * TO DO: This size is not right. Fix it to work with any input array. The
       * length of an array is accessible through an array's length field (e.g.,
       * values.length).
       */
      int[] newValues = new int[values.length]; // This allocates an array of

      /*
       * TO DO: The output array, newValues, should hold as its elements the square of
       * the corresponding element in the input array.
       *
       *
       * Write a loop to compute the square of each element from the input array and
       * to place the result into the output array.
       */

      for (int i = 0; i <= newValues.length - 1; i++) {
         newValues[i] = (int) Math.pow(values[i], 2);
         System.out.println(newValues[i]);
      }

      return newValues;
   }

   // public static void main(String[] args) {
   // int[] value = new int[] { 1, 2, 3 };

   // int[] newValue = squareAll(value);
   // System.out.println(Arrays.toString(newValue));
   // }
}


