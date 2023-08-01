package algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪婪算法演示
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-12 21:17
 */
public class GreedyDemo {
    public static void main(String[] args) {
        //创建广播电台，放入Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //放入电台
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入Map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas存放所有地区;
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建ArrayList,存放选择的电台集合;
        ArrayList<String> selects = new ArrayList<>();

        //创建一个临时的集合，用于存放遍历过程中的电台和尚未被覆盖地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义maxKey，保存在一次遍历过程中能够覆盖的最大未覆盖地区的电台的key
        //如果maxKey != null,则将其放入selects中;
        String maxKey;
        while (allAreas.size() != 0) {
            //每次while循环置空一次maxKey
            maxKey = null;
            //遍历broadcasts，取出对应的key
            for (String key : broadcasts.keySet()) {
                //每次for循环清空tempSet;
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas的交集;赋给tempSet;
                tempSet.retainAll(allAreas);
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            //如果maxKey != null，则将其加入selects
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区从allAreas中去除;
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的结果是" + selects);//
    }
}
