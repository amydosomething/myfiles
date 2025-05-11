import java.util.*;

class BusinessPrimsMST {
    private int numOffices; // Number of offices (vertices)

    public BusinessPrimsMST(int numOffices) {
        this.numOffices = numOffices;
    }

    int findMinCostOffice(int cost[], Boolean connectedOffices[]) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int office = 0; office < numOffices; office++) {
            if (!connectedOffices[office] && cost[office] < min) {
                min = cost[office];
                minIndex = office;
            }
        }
        return minIndex;
    }

    void displayOptimalNetwork(int parent[], int rentMatrix[][]) {
        System.out.println("\nOptimal Office Connection Network:");
        System.out.println("Office A - Office B \tRent");
        int totalRent = 0;
        for (int i = 1; i < numOffices; i++) {
            System.out.println(parent[i] + " - " + i + "\t\t" + rentMatrix[parent[i]][i]);
            totalRent += rentMatrix[parent[i]][i];
        }
        System.out.println("Total Minimum Rent: " + totalRent);
    }

    void computeMinimumNetwork(int rentMatrix[][]) {
        int parent[] = new int[numOffices]; // Stores the MST structure (parent of each office)
        int cost[] = new int[numOffices]; // Stores the minimum rent for each office
        Boolean connectedOffices[] = new Boolean[numOffices]; // Tracks connected offices

        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(connectedOffices, false);

        cost[0] = 0; // Start with the first office (node 0)
        parent[0] = -1; // The first office is the root (no parent)

        System.out.println("Step-by-step execution of Prim’s Algorithm:\n");

        for (int count = 0; count < numOffices - 1; count++) {
            int u = findMinCostOffice(cost, connectedOffices);
            connectedOffices[u] = true; // Include the selected office in the network

            // Print the current state of arrays
            System.out.println("Step " + (count + 1) + ": Connecting office " + u);
            System.out.println("Connected Offices: " + Arrays.toString(connectedOffices));
            System.out.println("Rent Cost Array: " + Arrays.toString(cost));
            System.out.println("Parent Array: " + Arrays.toString(parent));
            System.out.println("---------------------------------------");

            for (int v = 0; v < numOffices; v++) {
                if (rentMatrix[u][v] != 0 && !connectedOffices[v] && rentMatrix[u][v] < cost[v]) {
                    parent[v] = u;
                    cost[v] = rentMatrix[u][v];
                    System.out.println("Updating office " + v + ": Connected to " + u + ", Rent = " + rentMatrix[u][v]);
                }
            }
            System.out.println();
        }
        
        displayOptimalNetwork(parent, rentMatrix);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of offices: ");
        int numOffices = scanner.nextInt();
        
        BusinessPrimsMST network = new BusinessPrimsMST(numOffices);
        int rentMatrix[][] = new int[numOffices][numOffices];
        
        System.out.println("Enter the rent matrix (" + numOffices + "x" + numOffices + "):");
        for (int i = 0; i < numOffices; i++) {
            for (int j = 0; j < numOffices; j++) {
                rentMatrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        
        network.computeMinimumNetwork(rentMatrix);
    }
}
