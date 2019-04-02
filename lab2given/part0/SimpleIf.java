class SimpleIf {
   public static double max(double x, double y) {
      /*
       * TO DO: Write an if statement to determine which argument is larger and return
       * that value.
       */
      if (x > y) {
         return x;
      }
      else if (x<y){
         return y;
      }
      else if (x==y){
         return x;
      }

      return 0; // clearly not correct -- but testable
   }

   // public static void main(String[] args) {
   // double x = max(2, 3);
   // System.out.println(x);
   // }
}
