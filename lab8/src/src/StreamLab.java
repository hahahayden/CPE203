import java.util.*;
import java.util.stream.*;
import java.io.*;
public class StreamLab {
    public static void main(String[]args){
        List<Point> pointList=new ArrayList<>();
        scanFile(pointList);
        List<Point> zValueChange =
                pointList.stream()
                        .filter( s -> s.getZ() <= 2.0)
                        .collect(Collectors.toList());
        List<Point> scaled =
                zValueChange.stream()
                        .map(s -> new Point(s.getX() * 1.25,
                                s.getY() * 1.25,
                                (double)s.getZ() * 1.25))   // represent as stream then perform operations
                        .collect(Collectors.toList());
        List<Point> translate =
                scaled.stream()
                        .map(s -> new Point(s.getX() +212 ,
                                s.getY() - 57,
                                s.getZ() - 0))
                        .collect(Collectors.toList());
-
        Writer(translate);
    }
    public static void scanFile(List<Point> lst)
    {
        try
        {
            File input = new File("C:\\Users\\hayde\\OneDrive - California Polytechnic State University\\CPE203\\lab8\\src\\src\\positions.txt");
            Scanner sc = new Scanner(input);

            while (sc.hasNextLine())
            {

                String line = sc.nextLine();
                String [] nums = line.split(",");

                lst.add(new Point(Double.parseDouble(nums[0]), Double.parseDouble(nums[1]), Double.parseDouble(nums[2])));
            }
        }
        catch (Exception e){ System.out.println(e.getMessage()); }
    }

    public static void Writer(List<Point> lst)
    {
        try
        {
            PrintStream output = new PrintStream("C:\\Users\\hayde\\OneDrive - California Polytechnic State University\\CPE203\\lab8\\givendrawMe.txt");
            for(Point data : lst)
            {
                output.println(data.getX() + ", " + data.getY() + ", " + data.getZ());
            }
        }
        catch (Exception e) { System.out.println("Can't Write"); }
    }

}
