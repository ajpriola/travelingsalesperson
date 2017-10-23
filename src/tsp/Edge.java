package tsp;

public class Edge {

    public final Node from, to;
    public final double weight;

    public Edge(Node from, Node to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
