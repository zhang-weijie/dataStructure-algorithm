package datastructure.recursion;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-17 9:19
 */
public class Queen8Final {
    //定义一个max表示棋子的数量
    int max = 8;
    //定义数组array,存放皇后的位置;
    int[] array = new int[max];

    public static void main(String[] args) {
        Queen8Final queen8Final = new Queen8Final();
        queen8Final.check(0);
    }

    //编写方法放置第n个皇后;
    private void check(int n) {
        if (n == max) {//n = 8时表示已经摆放完毕;
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    //当我们放置第n个棋子时，检测该皇后是否和前面已经摆放的棋子冲突

    /**
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //判断第n个棋子是否跟前面的棋子在一条直线上;
            //利用绝对值判断第n个棋子是否跟前面的棋子在一条斜线上;
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }


    //定义方法打印结果;
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
