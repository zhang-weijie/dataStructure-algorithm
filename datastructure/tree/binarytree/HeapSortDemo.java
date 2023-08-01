package datastructure.tree.binarytree;

import java.util.Arrays;
import java.util.logging.Level;

/**
 * 堆排序步骤
 * 1.将待排序序列构造成一个大（小）顶堆;
 * 2.此时，整个序列的最大值就是顶堆的根节点;
 * 3.将其与末尾元素进行交换，此时末尾就为最大（小）值;
 * 4.然后将剩余n-1个元素重新构造成一个堆，重复上述步骤直到只剩1个元素;
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-03 10:57
 */
public class HeapSortDemo {
    public static void main(String[] args) {
        int[] arr = {4, 6, 12, 8, 5, 10, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    //我自己的思路;
    public static void myHeapSort(int[] arr, int length) {
        if (length == 0) {
            return;
        }

        int index = length / 2 - 1;
        int indexOfMax = -1;

        while (index >= 0) {
            if (index * 2 + 1 < length) {
                indexOfMax = arr[index] >= arr[index * 2 + 1] ? index : index * 2 + 1;
                if (index * 2 + 2 < length) {
                    indexOfMax = arr[indexOfMax] >= arr[index * 2 + 2] ? indexOfMax : index * 2 + 2;
                }
            }
            //如果index不是indexOfMax,则调换位置;
            if (indexOfMax != index) {
                int temp = arr[index];
                arr[index] = arr[indexOfMax];
                arr[indexOfMax] = temp;
            }
            //判断上一个非叶子结点在当前非叶子结点的左侧还是上方;
            //如果index为奇数,则其左侧不存在别的非叶子结点;
            if (index % 2 == 0 && index > 0) {
                index = (index - 1) / 2;
                //如果index为偶数,则其左侧必然存在非叶子结点;
            } else {
                index--;
            }
        }
        //将数组中最大的数移动到堆顶后将其与数组末尾的数交换位置;
        int temp = arr[0];
        arr[0] = arr[length - 1];
        arr[length - 1] = temp;

        myHeapSort(arr, --length);
    }

    //教程的思路
    public static void heapSort(int arr[]) {
        System.out.println("堆排序");
        int temp = 0;

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }
    }

    //将一个数组调整为大顶堆

    /**
     * @param arr
     * @param i      非叶子结点在数组中的索引
     * @param length 需要进行调整的元素的个数
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子节点右侧存在右子节点;且数值更大;
                k++;
            }
            if (arr[k] > temp) {//如果左/右子节点大于父节点
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        //循环结束后,已经将i为父节点的树的最大值,放在了局部的最顶部
        arr[i] = temp;//将temp值放到调整后的位置,完成交换;
    }
}
