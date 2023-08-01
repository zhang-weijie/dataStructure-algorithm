package datastructure.recursion;

import java.util.ArrayList;

/**
 * 迷宫最短路径问题;
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-15 14:23
 */
public class LabyrithFinal {

    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;
        map[3][3] = 1;
        map[2][3] = 1;

        //创建4个bias对象
        Bias b1 = new Bias(0, 1);
        Bias b2 = new Bias(0, -1);
        Bias b3 = new Bias(1, 0);
        Bias b4 = new Bias(-1, 0);
        //组成一个列表
        ArrayList<Bias> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        //利用Permutation中的permute方法进行排列
        ArrayList<Bias> target = new ArrayList<>();
        Permutation.permute(list, target, list.size());


        int count = 1;//用于给不同的策略进行编号;
        for (int i = 0; i < Permutation.lists.size(); i++) {
            //复制一份map用于操作，否则循环过程中map会改变;
            int[][] copyMap = copy(map);
            System.out.printf("------第%d种策略------\n", count++);
            setWay(copyMap, 1, 1, Permutation.lists.get(i));
            showMap(copyMap);
            System.out.printf("------共需%d步------\n", steps);
            steps = 0;
        }
    }

    static int steps = 0;//用于统计不同策略下小球走过的路径长度;


    //创建一个静态方法用来复制map
    public static int[][] copy(int[][] map) {
        int[][] copyMap = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }

    //创建一个静态方法用来打印地图
    public static void showMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //1.map表示地图
    //2.i,j表示从地图的哪个位置出发，例如(1,1);
    //3.如果小球能到map[6][5]则说明通路找到
    //4.约定，当map[i][j] 为0表示该点没有走过，1表示墙，2表示通路可以走，3表示该点已经路过，但走不通
    //5.在走迷宫时，需要确定一个策略，例如下-右-上-左,如果该点走不通，再回溯;
    //6.可以修改策略找出最短路径：4*3*2*1 = 24种策略
    public static boolean setWay(int[][] map, int i, int j, ArrayList<Bias> strategy) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;//假定该点可以走通;
                if (setWay(map, i + strategy.get(0).getX(), j + strategy.get(0).getY(), strategy)) {//尝试往下走
                    steps++;
                    return true;
                } else if (setWay(map, i + strategy.get(1).getX(), j + strategy.get(1).getY(), strategy)) {//尝试往右走
                    steps++;
                    return true;
                } else if (setWay(map, i + strategy.get(2).getX(), j + strategy.get(2).getY(), strategy)) {//尝试往上走
                    steps++;
                    return true;
                } else if (setWay(map, i + strategy.get(3).getX(), j + strategy.get(3).getY(), strategy)) {//尝试往左走
                    steps++;
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j] != 0, 则可能是1,2,3
                return false;
            }
        }
    }
}

class Bias {
    private int x;
    private int y;

    public Bias(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}