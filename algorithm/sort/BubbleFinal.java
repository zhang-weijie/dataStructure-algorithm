package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-17 10:38
 */
public class BubbleFinal {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        bubbleSort(array);

        //测试一下bubbleSort的时间复杂度，给80000个数据;
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        Date start = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = simpleDateFormat.format(start);
        System.out.println("排序前的时间是：" + startTime);
//        排序前的时间是：2020-10-17 11:06:39

        bubbleSort(arr);

        Date end = new Date();
        String endTime = simpleDateFormat.format(end);
        System.out.println("排序后的时间是：" + endTime);
//        排序后的时间是：2020-10-17 11:06:48
    }

    private static void bubbleSort(int[] array) {
        int temp = 0;
        boolean flag = false;//声明flag用于判断某一轮排序中是否有交换;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

//            System.out.printf("第%d轮排序后的顺序是：",i + 1);
//            System.out.println(Arrays.toString(array));

            if (!flag) {//在一轮排序中，一次交换都没有发生，说明数列已经有序，此时结束排序;
                break;
            } else {
                flag = false;
            }
        }
    }

}
