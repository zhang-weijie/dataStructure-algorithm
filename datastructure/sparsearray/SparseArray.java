package datastructure.sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-18 15:43
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个二维数组
        //0表示没有棋子；1表示黑子；2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 2;
        chessArr1[4][5] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println();

        //将二维数组转换为稀疏数组的思想
        //1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //遍历二维数组，将非0的值放到sparseArr中;
        //count用于记录第几个非0数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("输出稀疏数组");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        System.out.println();
        //利用IO流将稀疏数组保存到磁盘中，以map.data命名文件
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(new File("src\\datastructure\\map.data")));
            for (int i = 0; i < sparseArray.length; i++) {
                for (int j = 0; j < sparseArray[0].length; j++) {
                    dos.writeInt(sparseArray[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //利用IO流将稀疏数组从磁盘中读取出来，输出到控制台上
        ArrayList<Integer> list = new ArrayList<>();
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(new File("src\\datastructure\\map.data")));
            try {
                while (true) {
                    list.add(dis.readInt());
                }
            } catch (IOException e) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        //将稀疏数组恢复成原始的二维数组
        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组;
        int chessArr2[][] = new int[list.get(0)][list.get(1)];
        for (int i = 1; i < list.size() / 3; i++) {
            chessArr2[list.get(3 * i)][list.get(3 * i + 1)] = list.get(3 * i + 2);
        }

        //2.读取后几行的数据，并复制给原始二维数组即可，输出恢复后的二维数组;
        //3.输出恢复后的原始数组
        for (int[] row : chessArr2) {
            for (int data1 : row) {
                System.out.printf("%d\t", data1);
            }
            System.out.println();
        }
    }
}