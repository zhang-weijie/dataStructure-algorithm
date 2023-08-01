package datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DuplicateFormatFlagsException;
import java.util.LinkedList;

/**
 * 1.创建以下的图：
 * B
 * /  | |  \
 * /   |  |   E
 * C    |   |
 * \  |    D
 * A
 * 2.图的深度优先查找算法DFS Depth First Search
 * 3.图的广度优先查找算法BFS Broad First Search
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-06 9:21
 */
public class GraphDemo {


    public static void main(String[] args) {
        int n = 5;
        String[] vertexValues = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        //添加节点;
        for (String value : vertexValues) {
            graph.insertVertex(value);
        }
        //添加边;
        //A-B, A-C, B-C, B-D, B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        //显示邻接矩阵
//        graph.showGraph();

        //深度遍历;
        System.out.println("深度优先遍历...");
        graph.dfs();

        //广度遍历;
        System.out.println("广度优先遍历...");
        graph.bfs();
    }
}

class Graph {
    private ArrayList<String> vertexList;//存储顶点集合;
    private int[][] edges;//存储图对应的邻接矩阵;
    private int numOfEdges;//表示边的数目;
    //定义boolean[]数组,用于记录某个节点是否被访问;
    private boolean[] isVisited;

    public Graph(int n) {
        //初始化邻接矩阵和顶点集合;
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边;

    /**
     * @param v1     表示点的下标即第几个顶点
     * @param v2     表示点的下标即第几个顶点
     * @param weight 权值,例如1表示连接,0表示不连接;
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
    }

    //图中常用的方法:
    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的个数;
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点v保存的关键数据
    public String getValByIndex(int v) {
        return vertexList.get(v);
    }

    //返回v1和v2的权值;
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示邻接矩阵;
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //实现DFS算法
    //1.得到当前节点的第一个邻接节点的下标;
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //2.根据当前节点的一个邻接节点的下标获取下一个邻接节点;

    /**
     * @param index 当前节点的下标;
     * @param v     当前节点的一个邻接节点;
     * @return 当前节点的下一个邻接节点;
     */
    public int getNextNeighbor(int index, int v) {
        for (int i = v + 1; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //3. DFS遍历方法
    private void dfs(boolean[] isVisited, int v) {
        //(1) 首先方法当前节点v,并输出
        System.out.println(getValByIndex(v));
        //(2) 将当前节点设置为已经访问
        isVisited[v] = true;
        //(3) 查找当前节点的第一个邻接节点;
        int w = getFirstNeighbor(v);
        //(4) 如果w != -1,需要判断w是否被访问过了;如果w == -1,则结束当前访问进程;
        while (w != -1) {
            if (!isVisited[w]) {//没有被访问过;
                dfs(isVisited, w);//递归dfs()
            }
            //如果节点被访问过了;
            w = getNextNeighbor(v, w);//将w更新为v的下一个邻接节点;
        }
    }


    //重载dfs,遍历所有节点,并进行dfs
    public void dfs() {
        //重新初始化isVisited[],清除上一轮遍历的痕迹;
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {//如果当前节点没有被访问,则递归执行dfs;
                dfs(isVisited, i);
            }
        }
    }

    //对一个节点进行广度优先遍历
    private void bfs(boolean[] isVisited, int v) {
        int u; //表示队列的头节点下标;
        int w; //邻接节点w;
        //定义队列,用于记录访问顺序;
        LinkedList queue = new LinkedList();
        //1.访问当前节点,输出节点信息;
        System.out.println(getValByIndex(v));
        //2.标记为已访问;
        isVisited[v] = true;
        //3.将节点加入队列;
        queue.addLast(v);
        //4.若队列不为空,进行循环递归访问
        while (!queue.isEmpty()) {
            //(1) 取出队列的头结点;
            u = (int) queue.removeFirst();
            //(2) 获得该节点的第一个邻接节点;
            w = getFirstNeighbor(u);
            //(3) 判断w节点是否能找到,以及是否被访问过;
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.println(getValByIndex(w));
                    //表示为已访问;
                    isVisited[w] = true;
                    //将w节点加入队列;
                    queue.addLast(w);
                }
                //以u为前驱节点,找w后面的下一个邻接节点;
                w = getNextNeighbor(u, w);
            }
        }
    }

    //重载bfs,遍历所有节点进行bfs
    public void bfs() {
        //重新初始化isVisited[],清除上一轮遍历的痕迹;
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }
}
