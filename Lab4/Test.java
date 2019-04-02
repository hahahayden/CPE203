import java.lang.*;
import java.lang.Object.getClass();
public class Test
{


        public static void main(String []args){
            double myDouble = 1.1;
            int my= (int) myDouble;
            // boolean here= myInt instanceof Integer;
            System.out.println(myDouble.getClass().getSimpleName());
        }
    }

