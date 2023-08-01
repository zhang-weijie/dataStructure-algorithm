package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * //shell排序是选择排序的优化版
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-17 18:06
 */
public class Shell {
    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(array);
    }

    public static void shellSort(int[] arr) {
        int count = 0;
        int judge = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {//gap每一轮缩量
            for (int i = 0; i < arr.length - gap; i++) {
                //以下为插入排序的步骤;
                int insertVal = arr[i + gap];
                int insertIndex = i;

                while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                    judge++;
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }

                arr[insertIndex + gap] = insertVal;
            }
            System.out.printf("第%d轮希尔排序后的顺序是：%s\n", ++count, Arrays.toString(arr));
        }
        System.out.printf("共判断了%d次\n", judge);
    }
}
