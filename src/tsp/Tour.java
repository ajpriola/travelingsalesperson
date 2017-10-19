package tsp;

import java.util.ArrayList;

public class Tour {

    Node start;
    ArrayList<Node> route;
    double distance;

    public Tour(Node start) {
        this.start = start;
        route = new ArrayList<Node>();
        distance = 0;
    }


    static Tour optimized() {
        Tour tour = new Tour(Node.nodes[0]);

        Node current = tour.start;

        while (tour.route.size() < Node.nodes.length) {

            for (Node o : current.ordered) {
                if (!tour.route.contains(o)) {
                    tour.route.add(o);
                    tour.distance += current.distanceTo(o);
                    current = o;
                    break;
                }
            }
        }

        return tour;
    }
}
