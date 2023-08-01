package datastructure.tree.binarytree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-03 17:39
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node rootOfHuffmanTree = createHuffmanTree(arr);
        preOrder(rootOfHuffmanTree);

    }

    public static Node createHuffmanTree(int[] arr) {
        //为了操作方便，遍历数组创建ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);

            //取出根节点权值最小的两棵二叉树
            Node left = nodes.get(0);
            Node right = nodes.get(1);

            //创建新的二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            //删除left和right
            nodes.remove(left);
            nodes.remove(right);

            //将parent加入ArrayList
            nodes.add(parent);
        }
        //返回Huffman树的root节点
        return nodes.get(0);
    }

    //写一个前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空！");
        }
    }
}


//实现Comparable接口以方便排序
class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(@NotNull Node o) {
        return this.value - o.value;
    }

    //创建前序遍历方法
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}