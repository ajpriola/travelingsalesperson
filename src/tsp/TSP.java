package tsp;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

public class TSP {

    public static Tour tour(String path) {
        return tour(new File(path), null);
    }

    public static Tour tour(File data) {
        return tour(data, null);
    }

    public static Tour tour(File data, Consumer<Double> progress) {
        BufferedReader reader = null;

        Node[] nodes = new Node[0];

        try {
            String line;

            reader = new BufferedReader(new FileReader(data));

            while ((line = reader.readLine()) != null) {

                if (line.contains("DIMENSION")) {

                    String[] arr = line.split(" ");
                    for (String s : arr) {
                        System.out.println(s);
                    }
                    nodes = new Node[Integer.parseInt(arr[1])];
                }

                if (line.equalsIgnoreCase("node_coord_section")) {
                    String node;

                    while ((node = reader.readLine()) != null && !node.equals("EOF")) {

                        String[] arr = node.trim().split("[ \\t]+");
                        int index = Integer.parseInt(arr[0]);
                        double x = Double.parseDouble(arr[1]);
                        double y = Double.parseDouble(arr[2]);

                        nodes[index - 1] = new Node(x, y, index);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        long start = System.currentTimeMillis();

        Node.nodes = nodes;

        Node.preprocess();

        Tour tour = Tour.optimized(progress);

        System.out.println(tour.route.toString());
        System.out.println(tour.distance);

        long end = System.currentTimeMillis();

        System.out.println("MS: " + (end - start));

        return tour;
    }

    public static void main(String[] args) {

        tour(args[0]);

    }
}