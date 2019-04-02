//import java.util.List;
//import processing.core.PImage;
//
////public interface Entity
//public interface Entity
//{
//   Point getposition();
//   void setposition(Point point);
//   PImage getCurrentImage(Object entity);
//}

import processing.core.PImage;
import java.util.List;
import java.util.Optional;
import java.util.LinkedList;
public abstract class Entity{
    protected String id;
    protected Point position;
    protected List<PImage> images;
    protected int imageIndex;

    public Entity(String id, Point position,
                          List<PImage> images) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
    }
       public Point getposition() { return position; }
    public void setposition(Point point) { this.position = point; }
    public List<PImage> getimages() { return images; }
    public String getid() { return id; }
    public int getImageIndex() { return imageIndex; }
    protected void setImageIndex(int integer) { imageIndex = integer; } // only want children to set image index for images

    public PImage getCurrentImage(Object entity)
    {
        if (entity instanceof Background)
        {
            return ((Background)entity).getImages()
                    .get(((Background)entity).getImageIndex());
        }
        else if (entity instanceof Entity)
        {
            return (this.getimages().get(this.getImageIndex()));
        }
        else
        {
            throw new UnsupportedOperationException(
                    String.format("getCurrentImage not supported for %s",
                            entity));
        }
    }

}
