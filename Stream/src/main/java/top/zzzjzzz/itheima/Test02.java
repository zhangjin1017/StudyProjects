package top.zzzjzzz.itheima;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class Test02 {
    public static void main(String[] args) {
        //求所有年龄的总和
        Stream<Person> personStream =
                Stream.of(new Person("张三", 18),
                new Person("李四", 19),
                new Person("王五", 20));
//        System.out.println(personStream.map(Person::getAge).reduce(0, Integer::sum));

        //找出最大的年龄
        System.out.println(personStream.map(Person::getAge).reduce(0,Integer::max));

        //统计a出现的次数
        Stream<String> a = Stream.of("a", "c", "b", "a", "b", "a");
        System.out.println(a.map(s -> {
            if (s.equals("a")) return 1;
            else return 0;
        }).reduce(0, Integer::sum));



    }
}
