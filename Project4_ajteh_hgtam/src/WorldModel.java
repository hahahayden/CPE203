import processing.core.PImage;

import java.util.*;

final class WorldModel {
   private int numRows;
   private int numCols;
   private Background background[][];
   private Entity occupancy[][];
   private Set<Entity> entities;
//   private Point mousepress;
   private static final int ORE_REACH = 1;
   private static final int PROPERTY_KEY = 0;
   private static final String BGND_KEY = "background";


   private static final String MINER_KEY = "miner";


   private static final String OBSTACLE_KEY = "obstacle";


   private static final String ORE_KEY = "ore";


   private static final String SMITH_KEY = "blacksmith";

   private static final String VEIN_KEY = "vein";




   public int getnumCols() { return numCols; }
   public int getnumRows() { return numRows; }

   public Background[][] getBackground() { return background; }
   public Entity[][] getoccupancy() { return occupancy; }
   public Set<Entity> getentities() { return entities;}







   public WorldModel(int numRows, int numCols, Background defaultBackground) {
      this.numRows = numRows;
      this.numCols = numCols;
      this.background = new Background[numRows][numCols];
      this.occupancy = new Entity[numRows][numCols];
      this.entities = new HashSet<>();

      for (int row = 0; row < numRows; row++) {
         Arrays.fill(this.background[row], defaultBackground);
      }
   }

//   //1
   public Optional<Entity> findNearest(Point pos,
                                       Class kind) {
      List<Entity> ofType = new LinkedList<>();
      for (Entity entity : this.entities) {
         if (kind.isInstance(entity) ){
            ofType.add(entity);
         }
      }

      return this.nearestEntity(ofType, pos);
   }
   //2
   public Optional<Entity> getOccupant(Point pos) {
      if (this.isOccupied(pos)) {
         return Optional.of(this.getOccupancyCell(pos));
      } else {
         return Optional.empty();
      }
   }
   //3
   public boolean isOccupied(Point pos) {
      return this.withinBounds(pos) &&
              getOccupancyCell(pos) != null;
   }
//4
   public Entity getOccupancyCell(Point pos) {
      return this.occupancy[pos.getY()][pos.getX()];
   }

//5
   public boolean withinBounds(Point pos) {
      return pos.getX() >= 0 && pos.getY() < this.numRows &&
              pos.getX() >= 0 && pos.getX() < this.numCols;
   }
//6
   public void removeEntity(Entity entity) {
      this.removeEntityAt(entity.getposition());
   }
//7
   public void removeEntityAt(Point pos) {
      if (this.withinBounds(pos)
              && this.getOccupancyCell(pos) != null) {
         Entity entity = this.getOccupancyCell(pos);

         /* this moves the entity just outside of the grid for
            debugging purposes */
        entity.setposition(new Point(-1,-1));
         this.entities.remove(entity);
         this.setOccupancyCell(pos, null);
      }
   }
//8
   public void tryAddEntity(Entity entity,Point position) {
      if (this.isOccupied(position)) {
         // arguably the wrong type of exception, but we are not
         // defining our own exceptions yet
         throw new IllegalArgumentException("position occupied");
      }

      this.addEntity(entity);
   }
//9
   public void addEntity(Entity entity) {
      if (this.withinBounds(entity.getposition())) {
         this.setOccupancyCell(entity.getposition(), entity);
         this.entities.add(entity);
      }
   }

//10
   public void setOccupancyCell(Point pos,
                                Entity entity) {
      this.occupancy[pos.getY()][pos.getX()] = entity;
   }
//11
   public void moveEntity(Entity entity, Point pos) {
      Point oldPos = entity.getposition();
      if (this.withinBounds(pos) && !pos.equals(oldPos)) {
         this.setOccupancyCell(oldPos, null);
         this.removeEntityAt(pos);
         this.setOccupancyCell(pos, entity);
         entity.setposition(pos);
      }
   }

//12
   public void setBackgroundCell(Point pos,
                                 Background background) {
      this.background[pos.getY()][pos.getX()] = background;
   }
//13
   public Background getBackgroundCell(Point pos) {
      return this.background[pos.getY()][pos.getX()];
   }

//14
   public void setBackground(Point pos,
                             Background background) {
      if (this.withinBounds(pos)) {
         this.setBackgroundCell(pos, background);
      }
   }
//15
   public Optional<PImage> getBackgroundImage(
           Point pos) {
      if (this.withinBounds(pos)) {
         return Optional.of(this.getBackgroundCell(pos).getCurrentImage(getBackgroundCell(pos)));
      } else {
         return Optional.empty();
      }
   }
//16
//   public boolean parseVein(String[] properties,
//                            ImageStore imageStore) {
//      if (properties.length == VEIN_NUM_PROPERTIES) {
//         Point pt = new Point(Integer.parseInt(properties[VEIN_COL]),
//                 Integer.parseInt(properties[VEIN_ROW]));
//         Entity entity = createFactory.createVein(properties[VEIN_ID],
//
//                 Integer.parseInt(properties[VEIN_ACTION_PERIOD]),pt,
//                 imageStore.getImageList(VEIN_KEY));
//         this.tryAddEntity(entity);
//      }
//
//      return properties.length == VEIN_NUM_PROPERTIES;
//   }
   //17
   public void load(Scanner in, ImageStore imageStore)
   {
      int lineNumber = 0;
      while (in.hasNextLine())
      {
         try
         {
            if (!processLine(in.nextLine(),imageStore))
            {
               System.err.println(String.format("invalid entry on line %d",
                       lineNumber));
            }
         }
         catch (NumberFormatException e)
         {
            System.err.println(String.format("invalid entry on line %d",
                    lineNumber));
         }
         catch (IllegalArgumentException e)
         {
            System.err.println(String.format("issue on line %d: %s",
                    lineNumber, e.getMessage()));
         }
         lineNumber++;
      }
   }
   //18
   public boolean processLine(String line,
                                     ImageStore imageStore)
   {
      String[] properties = line.split("\\s");
      if (properties.length > 0)
      {
         switch (properties[PROPERTY_KEY])
         {
            case BGND_KEY:
               return parseFactory.parseBackground(properties, imageStore,this);
            case MINER_KEY:
               return parseFactory.parseMiner(properties,imageStore,this);
            case OBSTACLE_KEY:
               return parseFactory.parseObstacle(properties,imageStore,this);
            case ORE_KEY:
               return parseFactory.parseOre(properties, imageStore,this);
            case SMITH_KEY:
               return parseFactory.parseSmith(properties, imageStore,this);
            case VEIN_KEY:
               return parseFactory.parseVein(properties,imageStore,this);
         }
      }

      return false;
   }
//19
//   public boolean parseBackground(String [] properties,
//                                         ImageStore imageStore)
//   {
//      if (properties.length == BGND_NUM_PROPERTIES)
//      {
//         Point pt = new Point(Integer.parseInt(properties[BGND_COL]),
//                 Integer.parseInt(properties[BGND_ROW]));
//         String id = properties[BGND_ID];
//         setBackground(pt,
//                 new Background(id, imageStore.getImageList(id)));
//      }
//
//      return properties.length == BGND_NUM_PROPERTIES;
//   }
//   //20
//   public boolean parseMiner(String [] properties, ImageStore imageStore)
//   {
//       if (properties.length == MINER_NUM_PROPERTIES)
//       {
//           Point pt = new Point(Integer.parseInt(properties[MINER_COL]),
//                   Integer.parseInt(properties[MINER_ROW]));
//           Entity entity = createFactory.createMinerNotFull(properties[MINER_ID],Integer.parseInt(properties[MINER_LIMIT]),pt,
//
//                   Integer.parseInt(properties[MINER_ACTION_PERIOD]),
//                   Integer.parseInt(properties[MINER_ANIMATION_PERIOD]),
//                   imageStore.getImageList(MINER_KEY));
//           this.tryAddEntity(entity);
//       }
//
//       return properties.length == MINER_NUM_PROPERTIES;
//   }
////21
//   public boolean parseObstacle(String [] properties,
//                                       ImageStore imageStore)
//   {
//      if (properties.length == OBSTACLE_NUM_PROPERTIES)
//      {
//         Point pt = new Point(
//                 Integer.parseInt(properties[OBSTACLE_COL]),
//                 Integer.parseInt(properties[OBSTACLE_ROW]));
//         Obstacle entity= createFactory.createObstacle(properties[OBSTACLE_ID],imageStore.getImageList(OBSTACLE_KEY),pt)
//                 ;
//         this.tryAddEntity(entity);
//      }
//
//      return properties.length == OBSTACLE_NUM_PROPERTIES;
//   }
//
////22
//   public boolean parseOre(String [] properties,
//                                  ImageStore imageStore)
//   {
//      if (properties.length == ORE_NUM_PROPERTIES)
//      {
//         Point pt = new Point(Integer.parseInt(properties[ORE_COL]),
//                 Integer.parseInt(properties[ORE_ROW]));
//         Ore ore = createFactory.createOre(properties[ORE_ID], Integer.parseInt(properties[ORE_ACTION_PERIOD]),pt,
//                 imageStore.getImageList(ORE_KEY));
//         this.tryAddEntity(ore);
//      }
//
//      return properties.length == ORE_NUM_PROPERTIES;
//   }
//   //23
//   public boolean parseSmith(String [] properties,
//                                    ImageStore imageStore)
//   {
//      if (properties.length == SMITH_NUM_PROPERTIES)
//      {
//         Point pt = new Point(Integer.parseInt(properties[SMITH_COL]),
//                 Integer.parseInt(properties[SMITH_ROW]));
//         Entity entity = createFactory.createBlacksmith(properties[SMITH_ID],
//                 imageStore.getImageList(SMITH_KEY),pt);
//         this.tryAddEntity(entity);
//      }
//
//      return properties.length == SMITH_NUM_PROPERTIES;
//   }
   //24
   public Optional<Point> findOpenAround(Point pos)
   {
      for (int dy = -ORE_REACH; dy <= ORE_REACH; dy++)
      {
         for (int dx = -ORE_REACH; dx <= ORE_REACH; dx++)
         {
            Point newPt = new Point(pos.getX() + dx, pos.getY() + dy);
            if (this.withinBounds(newPt) &&
                    !this.isOccupied(newPt))
            {
               return Optional.of(newPt);
            }
         }
      }

      return Optional.empty();
   }

//
//   public Entity createMinerNotFull(String id,Point pos, int resourceLimit,
//                                    int actionPeriod, int animationPeriod,
//                                    List<PImage> images)
//   {
//      return new Entity(EntityKind.MINER_NOT_FULL, id, pos, images,
//              resourceLimit, 0, actionPeriod, animationPeriod);
//   }
//
//
//   public Entity createVein(String id, Point pos,int actionPeriod,
//                            List<PImage> images)
//   {
//      return new Entity(EntityKind.VEIN, id, pos, images, 0, 0, actionPeriod, 0);
//   }
//
//   public Entity createQuake( Point pos, List<PImage> images)
//   {
//      return new Entity(EntityKind.QUAKE, QUAKE_ID, pos, images,
//              0, 0, QUAKE_ACTION_PERIOD, QUAKE_ANIMATION_PERIOD);
//   }
//
//   public Entity createOreBlob(String id,Point pos,
//                               int actionPeriod, int animationPeriod, List<PImage> images)
//   {
//      return new Entity(EntityKind.ORE_BLOB, id, pos, images,
//              0, 0, actionPeriod, animationPeriod);
//   }
//   public Entity createOre(String id,Point pos,int actionPeriod,
//                           List<PImage> images)
//   {
//      return new Entity(EntityKind.ORE, id, pos, images, 0, 0,
//              actionPeriod, 0);
//
//
//   }
//
//   public Entity createObstacle(String id,Point pos,
//                                List<PImage> images)
//   {
//      return new Entity(EntityKind.OBSTACLE, id, pos, images,
//              0, 0, 0, 0);
//   }
//
//   public Entity createMinerFull(String id, Point pos, int resourceLimit,
//                                 int actionPeriod, int animationPeriod,
//                                 List<PImage> images)
//   {
//      return new Entity(EntityKind.MINER_FULL, id, pos, images,
//              resourceLimit, resourceLimit, actionPeriod, animationPeriod);
//   }
//
//   public Entity createBlacksmith(String id,Point pos,
//                                  List<PImage> images)
//   {
//      return new Entity(EntityKind.BLACKSMITH, id, pos, images,
//              0, 0, 0, 0);
//   }
//
   public Optional<Entity> nearestEntity(List<Entity> entities,
      Point pos)
   {
      if (entities.isEmpty())
      {
         return Optional.empty();
      }
      else
      {
         Entity nearest = entities.get(0);
         int nearestDistance = nearest.getposition().distanceSquared(pos);

         for (Entity other : entities)
         {
            int otherDistance = other.getposition().distanceSquared(pos);

            if (otherDistance < nearestDistance)
            {
               nearest = other;
               nearestDistance = otherDistance;
            }
         }

         return Optional.of(nearest);
      }
   }
//
//   public void drawVolcanoInWorld(int x, int y, Viewport view, List<PImage> listImage){
//      Point worldPoint= view.viewportToWorld(x,y);
//
//      if (withinBounds(worldPoint)) {
//         setBackgroundCell(worldPoint,new Background("VOLCANO",listImage));
//
//
//      }
//   }

