import java.util.List;
class Polygon{
    private List<Point> listofPoints;

    public Polygon(List<Point>listofPoints){
        this.listofPoints=listofPoints;
    }
    public List<Point>getPoints(){
        return listofPoints;
    }
}