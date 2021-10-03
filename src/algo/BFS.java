package algo;

import java.util.*;

public class BFS {
    public static void main(String[] args) {
        Graph graph = Graph.setup();

        Node node = search(graph, 6);
        assert node != null;
        System.out.println(node.value);
    }

    public static Node search(Graph graph, int value) {
        Queue<Node> processNext = new LinkedList<>();
        List<Node> nodes = graph.nodes;
        Set<Integer> visited = new HashSet<>();
        processNext.add(nodes.get(0));

        for (Node node : nodes) {
            if (!visited.contains(node.value)) {
                processNext.add(node);
                Node found = search(graph, value, processNext, visited);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    private static Node search(Graph graph, int value, Queue<Node> processNext, Set<Integer> visited) {
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
}