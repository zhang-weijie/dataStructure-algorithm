package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-17 15:37
 */
public class Insert {
    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, -2};
        insertSort(array);

        //测试插入排序的效率
        //测试一下bubbleSort的时间复杂度，给80000个数据;
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        Date start = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = simpleDateFormat.format(start);
        System.out.println("排序前的时间是：" + startTime);
//        排序前的时间是：2020-10-17 17:33:59


        insertSort(arr);

        Date end = new Date();
        String endTime = simpleDateFormat.format(end);
        System.out.println("排序后的时间是：" + endTime);
//        排序后的时间是：2020-10-17 17:34:00
    }

    public static void insertSort(int[] arr) {
        int[] arrInOrder = new int[arr.length];
        arrInOrder[0] = arr[0];

        int temp;
        int index;
        for (int i = 1; i < arr.length; i++) {
            index = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] < arrInOrder[j]) {
                    for (int k = i; k > index; k--) {
                        temp = arrInOrder[k];
                        arrInOrder[k] = arrInOrder[k - 1];
                        if (k < arrInOrder.length - 1) {//如果已经索引到arrInOrder的末尾则不能再往后交换了
                            arrInOrder[k + 1] = temp;
                        }
                    }
                    break;
                } else {
                    index++;
                }
            }
            arrInOrder[index] = arr[i];

//            System.out.printf("第%d轮排序后的顺序是：", i);
//            System.out.println(Arrays.toString(arrInOrder));
        }
    }
}
