package algo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class TopoSort {
    public static void main(String[] args) {
        Graph<TopoNode> graph = new Graph<>();

        TopoNode n0 = new TopoNode(0);
        TopoNode n1 = new TopoNode(1);
        TopoNode n2 = new TopoNode(2);
        TopoNode n3 = new TopoNode(3);
        TopoNode n4 = new TopoNode(4);
        TopoNode n5 = new TopoNode(5);
        TopoNode n6 = new TopoNode(6);
        TopoNode n7 = new TopoNode(7);

        n0.adjacents = List.of(n1, n2);
        n1.adjacents = List.of(n3, n4);
        n2.adjacents = List.of(n1, n5);
        n3.adjacents = List.of(n5);
        n4.adjacents = List.of(n6);
        n5.adjacents = List.of(n6);

        graph.nodes = List.of(n0, n1, n2, n3, n4, n5, n6, n7);

        graph.nodes = sort(graph);
        graph.print();
    }

    private static List<TopoNode> sort(Graph<TopoNode> graph) {
        List<TopoNode> nodes = graph.nodes;

        for (TopoNode node : nodes) {
            for (TopoNode child : node.adjacents) {
                child.numInbound++;
            }
        }

        Queue<TopoNode> finalOrder = new LinkedList<>();
        Queue<TopoNode> processNext = nodes.stream().filter(topoNode -> topoNode.numInbound == 0)
                .collect(Collectors.toCollection(LinkedList::new));

        sort(processNext, finalOrder);
        return new ArrayList<>(finalOrder);
    }

    private static void sort(Queue<TopoNode> processNext, Queue<TopoNode> finalOrder) {
        while (!processNext.isEmpty()) {
            TopoNode node = processNext.poll();

            finalOrder.add(node);
            processChildren(processNext, node);
        }
    }

    private static void processChildren(Queue<TopoNode> processNext, TopoNode node) {
        for (TopoNode child : node.adjacents) {
            child.numInbound = child.numInbound - 1;
            if (child.numInbound == 0) {
                processNext.add(child);
            }
        }
    }
}

class TopoNode extends Node {
    int numInbound = 0;
    List<TopoNode> adjacents = new ArrayList<>();

    public TopoNode(Integer value) {
        super(value);
    }
}
