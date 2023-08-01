package datastructure.tree.binarytree;

import java.util.IdentityHashMap;
import java.util.TreeMap;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-20 16:41
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一棵二叉树;
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点;
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        //手动创建二叉树;也可以递归创建;
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        //把root放到二叉树中;
        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历");
        binaryTree.preOrder();//1,2,3,4
        System.out.println("中序遍历");
        binaryTree.infixOrder();//2,1,3,4
        System.out.println("后序遍历");
        binaryTree.postOrder();//2,4,3,1
        System.out.println("前序查找");
        binaryTree.preOrderSearch(4);
        System.out.println("中序查找");
        binaryTree.infixOrderSearch(2);
        System.out.println("后序查找");
        binaryTree.postOrderSearch(3);
        System.out.println("删除节点");
        binaryTree.delNode(3);
        System.out.println("中序查找");
        binaryTree.infixOrderSearch(3);
        binaryTree.infixOrderSearch(4);
        binaryTree.infixOrderSearch(2);

    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    //前序遍历
    public void preOrder() {
        if (root == null) {
            System.out.println("二叉树为空！");
            return;
        }
        root.preOrder();
    }

    //中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉树为空！");
            return;
        }
        root.infixOrder();
    }

    //后序遍历
    public void postOrder() {
        if (root == null) {
            System.out.println("二叉树为空！");
            return;
        }
        root.postOrder();
    }

    //前序查找
    public void preOrderSearch(int no) {
        HeroNode res = root.preOrderSearch(no);
        if (res == null) {
            System.out.println("该节点不存在！");
            return;
        }
        System.out.println(res.toString());
    }

    //中序查找
    public void infixOrderSearch(int no) {
        HeroNode res = root.infixOrderSearch(no);
        if (res == null) {
            System.out.println("该节点不存在！");
            return;
        }
        System.out.println(res.toString());
    }

    //后续查找
    public void postOrderSearch(int no) {
        HeroNode res = root.postOrderSearch(no);
        if (res == null) {
            System.out.println("该节点不存在！");
            return;
        }
        System.out.println(res.toString());
    }

    //递归删除节点：如果是叶节点，就删除节点，如果是非叶子节点，则删除子树;
    public void delNode(int no) {
        if (root == null) {
            System.out.println("二叉树为空！");
            return;
        }
        if (root.getNo() == no) {
            root = null;
            return;
        }
        root.delNode(no);
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this.toString());
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this.toString());
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this.toString());
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.preOrderSearch(no);
        }
//        if (res != null && res.no == no){//不需要判断res.no == no
        if (res != null) {//因为this.left.preOrderSearch(no)返回的要么是目标节点，要么是null;
            return res;//所以只要res != null,则必然是目标节点;
        }
        if (this.right != null) {
            res = this.right.preOrderSearch(no);
        }
        return res;

    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.infixOrderSearch(no);
        }
//        if (res != null && res.no == no){
        if (res != null) {
            return res;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            res = this.right.infixOrderSearch(no);
        }
        return res;
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.postOrderSearch(no);
        }
//        if (res != null && res.no == no){
        if (res != null) {
            return res;
        }
        if (this.right != null) {
            res = this.right.postOrderSearch(no);
        }
        if (this.no == no) {
            return this;
        }
        return res;
    }

    //递归删除节点：如果是叶节点，就删除节点，如果是非叶子节点，则删除子树;
    public void delNode(int no) {
//        if (this.no == no){
//            this = null;//这个表达式不合法，this不能单独出现，必须this.xxx
//            return;
//        }

        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

}


/*class BinaryTree {
    private HeroNode root;
    public void setRoot(HeroNode root){
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    //前序遍历
    public void preOrder(){
        if (root != null){
            root.preOrder();
        } else {
            System.out.println("二叉树为空！");
        }
    }

    //中序遍历
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        } else {
            System.out.println("二叉树为空！");
        }
    }

    //后序遍历
    public void postOrder(){
        if (root != null){
            root.postOrder();
        } else {
            System.out.println("二叉树为空！");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no){
        if (root != null){
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //递归删除节点
    public void delNode(int no){
        if (root != null){
            //此处应先判断root是否为要删除的节点，否则之后无法回溯，因为二叉树是单向的;
            if (root.getNo() == no){
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("二叉树为空！");
        }
    }
}

//创建节点类
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder(){
        //先输出父节点;
        System.out.println(this);
        //递归向左子树前序遍历
        if (left != null){
            left.preOrder();
        }
        //递归向右子树前序遍历
        if (right != null){
            right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        if (left != null){
            left.infixOrder();
        }
        System.out.println(this);
        if (right != null){
            right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        if (left != null){
            left.postOrder();
        }
        if (right != null){
            right.postOrder();
        }
        System.out.println(this);
    }

    //前序查找
    public HeroNode preOrderSearch(int no){
        //比较当前节点是不是目标
        if (this.no == no){
            return this;//若是则返回;
        }
        HeroNode resNode = null;
        if (this.left != null){//判断左子节点是否为空;
            resNode = this.left.preOrderSearch(no);//否,则向左递归查找;
        }
        if (resNode != null){//如果resNode不为空，则说明上面的向左递归查找找到了;
            return resNode;
        }
        if (this.right != null){//否则判断右子节点是否为空;
            resNode = this.right.preOrderSearch(no);//否,则向右递归查找;
        }
        return resNode;//如果向右递归找到了,则resNode即为目标;否则resNode仍为null;
    }

    //中序查找
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        if (this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序查找;
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        return resNode;
    }

    //递归删除节点：如果是叶节点，就删除节点，如果是非叶子节点，则删除子树;
    public void delNode(int no){


        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }

        if (this.left != null){
            this.left.delNode(no);
        }

        if (this.right != null){
            this.right.delNode(no);
        }
    }
}*/
