import java.util.LinkedList;
import java.util.List;
import processing.core.PImage;
import java.util.Optional;
public class OreBlob extends AnimatedEntity implements MovableEntities{
//
//    private String id;
//    private Point position;
//    private List<PImage> images;
//    private int imageIndex;


//    private int resourceLimit;
//    private int resourceCount;
//
//    private int actionPeriod;
//    private int animationPeriod;
    private static final String QUAKE_KEY = "quake";
    public OreBlob(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod)
    {
//        this.id = id;
//        this.position = position;
//        this.images = images;
//        this.imageIndex = 0;
//
//
//
//        this.actionPeriod = actionPeriod;
//        this.animationPeriod = animationPeriod;
        super(id, position, images, actionPeriod, animationPeriod, 0);
    }

//    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
//    {
//        scheduler.scheduleEvent(this, ActionFactory.createActivityAction(this,world, imageStore),
//                getactionPeriod());
//        scheduler.scheduleEvent(this, ActionFactory.createAnimationAction(this,0),
//                getanimationPeriod());
//    }


    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> blobTarget = world.findNearest(this.getposition(), Vein.class);
        long nextPeriod = this.actionPeriod;

        if (blobTarget.isPresent())

        {
            Point tgtPos = blobTarget.get().getposition();

            if (this.moveTo(world, blobTarget.get(), scheduler))
            {
                Entity quake = createFactory.createQuake(imageStore.getImageList(QUAKE_KEY),tgtPos);

                world.addEntity(quake);
                nextPeriod += getactionPeriod();
                ((ActionEntity)quake).scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                ActionFactory.createActivityAction(this, world, imageStore),
                nextPeriod);
    }



    //12 moveToOreBlob Originally.
    public boolean moveTo(WorldModel world,
                              Entity target, EventScheduler scheduler)
    {
        if (this.position.adjacent(target.getposition()))
        {
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;
        }
        else
        {
            Point nextPos = this.nextPosition(world, target.getposition());
            //Point nextPos = this.AStarPathingStrategy(world, target.getposition());
           // Point nextPos = this.StarStrat(world, target.getposition());
            if (!this.position.equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    //14
    public Point nextPosition(WorldModel world, Point destPos)
    {
        //int horiz = Integer.signum(destPos.getX() - this.position.getX());
        //Point newPos = new Point(this.position.getX() + horiz,
                //this.position.getY());

        //Optional<Entity> occupant = world.getOccupant(newPos);

//        if (horiz == 0 ||
//                (occupant.isPresent() && !(occupant.get() instanceof Ore)))
//        {
//            int vert = Integer.signum(destPos.getY() - this.position.getY());
//            newPos = new Point(this.position.getX(), this.position.getY() + vert);
//            occupant = world.getOccupant(newPos);
//
//            if (vert == 0 ||
//                    (occupant.isPresent() && !(occupant.get() instanceof Ore)))
//            {
//                newPos = this.position;
//            }
//        }
        Point nextPos = this.Strategy(world, destPos);

        return nextPos;
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
public Point Strategy(WorldModel world, Point goal) {
    PathingStrategy newStrat = new AStarPathingStrategy();
    //PathingStrategy newStrat= new SingleStepPathingStrategy();         // UNCOMMENT FOR SINGLE STEP
    List<Point> listOfPoints = newStrat.computePath(this.position, goal,
            (point -> world.withinBounds(point) && !(world.isOccupied(point))), ((p1, p2) -> p1.adjacent(p2)), PathingStrategy.CARDINAL_NEIGHBORS);

    if(listOfPoints.isEmpty()) {
        return this.getposition();
    }

    return listOfPoints.get(0);
}
}


