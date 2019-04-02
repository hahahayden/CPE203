

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Comparator;
import java.lang.Math;
import java.util.HashMap;

public class AStarPathingStrategy extends SingleStepPathingStrategy {

    @Override
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {

        List<Point> path = new ArrayList<>();

        Node current;    // define currrent node
        PriorityQueue<Node> openNodes = initQueue();   // create a priority queue

        HashMap<Point, Node> openHash = new HashMap<>();  //i need a HashMap to show me where my points are; what point correspnds
        HashMap<Point, Node> closedList = new HashMap<>();

        Node startNode = new Node(start, 0, 0, null);
        Node endNode = new Node(end, -1, -1, null);

        openNodes.add(startNode);          // put the node in first
        openHash.put(start, startNode);


        while (!openNodes.isEmpty()) {

            current = openNodes.remove();   // takes out the value just added in to the current node

            openHash.remove(current.getPosition());


            //filter the neighbors and end in list
            List<Point> neighbors = potentialNeighbors.apply(current.getPosition())
                    .filter(canPassThrough)
                    .filter(p -> !p.equals(start) && !p.equals(end))
                    .collect(Collectors.toList());


            if (withinReach.test(current.getPosition(), endNode.getPosition())) {   //using the Predicate see if it is close by if so reconstruct
                return reconstructPath(current, path);
            }

            for (Point p : neighbors) {
                double fVal;
                double gVal = current.getgDist() + 1; //All costs are 1 so just add one
                double hVal = calcDistance(p, endNode.getPosition());
                fVal = gVal + hVal;
                //create Node for this point
                Node nextNode = new Node(p, gVal, fVal, current);

                Node testNode = closedList.get(p);   // used to check if this is already put in
                Node testNode2 = openHash.get(p);

                if (testNode != null)//&& nextNode.getfDist() >= testNode.getfDist())  // also making sure that
                continue;
                if (testNode2 != null)//&& nextNode.getfDist() >= testNode2.getfDist())
                    continue;
                openHash.put(p, nextNode);
                openNodes.add(nextNode);
            }
            closedList.put(current.getPosition(), current);  // when lowest found per tile go back and put that currrent into close list
        }
        return path;
    }

    public double calcDistance(Point p, Point endNode) {
        return (Math.abs(p.getX() - endNode.getX()) + Math.abs(p.getY() - endNode.getY()));
    }

    public List<Point> reconstructPath(Node endNode, List<Point> myPath) {
        if (endNode == null)
            return myPath;
        if (endNode.getPrior() != null)
            myPath.add(0, endNode.getPosition());
        return reconstructPath(endNode.getPrior(), myPath);
    }

    private PriorityQueue<Node> initQueue() {
        return new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node x, Node y) {
                return ((int) (x.getfDist() - y.getfDist()));  // i want a priority queue that is basing off of the f distance

            }
        });
    }

}