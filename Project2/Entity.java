import processing.core.*;
public interface Entity
{
    PImage getCurrentImage(Object entity);
    Point getPosition();
    void setPosition(Point position);
}
