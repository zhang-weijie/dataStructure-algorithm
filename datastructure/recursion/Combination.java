package datastructure.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * //递归实现组合combination:参见Evernote:Datastructure:排列组合
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-16 14:24
 */
public class Combination {
    public static void main(String[] args) {
        Bias b1 = new Bias(0, 1);
        Bias b2 = new Bias(0, -1);
        Bias b3 = new Bias(1, 0);
        Bias b4 = new Bias(-1, 0);

        ArrayList<Bias> data = new ArrayList<>();
        data.add(b1);
        data.add(b2);
        data.add(b3);
        data.add(b4);

        ArrayList<Bias> target = new ArrayList<>();

        combine(data, target, data.size(), 3);


    }

    public static void combine(List<Bias> data, List<Bias> target, int n, int k) {
        List<Bias> copyData;
        List<Bias> copyTarget;

        if (target.size() == k) {
            for (Bias b : target) {
                System.out.printf("(%d,%d)\t", b.getX(), b.getY());
            }
            System.out.println();
        }

        for (int i = 0; i < data.size(); i++) {
            copyData = new ArrayList<>(data);
            copyTarget = new ArrayList<>(target);
            copyTarget.add(copyData.get(i));

            for (int j = i; j >= 0; j--) {
                copyData.remove(j);//例如C(4,3),(1,2,3)和(1,3,4)之后就不能由1开头了,否则会重复;
            }
            combine(copyData, copyTarget, n, k);
        }

    }
}


