package datastructure.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * //permute()来自网上的排列案例：参见Evernote:Datastructure:7
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-16 7:40
 */
public class Permutation {
    public static void main(String[] args) {
        ArrayList<Bias> data = new ArrayList<>();
        ArrayList<Bias> target = new ArrayList<>();
        data.add(new Bias(0, 1));
        data.add(new Bias(0, -1));
        data.add(new Bias(1, 0));
        data.add(new Bias(-1, 0));

        permute(data, target, data.size());

        for (int i = 0; i < lists.size(); i++) {
            ArrayList outerList = lists.get(i);
            for (int j = 0; j < outerList.size(); j++) {
                Bias b = (Bias) outerList.get(j);
                System.out.printf("(%d, %d)\t", b.getX(), b.getY());
            }
            System.out.println();
        }
    }

    //声明一个List用于接受每一种情形下的target;
    static ArrayList<ArrayList> lists = new ArrayList<>();

    public static void permute(List<Bias> data, List<Bias> target, int k) {
        List<Bias> copyData;
        List<Bias> copyTarget;
        if (target.size() == k) {
//            for (Bias b: target) {
//                System.out.printf("(%d, %d)\t",b.getX(),b.getY());
//            }
            lists.add((ArrayList) target);
        }

        for (int i = 0; i < data.size(); i++) {
            copyData = new ArrayList<>(data);
            copyTarget = new ArrayList<>(target);

            copyTarget.add(copyData.get(i));
            copyData.remove(i);

            permute(copyData, copyTarget, k);
        }
    }
}
