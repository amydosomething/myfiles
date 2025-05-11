import java.util.*;

public class DijkstraVIIT {
    static int V; // Number of vertices
    static String[] places;

    // Function to find the vertex with the minimum distance value
    int minDistance(double[] dist, Boolean[] sptSet) {
        double min = Double.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    // Function to implement Dijkstra's algorithm
    void dijkstra(double[][] graph, int src) {
        double[] dist = new double[V]; // Output array: shortest distance from src to i
        Boolean[] sptSet = new Boolean[V]; // True if vertex is included in the shortest path tree
        int[] parent = new int[V]; // Stores the shortest path tree

        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(sptSet, false);
        Arrays.fill(parent, -1); // No parent initially

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != Double.MAX_VALUE && dist[u] != Double.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u; // Store parent
                }
            }
        }

        printSolution(dist, parent, src);
    }

    // Function to print the shortest path
    void printSolution(double[] dist, int[] parent, int src) {
        System.out.println("Destination\tDistance from " + places[src] + "\tPath");
        for (int i = 0; i < V; i++) {
            if (i != src) {
                System.out.print(places[i] + "\t\t" + dist[i] + "\t\t");
                printPath(i, parent);
                System.out.println();
            }
        }
    }

    // Recursive function to print the path from source to destination
    void printPath(int j, int[] parent) {
        if (parent[j] == -1) {
            System.out.print(places[j]);
            return;
        }
        printPath(parent[j], parent);
        System.out.print(" -> " + places[j]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of locations: ");
        V = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        places = new String[V];
        System.out.println("Enter the names of the locations:");
        for (int i = 0; i < V; i++) {
            places[i] = scanner.nextLine();
        }

        double[][] graph = new double[V][V];
        System.out.println("Enter the adjacency matrix (use -1 for no direct path):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextDouble();
                if (graph[i][j] == -1) {
                    graph[i][j] = Double.MAX_VALUE; // Representing no connection
                }
            }
        }

        System.out.print("Enter the source location index (0 to " + (V - 1) + "): ");
        int src = scanner.nextInt();

        DijkstraVIIT t = new DijkstraVIIT();
        t.dijkstra(graph, src);

        scanner.close();
    }
}
