//public interface ActionEntity {
//    void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
//    void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);
//
//}
import processing.core.PImage;
import java.util.List;
import java.util.Optional;

public abstract class ActionEntity extends Entity{
    protected int actionPeriod;
    public ActionEntity(String id, Point position,
                            List<PImage> images,int actionPeriod)
    {
        super(id, position, images);
        this.actionPeriod = actionPeriod;
    }
    public int getactionPeriod() { return actionPeriod; }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                ActionFactory.createActivityAction(this, world, imageStore),
                this.getactionPeriod());
    }

    protected abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

}
