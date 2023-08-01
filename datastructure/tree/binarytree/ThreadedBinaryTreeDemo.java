package datastructure.tree.binarytree;

import java.util.ArrayList;

/**
 * 线索化二叉树
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-21 8:40
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode2 root = new HeroNode2(1, "tom");
        HeroNode2 node2 = new HeroNode2(3, "jack");
        HeroNode2 node3 = new HeroNode2(6, "smith");
        HeroNode2 node4 = new HeroNode2(8, "mary");
        HeroNode2 node5 = new HeroNode2(10, "king");
        HeroNode2 node6 = new HeroNode2(14, "tim");
        HeroNode2 node7 = new HeroNode2(20, "john");
        HeroNode2 node8 = new HeroNode2(25, "sophie");
        HeroNode2 node9 = new HeroNode2(30, "maria");
        HeroNode2 node10 = new HeroNode2(35, "david");


        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node4.setLeft(node7);
        node4.setRight(node8);
        node5.setLeft(node9);
        node5.setRight(node10);
        //前序：1,3,8,10,6,14
        //中序：8,3,10,1,14,6
        //后序：8,10,3,14,6,1

        //创建二叉树
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);

        //后序线索化二叉树;
        threadedBinaryTree.postThreadedNodes(root);
        //线性遍历后序线索化二叉树
        System.out.println("线性遍历后序线索化二叉树");
        threadedBinaryTree.postThreadedList();

        /*//中序线索化二叉树;
        threadedBinaryTree.infixThreadedNodes(root);
        //线性遍历中序线索化二叉树
        System.out.println("线性遍历中序线索化二叉树");
        threadedBinaryTree.infixThreadedList();//8,3,10,1,14,6*/

    }


}

class ThreadedBinaryTree extends BinaryTree {
    //为了实现线索化，需要创建指向当前节点的前驱节点的指针;
    //在线索化的过程中，它始终保存当前处理的节点(在中序遍历时的)前一个节点;
    //由于根节点没有前驱节点，故而pre初始化为null即可;
    //注意每次前、中或后序线索化之后要将pre还原为null;
    //也可各自创建ThreadedBinaryTree对象进行前中后序线索化
    private HeroNode1 pre;


    //为普通的BinaryTree添加前序线索化的方法;
    public void preThreadedNodes(HeroNode1 node) {
        if (node == null) {
            return;
        }

        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        if (node.getLeftType() == 0) {
            preThreadedNodes((HeroNode1) node.getLeft());
        }

        if (node.getRightType() == 0) {
            preThreadedNodes((HeroNode1) node.getRight());
        }
    }

    //为普通的BinaryTree添加中序线索化的方法;

    /**
     * @param node 表示需要线索化的节点
     */
    public void infixThreadedNodes(HeroNode1 node) {
        if (node == null) {//如果node为空，则不需要线索化;
            return;
        }
        //1.先线索化左子树
        infixThreadedNodes((HeroNode1) node.getLeft());
        //2.再线索化当前节点;
        //  a.尝试在当前节点与其前驱节点之间建立前驱关系;
        if (node.getLeft() == null) {//如果当前节点的左指针的指向为空
            node.setLeft(pre);//则将当前节点的左指针指向前驱节点
            node.setLeftType(1);//将当前节点的左指针的类型改为1
        }
        //  b.尝试在当前节点与其前驱节点之间建立后继关系;
        if (pre != null && pre.getRight() == null) {//此处需要额外判断前驱结点是否为空
            pre.setRight(node);//让前驱节点的右指针指向当前节点;
            pre.setRightType(1);
        }
        //  c.结束后将前驱节点更新为当前节点;
        pre = node;

        //3.最后线索化右子树;
        infixThreadedNodes((HeroNode1) node.getRight());
    }

    //后序线索化二叉树
    public void postThreadedNodes(HeroNode1 node) {
        if (node == null) {
            return;
        }
        postThreadedNodes((HeroNode1) node.getLeft());
        postThreadedNodes((HeroNode1) node.getRight());

        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
    }

