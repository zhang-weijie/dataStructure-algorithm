package algorithm.sort;

import java.util.Arrays;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-17 15:18
 */
public class SelectFinal {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        selectSort(arr);
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            if (minIndex != i) {//判断minIndex是否放生了改变;
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

            System.out.printf("第%d轮后的顺序是：", i);
            System.out.println(Arrays.toString(arr));
        }
    }
}