import processing.core.PImage;
import java.util.List;
import java.util.Optional;


public class MinerFull implements Miner
{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;

    private int resourceLimit;
    private int resourceCount;
    private int animationPeriod;
    private int actionPeriod;

    public MinerFull(String id, Point position, List<PImage> images,
                     int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod)
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.resourceCount = resourceCount;
        this.resourceLimit = resourceLimit;
        this.animationPeriod = animationPeriod;
        this.actionPeriod = actionPeriod;
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

    //AnimationEntity Interface
    public int getAnimationPeriod()
    {
        return this.animationPeriod;
    }

    public void nextImage()
    {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    //Movable Interface
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (this.position.adjacent(target.getPosition()))
        {
            return true;
        }
        else
        {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.position.equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant( nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    public Point nextPosition(WorldModel world, Point pos)
    {
        int horiz = Integer.signum(pos.getX() - this.position.getX());
        Point newPos = new Point(this.position.getX() + horiz,
                this.position.getY());

          if (horiz == 0 || world.isOccupied(newPos))
        {
            int vert = Integer.signum(pos.getY() - this.position.getY());
            newPos = new Point(this.position.getX(),
                    this.position.getY() + vert);

            if (vert == 0 || world.isOccupied(newPos))
            {
                newPos = this.position;
            }
        }

          return newPos;
    }

    //ActionEntity Interface
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this, createAction.createActivityAction(this, world, imageStore),
                this.actionPeriod);
        scheduler.scheduleEvent(this, createAction.createAnimationAction(this,0),
                this.getAnimationPeriod());
        }

    public void executeActivity(WorldModel world, ImageStore imageStore,EventScheduler scheduler)
    {
        Optional<Entity> fullTarget = world.findNearest(this.position, Blacksmith.class);

        if (fullTarget.isPresent() &&
                this.moveTo(world, fullTarget.get(), scheduler))
        {
            transform(world, scheduler, imageStore);
        }
        else
        {
            scheduler.scheduleEvent(this, createAction.createActivityAction( this, world, imageStore),
                    this.actionPeriod);
        }
    }

    //Miner Interface
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        Entity miner = createEntity.createMinerNotFull(this.id, this.position, this.resourceLimit,
                this.actionPeriod, this.getAnimationPeriod(), this.images);

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(miner);
        ((Miner)miner).scheduleActions(scheduler, world, imageStore);
        return false;
    }
}
