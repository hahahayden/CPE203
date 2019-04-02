public class parseFactory
{
    private static final int SMITH_NUM_PROPERTIES = 4;
    private static final int SMITH_ID = 1;
    private static final int SMITH_COL = 2;
    private static final int SMITH_ROW = 3;
    private static final String SMITH_KEY = "blacksmith";
    private static final String OBSTACLE_KEY = "obstacle";
    private static final int OBSTACLE_NUM_PROPERTIES = 4;
    private static final int OBSTACLE_ID = 1;
    private static final int OBSTACLE_COL = 2;
    private static final int OBSTACLE_ROW = 3;
    private static final String MINER_KEY = "miner";
    private static final int MINER_NUM_PROPERTIES = 7;
    private static final int MINER_ID = 1;
    private static final int MINER_COL = 2;
    private static final int MINER_ROW = 3;
    private static final int MINER_LIMIT = 4;
    private static final int MINER_ACTION_PERIOD = 5;
    private static final int MINER_ANIMATION_PERIOD = 6;
    private static final String ORE_KEY = "ore";
    private static final int ORE_NUM_PROPERTIES = 5;
    private static final int ORE_ID = 1;
    private static final int ORE_COL = 2;
    private static final int ORE_ROW = 3;
    private static final int ORE_ACTION_PERIOD = 4;
    private static final String VEIN_KEY = "vein";
    private static final int VEIN_NUM_PROPERTIES = 5;
    private static final int VEIN_ID = 1;
    private static final int VEIN_COL = 2;
    private static final int VEIN_ROW = 3;
    private static final int VEIN_ACTION_PERIOD = 4;
//    private static final String BGND_KEY = "background";
    private static final int BGND_NUM_PROPERTIES = 4;
    private static final int BGND_ID = 1;
    private static final int BGND_COL = 2;
    private static final int BGND_ROW = 3;

    public static boolean parseSmith(String [] properties, ImageStore imageStore, WorldModel world)
    {
        if (properties.length == SMITH_NUM_PROPERTIES)
        {
            Point pt = new Point(Integer.parseInt(properties[SMITH_COL]),
                    Integer.parseInt(properties[SMITH_ROW]));
            Blacksmith entity = createFactory.createBlacksmith(properties[SMITH_ID],
                    imageStore.getImageList(SMITH_KEY),pt);
            world.tryAddEntity(entity,pt);
        }

        return properties.length == SMITH_NUM_PROPERTIES;
    }

    public static boolean parseObstacle(String [] properties, ImageStore imageStore, WorldModel world)
    {
        if (properties.length == OBSTACLE_NUM_PROPERTIES)
        {
            Point pt = new Point(
                    Integer.parseInt(properties[OBSTACLE_COL]),
                    Integer.parseInt(properties[OBSTACLE_ROW]));
            Obstacle entity = createFactory.createObstacle(properties[OBSTACLE_ID],
                    imageStore.getImageList(OBSTACLE_KEY),pt);
            world.tryAddEntity(entity,pt);
        }

        return properties.length == OBSTACLE_NUM_PROPERTIES;
    }

    public static boolean parseMiner(String [] properties, ImageStore imageStore, WorldModel world)
    {
        if (properties.length == MINER_NUM_PROPERTIES)
        {
            Point pt = new Point(Integer.parseInt(properties[MINER_COL]),
                    Integer.parseInt(properties[MINER_ROW]));

            Entity entity = createFactory.createMinerNotFull(properties[MINER_ID],
                    Integer.parseInt(properties[MINER_LIMIT]), pt,
                    Integer.parseInt(properties[MINER_ACTION_PERIOD]),
                    Integer.parseInt(properties[MINER_ANIMATION_PERIOD]),
                    imageStore.getImageList(MINER_KEY));
            world.tryAddEntity(entity,pt);
        }

        return properties.length == MINER_NUM_PROPERTIES;
    }

    public static boolean parseOre(String [] properties, ImageStore imageStore,WorldModel world)
    {
        if (properties.length == ORE_NUM_PROPERTIES)
        {
            Point pt = new Point(Integer.parseInt(properties[ORE_COL]),
                    Integer.parseInt(properties[ORE_ROW]));
            Ore Ore = createFactory.createOre(properties[ORE_ID],
                    Integer.parseInt(properties[ORE_ACTION_PERIOD]),pt,
                    imageStore.getImageList(ORE_KEY));
            world.tryAddEntity(Ore,pt);
        }

        return properties.length == ORE_NUM_PROPERTIES;
    }

    public static boolean parseVein(String [] properties, ImageStore imageStore, WorldModel world)
    {
        if (properties.length == VEIN_NUM_PROPERTIES)
        {
            Point pt = new Point(Integer.parseInt(properties[VEIN_COL]),
                    Integer.parseInt(properties[VEIN_ROW]));
            Vein entity = createFactory.createVein(properties[VEIN_ID],
                    Integer.parseInt(properties[VEIN_ACTION_PERIOD]),pt,
                    imageStore.getImageList(VEIN_KEY));
            world.tryAddEntity(entity,pt);
        }

        return properties.length == VEIN_NUM_PROPERTIES;
    }

    public static boolean parseBackground(String [] properties, ImageStore imageStore, WorldModel world)
    {
        if (properties.length == BGND_NUM_PROPERTIES)
        {
            Point pt = new Point(Integer.parseInt(properties[BGND_COL]),
                    Integer.parseInt(properties[BGND_ROW]));
            String id = properties[BGND_ID];
            world.setBackground(pt,
                    new Background(id, imageStore.getImageList(id)));
        }

        return properties.length == BGND_NUM_PROPERTIES;
    }

}
