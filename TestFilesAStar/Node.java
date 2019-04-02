
public class Node {

    private Point position;
    private double gDist;
    private double fDist;
    private Node prior;


    public Node(Point position, double gDist, double fDist, Node prior) {
        this.position = position;
        this.gDist = gDist;
        this.fDist = fDist;
        this.prior = prior;

    }


    public double getfDist() {
        return this.fDist;
    }

    public Node getPrior() {
        return prior;
    }

    public Point getPosition(){
        return this.position;
    }

    public double getgDist(){return this.gDist;}
//        public void setPrior(Node node) {
//            this.prior = node;
//        }

}