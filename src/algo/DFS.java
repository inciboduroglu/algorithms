package algo;

import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class DFS {
    public static void main(String[] args) {
        Graph graph = Graph.setup();

        Node node = search(graph, 6);
        assert node != null;
        System.out.println(node.value);
    }

    @Nullable
    public static Node search(Graph graph, int value) {
        Set<Integer> visited = new HashSet<>();

        for (Node node : graph.nodes) {
            if (!visited.contains(node.value)) {
                Node found = search(node, value, visited);
                if (found != null) {
                    return found;
                }
            }
        }

        return null;
    }

    @Nullable
    public static Node search(Node node, int value, Set<Integer> visited) {
        if (node.value == value) {
            return node;
        }
        visited.add(node.value);

        for (Node child : node.adjacents) {
            if (!visited.contains(child.value)) {
                Node found = search(child, value, visited);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}