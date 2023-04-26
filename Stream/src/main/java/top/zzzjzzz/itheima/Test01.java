package top.zzzjzzz.itheima;

import java.util.stream.Stream;

public class Test01 {
    public static void main(String[] args) {

//        Stream<String> stream= Stream.of("张三", "张锦", "张三丰", "王五");
//        System.out.println(stream.count());
//        stream.forEach(System.out::println);
//        stream.filter(s -> s.startsWith("张"))
//                .filter(s -> s.length()==3)
//                .forEach(System.out::println);
//        stream.limit(3).forEach(System.out::println);
//        stream.skip(2).forEach(System.out::println);

//        Stream<String> stream= Stream.of("11", "22", "33", "44");
//        stream.map(Integer::parseInt).forEach(System.out::println);

        Stream<Integer> stream= Stream.of(25, 25, 34, 45);
//        stream.sorted().forEach(System.out::println);
//        stream.sorted((a,b)->b-a).forEach(System.out::println);
//        stream.distinct().forEach(System.out::println);
        //对自定义类型去重  要重写equals和hashcode方法

//        System.out.println(stream.allMatch(s -> s > 30));

//        stream.findFirst().ifPresent(System.out::println);
//        stream.findAny().ifPresent(System.out::println);

//        stream.max(Integer::compareTo).ifPresent(System.out::println);
//        stream.min(Integer::compareTo).ifPresent(System.out::println);



    }
}
