
public class ActionFactory
{
    public static Action createAnimationAction(AnimatedEntity entity, int repeatCount)
    {
        return new Animation(entity, repeatCount);
    }

    public static Action createActivityAction(Entity entity, WorldModel world,
                                              ImageStore imageStore)
    {
        return new Activity(entity, world, imageStore);
    }
}

