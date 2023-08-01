package algorithm.search;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-20 9:31
 */
public class Fibonacci {
    public static void main(String[] args) {
        int[] arr = {1, 8, 20, 89, 1000, 1234};
        System.out.println(fibSearch(arr, 89));
    }

    //用非递归的方式得到斐波那契数列
    //设置一个maxSize
    static int maxSize = 20;

    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //使用非递归的方式实现斐波那契查找;
    //要求数组必须有序;
    public static int fibSearch(int[] arr, int FindVal) {
        int left = 0;
        int right = arr.length - 1;
        int k = 0;//表示斐波那契分割数值的下标;
        int mid = 0;
        int[] f = fib();
        //为了最大程度地对数组实现精确的黄金分割，应当尽量使用靠后的f[k];
        //此时需要根据数组长度来获取合适的f[k]
        while (right > f[k] - 1) {
            k++;
        }
        //考虑到f(k)可能大于数组长度，因此需要扩展原数组再填充;
        // 将多余的部分用原数组最后一位进行填充;
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        //使用while循环来查找findVal;
        while (left <= right) {
            mid = left + f[k - 1] - 1;//mid始终在黄金分割的位置;
            if (FindVal < temp[mid]) {//如果findVal较小则向左查找;
                right = mid - 1;
                k--;//此时左边部分的长度正好为f[k-2]
            } else if (FindVal > temp[mid]) {//如果findVal较小则向右查找;
                left = mid + 1;
                k -= 2;//此时右边部分的长度正好为f[k-3];
            } else {//找到
                //确认返回哪个下标;
                if (mid <= right) {//此时findVal出现在temp数组的非填充部分，即原数组arr中间;
                    return mid;
                } else {//此时findVal出现在temp数组的填充部分，其数值与arr[right]相等，因为填充部分的数值均等于arr[right];
                    return right;
                }
            }
        }
        //若未找到则返回-1
        return -1;
    }
}
