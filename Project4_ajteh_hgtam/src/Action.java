public interface Action   // kept as interface because only entities are shared and in fact the entities are of different types/instances
{
   void executeAction(EventScheduler scheduler);
   Entity getEntity();
}