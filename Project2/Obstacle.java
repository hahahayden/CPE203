import processing.core.PImage;
import java.util.List;

public class Obstacle implements Entity
{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;

    private int resourceLimit;
    private int resourceCount;
    private int animationPeriod;
    private int actionPeriod;
    public Obstacle(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount,
                    int actionPeriod, int animationPeriod)
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.animationPeriod = animationPeriod;
        this.actionPeriod = actionPeriod;
        this.resourceCount = resourceCount;
        this.resourceLimit = resourceLimit;
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

    public int getResourceLimit() {
        return resourceLimit;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public int getAnimationPeriod() {
        return animationPeriod;
    }

    public int getActionPeriod() {
        return actionPeriod;
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
}
