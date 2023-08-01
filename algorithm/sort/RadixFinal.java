package algorithm.sort;

import java.util.Arrays;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-19 21:43
 */
public class RadixFinal {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        //得到数组中最大的数;
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数的位数;
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中的数据数量，用一个一维数组予以存贮;
        //例如bucketElementCount[0]，表示bucket[0]这个桶数组内放入的数据的个数;
        int[] bucketElementCount = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
                bucketElementCount[digitOfElement]++;
            }

            //遍历每个桶，将桶内的数据放入原数组;
            int index = 0;
            for (int k = 0; k < bucketElementCount.length; k++) {
                //桶中有数据才放入原数组;
                if (bucketElementCount[k] != 0) {
                    //遍历第k个桶;
                    for (int l = 0; l < bucketElementCount[k]; l++) {
                        //取出数据放入原数组;
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //本轮处理后，需要将bucketElementCount[k]归零，方便后续的对高位的操作;
                bucketElementCount[k] = 0;
            }
        }
    }
}
