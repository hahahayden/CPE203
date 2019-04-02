import java.util.List;
import processing.core.PImage;
import java.util.Optional;



public class Quake extends AnimatedEntity {
    //    private int actionPeriod;
//    private int resourceLimit;
//    private int resourceCount;


    //    public static final String QUAKE_ID = "quake";
//    private static final String QUAKE_KEY = "quake";
//    private static final int QUAKE_ACTION_PERIOD = 1100;
//    private static final int QUAKE_ANIMATION_PERIOD = 100;
    private static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;

    public Quake(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
//        this.id=id;
//        this.position=position;
//        this.animationPeriod=animationPeriod;
//        this.images=images;
        super(id, position, images, actionPeriod, animationPeriod, QUAKE_ANIMATION_REPEAT_COUNT);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }

//    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
//    {
//        scheduler.scheduleEvent(this,
//                ActionFactory.createActivityAction(this, world, imageStore),
//                this.actionPeriod);
//        scheduler.scheduleEvent(this,
//                ActionFactory.createAnimationAction(this, QUAKE_ANIMATION_REPEAT_COUNT),
//                this.animationPeriod);
        //}

//    public PImage getCurrentImage(Object entity)
//    {
//        if (entity instanceof Background)
//        {
//            return ((Background)entity).getImages()
//                    .get(((Background)entity).getImageIndex());
//        }
//        else if (entity instanceof Entity)
//        {
//            return (this.getimages().get(this.getImageIndex()));
//        }
//        else
//        {
//            throw new UnsupportedOperationException(
//                    String.format("getCurrentImage not supported for %s",
//                            entity));
//        }
//    }

//    public int getactionPeriod() { return actionPeriod; }
//    public int getanimationPeriod() { return animationPeriod; }
//    public void nextImage() { imageIndex = (imageIndex + 1) % images.size(); }
//
//    public Point getposition() { return position; }
//    public void setposition(Point point) { this.position = point; }
//    public List<PImage> getimages() { return images; }
//    public String getid() { return id; }
//    public int getImageIndex() { return imageIndex; }




}
