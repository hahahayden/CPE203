import java.util.LinkedList;
import java.util.List;
import processing.core.PImage;
import java.util.Optional;
//public interface Miner {
//    void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);
//    void executeActivity(WorldModel world,ImageStore imageStore, EventScheduler scheduler);
//    Optional<Entity>findnearest(WorldModel world, Point pos, Entity e);
//    Optional<Entity>nearestEntity(List<Entity> entities, Point Pos);
//    boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);
//    Point nextPosition(WorldModel world, Point destPos);
//    boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);

public abstract class Miner extends AnimatedEntity implements MovableEntities
{
    protected int resourceCount;
    protected int resourceLimit;

    public Miner(String id, Point position,
                         List<PImage> images, int resourceLimit,
                         int resourceCount, int actionPeriod,
                         int animationPeriod,int repeatCount)
    {
        super(id, position, images,actionPeriod, animationPeriod,0);
        this.resourceCount=resourceCount;
        this.resourceLimit=resourceLimit;

    }
    public Point nextPosition(WorldModel world, Point destPos)
    {
        int horiz = Integer.signum(destPos.getX() - this.getposition().getX());
        Point newPos = new Point(this.getposition().getX() + horiz,
                this.getposition().getY());

        if (horiz == 0 || world.isOccupied(newPos))
        {
            int vert = Integer.signum(destPos.getY() - this.getposition().getY());
            newPos = new Point(this.getposition().getX(),
                    this.getposition().getY() + vert);

            if (vert == 0 || world.isOccupied(newPos))
            {
                newPos = this.getposition();
            }
        }

        return newPos;
    }

  //  protected abstract boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);
public boolean transform(WorldModel world,
                           EventScheduler scheduler, ImageStore imageStore) {


    if (this.getClass().equals(MinerNotFull.class)) {

        if (getResourceCount() >= getResourceLimit()) {
            MinerFull miner = createFactory.createMinerFull(getid(), getResourceLimit(),
                    getactionPeriod(), getanimationPeriod(), getposition(),
                    getimages());


            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity((Entity) miner);
            miner.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    } else {

        Entity miner = createFactory.createMinerNotFull(getid(), getResourceLimit(), getposition(),
                getactionPeriod(), getanimationPeriod(),
                getimages());

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(miner);
        ((ActionEntity) miner).scheduleActions(scheduler, world, imageStore);
        return false;

    }
}






//        Entity miner = createFactory.createMinerNotFull(getid(), getResourceLimit(),getposition(),
//                    getactionPeriod(), getanimationPeriod(),
//                    getimages());
//
//            world.removeEntity(this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity(miner);
//            ((ActionEntity)miner).scheduleActions(scheduler, world, imageStore);
//            return false;




   // public abstract boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);



    public int getResourceLimit() { return resourceLimit; }

    public int getResourceCount() { return resourceCount; }


}
