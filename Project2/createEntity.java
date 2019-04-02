import processing.core.PImage;
import java.util.List;

public class createEntity
{
    private static final String QUAKE_ID = "quake";

    public static Entity createBlacksmith(String id, Point position, List<PImage> images) {
        return new Blacksmith(id, position, images);
    }

    public static Entity createMinerFull(String id, Point position, int resourceLimit,int actionPeriod, int animationPeriod,
                                  List<PImage> images) {
        return new MinerFull(id, position, images,
                resourceLimit, resourceLimit, actionPeriod, animationPeriod);
    }

    public static Entity createMinerNotFull(String id, Point position, int resourceLimit,
                                     int actionPeriod, int animationPeriod,
                                     List<PImage> images) {
        return new MinerNotFull(id, position, images,
                resourceLimit, 0, actionPeriod, animationPeriod);
    }

    public static Entity createObstacle(String id, Point position, List<PImage> images) {
        return new Obstacle(id, position, images, 0, 0, 0, 0);
    }

    public static Entity createOre(String id, Point position, int actionPeriod,
                            List<PImage> images) {
        return new Ore(id, position, images, actionPeriod);
    }

    public static Entity createOreBlob(String id, Point position, int actionPeriod, int animationPeriod, List<PImage> images) {
        return new OreBlob( id, position, images, actionPeriod, animationPeriod);
    }

    public static Entity createQuake( Point position, List<PImage> images) {
        return new Quake(QUAKE_ID, position, images);
    }

    public static Entity createVein(String id, Point position, int actionPeriod,
                             List<PImage> images) {
        return new Vein(id, position, images,
                actionPeriod);
    }
}
