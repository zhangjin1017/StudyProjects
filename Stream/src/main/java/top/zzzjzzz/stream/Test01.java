package top.zzzjzzz.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        //定义一个list集合
        List<String> list= Arrays.asList("张三","张锦","张三丰","王五");
        //1.获取所有姓张的信息
        List<String> list1=new ArrayList<>();
        for (String s:list
             ) {
            if (s.startsWith("张")){
                list1.add(s);
            }
        }
        //2.获取长度为3的用户
        List<String> list2=new ArrayList<>();
        for (String s:list1
             ) {
            if (s.length()==3){
                list2.add(s);
            }
        }
        for (String s:list1
             ) {
            System.out.println(s);
        }
        System.out.println("-----");
        for (String s:list2
        ) {
            System.out.println(s);
        }
    }
}
