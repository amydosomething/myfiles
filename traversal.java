import java.util.*;

class traversal {

    // BFS function
    static void bfs(List<List<Integer>> adj, int s) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adj.size()];
        
        visited[s] = true;
        q.add(s);
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            System.out.print(curr + " ");
            
            for (int x : adj.get(curr)) {
                if (!visited[x]) {
                    visited[x] = true;
                    q.add(x);
                }
            }
        }
    }

    // Recursive DFS function
    static void DFSRec(List<List<Integer>> adj, boolean[] visited, int s) {
        visited[s] = true;
        System.out.print(s + " ");
        
        for (int i : adj.get(s)) {
            if (!visited[i]) {
                DFSRec(adj, visited, i);
            }
        }
    }

    // Main DFS function
    static void DFS(List<List<Integer>> adj, int s) {
        boolean[] visited = new boolean[adj.size()];
        DFSRec(adj, visited, s);
    }

    // Function to add an edge to the adjacency list
    static void addEdge(List<List<Integer>> adj, int s, int t) {
        adj.get(s).add(t);
        adj.get(t).add(s);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter number of vertices:");
        int V = sc.nextInt();
        
        List<List<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        System.out.println("Enter number of edges:");
        int E = sc.nextInt();
        
        System.out.println("Enter edges (format: u v):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(adj, u, v);
        }
        
        System.out.println("Enter source vertex:");
        int source = sc.nextInt();
        
        System.out.println("BFS from source: " + source);
        bfs(adj, source);
        
        System.out.println("\nDFS from source: " + source);
        DFS(adj, source);
        
        sc.close();
    }
}
