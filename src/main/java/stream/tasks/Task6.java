package stream.tasks;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Task6 {
    public static void realization(Data data) {
               var collect = data
                .getNodes()
                .stream()
                .collect(groupingBy(Node::getName, mapping(Node::getIndex, toList())));

        System.out.println(collect);
    }

    public static void realization(List<Data> data) {
        Map<String, Integer> collect = data
                .stream()
                .flatMap(x -> x.getNodes().stream())
                .distinct()
                .collect(Collectors.toMap(Node::getName, Node::getIndex));

        System.out.println(collect);
    }
}

@lombok.Data
class Data {
    @NonNull
    private List<Node> nodes;
}

@lombok.Data
class Node {
    @NonNull
    private int index;
    @NonNull
    private String name;
}
