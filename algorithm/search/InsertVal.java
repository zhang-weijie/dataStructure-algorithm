package algorithm.search;

import java.util.ArrayList;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-20 9:17
 */
public class InsertVal {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        ArrayList<Integer> list = insertValSearch(arr, 0, arr.length - 1, 100);
        System.out.println(list.toString());
    }

    // 差值查找算法;适用于数据量较大且关键字分布比较均匀的情形;
    // 根据findVal和arr[left]的差值与arr[left]和arr[right]的差值的比例来调整mid的位置
    public static ArrayList<Integer> insertValSearch(int[] arr, int left, int right, int findVal) {

        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {//最后的情形是紧邻的两个数没有任何一个跟findVal匹配
            return new ArrayList<Integer>();
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (midVal < findVal) {
            return insertValSearch(arr, mid + 1, right, findVal);
        } else if (midVal > findVal) {
            return insertValSearch(arr, left, mid - 1, findVal);
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
