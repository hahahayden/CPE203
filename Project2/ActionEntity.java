public interface ActionEntity extends Entity
{
    void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);
    void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
}
