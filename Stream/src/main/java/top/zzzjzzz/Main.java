package top.zzzjzzz;

import java.util.*;

// 按两次 ⇧ 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) {
        List<Map<String,Object>> list = new ArrayList<>();
            Map<String,Object> map1 = new HashMap<>();
            map1.put("name","张三");
            map1.put("sex","男");
            Map<String,Object> map2 = new HashMap<>();
            map2.put("name","李四");
            map2.put("sex","男");
            Map<String,Object> map3 = new HashMap<>();
            map3.put("name","王五");
            map3.put("sex","男");
            list.add(map1);
            list.add(map2);
            list.add(map3);
        boolean b = list.stream().anyMatch(s -> s.equals("a"));
        System.out.println(b);
    }

}
