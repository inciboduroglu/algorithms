package algo;

import java.util.*;

public class BFS {
    public static void main(String[] args) {
        Graph<Node> graph = setup();

        Node node = search(graph, 6);
        assert node != null;
        System.out.println(node.value);
    }

    public static Node search(Graph<Node> graph, int value) {
        Queue<Node> processNext = new LinkedList<>();
        List<Node> nodes = graph.nodes;
        Set<Integer> visited = new HashSet<>();
        processNext.add(nodes.get(0));

        for (Node node : nodes) {
            if (!visited.contains(node.value)) {
                processNext.add(node);
                Node found = search(value, processNext, visited);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    private static Node search(int value, Queue<Node> processNext, Set<Integer> visited) {
        while (!processNext.isEmpty()) {
            Node node = processNext.poll();

            // visit
            if (node.value == value) {
                return node;
            }
            visited.add(node.value);

            for (Node child : node.adjacents) {
                if (!visited.contains(child.value)) {
                    processNext.add(child);
                }
            }
        }
        return null;
    }

    public static Graph<Node> setup() {
        Graph<Node> graph = new Graph<>();

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