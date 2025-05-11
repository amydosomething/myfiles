import java.util.*;

public class MinMaxDC {

    // Inner class to hold the min and max values
    static class MinMax {
        int min;
        int max;

        MinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    // Method to find the minimum and maximum using Divide and Conquer
    public static MinMax findMinMax(int[] arr, int low, int high) {
        if (low == high) {
            return new MinMax(arr[low], arr[low]);
        }
        
        if (high == low + 1) {
            if (arr[low] < arr[high]) {
                return new MinMax(arr[low], arr[high]);
            } else {
                return new MinMax(arr[high], arr[low]);
            }
        }

        int mid = (low + high) / 2;
        MinMax leftMinMax = findMinMax(arr, low, mid);
        MinMax rightMinMax = findMinMax(arr, mid + 1, high);

        int overallMin = Math.min(leftMinMax.min, rightMinMax.min);
        int overallMax = Math.max(leftMinMax.max, rightMinMax.max);

        return new MinMax(overallMin, overallMax);
    }

    // Main method to take user input and find min and max
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the number of elements in the array:");
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        MinMax result = findMinMax(arr, 0, n - 1);
        System.out.println("Minimum element is: " + result.min);
        System.out.println("Maximum element is: " + result.max);
        
        sc.close();
    }
}