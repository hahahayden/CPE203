import java.util.List;
import processing.core.PImage;

final class Background
{
   private String id;
   private List<PImage> images;
   private int imageIndex;

   public Background(String id, List<PImage> images)
   {
      this.id = id;
      this.images = images;
   }
   public String getid(){
      return this.id;
   }
   public List<PImage> getImages()
   {
      return this.images;
   }
   public int getImageIndex(){
      return this.imageIndex;
   }

   public PImage getCurrentImage(Background entity){
      return images.get(entity.imageIndex);


   }

   public boolean equals(Object o){
      if (this==o) return true;
      if (o==null) return false;
      if (getClass()!= o.getClass()) return false;
      Background other= (Background) o;
      return other.id==id && other.images.equals(images)&& other.imageIndex==imageIndex;
   }

}
