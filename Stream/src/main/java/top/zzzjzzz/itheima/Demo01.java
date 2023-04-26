package top.zzzjzzz.itheima;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Demo01 {
    public static void main(String[] args) {
        //第一个队伍
        List<String> one= Arrays.asList("迪丽热巴", "宋远桥","苏星河","老子","庄子","孙子","洪七公");
        //第二个队伍
        List<String> two=Arrays.asList("古力娜扎", "张无忌","张三丰","赵丽颖","张二狗","张天爱","张三");

        //1、第一个队伍只要名字为3个字的成员姓名
        one.stream().filter(n->n.length()==3).forEach(System.out::print);
        System.out.println();
        //2、第一个队伍筛选之后只要前3人
        one.stream().limit(3).forEach(System.out::print);
        System.out.println();
        //3、第二个队伍只要姓张的成员姓名
        two.stream().filter(n->n.startsWith("张")).forEach(System.out::print);
        System.out.println();
        //4、第二个队伍筛选之后不要前2人
        two.stream().skip(2).forEach(System.out::print);
        System.out.println();
        //5、将两个队伍合并为一个队伍
        Stream<String> limitA = one.stream().filter(n -> n.length() == 3).limit(3);
        Stream<String> limitB = two.stream().filter(n -> n.startsWith("张")).skip(2);
        Stream<String> three = Stream.concat(limitA, limitB);
//        three.forEach(System.out::print);
        System.out.println();
        //6、根据姓名创建Person对象
        Stream<Demo01Person> demo01PersonStream = three.map(Demo01Person::new);
        //7、打印整个队伍的Person对象信息
        demo01PersonStream.forEach(System.out::print);


    }
}
