package top.zzzjzzz.itheima;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo02 {
    public static void main(String[] args) {
        Stream<String> aa = Stream.of("aa", "bb", "cc");
        List<String> collect = aa.collect(Collectors.toList());
        System.out.println(collect);

    }
}
