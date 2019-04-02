//public class Point
//{
//   public final int x;
//   public final int y;
//
//   public Point(int x, int y)
//   {
//      this.x = x;
//      this.y = y;
//   }
//
//   public String toString()
//   {
//       return ("Point(" + x + ", " + y + ")");
//   }
//      public int hashCode()
//   {
//      int result = 17;
//      result = result * 31 + x;
//      result = result * 31 + y;
//      return result;
//   }
//
//   public boolean equals(Object o)
//   {
//       if (o == null)
//           return false;
//       if (getClass() != o.getClass())
//           return false;
//       return (x == ((Point)o).x &&
//           y == ((Point)o).y);
//   }
//
//}
import processing.core.PImage;

import java.util.List;

final class Point
{
    //   private final int x;
//   private final int y;
    public final int x;
    public final int y;

//   private static final String QUAKE_KEY = "quake";
//   private static final String QUAKE_ID = "quake";
//   private static final int QUAKE_ACTION_PERIOD = 1100;
//   private static final int QUAKE_ANIMATION_PERIOD = 100;
//   private static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int getX() { return x; }
    public int getY() { return y; }

    public String toString()
    {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object other)
    {
        return other instanceof Point &&
                ((Point)other).x == this.x &&
                ((Point)other).y == this.y;
    }

    public int hashCode()
    {
        int result = 17;
        result = result * 31 + x;
        result = result * 31 + y;
        return result;
    }
    public int distanceSquared(Point p2)
    {
        int deltaX = this.x - p2.x;
        int deltaY = this.y - p2.y;

        return deltaX * deltaX + deltaY * deltaY;
    }
    public boolean adjacent(Point p2)
    {
        return (this.x == p2.x && Math.abs(this.y - p2.y) == 1) ||
                (this.y == p2.y && Math.abs(this.x - p2.x) == 1);
    }
//
//   public Entity createMinerNotFull(String id, int resourceLimit,
//                                            int actionPeriod, int animationPeriod,
//                                           List<PImage> images)
//   {
//      return new Entity(EntityKind.MINER_NOT_FULL, id, this, images,
//              resourceLimit, 0, actionPeriod, animationPeriod);
//   }
//
//
//   public Entity createVein(String id, int actionPeriod,
//      List<PImage> images)
//      {
//      return new Entity(EntityKind.VEIN, id, this, images, 0, 0, actionPeriod, 0);
//   }
//
//   public Entity createQuake( List<PImage> images)
//   {
//      return new Entity(EntityKind.QUAKE, QUAKE_ID, this, images,
//              0, 0, QUAKE_ACTION_PERIOD, QUAKE_ANIMATION_PERIOD);
//   }
//
//   public Entity createOreBlob(String id,
//                                      int actionPeriod, int animationPeriod, List<PImage> images)
//   {
//      return new Entity(EntityKind.ORE_BLOB, id, this, images,
//              0, 0, actionPeriod, animationPeriod);
//   }
//   public Entity createOre(String id,int actionPeriod,
//                                  List<PImage> images)
//   {
//      return new Entity(EntityKind.ORE, id, this, images, 0, 0,
//              actionPeriod, 0);
//
//
//   }
//
//   public Entity createObstacle(String id,
//                                       List<PImage> images)
//   {
//      return new Entity(EntityKind.OBSTACLE, id, this, images,
//              0, 0, 0, 0);
//   }
//
//   public Entity createMinerFull(String id, int resourceLimit,
//                                        int actionPeriod, int animationPeriod,
//                                        List<PImage> images)
//   {
//      return new Entity(EntityKind.MINER_FULL, id, this, images,
//              resourceLimit, resourceLimit, actionPeriod, animationPeriod);
//   }
//
//   public Entity createBlacksmith(String id,
//                                         List<PImage> images)
//   {
//      return new Entity(EntityKind.BLACKSMITH, id, this, images,
//              0, 0, 0, 0);
//   }



}
