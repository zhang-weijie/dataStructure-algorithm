package datastructure.tree.binarytree;

import java.io.PrintStream;

/**
 * 二叉排序树
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-04 14:36
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node2(arr[i]));
        }

        //测试删除节点;
        binarySortTree.delNode(1);
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private Node2 root;

    public Node2 getRoot() {
        return root;
    }

    public void add(Node2 node) {
        if (root == null) {//如果二叉树为空;
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空！");
        }
    }

    public Node2 search(int value) {
        if (root != null) {
            return root.search(value);
        } else {
            return null;
        }
    }

    public Node2 searchParent(int value) {
        if (root != null) {
            return root.searchParent(value);
        } else {
            return null;
        }
    }

    //在删除有左右两个子节点的targetNode时,有以下两种方案可以保证二叉树依然有序;
    //1. 从targetNode的左子树中找到value最大的那个节点,将其value赋值给targetNode之后删除;
    //2. 从targetNode的右子树中找到value最小的那个节点,将其value赋值给targetNode之后删除;

    //方案1

    /**
     * @param node 传入的节点(当做二叉排序树的根节点)
     * @return 返回以node为根节点的二叉排序树的最小结点的值;
     */
    public int delRightTreeMin(Node2 node) {
        Node2 target = node;
        //循环地查找左子节点,找到值最小的左子节点
        while (target.left != null) {
            target = target.left;
        }
        //此时target指向最小节点;将其删除;
        delNode(target.value);
        return target.value;
    }

    //方案2
    public int delLeftTreeMax(Node2 node) {
        Node2 target = node;
        while (target.right != null) {
            target = target.right;
        }
        delNode(target.value);
        return target.value;
    }


    //删除节点;
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //获得要删除的节点targetNode
            Node2 targetNode = search(value);
            //可能没有找到;
            if (targetNode == null) {
                return;
            }
            //如果二叉树只有一个节点root,且恰好是targetNode
            //此时targetNode没有父节点;
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //否则去寻找targetNode的父节点;
            Node2 parent = searchParent(value);
            //如果要删除的是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //如果targetNode是parent的左子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                //如果要删除的节点有两棵子节点
            } else if (targetNode.left != null && targetNode.right != null) {
                //方案1：
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
                //方案2：
//                int maxVal = delLeftTreeMax(targetNode.left);
//                targetNode.value = maxVal;
                //如果要删除的节点只有一个子节点
            } else {
                if (targetNode.left != null) {//targetNode只有一个左子节点;
                    //此处需要分情况讨论：parent是否为空？即targetNode是否为root;
                    if (parent != null) {
                        if (parent.left != null && parent.left.value == value) {//targetNode是parent的左子节点;
                            parent.left = targetNode.left;
                        } else {//targetNode是parent的右子节点;
                            parent.right = targetNode.left;
                        }
                    } else {
                        //parent为空,则令root指向targetNode的左子节点
                        root = targetNode.left;
                    }
                } else {//targetNode只有一个右子节点
                    if (parent != null) {
                        if (parent.left != null && parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}

class Node2 {
    int value;
    Node2 left;
    Node2 right;

    public Node2(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node2{" +
                "value=" + value +
                '}';
    }

    //递归添加节点
    public void add(Node2 node) {
        if (node == null) {
            return;
        }
        //判断传入节点的value和当前子树根节点的value的大小
        if (node.value < this.value) {
            //如果当前子树的左子节点为空
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历;
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //查找要删除的节点
    public Node2 search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {//查询的值小于当前节点的值，则向左查找;
            if (this.left == null) {//如果左子节点为空;
                return null;
            }
            return this.left.search(value);
        } else {//查询的值大于当前节点的值，向右查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找某一节点的父节点
    public Node2 searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {//如果要查找的值小于当前节点的值，并且当前节点的左子节点不为空;
                return this.left.searchParent(value);//向左递归查找;
            } else if (value > this.value && this.right != null) {
                return this.right.searchParent(value);//向右递归查找;
            } else {
                return null;//未找到的情形;
            }
        }
    }
}