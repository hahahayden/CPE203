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
//    public Point nextPosition(WorldModel world, Point destPos)
//    {
//
//        int horiz = Integer.signum(destPos.getX() - this.position.getX());
//        Point newPos = new Point(this.position.getX() + horiz,
//                this.position.getY());
//        //Point nextPos = this.StarStrat(world, destPos);
//        if (horiz == 0 || world.isOccupied(newPos) ) {
//            int vert = Integer.signum(destPos.getY() - this.position.getY());
//           newPos = new Point(this.position.getX(),
//                    this.position.getY() + vert);
//
//            if (vert == 0 || world.isOccupied(newPos)) {
//                newPos = this.position;
//                ;
//            }
//        }
//
//        return newPos;
//    }
public Point nextPosition(WorldModel world, Point pos) {
    //int horiz = Integer.signum(pos.getX() - this.position.getX());
    //Point newPos = new Point(this.position.getX() + horiz,
            //this.position.getY());
    Point nextPos = this.Strategy(world, pos);
//    if (horiz == 0 || world.isOccupied(newPos))
//    {
//        int vert = Integer.signum(pos.getY() - this.position.getY());
//        newPos = new Point(this.position.getX(),
//                this. position.getY() + vert);
//        //System.out.println(newPos);
//        if (vert == 0 || world.isOccupied(newPos))
//        {
//            newPos = this.position;
//            //System.out.println(newPos);
//        }
//}

    return nextPos;
}
    //private static PathingStrategy pathingStrategy= new SingleStepPathingStrategy();
    protected abstract boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);

    public abstract boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);



    public int getResourceLimit() { return resourceLimit; }

    public int getResourceCount() { return resourceCount; }

//    public Point SinglePathingStrategy(WorldModel world, Point goal) {
//        SingleStepPathingStrategy newStrat = new SingleStepPathingStrategy();
//        List<Point> listOfPoints = newStrat.computePath(this.getposition(), goal,
//                (point -> world.withinBounds(point) && !(world.isOccupied(point))), ((p1, p2) -> p1.adjacent(p2)), PathingStrategy.CARDINAL_NEIGHBORS);
//
//        if(listOfPoints.isEmpty()) {
//            return this.getposition();
//        }
//
//        return listOfPoints.get(1);
//    }
//public Point StarStrat(WorldModel world, Point goal) {
//    PathingStrategy newStrat = new SingleStepPathingStrategy();
//    List<Point> listOfPoints = newStrat.computePath(this.getposition(), goal,
//            (point -> !(world.isOccupied(point))&& world.withinBounds(point)), ((p1, p2) -> p1.adjacent(p2)), PathingStrategy.CARDINAL_NEIGHBORS);
//
//    if(listOfPoints.isEmpty()) {
//        return this.getposition();
//    }
//
//    return listOfPoints.get(0);
public Point Strategy(WorldModel world, Point goal) {
    PathingStrategy newStrat = new AStarPathingStrategy();
    //PathingStrategy newStrat= new SingleStepPathingStrategy();         // UNCOMMENT FOR SINGLE STEP
    List<Point> listOfPoints = newStrat.computePath(this.position, goal,
            (point -> world.withinBounds(point)&& !(world.isOccupied(point))), ((p1, p2) -> p1.adjacent(p2)), PathingStrategy.CARDINAL_NEIGHBORS);
//    List<Point> listOfPoints = newStrat.computePath(this.position, goal,
//            (point -> world.withinBounds(point)&& !(world.isOccupied(point))), ((p1, p2) -> p1.adjacent(p2)), PathingStrategy.DIAGONAL_NEIGHBORS);
//    List<Point> listOfPoints = newStrat.computePath(this.position, goal,
//            (point -> world.withinBounds(point)&& !(world.isOccupied(point))), ((p1, p2) -> p1.adjacent(p2)), PathingStrategy.DIAGONAL_CARDINAL_NEIGHBORS);
    if(listOfPoints.isEmpty()) {
        return this.getposition();
    }

    return listOfPoints.get(0);
}
}
