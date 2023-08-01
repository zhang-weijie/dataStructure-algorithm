package datastructure.tree.binarytree;

/**
 * //以数组的方式存储二叉树并完成遍历，即顺序存储二叉树
 * (0)1
 * /    \
 * (1)2      (2)3
 * /   \     /   \
 * (3)4    (4)5 (5)6  (6)7
 * 注意到：下标为i的节点的左子节点下标为2*i+1,右子节点下标为2*i+2;
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-20 20:41
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);//1	 2	4	5	3	6	7
        System.out.println();
        arrBinaryTree.infixOrder(0);//4	2	5	1	6	3	7
        System.out.println();
        arrBinaryTree.postOrder(0);//4	5	2	6	7	3	1
        System.out.println();
    }
}


class ArrBinaryTree {
    private int[] arr;//存储数据节点的数组;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder(int index)方法，去掉index，默认从0开始;
    public void preOrder() {
        this.preOrder(0);
    }

    //前序遍历方法
    //由于只考虑完全二叉树的情形，因此不用判断左右子节点是否存在;
    //index表示遍历的起始位置;
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空!");
            return;
        }
        System.out.print(arr[index] + "\t");//输出当前的元素;
        //向左递归遍历;先判断index是否会越出数组的界，或者说超出二叉树之外;
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //中序遍历方法;
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空！");
            return;
        }
        //向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }
        //输出当前的元素;
        System.out.print(arr[index] + "\t");
        //向右递归遍历;
        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    //后序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空！");
            return;
        }
        //向左递归遍历;
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        //向右递归遍历;
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        System.out.print(arr[index] + "\t");
    }
}
