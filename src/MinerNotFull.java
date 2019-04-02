import java.util.LinkedList;
import java.util.List;
import processing.core.PImage;
import java.util.Optional;

public class MinerNotFull extends Miner {



    public MinerNotFull(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod,int resourceCount,int resourceLimit)
    {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod, 0);
    }

    public void executeActivity(WorldModel world,ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> notFullTarget = world.findNearest(getposition(), Ore.class);

        if (!notFullTarget.isPresent() ||
                !this.moveTo(world, notFullTarget.get(), scheduler) ||
                !transform(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this,
                    ActionFactory.createActivityAction(this, world, imageStore),
                    getactionPeriod());
        }
    }

    //5
//    public Optional<Entity> findnearest(WorldModel world, Point pos, Entity e)
//    {
//        List<Entity> ofType = new LinkedList<>();
//        for (Entity entity : world.getentities())
//        {
//            if (entity instanceof Ore)
//            {
//                ofType.add(entity);
//            }
//        }
//
//        return nearestEntity(ofType, pos);
//    }

    //15
//    public Optional<Entity> nearestEntity(List<Entity> entities, Point pos)
//    {
//        if (entities.isEmpty())
//        {
//            return Optional.empty();
//        }
//        else
//        {
//            Entity nearest = entities.get(0);
//            int nearestDistance = nearest.getposition().distanceSquared(pos);
//
//            for (Entity other : entities)
//            {
//                int otherDistance = other.getposition().distanceSquared(pos);
//
//                if (otherDistance < nearestDistance)
//                {
//                    nearest = other;
//                    nearestDistance = otherDistance;
//                }
//            }
//
//            return Optional.of(nearest);
//        }
//    }

    //THIS IS MOVETONOTFULL
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (getposition().adjacent(target.getposition()))
        {
            this.resourceCount += 1;
//            setResourceCount(1);
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);

            return true;
        }
        else
        {
            Point nextPos = nextPosition(world, target.getposition());

            if (!getposition().equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity((Entity) this, nextPos);
            }
            return false;
        }
    }


    //13
//    public Point nextPosition(WorldModel world, Point destPos)
//    {
//        int horiz = Integer.signum(destPos.getX() - this.getposition().getX());
//        Point newPos = new Point(this.getposition().getX() + horiz,
//                this.getposition().getY());
//
//        if (horiz == 0 || world.isOccupied(newPos))
//        {
//            int vert = Integer.signum(destPos.getY() - this.getposition().getY());
//            newPos = new Point(this.getposition().getX(),
//                    this.getposition().getY() + vert);
//
//            if (vert == 0 || world.isOccupied(newPos))
//            {
//                newPos = this.getposition();
//            }
//        }
//
//        return newPos;
//    }


//    //8
//    public boolean transform(WorldModel world,
//                                    EventScheduler scheduler, ImageStore imageStore)
//    {
//        if (getResourceCount() >= getResourceLimit())
//        {
//            MinerFull miner = createFactory.createMinerFull(getid(), getResourceLimit(),
//                    getactionPeriod(), getanimationPeriod(),getposition(),
//                    getimages());
//
//
//
//
//            world.removeEntity(this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity((Entity)miner);
//            miner.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }

//    public int getactionPeriod() { return actionPeriod; }
//    public int getanimationPeriod() { return animationPeriod; }
//    public int getResourceLimit() { return resourceLimit; }
//    public int getResourceCount() { return resourceCount; }
//    public void nextImage() { imageIndex = (imageIndex + 1) % images.size(); }
//
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

