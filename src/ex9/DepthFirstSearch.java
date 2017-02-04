package ex9;

import java.util.ArrayList;

public class DepthFirstSearch {
    private class Node {
        int label;
        ArrayList<Node> children = new ArrayList<>();

        public Node(int label) {
            this.label = label;
        }

        void addChild(Node child) {
            children.add(child);
        }

        private void Print(Node node) {
            System.out.println(node.label);
            for (Node childNode : node.children) {
                Print(childNode);
            }
        }
    }

    private Node root;

    private DepthFirstSearch() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.addChild(node2);
        node1.addChild(node3);
        node1.addChild(node4);
        node2.addChild(node5);
        node3.addChild(node6);
        node3.addChild(node7);

        root = node1;
    }

    public static void main(String[] args) {
        DepthFirstSearch dfs = new DepthFirstSearch();

        Node node = dfs.root;
        node.Print(node);
    }

}
