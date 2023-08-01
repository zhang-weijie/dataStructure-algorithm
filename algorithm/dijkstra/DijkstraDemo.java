package algorithm.dijkstra;


import java.util.Arrays;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-13 15:56
 */
public class DijkstraDemo {
    static final int N = 65535;

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        Graph graph = new Graph(vertexes, matrix);
        int index = 5;
        graph.dijkstra(index);
        graph.showDijkstra(index);

    }
}


class VisitedVertex {
    //记录各个顶点是否被访问过：1表示是,0表示否;会动态更新
    public int[] already_arr;
    //记录每个顶点的前一个顶点的下标;会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离,比如G为出发顶点,就会记录G到其他顶点的距离;会动态更新
    public int[] dis;

    /**
     * @param length 顶点的个数
     * @param index  出发顶点的下标
     */

    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组;
        Arrays.fill(this.dis, 65535);
        this.dis[index] = 0;
        //设置出发顶点为被访问过;
        this.already_arr[index] = 1;
        //设置出发顶点的前驱结点为-1;
        this.pre_visited[index] = -1;
    }

    //判断index顶点是否被访问过;
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    //更新出发顶点到index顶点的距离;
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    //更新pre顶点的前驱节点为index顶点;
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    //返回出发顶点到index顶点的距离;
    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的访问顶点;
    public int updateArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            //从尚未访问的顶点中寻找与出发节点距离最短的顶点i
            //注意此处出发顶点到顶点i的距离不一定是二者的直接距离,还可能是间接距离;
            if (!in(i) && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //将index顶点设置为被访问过;
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果,index代表起始顶点
    public void show(int startIndex) {
        for (int i = 0; i < already_arr.length; i++) {
            System.out.println("<" + getPath(i, startIndex) + "> =" + dis[i]);
        }
    }

    public String getPath(int index, int startIndex) {
        String path = "" + (char) (index + 65);
        if (pre_visited[index] == -1) {//如果遇到起始顶点则直接返回;
            return path;
        }
        while (true) {
            path = (char) (pre_visited[index] + 65) + "->" + path;
            index = pre_visited[index];
            if (index == startIndex) {
                break;
            }
        }
        return path;
    }
}

class Graph {
    private char[] vertexes;
    private int[][] matrix;
    private VisitedVertex vv;//已经访问过的顶点的集合;

    //此处利用接收新建Graph对象,若要安全创建则可选择复制;
    public Graph(char[] vertexes, int[][] matrix) {
        this.vertexes = vertexes;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //Dijkstra算法的实现
    public void dijkstra(int index) {
        //1. 创建一个visitedVertex
        this.vv = new VisitedVertex(vertexes.length, index);
        //2. 更新index顶点到周围顶点的距离以及周围顶点的前驱节点;
        update(index);
        //3. 继续选择并返回新的访问顶点,需要利用图的广度优先遍历算法;
        for (int i = 1; i < vertexes.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }

    //更新index顶点到周围顶点的距离以及周围顶点的前驱节点;
    private void update(int index) {
        int len;
        for (int j = 0; j < vertexes.length; j++) {
            //len=出发顶点到index顶点的距离 + index顶点到j顶点的距离
            len = vv.getDis(index) + matrix[index][j];
            //如果j顶点没有被访问过,且len < 出发顶点到j顶点的距离;
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index);//更新j顶点的前驱顶点为index顶点;
                vv.updateDis(j, len);//更新出发顶点到j顶点的距离;
            }
        }
    }

    public void showDijkstra(int startIndex) {
        vv.show(startIndex);
    }
}


/*
class Graph1 {
    private char[] vertexes;
    private int[][] matrix;
    private VisitedVertex vv;

    public Graph1(char[] vertexes, int[][] matrix) {
        this.vertexes = vertexes;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link:matrix){
            Arrays.toString(link);
        }
    }

    public void update(int index){
        int len;
        for (int i = 0; i < vertexes.length; i++) {
            len = vv.getDis(index) + matrix[index][i];
            if (!vv.in(i) && len < vv.getDis(i)){
                vv.updatePre(i,index);
                vv.updateDis(i,len);
            }
        }
    }

    public void dijkstra(int index){
        vv = new VisitedVertex(vertexes.length, index);
        update(index);
        for (int i = 1; i < vertexes.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }

    public void showDijkstra(){
        vv.showDijkstra();
    }
}

class VisitedVertex {
    private int[] already_list;
    private int[] pre_list;
    private int[] dis;

    public VisitedVertex(int len,int index){
        already_list = new int[len];
        pre_list = new int[len];
        dis = new int[len];

        Arrays.fill(dis,65535);
        dis[index] = 0;
        already_list[index] = 1;
    }

    public boolean in(int index){
        return already_list[index] == 1;
    }

    public void updatePre(int pre, int index){
        pre_list[pre] = index;
    }

    public int getDis(int index){
        return dis[index];
    }

    public void updateDis(int index, int length){
        dis[index] = length;
    }

    public int updateArr(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_list.length; i++) {
            if (!in(i) && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        already_list[index] = 1;
        return index;
    }

    public void showDijkstra(){
        System.out.println(Arrays.toString(already_list));
        System.out.println(Arrays.toString(pre_list));
        System.out.println(Arrays.toString(dis));

        //[1, 1, 1, 1, 1, 1, 1]
        //[6, 6, 0, 5, 6, 6, 0]
        //[2, 3, 9, 10, 4, 6, 0]

    }
}
*/