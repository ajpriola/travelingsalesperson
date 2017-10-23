package tsp;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Tour {

    Node start;
    ArrayList<Node> route;
    ArrayList<Edge> edges;

    double distance;
    public double xMin, xMax, yMin, yMax;

    public Tour(Node start) {
        this.start = start;
        route = new ArrayList<Node>();
        edges = new ArrayList<>();
        distance = 0;
    }

    public ArrayList<Node> getRoute() {
        return route;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    //public static void logoutWithCompletion(Consumer<Boolean> completion)

    static Tour optimized(Consumer<Double> progress) {
        Tour tour = new Tour(Node.nodes[0]);

        Node current = tour.start;

        while (tour.route.size() < Node.nodes.length) {

            progress.accept((double)tour.route.size() / Node.nodes.length);

            if (current.x < tour.xMin) tour.xMin = current.x;
            if (current.x > tour.xMax) tour.xMax = current.x;
            if (current.y < tour.yMin) tour.yMin = current.y;
            if (current.y > tour.yMax) tour.yMax = current.y;

            for (Node o : current.ordered) {
                if (!tour.route.contains(o)) {
                    tour.route.add(o);
                    double distance = current.distanceTo(o);
                    tour.edges.add(new Edge(current, o, distance));
                    tour.distance += distance;
                    current = o;
                    break;
                }
            }
        }

        progress.accept(1.0);

        return tour;
    }
}
