import java.util.*;

class Item {
    float weight;
    int value;

    Item(float weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

class Node {
    int level, profit, bound;
    float weight;
    List<Integer> itemsIncluded; // Track selected items

    Node(int level, int profit, float weight, List<Integer> itemsIncluded) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.itemsIncluded = new ArrayList<>(itemsIncluded);
    }
}

public class KnapsackBranchAndBound {
    static Comparator<Item> itemComparator = (a, b) -> {
        double ratio1 = (double) a.value / a.weight;
        double ratio2 = (double) b.value / b.weight;
        return Double.compare(ratio2, ratio1); // descending order
    };

    static int bound(Node u, int n, int W, Item[] arr) {
        if (u.weight >= W) return 0;

        int profitBound = u.profit;
        int j = u.level + 1;
        float totalWeight = u.weight;

        while (j < n && totalWeight + arr[j].weight <= W) {
            totalWeight += arr[j].weight;
            profitBound += arr[j].value;
            j++;
        }

        if (j < n)
            profitBound += (int) ((W - totalWeight) * arr[j].value / arr[j].weight);

        return profitBound;
    }

    static int knapsack(int W, Item[] arr, int n, List<Integer> result) {
        Arrays.sort(arr, itemComparator);
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.bound, a.bound));

        Node u = new Node(-1, 0, 0, new ArrayList<>());
        Node v;
        pq.offer(u);

        int maxProfit = 0;

        while (!pq.isEmpty()) {
            u = pq.poll();

            if (u.level == n - 1)
                continue;

            int nextLevel = u.level + 1;

            // Include item
            v = new Node(nextLevel, u.profit, u.weight, u.itemsIncluded);
            v.weight += arr[nextLevel].weight;
            v.profit += arr[nextLevel].value;
            v.itemsIncluded.add(nextLevel);

            if (v.weight <= W && v.profit > maxProfit) {
                maxProfit = v.profit;
                result.clear();
                result.addAll(v.itemsIncluded);
            }

            v.bound = bound(v, n, W, arr);
            if (v.bound > maxProfit)
                pq.offer(v);

            // Exclude item
            v = new Node(nextLevel, u.profit, u.weight, u.itemsIncluded);
            v.bound = bound(v, n, W, arr);
            if (v.bound > maxProfit)
                pq.offer(v);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter knapsack capacity: ");
        int W = scanner.nextInt();

        System.out.print("Enter number of items: ");
        int n = scanner.nextInt();

        Item[] arr = new Item[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight of item " + (i + 1) + ": ");
            float weight = scanner.nextFloat();
            System.out.print("Enter value of item " + (i + 1) + ": ");
            int value = scanner.nextInt();
            arr[i] = new Item(weight, value);
        }

        List<Integer> itemsChosen = new ArrayList<>();
        int maxProfit = knapsack(W, arr, n, itemsChosen);

        System.out.println("\nMaximum possible profit = " + maxProfit);
        System.out.println("Items included (0-based indices after sorting):");
        for (int idx : itemsChosen) {
            System.out.printf("Item %d -> Weight: %.2f, Value: %d\n", idx + 1, arr[idx].weight, arr[idx].value);
        }
    }
}
