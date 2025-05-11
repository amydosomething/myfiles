import java.util.*;

class BellmanFord {
    static int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e8);
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                System.out.println("Graph contains a negative weight cycle");
                return new int[]{-1};
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        
        int[][] edges = new int[E][3];
        System.out.println("Enter edges (format: source destination weight):");
        for (int i = 0; i < E; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            edges[i][2] = sc.nextInt();
        }
        
        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();
        
        int[] result = bellmanFord(V, edges, src);
        
        if (result.length > 1) {
            System.out.println("Shortest distances from source node " + src + ":");
            for (int i = 0; i < result.length; i++) {
                System.out.println("Node " + i + " : " + (result[i] == 1e8 ? "INF" : result[i]));
            }
        }
        
        sc.close();
    }
}