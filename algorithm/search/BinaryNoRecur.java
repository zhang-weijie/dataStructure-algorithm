package algorithm.search;

/**
 * 二分查找的非递归实现;
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-06 11:16
 */
public class BinaryNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 1);
        System.out.println(index);//0
    }

    //数组arr要求有序
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
