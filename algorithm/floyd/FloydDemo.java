package algorithm.floyd;

import java.util.Arrays;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-14 17:42
 */
public class FloydDemo {
    final static int N = 65535;

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        Graph graph = new Graph(vertexes, matrix);
        graph.floyd();
        graph.show();
    }
}

class Graph {
    private char[] vertexes;
    private int[][] dis;
    private int[][] pre;

    public Graph(char[] vertexes, int[][] matrix) {
        this.vertexes = vertexes;
        this.dis = matrix;
        this.pre = new int[vertexes.length][vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //显示dis和pre
    public void show() {
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.print(vertexes[pre[i][j]] + " ");
            }
            System.out.println();
            for (int j = 0; j < vertexes.length; j++) {
                System.out.print(dis[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Floyd算法
    public void floyd() {
        int len = 0;
        //对中间顶点的遍历
        for (int i = 0; i < dis.length; i++) {//i表示中间顶点;
            for (int j = 0; j < dis.length; j++) {//j表示起始顶点;
                for (int k = 0; k < dis.length; k++) {//k表示结束顶点;
                    len = dis[j][i] + dis[i][k];//求出len = Lji + Lik
                    if (len < dis[j][k]) {//比较len和Ljk
                        dis[j][k] = len;//更新距离;
                        pre[j][k] = pre[i][k];//把下标为k的顶点的前驱节点由j改为i,即把较长的j-k路线改为较短的j-i-k路线;
                    }
                }
            }
        }
    }
}
