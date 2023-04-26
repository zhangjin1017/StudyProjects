package top.zzzjzzz.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Test04 {
    public static void main(String[] args) {
        Map<String, Object> map=new HashMap<>();
        Stream<String> stream = map.keySet().stream();
        Stream<Object> stream1 = map.values().stream();
        Stream<Map.Entry<String, Object>> stream2 = map.entrySet().stream();

    }
}