   public void drawTilesInWorld(int x, int y, Viewport view, List<PImage> listImage){

      Point tilePoint= view.viewportToWorld(x,y);

      Point tilePoint2= view.viewportToWorld(x-1,y-1);
      Point tilePoint3= view.viewportToWorld(x,y-1);
      Point tilePoint4= view.viewportToWorld(x,y+1);
      Point tilePoint5= view.viewportToWorld(x+1,y);
      Point tilePoint6= view.viewportToWorld(x-1,y);
      Point tilePoint7= view.viewportToWorld(x+1,y-1);
      Point tilePoint8= view.viewportToWorld(x-1,y+1);
      Point tilePoint9= view.viewportToWorld(x+1,y+1);




      if (withinBounds(tilePoint) && withinBounds(tilePoint2)&& withinBounds(tilePoint3) && withinBounds(tilePoint4)&&withinBounds(tilePoint5) && withinBounds(tilePoint6)&& withinBounds(tilePoint7)&&withinBounds(tilePoint8) &&withinBounds(tilePoint9))
      {
         setBackgroundCell(tilePoint, new Background("lava", listImage));
         setBackgroundCell(tilePoint2, new Background("lava", listImage));
         setBackgroundCell(tilePoint3, new Background("lava", listImage));
         setBackgroundCell(tilePoint4, new Background("lava", listImage));
         setBackgroundCell(tilePoint5, new Background("lava", listImage));
         setBackgroundCell(tilePoint6, new Background("lava", listImage));
         setBackgroundCell(tilePoint7, new Background("lava", listImage));
         setBackgroundCell(tilePoint8, new Background("lava", listImage));
         setBackgroundCell(tilePoint9, new Background("lava", listImage));

//
//      }
//      return tilePoint;
      }
      else if ((withinBounds(tilePoint3)==false) && (withinBounds(tilePoint7)==false)&&(withinBounds(tilePoint)&& withinBounds(tilePoint8)&& withinBounds(tilePoint4)&& withinBounds(tilePoint9)&& withinBounds(tilePoint6)&& withinBounds(tilePoint5)   ) ){
         setBackgroundCell(tilePoint8, new Background("lava", listImage));
         setBackgroundCell(tilePoint4, new Background("lava", listImage));
         setBackgroundCell(tilePoint, new Background("lava", listImage));
         setBackgroundCell(tilePoint9, new Background("lava", listImage));
         setBackgroundCell(tilePoint6, new Background("lava", listImage));
         setBackgroundCell(tilePoint5, new Background("lava", listImage));

      }
      //&& (withinBounds(tilePoint3)==false) && (withinBounds(tilePoint7)==false))
      else if (withinBounds(tilePoint)&& (withinBounds(tilePoint5) &&  (withinBounds(tilePoint9)) &&(withinBounds(tilePoint4)))){
         setBackgroundCell(tilePoint, new Background("lava", listImage));
         setBackgroundCell(tilePoint5, new Background("lava", listImage));
         setBackgroundCell(tilePoint9, new Background("lava", listImage));
         setBackgroundCell(tilePoint4, new Background("lava", listImage));
//         setBackgroundCell(tilePoint3, new Background("lava", listImage));
//         setBackgroundCell(tilePoint7, new Background("lava", listImage));

      }
      else if (withinBounds(tilePoint)&& (withinBounds(tilePoint6) &&  (withinBounds(tilePoint8)) &&(withinBounds(tilePoint4)))){
         setBackgroundCell(tilePoint, new Background("lava", listImage));
         setBackgroundCell(tilePoint6, new Background("lava", listImage));
         setBackgroundCell(tilePoint8, new Background("lava", listImage));
         setBackgroundCell(tilePoint4, new Background("lava", listImage));
//         setBackgroundCell(tilePoint3, new Background("lava", listImage));
//         setBackgroundCell(tilePoint7, new Background("lava", listImage));
      }

      else if (withinBounds(tilePoint2)&& (withinBounds(tilePoint)) &&  (withinBounds(tilePoint6)) &&(withinBounds(tilePoint3))){
         setBackgroundCell(tilePoint2, new Background("lava", listImage));
         setBackgroundCell(tilePoint, new Background("lava", listImage));
         setBackgroundCell(tilePoint6, new Background("lava", listImage));
         setBackgroundCell(tilePoint3, new Background("lava", listImage));

      }
      else if (withinBounds(tilePoint7)&& withinBounds(tilePoint5)&& withinBounds(tilePoint)&& (withinBounds(tilePoint3) )){
         setBackgroundCell(tilePoint, new Background("lava", listImage));
         setBackgroundCell(tilePoint5, new Background("lava", listImage));

         setBackgroundCell(tilePoint3, new Background("lava", listImage));
         setBackgroundCell(tilePoint7, new Background("lava", listImage));

      }

   }

}

