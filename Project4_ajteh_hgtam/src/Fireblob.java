import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class Fireblob extends AnimatedEntity implements MovableEntities
{
    private int life = 100;
    private static final String QUAKE_KEY = "quake";

    public Fireblob(String id, Point position, int actionPeriod, int animationPeriod, List<PImage> images)
    {
        super(id, position, images, actionPeriod, animationPeriod,0);
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
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

    public Point nextPosition(WorldModel world, Point destPos)
    {
        int horiz = Integer.signum(destPos.getX() - this.position.getX());
        Point newPos = new Point(this.position.getX() + horiz,
                this.position.getY());

        Optional<Entity> occupant = world.getOccupant(newPos);

        if (horiz == 0 ||
                (occupant.isPresent() && !(occupant.get() instanceof Ore)))
        {
            int vert = Integer.signum(destPos.getY() - this.position.getY());
            newPos = new Point(this.position.getX(), this.position.getY() + vert);
            occupant = world.getOccupant(newPos);

            if (vert == 0 ||
                    (occupant.isPresent() && !(occupant.get() instanceof Ore)))
            {
                newPos = this.position;
            }
        }

        return newPos;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> blobTarget = world.findNearest(this.position,OreBlob.class);
        long nextPeriod = this.actionPeriod;

        if (blobTarget.isPresent())
        {
            Point tgtPos = blobTarget.get().getposition();
            if (world.isOccupied(this.position) && this.moveTo(world, blobTarget.get(), scheduler))
            {
                Entity quake = createFactory.createQuake(imageStore.getImageList(QUAKE_KEY), tgtPos);

                world.addEntity(quake);
                nextPeriod += this.actionPeriod;
                ((ActionEntity)quake).scheduleActions(scheduler, world, imageStore);
            }
        }
        if (life == 0) {
            world.removeEntity(this);
        }
        else
            life --;
        scheduler.scheduleEvent(this,
                ActionFactory.createActivityAction(this,world, imageStore), nextPeriod);
    }
}