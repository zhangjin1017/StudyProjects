package top.zzzjzzz.itheima;

import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test03 {
    public static void main(String[] args) {
//        IntStream intStream = Stream.of(1, 2, 3, 4, 5).mapToInt(Integer::intValue);
//        intStream.filter(n->n>3).forEach(System.out::println);
        Stream<String> streamA=Stream.of("zhangsan");
        Stream<String> streamB=Stream.of("lisi");
        Stream<String> concat = Stream.concat(streamA, streamB);
        concat.forEach(System.out::println);

    }
}
