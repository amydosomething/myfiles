import java.util.Scanner;

public class NQueens {
    static int[] board = new int[20];
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println(" - N Queens Problem Using Backtracking -");
        System.out.print("\nEnter number of Queens: ");
        int n = sc.nextInt();
        
        queen(1, n);
    }

    // Function for printing the solution
    public static void print(int n) {
        System.out.println("\n\nSolution " + (++count) + ":\n");

        for (int i = 1; i <= n; i++)
            System.out.print("\t" + i);

        for (int i = 1; i <= n; i++) {
            System.out.print("\n\n" + i);
            for (int j = 1; j <= n; j++) { // For nxn board
                if (board[i] == j)
                    System.out.print("\tQ"); // Queen at i,j position
                else
                    System.out.print("\t-"); // Empty slot
            }
        }
    }

    // Function to check for conflicts
    // If no conflict for desired position, return 1; otherwise return 0
    public static int place(int row, int column) {
        for (int i = 1; i <= row - 1; i++) {
            // Checking column and diagonal conflicts
            if (board[i] == column)
                return 0;
            else if (Math.abs(board[i] - column) == Math.abs(i - row))
                return 0;
        }
        return 1; // No conflicts
    }

    // Function to check for proper positioning of queen
    public static void queen(int row, int n) {
        int column;
        for (column = 1; column <= n; column++) {
            if (place(row, column) == 1) {
                board[row] = column; // No conflicts, so place queen
                if (row == n) // Dead end
                    print(n); // Print the board configuration
                else // Try queen with next position
                    queen(row + 1, n);
            }
        }
    }
}
