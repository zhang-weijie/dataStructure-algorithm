package algorithm.prim;

import java.util.Arrays;

/**
 * 普里姆算法解决修路问题
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-13 8:30
 */
public class PrimDemo {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexNum = data.length;
        int[][] weight = new int[][]{
                //极大的1000表示两点之间不连通或两个点表示同一个点;例如A-A == 1000
                //转置矩阵;
                {1000, 5, 7, 1000, 1000, 1000, 2},
                {5, 1000, 1000, 9, 1000, 1000, 3},
                {7, 1000, 1000, 1000, 8, 1000, 1000},
                {1000, 9, 1000, 1000, 1000, 4, 1000},
                {1000, 1000, 8, 1000, 1000, 5, 4},
                {1000, 1000, 1000, 4, 5, 1000, 6},
                {2, 3, 1000, 1000, 4, 6, 1000}
        };

        MyGraph graph = new MyGraph(vertexNum);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, vertexNum, data, weight);
        minTree.prim(graph, 0);


    }
}

//创建最小生成树
class MinTree {
    //创建图的邻接矩阵
    public void createGraph(MyGraph graph, int vertexNum, char data[], int[][] weight) {
        for (int i = 0; i < vertexNum; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < vertexNum; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图
    public void showGraph(MyGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写Prim算法，得到最小生成树

    /**
     * @param graph 要获取最小生成树的图
     * @param v     起始顶点
     */
    public void prim(MyGraph graph, int v) {
        //1. 创建visited[]标记节点是否被访问过;
        int[] visited = new int[graph.vertexNum];
        //2. 把当前节点标记为已访问
        visited[v] = 1;
        //3. 用h1,h2来记录两个顶点的下标;
        int h1 = -1;
        int h2 = -1;
        //4. 定义一个minWeight用于存放权值;
        int minWeight = 1000;
        //5. 遍历,生成vertexNum - 1条边
        for (int i = 1; i < graph.vertexNum; i++) {
            for (int j = 0; j < graph.vertexNum; j++) {
                for (int k = 0; k < graph.vertexNum; k++) {
                    if (visited[j] == 1 && visited[k] == 0 && graph.weight[j][k] < minWeight) {
                        //替换minWeight用于寻找已经访问过的节点和未访问过的节点间最小的权值;
                        minWeight = graph.weight[j][k];
                        h1 = j;
                        h2 = k;
                    }
                }
            }
            //退出for(j)循环时,找到一条weight最小的边，输出这条边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            //然后将h2节点标记为已访问过;
            visited[h2] = 1;
            //重置minWeight
            minWeight = 1000;
        }
    }
}

class MyGraph {
    int vertexNum;//存放图的节点个数;
    char[] data;//存放节点数据;
    int[][] weight;//存放边,即邻接矩阵;

    public MyGraph(int vertexNum) {
        this.vertexNum = vertexNum;
        data = new char[vertexNum];
        weight = new int[vertexNum][vertexNum];
    }
}