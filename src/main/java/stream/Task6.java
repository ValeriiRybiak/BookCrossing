package stream;

import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task6 {
    public static void realization(Data data) {
        Map<String, Integer> collect = data
                .getNodes()
                .stream()
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
