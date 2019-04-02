
public interface MovableEntities {
//    void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);
//    int getactionPeriod();
//    void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
    boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);

    Point nextPosition(WorldModel world, Point destPos);
}
