package algorithm.sort;

import java.util.Arrays;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-17 17:37
 */
public class InsertFinal {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1; //即arr[i]之前的一个数的下标;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            arr[insertIndex + 1] = insertVal;

            System.out.printf("第%d轮排序后的顺序是：", i);
            System.out.println(Arrays.toString(arr));
        }
    }
}
