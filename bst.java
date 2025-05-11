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

    // Function to insert a new node with the given key
    static Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (root.key == key) {
            return root; // Key is already present, so we return the root
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }
        return root;
    }

    // Function to search for a key in the BST
    static Node search(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        if (key < root.key) {
            return search(root.left, key);
        }
        return search(root.right, key);
    }

    // Function to delete a node with key x from the BST
    static Node delNode(Node root, int x) {
        if (root == null) {
            return root;
        }
        if (x < root.key) {
            root.left = delNode(root.left, x);
        } else if (x > root.key) {
            root.right = delNode(root.right, x);
        } else {
            // Node to be deleted is found

            // Case 1: Node has no children or only right child
            if (root.left == null) {
                return root.right;
            }

            // Case 2: Node has only left child
            if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has both children
            Node succ = getSuccessor(root);
            root.key = succ.key;
            root.right = delNode(root.right, succ.key); // Delete the successor
        }
        return root;
    }

    // Function to find the inorder successor of a node
    static Node getSuccessor(Node curr) {
        curr = curr.right;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    // Utility function to do inorder traversal
    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    // Function to calculate the height of the tree
    static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Function to print nodes at a specific level
    static void printLevel(Node root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.key + " ");
        } else if (level > 1) {
            printLevel(root.left, level - 1);
            printLevel(root.right, level - 1);
        }
    }

    // Function to print level-order traversal
    static void levelOrder(Node root) {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            printLevel(root, i);
            System.out.println();
        }
    }

    // Function to mirror the tree
    static Node mirror(Node root) {
        if (root == null) {
            return root;
        }
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
        return root;
    }

    // Driver code
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
            System.out.println("\nChoose operation:\n1. Inorder Traversal\n2. Level-order Traversal\n3. Search\n4. Delete\n5. Mirror\n6. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Inorder traversal: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Level-order traversal:");
                    levelOrder(root);
                    break;
                case 3:
                    System.out.println("Enter value to search:");
                    int searchKey = sc.nextInt();
                    System.out.println(search(root, searchKey) != null ? "Found" : "Not Found");
                    break;
                case 4:
                    System.out.println("Enter value to delete:");
                    int keyToDelete = sc.nextInt();
                    root = delNode(root, keyToDelete);
                    System.out.print("Inorder traversal after deletion: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 5:
                    root = mirror(root);
                    System.out.print("Inorder traversal after mirroring: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}


