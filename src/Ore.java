import java.util.List;
import processing.core.PImage;
import java.util.Random;

public class Ore extends ActionEntity{



    private static final Random rand = new Random();

//    public static final String ORE_KEY = "ore";
//    public static final String ORE_ID_PREFIX = "ore -- ";
//    public static final int ORE_CORRUPT_MIN = 20000;
//    public static final int ORE_CORRUPT_MAX = 30000;


    private static final String BLOB_KEY = "blob";
    private static final String BLOB_ID_SUFFIX = " -- blob";
    private static final int BLOB_PERIOD_SCALE = 4;
    private static final int BLOB_ANIMATION_MIN = 50;
    private static final int BLOB_ANIMATION_MAX = 150;

    public Ore(String id, Point position, List<PImage> images, int actionPeriod)
    {
//        this.id = id;
//        this.position = position;
//        this.images = images;
//        this.imageIndex = 0;
//
//        this.resourceLimit = 0;
//        this.resourceCount = 0;
//
//        this.actionPeriod = actionPeriod;
//        //NOT USED:
//        this.animationPeriod = 0;
        super(id, position, images, actionPeriod);
    }
//
//    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
//    {
//        scheduler.scheduleEvent(this,
//                ActionFactory.createActivityAction(this, world,imageStore),
//                actionPeriod);
//    }


    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Point pos = this.position;  // store current position before removing

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        OreBlob blob = createFactory.createOreBlob(this.id + BLOB_ID_SUFFIX,
                this.actionPeriod / BLOB_PERIOD_SCALE,
                BLOB_ANIMATION_MIN + rand.nextInt(BLOB_ANIMATION_MAX - BLOB_ANIMATION_MIN),
                imageStore.getImageList(BLOB_KEY),pos);

        world.addEntity(blob);
        ((ActionEntity)blob).scheduleActions(scheduler, world, imageStore);
    }

//    public int getactionPeriod() { return actionPeriod; }
//
//    public Point getposition() { return position; }
//    public void setposition(Point point) { this.position = point; }
//    public List<PImage> getimages() { return images; }
//    public String getid() { return id; }
//    public int getImageIndex() { return imageIndex; }
//    public List<PImage> getImages() {
//        return images;
//    }
//   public PImage getCurrentImage(Object entity)
//   {
//       if (entity instanceof Background)
//       {
//           return ((Background)entity).getImages()
//                   .get(((Background)entity).getImageIndex());
//       }
//       else if (entity instanceof Entity)
//       {
//           return (this.getimages().get(this.getImageIndex()));
//       }
//       else
//       {
//           throw new UnsupportedOperationException(
//                   String.format("getCurrentImage not supported for %s",
//                           entity));
//       }
//   }




}