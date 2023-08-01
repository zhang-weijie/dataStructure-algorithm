package algorithm.sort;

import java.util.Arrays;
import java.util.IdentityHashMap;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-19 9:33
 */
public class Merge {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 3, 1, 6, 2};
//        int[] arr = {4,7,3,5};
        int[] copyArr = new int[arr.length];
        mergeSort(arr, copyArr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(copyArr));
    }

    public static void mergeSort(int[] arr, int[] copyArr, int i, int j) {
        if (j - i > 1) {
            mergeSort(arr, copyArr, i, (i + j) / 2);
            mergeSort(arr, copyArr, (i + j) / 2 + 1, j);

        }
        int tempI = i;
        int tempJ = (i + j) / 2 + 1;
        int copyArrIndex = i;
        int tempIVal = 0;
        int tempJVal = 0;
        for (int k = i; k < j; k++) {
            if (tempI < (i + j) / 2 + 1) {
                tempIVal = arr[tempI];
            }
            if (tempJ < j + 1) {
                tempJVal = arr[tempJ];
            }

            if (tempIVal <= tempJVal) {
                copyArr[copyArrIndex++] = tempIVal;
                tempI++;
            } else {
                copyArr[copyArrIndex++] = tempJVal;
                tempJ++;
            }
        }

        if (tempI < (i + j) / 2 + 1) {
            copyArr[copyArrIndex] = arr[tempI];
        } else {
            copyArr[copyArrIndex] = arr[tempJ];
        }

        for (int k = i; k < j + 1; k++) {
            arr[k] = copyArr[k];
        }
    }
}
