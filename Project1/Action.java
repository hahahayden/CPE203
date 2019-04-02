import processing.core.PImage;

import java.util.List;
import java.util.Optional;

final class Action
{
   private ActionKind kind;
   private Entity entity;
   private WorldModel world;
   private ImageStore imageStore;
   private int repeatCount;

   public Action(ActionKind kind, Entity entity, WorldModel world,
      ImageStore imageStore, int repeatCount)
   {
      this.kind = kind;
      this.entity = entity;
      this.world = world;
      this.imageStore = imageStore;
      this.repeatCount = repeatCount;
   }

   public Entity getEntity() {
      return entity;
   }

   public void executeAction(EventScheduler scheduler)
   {
      switch (this.kind)
      {
         case ACTIVITY:
            executeActivityAction(scheduler);
            break;

         case ANIMATION:
            executeAnimationAction(scheduler);
            break;
      }
   }
   public void executeAnimationAction(EventScheduler scheduler)
   {
      this.entity.nextImage();

      if (this.repeatCount != 1)
      {
         scheduler.scheduleEvent(this.entity,
                 this.entity.createAnimationAction(Math.max(this.repeatCount - 1, 0)),
                 entity.getAnimationPeriod());
      }
   }
   public void executeActivityAction(EventScheduler scheduler)
   {
      switch (entity.getkind())
      {
         case MINER_FULL:
            entity.executeMinerFullActivity(this.world,
                    this.imageStore, scheduler);
            break;

         case MINER_NOT_FULL:
            entity.executeMinerNotFullActivity(this.world,
                    this.imageStore, scheduler);
            break;

         case ORE:
            entity.executeOreActivity(this.world, this.imageStore,
                    scheduler);
            break;

         case ORE_BLOB:
            entity.executeOreBlobActivity(this.world, this.imageStore, scheduler);
            break;

         case QUAKE:
            entity.executeQuakeActivity(this.world,
                    scheduler);
            break;

         case VEIN:
            entity.executeVeinActivity(this.world, this.imageStore,
                    scheduler);
            break;

         default:
            throw new UnsupportedOperationException(
                    String.format("executeActivityAction not supported for %s",
                            this.entity.getkind()));
      }
   }


}
