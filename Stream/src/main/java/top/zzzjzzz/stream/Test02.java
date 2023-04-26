package top.zzzjzzz.stream;

import java.util.Arrays;
import java.util.List;

public class Test02 {
    public static void main(String[] args) {
        //定义一个list集合
        List<String> list= Arrays.asList("张三","张锦","张三丰","王五");
        list.stream()
                .filter(s -> s.startsWith("张"))
                .filter(s -> s.length()==3)
                .forEach(System.out::println);
//                .forEach(s->System.out.println(s));

    }
}
