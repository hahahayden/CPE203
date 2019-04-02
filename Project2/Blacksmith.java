import processing.core.PImage;

import java.util.List;

public class Blacksmith implements Entity
{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;

    public Blacksmith(String id, Point position, List<PImage> images)

    {
        this.id = id;
        this.position = position;
        this.images = images;
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
    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }
}
