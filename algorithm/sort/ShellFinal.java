package algorithm.sort;

import java.awt.geom.GeneralPath;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-18 8:51
 */
public class ShellFinal {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(arr);


    }

    //1.交换法希尔排序
    public static void shellSort(int[] arr) {
        int temp;
        int count = 0;
        int judge = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        judge++;
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.printf("第%d轮排序后的顺序是：%s\n", ++count, Arrays.toString(arr));
        }
        System.out.printf("共判断了%d次\n", judge);
    }

    //2.希尔移位排序
    public static void shellSort2(int[] arr) {
        int count = 0;
        int judge = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        judge++;
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
//            System.out.printf("第%d轮希尔排序后的顺序是：%s\n", ++count, Arrays.toString(arr));
        }
        System.out.printf("共判断了%d次\n", judge);

    }


}
