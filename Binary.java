package binaryTree;
import java.util.*;

public class Binary {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.right = null;
            this.left = null;
        }
    }

    static class BinaryTree {
        static int idx = -1;

        public static Node buildTree(int nodes[]) {
            idx++;
            if (idx >= nodes.length || nodes[idx] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    public static void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node currNode = q.remove();
            System.out.print(currNode.data + " ");

            if (currNode.left != null) {
                q.add(currNode.left);
            }
            if (currNode.right != null) {
                q.add(currNode.right);
            }
        }
    }

    public static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int leftNodes = countNodes(root.left);
        int rightNodes = countNodes(root.right);
        return leftNodes + rightNodes + 1;
    }

    public static int sumOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);
        return leftSum + rightSum + root.data;
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        int diam1 = diameter(root.left);
        int diam2 = diameter(root.right);
        int diam3 = height(root.left) + height(root.right) + 1;

        return Math.max(diam3, Math.max(diam1, diam2));
    }

    static class TreeInfo {
        int ht;
        int diam;

        TreeInfo(int ht, int diam) {
            this.ht = ht;
            this.diam = diam;
        }
    }

    public static TreeInfo diameter2(Node root) {
        if (root == null) {
            return new TreeInfo(0, 0);
        }
        TreeInfo left = diameter2(root.left);
        TreeInfo right = diameter2(root.right);

        int myHeight = Math.max(left.ht, right.ht) + 1;
        int diam1 = left.diam;
        int diam2 = right.diam;
        int diam3 = left.ht + right.ht + 1;

        int myDiam = Math.max(Math.max(diam1, diam2), diam3);

        return new TreeInfo(myHeight, myDiam);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int n = sc.nextInt();
        int[] nodes = new int[n];

        System.out.println("Enter the node values (-1 for null): ");
        for (int i = 0; i < n; i++) {
            nodes[i] = sc.nextInt();
        }

        BinaryTree tree = new BinaryTree();
        Node root = BinaryTree.buildTree(nodes);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Pre-order traversal");
            System.out.println("2. In-order traversal");
            System.out.println("3. Post-order traversal");
            System.out.println("4. Level-order traversal");
            System.out.println("5. Count nodes");
            System.out.println("6. Sum of nodes");
            System.out.println("7. Height of tree");
            System.out.println("8. Diameter of tree");
            System.out.println("9. Optimized diameter of tree");
            System.out.println("0. Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Pre-order traversal: ");
                    preorder(root);
                    System.out.println();
                    break;
                case 2:
                    System.out.print("In-order traversal: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Post-order traversal: ");
                    postOrder(root);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Level-order traversal: ");
                    levelOrder(root);
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Number of nodes: " + countNodes(root));
                    break;
                case 6:
                    System.out.println("Sum of nodes: " + sumOfNodes(root));
                    break;
                case 7:
                    System.out.println("Height of tree: " + height(root));
                    break;
                case 8:
                    System.out.println("Diameter of tree: " + diameter(root));
                    break;
                case 9:
                    System.out.println("Optimized diameter of tree: " + diameter2(root).diam);
                    break;
                case 0:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
