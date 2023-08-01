package algorithm.divideandconquer;


/**
 * 汉诺塔：借助B柱将A柱上的金盘移动到C柱上
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-06 16:44
 */
public class HanoiDemo {
    public static void main(String[] args) {
        myHanoi(5, 'A', 'B', 'C');
        System.out.printf("共移动了%d次\n", count);
    }

    static int count = 0;

    public static void myHanoi(int n, char x, char y, char z) {
        count++;
        if (n == 1) {
            //如果只剩一个金盘，则只需移动一次;
            System.out.println(x + "->" + z);
            return;
        }
        //否则需要借助第3根柱子,在移动过程中更新a,b,c三个变量存放的数据;
        //1.将A柱上的n-1个金盘借助C柱移动到B柱;
        myHanoi(n - 1, x, z, y);
        //2.将第n个金盘从A柱移动到C柱;
        System.out.println(x + "->" + z);
        //3. 将B柱上n-1个的金盘借助A柱移动到C柱;
        myHanoi(n - 1, y, x, z);
    }
}
