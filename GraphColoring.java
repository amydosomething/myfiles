import java.util.*;

public class GraphColoring {

    @SuppressWarnings("unchecked")
    public static boolean graphColoring(int[][] edges, int v, int m) {
        // Step 1: Build adjacency list
        List<Integer>[] graph = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        // Step 2: Initialize color array
        int[] colors = new int[v];
        Arrays.fill(colors, 0); // 0 means no color assigned

        // Step 3: Use backtracking to solve
        boolean result = canColor(0, graph, colors, m);

        if (result) {
            System.out.println("Color assignment:");
            for (int i = 0; i < colors.length; i++) {
                System.out.println("Vertex " + i + ": Color " + colors[i]);
            }
        }

        return result;
    }

    private static boolean canColor(int node, List<Integer>[] graph, int[] colors, int m) {
        if (node == graph.length) {
            return true; // All vertices are colored successfully
        }

        for (int color = 1; color <= m; color++) {
            if (isSafeToColor(node, graph, colors, color)) {
                colors[node] = color;
                if (canColor(node + 1, graph, colors, m)) {
                    return true;
                }
                colors[node] = 0; // backtrack
            }
        }

        return false;
    }

    private static boolean isSafeToColor(int node, List<Integer>[] graph, int[] colors, int color) {
        for (int neighbor : graph[node]) {
            if (colors[neighbor] == color) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input
        System.out.print("Enter number of vertices: ");
        int v = scanner.nextInt();

        System.out.print("Enter number of edges: ");
        int e = scanner.nextInt();

        int[][] edges = new int[e][2];
        System.out.println("Enter each edge as two space-separated vertices (0-based index):");
        for (int i = 0; i < e; i++) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
        }

        System.out.print("Enter the number of colors (m): ");
        int m = scanner.nextInt();

        // Output result
        boolean canBeColored = graphColoring(edges, v, m);
        System.out.println("Graph can be colored with " + m + " colors: " + canBeColored);
    }
}
