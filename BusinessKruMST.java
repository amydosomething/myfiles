import java.util.*;

class BusinessKruMST {
    public static int kruskalsMST(int numCities, int[][] phoneLines) {
        // Sort all phone line connections by rent
        Arrays.sort(phoneLines, Comparator.comparingInt(line -> line[2]));
        System.out.println("Phone lines sorted by rent:");
        for (int[] line : phoneLines) {
            System.out.println(Arrays.toString(line));
        }
        
        Network network = new Network(numCities);
        int totalCost = 0, connections = 0;
        System.out.println("\nProcessing phone lines:");
        
        for (int[] line : phoneLines) {
            int cityA = line[0], cityB = line[1], rent = line[2];
            
            // Check if adding this connection creates a cycle
            if (network.find(cityA) != network.find(cityB)) {
                network.union(cityA, cityB);
                totalCost += rent;
                System.out.println("Connected: " + cityA + " - " + cityB + " (Rent: " + rent + ")");
                if (++connections == numCities - 1) break;
            } else {
                System.out.println("Skipping: " + cityA + " - " + cityB + " (Rent: " + rent + ") due to cycle");
            }
        }
        
        System.out.println("\nMinimum Total Rent to Connect All Offices: " + totalCost);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of cities: ");
        int numCities = scanner.nextInt();

        System.out.print("Enter number of phone line connections: ");
        int numLines = scanner.nextInt();
        
        int[][] phoneLines = new int[numLines][3];
        System.out.println("Enter phone line connections in format: cityA cityB rent");
        for (int i = 0; i < numLines; i++) {
            phoneLines[i][0] = scanner.nextInt();
            phoneLines[i][1] = scanner.nextInt();
            phoneLines[i][2] = scanner.nextInt();
        }
        
        kruskalsMST(numCities, phoneLines);
    }
}

// Disjoint Set Union (DSU) for tracking connected offices
class Network {
    private int[] parent, rank;

    public Network(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int office) {
        if (parent[office] != office) {
            parent[office] = find(parent[office]);
        }
        return parent[office];
    }

    public void union(int officeA, int officeB) {
        int rootA = find(officeA);
        int rootB = find(officeB);
        if (rootA != rootB) {
            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
        }
    }
}
