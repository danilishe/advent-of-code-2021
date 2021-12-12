package org.example;

import java.util.*;

import static org.example.InputDataLoader.loadLines;

public class D12t1 {

    public static void main(String[] args) {
        final List<LinkedList<Node>> variants = getVariants(loadLines("d12t1"));
        System.out.println("variants.size() = " + variants.size());
        final List<LinkedList<Node>> withSmall = variants.stream()
                .filter(list -> list.stream()
                        .anyMatch(n -> n.isSmall() && !n.isEnd() && !n.isStart()))
                .toList();
        System.out.println("withSmall = " + withSmall.size());
    }

    private static List<LinkedList<Node>> getVariants(List<String> connections) {
        final Map<String, Node> nodeMap = parseNodeMap(connections);
        final LinkedList<Node> path = new LinkedList<>();
        path.add(nodeMap.get("start"));
        final List<LinkedList<Node>> paths = new ArrayList<>();
        getPath(path, paths);
        return paths.stream().filter(list -> list.contains(nodeMap.get("end"))).toList();
    }

    protected static Map<String, Node> parseNodeMap(List<String> connections) {
        final Map<String, Node> nodeMap = new HashMap<>();
        for (String nodeConnection : connections) {
            final String[] nodes = nodeConnection.split("-");
            nodeMap.computeIfAbsent(nodes[0], Node::new)
                    .connectTo(nodeMap.computeIfAbsent(nodes[1], Node::new));
        }
        return nodeMap;
    }

    private static void getPath(final LinkedList<Node> currentPath, final List<LinkedList<Node>> paths) {
        final Node currentNode = currentPath.peek();
        if (currentNode.isEnd()
                || currentPath.size() > 1 && currentNode.isSmall() && currentPath.subList(1, currentPath.size()).contains(currentNode)
                || currentPath.size() > 150) { // cycles prevention
            paths.add(currentPath);
            return;
        }
        for (Node node : currentNode.getConnectedTo()) {
            final LinkedList<Node> newBranch = new LinkedList<>(currentPath);
            newBranch.push(node);
            getPath(newBranch, paths);
        }
    }
}

class Node {
    public final String name;

    public Node(String name) {
        this.name = name;
    }

    private Set<Node> connectedTo = new HashSet<>();

    public Node connectTo(Node node) {
        connectedTo.add(node);
        node.connectedTo.add(this);
        return this;
    }

    public Set<Node> getConnectedTo() {
        return connectedTo;
    }

    public boolean isSmall() {
        return this.name.toLowerCase().equals(this.name);
    }

    public boolean isStart() {
        return "start".equals(this.name);
    }

    public boolean isEnd() {
        return "end".equals(this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
/*
* start,HN,dc,HN,end
start,HN,dc,HN,kj,HN,end
start,HN,dc,end
start,HN,dc,kj,HN,end
start,HN,end
start,HN,kj,HN,dc,HN,end
start,HN,kj,HN,dc,end
start,HN,kj,HN,end
start,HN,kj,dc,HN,end
start,HN,kj,dc,end
start,dc,HN,end
start,dc,HN,kj,HN,end
start,dc,end
start,dc,kj,HN,end
start,kj,HN,dc,HN,end
start,kj,HN,dc,end
start,kj,HN,end
start,kj,dc,HN,end
start,kj,dc,end
* */
