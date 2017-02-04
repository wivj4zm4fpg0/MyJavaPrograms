package ex9;

import java.util.ArrayList;

public class Dijkstra {
    private final static int INFINITY = 999; // これを無限大とする

    private class Node {
        int key;
        int label;
        boolean isFixed;
        ArrayList<Edge> edges = new ArrayList<Edge>();

        public Node(int key) {
            this.key = key;
            this.label = INFINITY;
            this.isFixed = false;
        }

        void addEdge(Edge edge) {
            edges.add(edge);
        }
    }

    private class Edge {
        Node to;    // 隣接するノード
        int weight; // エッジの重み

        Edge(Node to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private Node start;
    private static ArrayList<Node> nodes = new ArrayList<Node>();

    private Dijkstra() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.addEdge(new Edge(node2, 20));
        node1.addEdge(new Edge(node3, 10));
        node1.addEdge(new Edge(node4, 15));
        node2.addEdge(new Edge(node1, 20));
        node2.addEdge(new Edge(node4, 10));
        node3.addEdge(new Edge(node1, 10));
        node3.addEdge(new Edge(node4, 20));
        node3.addEdge(new Edge(node5, 10));
        node4.addEdge(new Edge(node1, 15));
        node4.addEdge(new Edge(node2, 10));
        node4.addEdge(new Edge(node3, 20));
        node5.addEdge(new Edge(node3, 10));
        node5.addEdge(new Edge(node4, 20));

        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);

        start = nodes.get(0);
        start.label = 0;
    }

    public static void main(String[] args) {
        Dijkstra graph = new Dijkstra();
        do {
            // ここを作る
            // ダイクストラ法のアルゴリズムを実装する
            // 全てのノードを確定ラベルにする
            int Flags = 0;
            graph.start.isFixed = true;
            int min = 0;
            int minLapel = graph.start.edges.get(min).weight;
            int list = graph.start.edges.size();
            for (int i = 0; i < list; i++) {
                int value = graph.start.edges.get(i).weight;
//                一時ラベル更新
                if (nodes.get(graph.start.edges.get(i).to.key - 1).label > value + graph.start.label) {
                    nodes.get(graph.start.edges.get(i).to.key - 1).label = value + graph.start.label;
                }
//                確定ラベル選択
                if ((minLapel > value) && (!nodes.get(graph.start.edges.get(i).to.key - 1).isFixed)) {
                    min = i;
                    minLapel = graph.start.edges.get(min).weight + graph.start.label;
                }
            }
            graph.start = nodes.get(graph.start.edges.get(min).to.key - 1);
//            確定ラベルの数を調べる
            for (Node node : nodes) {
                if (node.isFixed) {
                    Flags++;
                }
            }
//            一時ラベルが一つ残っていたとしてもそれを確定ラベルにして終了する
            if (Flags == nodes.size() - 1) {
                break;
            }
        } while (true);

        for (Node node : nodes) {
            System.out.println("Node" + node.key + ": " + node.label);
        }
    }
}
