package algorithm.dynamicprogramming;

import org.jetbrains.annotations.NotNull;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-11 16:57
 */
public class ItemsInBagDemo {
    public static void main(String[] args) {
//        myItemsInBag();
        itemsInBag();
    }

    //教程的写法：
    public static void itemsInBag() {
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};
        int m = 4;//容量
        int n = val.length;


        int[][] v = new int[n + 1][m + 1];
        //为了记录放入商品的情况，定义一个二维数组;
        int[][] path = new int[n + 1][m + 1];

        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //动态规划
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放进背包的情况，需要使用if-else结构
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //记录path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //打印结果
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }

        //打印放入商品的情况
        System.out.println();
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }


    //我的写法：
    public static void myItemsInBag() {
        v = new int[4];
        w = new int[4];
        Item guitar = new Item("guitar", 1500, 1);
        Item speaker = new Item("speaker", 3000, 4);
        Item laptop = new Item("laptop", 2000, 3);

        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(guitar);
        itemList.add(speaker);
        itemList.add(laptop);

        //以最小物品的重量为单位重量;
        weightUnit = guitar.weight;

        v[0] = 0;
        w[0] = 0;
        for (int i = 1; i < v.length; i++) {
            v[i] = itemList.get(i - 1).value;
            w[i] = itemList.get(i - 1).weight;
        }

        //创建vm时以物品件数+1为第一个参数，以背包总容量/单位重量 + 1为第二个参数;
        vw = new int[v.length][CAPACITY / weightUnit + 1];

        for (int i = 0; i < vw.length; i++) {
            vw[i][0] = 0;
        }
        for (int i = 0; i < vw[0].length; i++) {
            vw[0][i] = 0;
        }


        for (int i = 1; i < vw[0].length; i++) {
            for (int j = 1; j < vw.length; j++) {
                putItem(i, j);
            }
        }

        for (int i = 1; i < vw.length; i++) {
            for (int j = 1; j < vw[0].length; j++) {
                System.out.print(vw[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static int[] v;
    static int[] w;
    static int[][] vw;
    static int weightUnit;
    final static int CAPACITY = 4;

    public static void putItem(int capacityIndex, int itemIndex) {
        int capacity = capacityIndex * weightUnit;
        //1.先判断背包的剩余空间能否放下当前物品;
        if (capacity < w[itemIndex]) {
            //(1)放不下则不放
            vw[itemIndex][capacityIndex] = vw[itemIndex - 1][capacityIndex];
        } else {
            //(2)放得下则判断放过之后是否总价值低于不放的情形;
            int valueIfNotPut = vw[itemIndex - 1][capacityIndex];
            int valueIfPut = v[itemIndex] + vw[itemIndex - 1][capacityIndex - w[itemIndex]];
            if (valueIfPut <= valueIfNotPut) {
                //a.低于或等于,不放
                vw[itemIndex][capacityIndex] = valueIfNotPut;
            } else {
                //b.大于,放
                vw[itemIndex][capacityIndex] = valueIfPut;
            }
        }
    }
}

class Item {
    String name;
    int value;
    int weight;

    public Item(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }
}
