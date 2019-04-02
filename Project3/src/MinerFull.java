import java.util.LinkedList;
import java.util.List;
import processing.core.PImage;
import java.util.Optional;
public class MinerFull extends Miner {


        public MinerFull(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod)
        {
            super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod, 0);
        }

//        public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
//        {
//            scheduler.scheduleEvent((Entity)this,
//                    ActionFactory.createActivityAction(this, world, imageStore),
//                    this.actionPeriod);
//            scheduler.scheduleEvent(this, ActionFactory.createAnimationAction(this, 0),
//                this.animationPeriod);
//        }
//
        public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
        {
            Optional<Entity> fullTarget = world.findNearest(getposition(), Blacksmith.class);

            if (fullTarget.isPresent() &&
                    this.moveTo(world, fullTarget.get(), scheduler))
            {
                this.transform(world, scheduler, imageStore);
            }
            else
            {
                scheduler.scheduleEvent((Entity)this,
                        ActionFactory.createActivityAction(this, world, imageStore),
                        getactionPeriod());
            }
        }



     //   11
        public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
        {
            if (getposition().adjacent(target.getposition()))
            {
                return true;
            }
            else
            {
                Point nextPos = this.nextPosition(world, target.getposition());

                if (!getposition().equals(nextPos))
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

        //13
//        public Point nextPosition(WorldModel world, Point destPos)
//        {
//            int horiz = Integer.signum(destPos.getX() - getposition().getX());
//            Point newPos = new Point(getposition().getX() + horiz,
//                    getposition().getY());
//
//            if (horiz == 0 || world.isOccupied(newPos))
//            {
//                int vert = Integer.signum(destPos.getY() - getposition().getY());
//                newPos = new Point(getposition().getX(),
//                        getposition().getY() + vert);
//
//                if (vert == 0 || world.isOccupied(newPos))
//                {
//                    newPos = getposition();
//                }
//            }
//
//            return newPos;
//        }

        //9
//        public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
//        {
//            Entity miner = createFactory.createMinerNotFull(getid(), getResourceLimit(),getposition(),
//                    getactionPeriod(), getanimationPeriod(),
//                    getimages());
//
//            world.removeEntity(this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity(miner);
//            ((ActionEntity)miner).scheduleActions(scheduler, world, imageStore);
//            return false;
//        }

//        public int getactionPeriod() { return actionPeriod; }
//        public int getanimationPeriod() { return animationPeriod; }
//        public int getResourceLimit() { return resourceLimit; }
//        public void nextImage() { imageIndex = (imageIndex + 1) % images.size(); }
//
//        public Point getposition() { return position; }
//        public void setposition(Point point) { this.position = point; }
//        public List<PImage> getimages() { return images; }
//        public String getid() { return id; }
//        public int getImageIndex() { return imageIndex; }
//
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
