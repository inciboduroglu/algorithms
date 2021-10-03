package algo;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<Node> nodes;

    public static Graph setup() {
        Graph graph = new Graph();

        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n0.adjacents = List.of(n1, n4, n5);
        n1.adjacents = List.of(n3, n4);
        n2.adjacents = List.of(n1);
        n3.adjacents = List.of(n2, n4);

        graph.nodes = List.of(n0, n1, n2, n3, n4, n5, n6);
        return graph;
    }
}

class Node {
    int value;
    List<Node> adjacents = new ArrayList<>();

    public Node(int value) {
        this.value = value;
    }
}