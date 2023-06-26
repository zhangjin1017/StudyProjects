package top.zzzjzzz.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class Test05 {
    public static void main(String[] args) {
        Stream<String> a1 = Stream.of("a", "b", "c");
        String[] arr = {"a", "b", "c"};
        Stream<String> a2 = Stream.of(arr);
        Stream<String> a3 = Arrays.stream(arr);
ccc
    }
}
