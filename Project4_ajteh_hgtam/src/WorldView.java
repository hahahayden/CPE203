import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;
import java.util.Optional;

final class WorldView
{
   private PApplet screen;
   private WorldModel world;
   private int tileWidth;
   private int tileHeight;
   private Viewport viewport;
   private static final String VOLCANO_ID= "volcano";

   public WorldView(int numRows, int numCols, PApplet screen, WorldModel world,
      int tileWidth, int tileHeight)
   {
      this.screen = screen;
      this.world = world;
      this.tileWidth = tileWidth;
      this.tileHeight = tileHeight;
      this.viewport = new Viewport(numRows, numCols);
   }
   public void drawViewport()
   {
      this.drawBackground();
      this.drawEntities();
   }
   public void drawEntities()
   {
      for (Entity entity : this.world.getentities())
      {
         Point pos = entity.getposition();

         if ((this.viewport.contains(pos)))
         {
            Point viewPoint = this.viewport.worldToViewport(pos.getX(), pos.getY());
            this.screen.image(entity.getCurrentImage(entity),
                    viewPoint.getX() * this.tileWidth, viewPoint.getY() * this.tileHeight);
         }
      }
   }

   public void drawBackground()
   {
      for (int row = 0; row < this.viewport.getNumRows(); row++)
      {
         for (int col = 0; col < this.viewport.getNumCols(); col++)
         {
            Point worldPoint = this.viewport.viewportToWorld(col, row);
            Optional<PImage> image = this.world.getBackgroundImage(
                    worldPoint);
            if (image.isPresent())
            {
               this.screen.image(image.get(), col * this.tileWidth,
                       row * this.tileHeight);
            }
         }
      }
   }

   public void shiftView(int colDelta, int rowDelta)
   {
      int newCol = Functions.clamp(this.viewport.getCol() + colDelta, 0,
              this.world.getnumCols() - this.viewport.getNumCols());
      int newRow = Functions.clamp(this.viewport.getRow() + rowDelta, 0,
              this.world.getnumRows() - this.viewport.getNumRows());

      this.viewport.shift(newCol, newRow);
   }


   public void drawLava(int x, int y, ImageStore imageStore){

      List<PImage> lavaImage= imageStore.getImageList("lava");
//      world.drawVolcanoInWorld(x,y,viewport,volcanoImage);
//      world.drawVolcanoInWorld(x+1,y,viewport,volcanoImage);
//      List<PImage> redStoneImage = imageStore.getImageList("RedStone");
      world.drawTilesInWorld(x, y, viewport, lavaImage);
//      world.drawTilesInWorld(x-1, y-1, viewport, volcanoImage);
//      world.drawTilesInWorld(x, y-1, viewport, volcanoImage);
//      world.drawTilesInWorld(x, y+1, viewport, volcanoImage);
//      world.drawTilesInWorld(x+1, y, viewport, volcanoImage);
//      world.drawTilesInWorld(x-1, y, viewport, volcanoImage);
//      world.drawTilesInWorld(x+1, y+1, viewport, volcanoImage);
//      world.drawTilesInWorld(x-1, y+1, viewport, volcanoImage);
//      world.drawTilesInWorld(x+1, y-1, viewport, volcanoImage);
      //      Point tilePoint2= view.viewportToWorld(x-1,y-1);
//      Point tilePoint3= view.viewportToWorld(x,y-1);
//      Point tilePoint4= view.viewportToWorld(x,y+1);
//      Point tilePoint5= view.viewportToWorld(x+1,y);
//      Point tilePoint6= view.viewportToWorld(x-1,y);
//      Point tilePoint7= view.viewportToWorld(x+1,y-1);
//      Point tilePoint8= view.viewportToWorld(x-1,y+1);
//      Point tilePoint9= view.viewportToWorld(x,y);
   }

//   public void drawTiles(int x, int y, ImageStore imageStore) {
//
//      List<PImage> volcanoImage = imageStore.getImageList("RedStone");
//      world.drawTilesInWorld(x, y, viewport, volcanoImage);
//   }
}
