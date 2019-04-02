import java.util.List;
class Polygon{
    private List<Point> listofPoints;

    public Polygon(List<Point>listofPoints){
        this.listofPoints=listofPoints;
    }
    public List<Point>getPoints(){
        return listofPoints;
    }
    public double perimeter () {
        Point next;
        double perimeter = 0;
        for (int i = 0; i < this.getPoints().size(); i++) {
            Point current = this.getPoints().get(i);
            if (i < this.getPoints().size() - 1) {
                next = this.getPoints().get(i + 1);

            } else {
                next = this.getPoints().get(0);  //connect to the first Point to form a shape
            }

            double distance = Math.pow(Math.pow(next.getX() - current.getY(), 2) + Math.pow(next.getY() - current.getX(), 2), 0.5);
            perimeter += distance;
        }

        return perimeter;
    }
}