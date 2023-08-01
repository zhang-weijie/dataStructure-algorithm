package algorithm.kmp;

import java.util.Arrays;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-12 12:19
 */
public class kmpDemo {
    public static void main(String[] args) {
        String s = "BBC ABCDAB ABCDABCDABDE";
        String p = "ABCDABD";
//        String s1 = "abcdcdababca";
//        String p1 = "abab";
//        System.out.println(Arrays.toString(myGetNextArr(p1)));
        int index = myKMP(s, p);
        System.out.println("模式串在字符串中首次出现的位置为" + index);
        int index1 = kmpSearch(s, p, kmpNext(p));
        System.out.println("模式串在字符串中首次出现的位置为" + index1);
        int index2 = kmp(s, p);
        System.out.println("模式串在字符串中首次出现的位置为" + index2);
    }

    public static int myKMP(String s, String p) {
        int[] nextArr = myGetNextArr(p);
        int i = 0;
        int j = 0;
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                j++;
            } else {
                j = nextArr[j] + 1;
            }
            i++;
        }

        if (j == p.length()) {
            return i - j;
        }
        return -1;
    }


    public static int[] myGetNextArr(String pattern) {
        char[] p = pattern.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        for (int i = 0; i < next.length - 1; i++) {
            //以下为未优化的操作;
            int k = next[i];
            while (k >= 0 && p[i] != p[k]) {
                k = next[k];
            }
            next[i + 1] = k + 1;
        }
        return next;
    }

    //教程的思路
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //文章的思路：
    public static int[] getNextVal(String p) {
        int[] next = new int[p.length()];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < p.length() - 1) {
            if (k == -1 || p.charAt(j) == p.charAt(k)) {
                ++j;
                ++k;
                if (p.charAt(j) != p.charAt(k)) {
                    next[j] = k;
                } else {
                    //针对如"abab"一类左右对称的模式串的优化
                    next[j] = next[k];
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public static int kmp(String s, String p) {
        int[] next = getNextVal(p);
        int i = 0;
        int j = 0;
        while (i < s.length() && j < p.length()) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == p.length()) {
            return i - j;
        }
        return -1;
    }
}
