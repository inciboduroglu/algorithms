package algo;

import java.util.ArrayList;
import java.util.List;

public class Graph<T extends Node> {
    List<T> nodes;

    public void print() {
        nodes.forEach(node -> System.out.println(node.value));
    }
}

class Node {
    int value;
    List<Node> adjacents = new ArrayList<>();

    public Node(int value) {
        this.value = value;
    }
}