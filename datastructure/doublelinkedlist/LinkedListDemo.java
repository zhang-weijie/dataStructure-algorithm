package datastructure.doublelinkedlist;

import javax.sound.midi.Soundbank;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-23 13:22
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(new HeroNode(3, "宋江", "呼保义"));
        list.addByOrder(new HeroNode(1, "宋江", "呼保义"));
        list.addByOrder(new HeroNode(2, "宋江", "呼保义"));
        list.addByOrder(new HeroNode(2, "卢俊义", "玉麒麟"));
        System.out.println("演示按顺序添加节点");
        list.list(list.getHeadNode());
        System.out.println("演示修改节点");
        list.update(new HeroNode(4, "公孙胜", "入云龙"));
        list.update(new HeroNode(3, "吴用", "智多星"));
        list.list(list.getHeadNode());
        System.out.println("演示删除节点");
        list.delete(4);
        list.delete(3);
        list.list(list.getHeadNode());
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode pre;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

class DoubleLinkedList {
    private HeroNode headNode = new HeroNode(0, "", "");

    public HeroNode getHeadNode() {
        return headNode;
    }

    //list()遍历
    public void list(HeroNode headNode) {
        if (headNode.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode currentNode = headNode.next;
        while (currentNode != null) {
            System.out.println(currentNode.toString());
            currentNode = currentNode.next;
        }
    }

    //add()添加
    public void add(HeroNode heroNode) {
        HeroNode currentNode = headNode;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = heroNode;
        heroNode.pre = currentNode;
    }

    //addByOrder()按顺序添加
    public void addByOrder(HeroNode heroNode) {
        HeroNode currentNode = headNode;
        while (currentNode.next != null) {
            if (!(currentNode.next.no < heroNode.no)) {
                break;
            }
            currentNode = currentNode.next;
        }
        //保存currentNode.next;并判断其是否为null，即判断新添加的节点是否在末尾;
        HeroNode nextNode = currentNode.next;
        if (nextNode == null) {
            currentNode.next = heroNode;
            heroNode.pre = currentNode;
            return;
        }
        if (heroNode.no == nextNode.no) {
            System.out.println("该座次上的英雄已存在！");
            return;
        }
        currentNode.next = heroNode;
        heroNode.pre = currentNode;
        heroNode.next = nextNode;
        nextNode.pre = heroNode;
    }

    //update()修改某个节点的内容;
    public void update(HeroNode heroNode) {
        HeroNode currentNode = headNode.next;
        if (currentNode == null) {
            System.out.println("列表为空！");
            return;
        }
        //声明一个flag用以表示是否找到该节点;
        boolean flag = false;
        while (true) {
            if (currentNode == null) {
                break;
            }
            if (currentNode.no == heroNode.no) {
                flag = true;
                break;
            }
            currentNode = currentNode.next;
        }
        if (flag) {
            currentNode.no = heroNode.no;
            currentNode.name = heroNode.name;
            currentNode.nickName = heroNode.nickName;
            return;
        }
        System.out.println("该节点不存在！");
    }

    //delete()删除节点
    public void delete(int no) {
        HeroNode currentNode = headNode.next;
        if (currentNode == null) {
            System.out.println("列表为空！");
            return;
        }
        //声明一个flag用以确定是否找到该节点;
        boolean flag = false;
        while (true) {
            if (currentNode == null) {
                break;
            }
            if (currentNode.no == no) {
                flag = true;
                break;
            }
            currentNode = currentNode.next;
        }
        if (flag) {
            //注意此处双链表删除节点操作与单链表删除节点操作的区别
            currentNode.pre.next = currentNode.next;
            //判断要删除的节点是否为最后一个节点;是则不进行下面操作;
            if (currentNode.next != null) {
                currentNode.next.pre = currentNode.pre;
            }
            return;
        }
        System.out.println("该节点不存在！");
    }
}