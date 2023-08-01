package algorithm.sort;

import datastructure.recursion.Permutation;

import java.io.PrintStream;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-17 9:56
 */
public class Bubble {
    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, -2};
        Bubble bubble = new Bubble();
        bubble.bubbleSort(array);
        bubble.print(array);

    }

    private void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private int[] bubbleSort(int[] array) {
        boolean loop = true;
        int bias = 1;//用于在第n轮排序后将(已经排序好的且最大的)倒数第n至倒数第1个数排除在排序之外;
        while (loop) {
            loop = false;
            for (int i = 0; i < array.length - bias; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    loop = true;
                }
            }
            bias++;
        }
        return array;
    }
}
