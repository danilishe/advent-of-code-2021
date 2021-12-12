package org.example;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class D12t2 {

    public static void main(String[] args) {
        final List<LinkedList<Node>> variants = getVariants(load());
        System.out.println("variants = " + String.join("\n", variants.stream().map(List::toString).toList()));
        System.out.println("variants.size() = " + variants.size());
        final List<LinkedList<Node>> withSmall = variants.stream()
                .filter(list -> list.stream()
                        .anyMatch(n -> n.isSmall() && !n.isEnd() && !n.isStart()))
                .toList();
        System.out.println("withSmall = " + withSmall.size());
    }

    @SneakyThrows
    private static List<String> load() {
        return Files.readAllLines(Paths.get("input/d12t1.txt"));
    }

    private static List<LinkedList<Node>> getVariants(List<String> connections) {
        final Map<String, Node> nodeMap = D12t1.parseNodeMap(connections);
        final LinkedList<Node> path = new LinkedList<>();
        path.add(nodeMap.get("start"));
        final List<LinkedList<Node>> paths = new ArrayList<>();
        getPath(path, paths);
        return paths.stream().filter(list -> list.contains(nodeMap.get("end"))).toList();
    }


    private static void getPath(final LinkedList<Node> currentPath, final List<LinkedList<Node>> paths) {
        final Node currentNode = currentPath.peekLast();
        if (currentNode.isEnd()
                || currentPath.size() > 1 && currentNode.isStart()
                || currentNode.isSmall() && moreThanTwoSmallVisitedTwice(currentPath)
                || currentPath.size() > 150) { // cycles prevention
            paths.add(currentPath);
            return;
        }
        for (Node node : currentNode.getConnectedTo()) {
            final LinkedList<Node> newBranch = new LinkedList<>(currentPath);
            newBranch.add(node);
            getPath(newBranch, paths);
        }
    }

    private static boolean moreThanTwoSmallVisitedTwice(LinkedList<Node> currentPath) {
        final Set<Map.Entry<String, List<Node>>> entries = currentPath.stream()
                .filter(Node::isSmall)
                .collect(Collectors.groupingBy(n -> n.name))
                .entrySet();
        return entries.stream()
                    .filter(entry -> entry.getValue().size() > 1)
                    .count() > 1
                || entries.stream().anyMatch(entry -> entry.getValue().size() > 2);
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
