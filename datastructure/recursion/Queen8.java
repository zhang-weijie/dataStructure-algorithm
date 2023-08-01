package datastructure.recursion;

/**
 * 八皇后问题：Evernote:Datastructure:八皇后问题
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-16 16:03
 */
public class Queen8 {
    public static void main(String[] args) {
        int[] arr = new int[8];
        //将arr的每一项初始化为-1,便于判断棋盘上的哪一行还没有棋子
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }
        check(arr, 0);
        System.out.printf("一共有%d种策略，共判断%d次\n", count, judgeCount);
    }

    static int count = 0;
    static int judgeCount = 0;

    public static void check(int[] arr, int num) {
        if (num == arr.length) {//当num达到arr.length时,表示操作完成
            count++;
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("%d\t", arr[i] + 1);
            }
            System.out.println();
        } else {
            //通过for循环尝试摆放棋子位置
            for (int i = 0; i < arr.length; i++) {
                arr[num] = i;
                if (judge(arr, num)) {
                    check(arr, num + 1);
                }
            }
        }
    }

    //判断摆放是否符合规则,num表示当前正在操作的棋子编号;
    public static boolean judge(int[] arr, int num) {
        judgeCount++;
        //1. 判断arr中有无相同的数值,即判断有无棋子在一条直线上;
        //2. 判断当前棋子是否与前面的棋子在一条斜线上;
        //  arr[num] != (arr[num - 1] + 1 || arr[num - 1] - 1)
        //  arr[num] != (arr[num - 2] + 2 || arr[num - 2] - 2)
        //  以此类推...
        for (int i = 1; i < num + 1; i++) {
            if (arr[num] == arr[num - i]) {
                return false;
            } else if (arr[num] == arr[num - i] + i || arr[num] == arr[num - i] - i) {
                return false;
            }
        }
        return true;
    }
}
