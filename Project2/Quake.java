import processing.core.PImage;
import java.util.List;

public class Quake implements AnimationEntity, ActionEntity
{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex = 0;
    private int animationPeriod;

    private static final int QUAKE_ACTION_PERIOD = 1100;
    private static final int QUAKE_ANIMATION_PERIOD = 100;
    private static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;


    public Quake(String id, Point position, List<PImage> images)
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.animationPeriod = QUAKE_ANIMATION_PERIOD;
        this.imageIndex = 0;
    }
    //Getters
    public String getId() {
        return id;
    }

    public List<PImage> getImages() {
        return images;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    //Entity Interface
    public PImage getCurrentImage(Object entity)
    {
        if (entity instanceof Background)
        {
            return images.get(imageIndex);
        }
        else if (entity instanceof Entity)
        {
            return images.get(imageIndex);
        }
        else
        {
            throw new UnsupportedOperationException(
                    String.format("getCurrentImage not supported for %s",
                            this));
        }
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public Point getPosition()
    {
        return this.position;
    }

    //ActionEntity Interface
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this, createAction.createActivityAction(this,world, imageStore),
                QUAKE_ACTION_PERIOD);
        scheduler.scheduleEvent(this, createAction.createAnimationAction(this,QUAKE_ANIMATION_REPEAT_COUNT),
                QUAKE_ANIMATION_PERIOD);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }

    //AnimationEntity Interface
    public int getAnimationPeriod() {
        return animationPeriod;
    }

    public void nextImage()
    {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }
}

