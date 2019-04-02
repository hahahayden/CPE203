class SimpleLoop {
   public static int sum(int low, int high) {
      /*
       * TO DO: Return the sum of the integers between low and high (inclusive). Yes,
       * this can be done without a loop, but the point is to practice the syntax for
       * a loop.
       */
      int sum = 0;
      for (int i = low; i <= high; i++) {
         sum += i;
      }


      return sum;
   }
      //return 0;
   // public static void main(String[] args) {
   // System.out.println(sum(2, 6));
   // }
}