    //线性(即非递归)遍历前序线索化的二叉树
    public void preThreadedList() {
        //首先获得根节点;
        HeroNode1 node = (HeroNode1) super.getRoot();
        while (node != null) {
            while (node.getLeftType() == 0) {
                System.out.println(node);
                node = (HeroNode1) node.getLeft();
            }
            while (node.getRightType() == 1) {
                System.out.println(node);
                node = (HeroNode1) node.getRight();
            }
            System.out.println(node);
            node = (HeroNode1) node.getRight();
        }
    }


    //线性(即非递归)遍历中序线索化的二叉树
    public void infixThreadedList() {
        //首先获得根节点;
        HeroNode1 node = (HeroNode1) super.getRoot();
        while (node != null) {
            //找到第一个leftType == 1的节点，即为首节点;
            while (node.getLeftType() == 0) {
                node = (HeroNode1) node.getLeft();
            }
            System.out.println(node);
            //从当前节点的后继节点开始进行中序线索化输出;
            while (node.getRightType() == 1) {
                node = (HeroNode1) node.getRight();
                System.out.println(node);
            }
            //在线索中断后将当前节点更新为当前节点的右子节点;
            node = (HeroNode1) node.getRight();
        }
    }

    //线性遍历后续线索化二叉树
    //需要为每一个节点添加指向父节点的指针;
    //需要记录上一次访问的指针以判断子树是否遍历完成;
    public void postThreadedList() {
        HeroNode2 root = (HeroNode2) super.getRoot();
        HeroNode2 node = root;
        //为了实现线性遍历后续线索化二叉树，需要引入一个end指针，记录遍历在当前的截止位置;
        HeroNode2 end = null;

        while (node.getLeftType() == 0) {
            node = (HeroNode2) node.getLeft();
        }
        node = node.getFather();

        while (root != end) {
            if (node.getLeft() != end && node.getRight() != end) {
                while (node.getLeftType() == 0) {
                    node = (HeroNode2) node.getLeft();
                }

                HeroNode2 father = node.getFather();
                while (node != father) {
                    System.out.println(node);
                    end = node;
                    node = (HeroNode2) node.getRight();
                }
            }

            if (end != node.getRight() && node.getRightType() == 0) {
                node = (HeroNode2) node.getRight();
                continue;
            }

            System.out.println(node);
            end = node;
            node = node.getFather();
        }
    }


    //由于HeroNode2对象多了一个指向父节点的指针，因此需要重新进行后序线索化
    //以便在线索化时将令子节点的father指针指向父节点;
    public void postThreadedNodes(HeroNode2 node) {
        if (node == null) {
            return;
        }

        //为当前节点的左右子节点的father赋值
        HeroNode2 tempLeft = (HeroNode2) node.getLeft();
        if (tempLeft != null && tempLeft.getFather() == null) {
            tempLeft.setFather(node);
        }
        HeroNode2 tempRight = (HeroNode2) node.getRight();
        if (tempRight != null && tempRight.getFather() == null) {
            tempRight.setFather(node);
        }

        postThreadedNodes((HeroNode2) node.getLeft());
        postThreadedNodes((HeroNode2) node.getRight());

        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;
    }
}


class HeroNode1 extends HeroNode {
    //为HeroNode添加两个新的属性
    //1.如果leftType为0表示left指向左子树，为1表示指向前驱节点;
    //2.如果rightType为0表示right指向右子树，为1表示指向后继节点;
    //3.这样在遍历时可以区分某一节点的左右指针究竟是指向左右子树，还是前驱/后继节点;
    private int leftType;
    private int rightType;

    public HeroNode1(int no, String name) {
        super(no, name);
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getLeftType() {
        return leftType;
    }

    public int getRightType() {
        return rightType;
    }
}

//为了实现线性遍历后续线索化二叉树，需要为每一个节点添加指向父节点的指针
class HeroNode2 extends HeroNode1 {
    private HeroNode2 father;


    public HeroNode2(int no, String name) {
        super(no, name);
    }

    public void setFather(HeroNode2 father) {
        this.father = father;
    }

    public HeroNode2 getFather() {
        return father;
    }

}