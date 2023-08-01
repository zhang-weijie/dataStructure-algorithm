package algorithm.sort;

import java.util.Arrays;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-18 14:32
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {1, 4, 3, 2, 5, 7, 9, 8};
//        int[] array = {1,2,5,4,5,6,5,8,9};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下标;
        int r = right;//右下标;
        int pivot = arr[(left + right) / 2];
        int temp;
        //利用while循环将比pivot小的值放到左边,比pivot大的值放到右边;
        while (l < r) {
            //在pivot的左边一直找，直到找到大于等于pivot的值;
            while (arr[l] < pivot) {
                l++;
            }
            //在pivot的右边一直找，直到找到小于等于pivot的值;
            while (arr[r] > pivot) {
                r--;
            }
            //如果l>=r则说明pivot左边所有的值都小于等于pivot,右边所有的值都大于等于pivot
            //此时退出循环;
            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //考虑到可能有跟pivot相等的值出现
            //例如{1,2,7,4,5,6,5,8,9} --> {1,2,5,4,5,6,7,8,9},l=2,arr[l]=5,r=6,arr[r]=
            //此时要将r向左移动一位r=5,将原数组分割成(1,2,5),4,5,(6,7,8,9)
            //否则数组仍保持(1,2,5),4,5,(7,8,9)的分组;
            //接下来r会往左检索直到(1,2,5),4,(5,7,8,9);
            //然后进行arr[l]=5和a[r]=5的交换,陷入死循环。
            if (arr[l] == pivot) {
                r--;
            }

            if (arr[r] == pivot) {
                l++;
            }
            //有了这两个限制条件后,例子中的数组会进行以下变化
            //(1,2,5),4,5,(6,7,8,9)
            //(1,2,5),4,(5,6,7,8,9) 因为arr[r]=pivot=5;所以l++
            //(1,2,5,4),(5,6,7,8,9) 右边排序完毕,此时l=3,r=4;
            //(1,2,5,4)左边进行递归-->(1,2)(5,4)
            //(1,2,5)(4);
            //(1,2,4)(5);
        }

        //如果l==r，必须l++,r--，否则会栈溢出;
        //例如(1,4,3,2,(5),6,7,9,8)
        if (l == r) {
            l++;
            r--;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
