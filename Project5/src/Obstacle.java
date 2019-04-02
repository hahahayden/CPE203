import java.util.List;
import processing.core.PImage;
public class Obstacle extends Entity {


    //Unsure on below variables:
//    private int resourceLimit;
//    private int resourceCount;
//    private int actionPeriod;
//    private int animationPeriod;

//    private static final String OBSTACLE_KEY = "obstacle";
//    private static final int OBSTACLE_NUM_PROPERTIES = 4;
//    private static final int OBSTACLE_ID = 1;
//    private static final int OBSTACLE_COL = 2;
//    private static final int OBSTACLE_ROW = 3;

    public Obstacle(String id, Point position, List<PImage> images)
    {
//        this.id = id;
//        this.position = position;
//        this.images = images;
//        this.imageIndex = 0;
        //All zero for BlackSmith below:
//        this.resourceLimit = 0;
//        this.resourceCount = 0;
//        this.actionPeriod = 0;
//        this.animationPeriod = 0;
        super(id, position, images);
    }

//    public Point getposition() { return position; }
//    public void setposition(Point point) { this.position = point; }
//    public List<PImage> getimages() { return images; }
//    public String getid() { return id; }
//    public int getImageIndex() { return imageIndex; }
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

}

