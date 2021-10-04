package algo;

import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        Graph<DNode> graph = new Graph<>();

        DNode n0 = new DNode(0);
        DNode n1 = new DNode(1);
        DNode n2 = new DNode(2);
        DNode n3 = new DNode(3);
        DNode n4 = new DNode(4);
        DNode n5 = new DNode(5);
        DNode n6 = new DNode(6);
        DNode n7 = new DNode(7);
        DNode n8 = new DNode(8);

        n0.adjacents = Map.of(n1, 5, n2, 3, n7, 2);
        n1.adjacents = Map.of(n3, 2);
        n2.adjacents = Map.of(n3, 1);
        n3.adjacents = Map.of(n0, 1, n5, 2, n6, 1);
        n4.adjacents = Map.of(n5, 1);
        n5.adjacents = Map.of(n2, 3, n8, 2);
        n6.adjacents = Map.of(n2, 2, n4, 2, n5, 2);
        n7.adjacents = Map.of(n0, 1, n6, 4, n8, 7);

        graph.nodes = List.of(n0, n1, n2, n3, n4, n5, n6, n7, n8);

        int pathLen = findPathLen(n0, n8);
        System.out.println(pathLen);
//        for (DNode node : graph.nodes) {
//            node.print();
//        }
    }

    static int findPathLen(DNode start, DNode end) {
        PriorityQueue<DNode> priorityQueue = new PriorityQueue<>(new DNodeComparator());

        start.pathLen = 0;
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            DNode node = priorityQueue.poll();

            for (Map.Entry<DNode, Integer> adj : node.adjacents.entrySet()) {
                int newPathLen = node.pathLen + adj.getValue();
                DNode child = adj.getKey();

                if (newPathLen < child.pathLen) {
                    child.pathLen = newPathLen;
                    child.previous = node;
                    priorityQueue.add(child);
                }
            }
        }
        return end.pathLen;
    }
}

class DNodeComparator implements Comparator<DNode> {
    @Override
    public int compare(DNode o1, DNode o2) {
        return o1.pathLen - o2.pathLen;
    }
}

class DNode extends Node {
    Map<DNode, Integer> adjacents = new HashMap<>();
    DNode previous;
    int pathLen = Integer.MAX_VALUE;

    public DNode(int value) {
        super(value);
    }

    public void print() {
        System.out.println("value: " + value);
        System.out.println("pathLen: " + pathLen);
        System.out.println();
    }
}
