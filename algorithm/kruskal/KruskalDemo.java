package algorithm.kruskal;

import java.util.Arrays;

/**
 * kruskal算法解决公交站设置问题
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-13 9:49
 */
public class KruskalDemo {
    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                //0表示同一个点之间的关系;INF表示两个点不连通;
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
//        kruskalDemo.print();
        KruskalDemo kruskalDemo = new KruskalDemo(vertexes, matrix);
        Edata[] edges = kruskalDemo.getEdges();
        System.out.println("排序前：" + Arrays.toString(edges));
        //进行排序
        kruskalDemo.sortEdges(edges);
        System.out.println("排序后：" + Arrays.toString(edges));

        kruskalDemo.kruskal();
    }

    private int edgeNum;
    private char[] vertexes;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;//使用INF表示两个顶点不能联通

    //构造器;
    public KruskalDemo(char[] vertexes, int[][] matrix) {
        //使用复制拷贝,以免对传入的vertexes和matrix造成改动;
        int vlen = vertexes.length;
        //初始化顶点;
        this.vertexes = new char[vlen];
        for (int i = 0; i < vlen; i++) {
            this.vertexes[i] = vertexes[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边的数量
        for (int i = 0; i < vlen; i++) {
            //遍历转置矩阵的右上角,避免同一条边在两个顶点出重复统计;
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序处理,使用冒泡排序
    private void sortEdges(Edata[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    Edata temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    //传入一个顶点,比如'A',返回该顶点的下标;
    private int getPosition(char ch) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    //获取图中的边,放到EData[]中,用于后面的遍历;
    //获得：['A','B',12],['B','F',7]...
    private Edata[] getEdges() {
        int index = 0;
        Edata[] edges = new Edata[edgeNum];
        //遍历转置矩阵的右上角;
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new Edata(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    //以下是kruskal算法的核心思想,判断新添加的边是否构成回路;

    /**
     * 功能：获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * 注意：
     * 1. ends本质上是一个递推关系,它存储的并非顶点的终点的下标,而是得到该下标的路径。例如在以下ends数组中
     * index: 0, 1, 2, 3, 4, 5, 6
     * ends：[6, 5, 3, 5, 5, 6, 0]
     * getEnd(int[] ends,2)会生成以下的路径：
     * ends[2]=>ends[3]=>ends[5]=>ends[6]==0 => return 6
     * 这说明索引为2的顶点C的终点为索引为6的顶点G
     * 事实上,此处的ends数组中所有顶点的终点都是G
     *
     * @param ends ends数组是在遍历过程中生成的;
     * @param i
     * @return 返回下标为i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {//
            i = ends[i];
        }
        return i;
    }

    public void kruskal() {
        int index = 0;//结果数组的索引;
        int[] ends = new int[vertexes.length];//用于保存已有的最小生成树中的每个顶点在树中的终点;
        //1.创建结果数组;
        Edata[] res = new Edata[edgeNum];
        //2.获取图中所有边的集合
        Edata[] edges = getEdges();
        //3.按照边的weight进行排序;
        sortEdges(edges);
        //4. 遍历edges数组,将边添加到最小生成树时,判断是否构成回路;
        for (int i = 0; i < edgeNum; i++) {
            //(1) 获取第i条边的start顶点在vertexes数组中的下标;以第一条边<E,F>为例
            int p1 = getPosition(edges[i].start);//p1 = 4
            //(2) 获取第i条边的end顶点在vertexes数组中的下标;
            int p2 = getPosition(edges[i].end);//p2 = 5
            //(3) 获取p1在已有的最小生成树中的终点;此时ends=[0,0,0,0,0,0,0]
            int m = getEnd(ends, p1);//ends[p1]=ends[4]=0 => m=4;
            //(4) 获取p2在已有的最小生成树中的终点;
            int n = getEnd(ends, p2);//ends[p2]=ends[5]=0 => n=5;
            //(3) 判断是否构成回路;
            if (m != n) {
                //a. 若未构成回路,更先新ends数组：令end点的终点成为start点的终点的指向;
                ends[m] = n;//此时ends=[0,0,0,0,5,0,0]
                System.out.println("ends：" + Arrays.toString(ends));
                //b. 再将当前的边加入res数组中;
                res[index++] = edges[i];
            }
        }

        //输出res数组
        System.out.println("最小生成树：");
        for (int i = 0; i < index; i++) {
            System.out.println(res[i]);
        }
    }
}

//创建一个类，其对象表示边
class Edata {
    char start;//边的一个点;
    char end;//边的另一个点;
    int weight;//权值;

    public Edata(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edata[" +
                "<" + start +
                ", " + end +
                "> =" + weight +
                ']';
    }
}