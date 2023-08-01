package algorithm.horseonboard;


import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 使用贪心算法优化骑士周游算法：
 * 1. 获取到下一步可以到达的位置的集合ps;
 * 2. 对ps中的所有的p的下一步可以到达的位置的集合的size,进行非递减(递增中允许数据重复出现)排序;
 * 如{1,2,2,3,3,4}
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-14 20:19
 */
public class HorseOnBoardDemo {
    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        int[][] chessboard = new int[Y][X];
        visited = new boolean[X * Y];
        travelChessBoard(chessboard, row - 1, column - 1, 1);
        //输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    private static int X;//棋盘的列数;
    private static int Y;//棋盘的行数;
    private static boolean[] visited;//创建数组标记棋盘的任意位置是否被访问过
    private static boolean finished;//标记骑士是否周游完成;

    /**
     * @param chessboard 棋盘
     * @param row        骑士所在的行
     * @param column     骑士所在的列
     * @param step       已走过的步数，将其标记在骑士当前所在位置上
     */
    public static void travelChessBoard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true;//注意visited[]是一维数组
        //获取下一步可走的位置的集合;
        ArrayList<Point> ps = next(new Point(column, row));
        //利用sort()优化回溯过程,优先选择下一步能到达的位置最少的
        sort(ps);
        //遍历ps并回溯
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);//取出一个位置;
            if (!visited[p.y * X + p.x]) {//判断此位置是否已被访问
                travelChessBoard(chessboard, p.y, p.x, step + 1);//未被访问才进行标记,进入当前位置的下一位置;
            }
        }
        //判断骑士是否完成任务
        //未完成的情形有以下两种
        //1. 骑士刚走完第一条路径,但未走满x * Y步; 此时step < X * Y && !finished;
        //2. 骑士至少已经走完一条路径,但始终未走满X * Y步, 需要回溯到上一条路径;即step < X * Y && !finished;
        if (step < X * Y && !finished) {//未完成
            chessboard[row][column] = 0;//将上一步所在的位置置零,相当于撤销上一步的操作,回退到上上一步;
            visited[row * X + column] = false;//同时将上一步所在的位置标记为未被访问;
        } else {//已完成;即step >= X * Y || finished
            finished = true;
        }
    }

    /**
     * 根据当前位置curPoint判断骑士下一步能走到哪些位置,最多8种,放入ArrayList
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //利用贪心算法优化骑士周游算法
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
