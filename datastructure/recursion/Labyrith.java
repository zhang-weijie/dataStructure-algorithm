package datastructure.recursion;

import java.util.regex.Matcher;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-15 9:57
 */
public class Labyrith {
    public static void main(String[] args) {
        Map map = new Map(8, 7);
        map.setWay();
        map.showChess();
    }

}

class Map {
    private int[][] chess;
    private Ball ball = new Ball();

    public Map(int m, int n) {
        this.chess = new int[m][n];
        //初始化迷宫的围墙和内墙，例如：
//      j 0 1 2 3 4 5 6
//    i 0 1 1 1 1 1 1 1
//      1 1 0 0 0 0 0 1
//      2 1 0 0 0 0 0 1
//      3 1 1 1 0 0 0 1
//      4 1 0 0 0 0 0 1
//      5 1 0 0 0 0 0 1
//      6 1 0 0 0 0 0 1
//      7 1 1 1 1 1 1 1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == 7) {
                    chess[i][j] = 1;
                } else if (j == 0 || j == 6) {
                    chess[i][j] = 1;
                } else if (i == 3 && (j == 1 || j == 2)) {
                    chess[i][j] = 1;
                } else {
                    chess[i][j] = 0;
                }
            }
        }
    }


    public void setWay() {
        //判断递归结束：小球已经到达右下角,即(m-2,n-2)处
        if (chess[chess.length - 2][chess[0].length - 2] == 2) {
            return;
        }

        int direction = 0;
        int x = ball.getX();
        int y = ball.getY();
        //优先级：下、右、上、左
        if (chess[y + 1][x] != 1) {
            direction = 1;
        } else if (chess[y][x + 1] != 1) {
            direction = 2;
        } else if (chess[y][x - 1] != 1) {
            direction = 3;
        } else if (chess[y - 1][x] != 1) {
            direction = 4;
        } else {
            throw new RuntimeException("小球被困住了！");
        }
        //显示小球当前位置
        ball.show();

        //将小球当前所在位置的值改为2，
        chess[y][x] = 2;

        //移动小球
        ball.move(direction);

        //递归调用setWay();
        setWay();
    }

    public void showChess() {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class Ball {
    //注意小球初始化位置是(1,1),而非(0,0)
    //注意x是横坐标，在n方向上移动,y是纵坐标，在m方向上移动
    private int x = 1;
    private int y = 1;

    public void move(int direction) {//根据传入的数字1,2,3,4分别进行向下、右、上、左移动一格
        switch (direction) {
            case 1:
                y++;
                break;
            case 2:
                x++;
                break;
            case 3:
                y--;
                break;
            case 4:
                x--;
                break;
        }
    }

    public void show() {
        System.out.printf("x = %d, y = %d\n", x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}