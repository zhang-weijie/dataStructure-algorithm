package algorithm.sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-17 11:23
 */
public class Select {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        System.out.println(Arrays.toString(selectSort(arr)));

        //测试选择排序的效率
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 80000);
        }

        Date start = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = simpleDateFormat.format(start);

        System.out.println("排序前的时间是：" + startTime);
//        排序前的时间是：2020-10-17 15:17:06

        selectSort(array);

        Date end = new Date();
        String endTime = simpleDateFormat.format(end);
        System.out.println("排序后的时间是：" + endTime);
//        排序后的时间是：2020-10-17 15:17:09
    }

    //    static int min = 0;
//    static int minIndex = 0;
    private static int[] selectSort(int[] arr) {
        int min;
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
        return arr;
    }
}
