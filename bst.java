import java.util.Scanner;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class bst {

    // Insert a new node with the given key
    static Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    // Search for a key in the BST
    static Node search(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        if (key < root.key) {
            return search(root.left, key);
        }
        return search(root.right, key);
    }

    // Delete a node with the given key
    static Node delNode(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = delNode(root.left, key);
        } else if (key > root.key) {
            root.right = delNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            Node succ = getSuccessor(root);
            root.key = succ.key;
            root.right = delNode(root.right, succ.key);
        }
        return root;
    }

    // Find inorder successor
    static Node getSuccessor(Node curr) {
        curr = curr.right;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    // Inorder traversal
    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    // Tree height
    static int height(Node root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    // Print nodes at a specific level
    static void printLevel(Node root, int level) {
        if (root == null) return;
        if (level == 1) {
            System.out.print(root.key + " ");
        } else {
            printLevel(root.left, level - 1);
            printLevel(root.right, level - 1);
        }
    }

    // Level-order traversal
    static void levelOrder(Node root) {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            printLevel(root, i);
            System.out.println();
        }
    }

    // Mirror the tree
    static Node mirror(Node root) {
        if (root == null) return root;
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
        return root;
    }

    // Main driver method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node root = null;

        System.out.println("Enter number of nodes:");
        int n = sc.nextInt();
        System.out.println("Enter the node values:");
        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();
            root = insert(root, value);
        }

        while (true) {
            System.out.println("\nChoose operation:");
            System.out.println("1. Insert");
            System.out.println("2. Inorder Traversal");
            System.out.println("3. Level-order Traversal");
            System.out.println("4. Search");
            System.out.println("5. Delete");
            System.out.println("6. Mirror");
            System.out.println("7. Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter value to insert:");
                    int value = sc.nextInt();
                    root = insert(root, value);
                    System.out.print("Inorder traversal after insert: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Inorder traversal: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Level-order traversal:");
                    levelOrder(root);
                    break;
                case 4:
                    System.out.println("Enter value to search:");
                    int searchKey = sc.nextInt();
                    System.out.println(search(root, searchKey) != null ? "Found" : "Not Found");
                    break;
                case 5:
                    System.out.println("Enter value to delete:");
                    int keyToDelete = sc.nextInt();
                    root = delNode(root, keyToDelete);
                    System.out.print("Inorder traversal after deletion: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 6:
                    root = mirror(root);
                    System.out.print("Inorder traversal after mirroring: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
