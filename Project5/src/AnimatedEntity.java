//public interface AnimatedEntity
//////        implements Entity
////{
////    void nextImage();
////    int getanimationPeriod();
////
////}
import processing.core.PImage;
import java.util.List;

public abstract class AnimatedEntity extends ActionEntity {
    protected int repeatCount;
    protected int animationPeriod;

    public AnimatedEntity(String id, Point position,
                          List<PImage> images, int actionPeriod,
                          int animationPeriod, int repeatCount) {
        super(id, position, images, actionPeriod);
        this.animationPeriod = animationPeriod;
        this.repeatCount = repeatCount;
//        this.repeatCount = repeatCount; //New variable to take in for schedule Actions.
//        this.animationPeriod = animationPeriod;
    }

    public int getanimationPeriod() {
        return animationPeriod;
    }

    public void nextImage() {
        setImageIndex((getImageIndex() + 1) % getimages().size());
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this,
                ActionFactory.createActivityAction( this, world, imageStore),
                this.getactionPeriod());
        scheduler.scheduleEvent( this, ActionFactory.createAnimationAction(this, 0), this.getanimationPeriod());
    }
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
//public Point AStarPathingStrategy(WorldModel world, Point goal) {
//    PathingStrategy newStrat = new AStarPathingStrategy();
//    List<Point> listOfPoints = newStrat.computePath(this.getposition(), goal,
//            (point -> world.withinBounds(point) && !(world.isOccupied(point))), ((p1, p2) -> p1.adjacent(p2)), PathingStrategy.CARDINAL_NEIGHBORS);
//
//    if(listOfPoints.isEmpty()) {
//        return this.getposition();
//    }
//
//    return listOfPoints.get(1);
//}
}
