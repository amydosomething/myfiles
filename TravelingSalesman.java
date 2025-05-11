import java.util.Scanner;

public class TravelingSalesman {
    static int[][] a = new int[10][10];
    static int[] visited = new int[10];
    static int n, cost = 0;

    // Method to get the input
    public static void get() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter No. of Cities: ");
        n = sc.nextInt();
        System.out.println("\nEnter Cost Matrix: ");
        for (int i = 0; i < n; i++) {
            System.out.println("\n Enter Elements of Row# : " + (i + 1));
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
            visited[i] = 0;
        }
        System.out.println("\n\nThe cost list is:\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("\t" + a[i][j]);
            }
            System.out.println();
        }
    }

    // Method to find the minimum cost path
    public static void mincost(int city) {
        visited[city] = 1;
        System.out.print((city + 1) + " --> ");
        int ncity = least(city);
        if (ncity == 999) {
            ncity = 0;
            System.out.print((ncity + 1));
            cost += a[city][ncity];
            return;
        }
        mincost(ncity);
    }

    // Method to find the least cost city
    public static int least(int c) {
        int i, nc = 999;
        int min = 999, kmin = 0;
        for (i = 0; i < n; i++) {
            if (a[c][i] != 0 && visited[i] == 0) {  // Check for unvisited cities
                if (a[c][i] < min) {
                    min = a[c][i];
                    kmin = a[c][i];
                    nc = i;
                }
            }
        }
        if (min != 999) {
            cost += kmin;
        }
        return nc;
    }

    // Method to print the minimum cost
    public static void put() {
        System.out.println("\n\nMinimum cost: " + cost);
    }

    public static void main(String[] args) {
        get();
        System.out.println("\n\nThe Path is:\n");
        mincost(0);  // Starting from city 0
        put();
    }
}
