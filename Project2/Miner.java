public interface Miner extends Movable, ActionEntity, AnimationEntity
{
    boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);
}

