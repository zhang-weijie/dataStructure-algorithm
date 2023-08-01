package algorithm.search;

import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-20 8:28
 */
public class Binary {
    public static void main(String[] args) {
        int[] arr = {1, 8, 20, 89, 1000, 1234};
        int[] arr1 = {1, 8, 20, 89, 1000, 1000, 1234};
        ArrayList<Integer> list = binarySearch(arr, 0, arr.length - 1, 1000);
        ArrayList<Integer> list1 = binarySearch(arr1, 0, arr.length - 1, 1000);
        System.out.println(list.toString());
        System.out.println(list1.toString());
    }

    //二分查找要求数组是有序的;
    //重复出现的数要求全部找到;
    public static ArrayList<Integer> binarySearch(int[] arr, int left, int right, int findVal) {

        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {//最后的情形是紧邻的两个数没有任何一个跟findVal匹配
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (midVal < findVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (midVal > findVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            //找到目标索引后，分别向左向右扫描;
            //因为此处的ArrayList是在找到findVal后才创建的，此时查找已经结束了;
            //因此不必考虑递归过程中的传递和合并问题;
            ArrayList<Integer> resIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }
}
