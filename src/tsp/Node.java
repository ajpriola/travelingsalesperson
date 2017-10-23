package tsp;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class Node {

    static Node[] nodes;

    public final int index;
    public final double x;
    public final double y;

    ArrayList<Node> ordered;

    public Node(double x, double y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    double distanceTo(Node i) {
        return Math.sqrt(Math.pow((x-i.x), 2) + Math.pow((y-i.y), 2));
    }

    @Override
    public String toString() {
        return String.format("{ tsp.Node: %d, (%f, %f) }", index, x, y);
    }

    static void preprocess() {
        for (Node node : nodes) {
            node.ordered = new ArrayList(Arrays.asList(nodes));
            Collections.sort(node.ordered, (n, m) -> Double.compare(node.distanceTo((Node) n), node.distanceTo((Node) m)));
        }
    }


}
