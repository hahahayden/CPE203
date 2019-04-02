import java.util.List;
import processing.core.PImage;
import java.util.Random;
import java.util.Optional;
public class Vein  extends ActionEntity{



    private static final Random rand = new Random();
    private static final String ORE_KEY = "ore";
    private static final String ORE_ID_PREFIX = "ore -- ";
    private static final int ORE_CORRUPT_MIN = 20000;
    private static final int ORE_CORRUPT_MAX = 30000;

//    private static final int ORE_REACH = 1;

    public Vein(String id, Point position, List<PImage> images, int actionPeriod)
    {

        super(id, position, images,actionPeriod);
    }



    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Point> openPt = world.findOpenAround(this.position);

        if (openPt.isPresent())
        {
            Entity ore = createFactory.createOre(ORE_ID_PREFIX + this.id,
                      1000 + rand.nextInt(ORE_CORRUPT_MAX - ORE_CORRUPT_MIN),openPt.get(),imageStore.getImageList(ORE_KEY)
                  );
            world.addEntity(ore);
            ((ActionEntity)ore).scheduleActions(scheduler, world, imageStore);
        }

        scheduler.scheduleEvent(this,
                ActionFactory.createActivityAction(this, world, imageStore),
                getactionPeriod());
    }




}