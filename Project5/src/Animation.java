public class Animation implements Action
{
    private AnimatedEntity entity;
    private int repeatCount;

    public Animation(AnimatedEntity entity, int repeatCount)
    {
        this.entity = entity;
        this.repeatCount = repeatCount;
    }
//
//    public static Animation createAnimationAction(Entity entity, int repeatCount)
//    {
//        return new Animation(entity, repeatCount);
//    }

    public void executeAction(EventScheduler scheduler)
    {
//        ((AnimatedEntity)this.entity).nextImage();

        entity.nextImage();


        if (this.repeatCount != 1)
        {
            scheduler.scheduleEvent((Entity)this.entity,
                    ActionFactory.createAnimationAction(this.entity,
                            Math.max(this.repeatCount - 1, 0)),
                    (this.entity).getanimationPeriod());
        }
    }

    public Entity getEntity() { return this.entity; }
    public int getRepeatCount() { return repeatCount; }


}