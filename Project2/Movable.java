public interface Movable extends Entity //Oreblob, Miner
{
    boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);
    Point nextPosition(WorldModel world,Point pos);
}
