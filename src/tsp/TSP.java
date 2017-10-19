package tsp;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TSP {

    public static void main(String[] args) {

        File data = new File(args[0]);

        BufferedReader reader = null;

        try {
            String line;

            reader = new BufferedReader(new FileReader(data));

            Node[] nodes = new Node[0];

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
                        int x = Integer.parseInt(arr[1]);
                        int y = Integer.parseInt(arr[2]);

                        nodes[index - 1] = new Node(x, y, index);
                    }
                }
            }

            Node.nodes = nodes;

            Node.preprocess();

            Tour tour = Tour.optimized();

            System.out.println(tour.route.toString());
            System.out.println(tour.distance);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}