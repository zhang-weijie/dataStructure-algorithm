package datastructure.tree.binarytree;

/**
 * 左旋转{4,3,6,5,7,8}
 * 4                     6
 * /  \                  /  \
 * 3     6               4    7
 * /  \     ==>    /  \    \
 * 5    7          3    5    8
 * \
 * 8
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-04 16:09
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
        AVLTree avlTree = new AVLTree();
        //添加节点;
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node3(arr[i]));
        }
        System.out.println("左旋转前的中序遍历");
        avlTree.infixOrder();
        System.out.println("树高度为：" + ((Node3) avlTree.getRoot()).height());//4
        System.out.println("左子树的高度为：" + ((Node3) avlTree.getRoot()).leftHeight());//1
        System.out.println("右子树的高度为：" + ((Node3) avlTree.getRoot()).rightHeight());//3

        System.out.println("执行左旋转操作...");
    }
}

class AVLTree extends BinarySortTree {

}

class Node3 extends Node2 {

    public Node3(int value) {
        super(value);
    }

    //返回以当前节点为根节点的数的高度;
    public int height() {
        return Math.max(left == null ? 0 : ((Node3) left).height(), right == null ? 0 : ((Node3) right).height()) + 1;
    }

    //返回左子树的高度;
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return ((Node3) left).height();
    }

    //返回右子树的高度;
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return ((Node3) right).height();
    }

    //左旋转方法
    private void leftRotate() {
        //1. 以当前节点的值创建新节点;
        Node3 newNode = new Node3(value);
        //2. 把新的节点的左子节点(左子树)设置为当前节点的左子节点(左子树)
        newNode.left = left;
        //3. 把新的节点的右子节点(右子树)设置为当前节点的右子节点的左子节点(左子树);
        newNode.right = right.left;
        //4. 把当前节点的值替换成其右子节点的值;
        value = right.value;
        //5. 把当前节点的右子树设置成当前节点的右子树的右子树;
        right = right.right;
        //6. 把当前节点的左子节点(左子树)设置为新的节点;
        left = newNode;
    }

    //有旋转方法
    private void rightRotate() {
        Node3 newNode = new Node3(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    //每当向二叉排序树中添加一个新的节点时,树的高度都可能发生改变,此时需要判断是否调用左旋转方法;
    //重写Node2的add(Node2 node)方法
    public void add(Node3 node) {
        if (node == null) {
            return;
        }
        if (node.value < value) {
            if (left == null) {
                left = node;
            } else {
                left.add(node);
            }
        } else {
            if (right == null) {
                right = node;
            } else {
                right.add(node);
            }
        }
        //添加判断：
        //如果rightHeight - leftHeight > 1需要进行leftRotate
        if (rightHeight() - leftHeight() > 1) {
            //双旋转的情形：如果当前节点的右子树的左子树高度大于右子树的右子树
            if (right != null && ((Node3) right).leftHeight() > ((Node3) right).rightHeight()) {
                //先对当前节点的右子树进行右旋转;
                ((Node3) right).rightRotate();
            }
            leftRotate();
            return;//注意此处需要结束;
        }
        //如果leftHeight - rightHeight > 1需要进行rightRotate
        if (leftHeight() - rightHeight() > 1) {
            //双旋转的情形：如果当前节点的左子树的右子树高度大于左子树的左子树的高度
            if (left != null && ((Node3) left).rightHeight() > ((Node3) left).leftHeight()) {
                //先对当前节点的左子树进行左旋转;
                ((Node3) left).leftRotate();
            }
            rightRotate();
        }
    }
}


