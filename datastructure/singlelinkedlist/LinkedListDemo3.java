package datastructure.singlelinkedlist;


import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-21 19:12
 */
public class LinkedListDemo3 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //获得单链表中有效节点的个数
        SingleLinkedList2 singleLinkedList2 = new SingleLinkedList2();
        singleLinkedList2.add(new HeroNode2(1, "宋江", "呼保义"));
        singleLinkedList2.add(new HeroNode2(3, "宋江", "呼保义"));
        singleLinkedList2.add(new HeroNode2(5, "宋江", "呼保义"));
        int length = getLength(singleLinkedList2.getHeadNode());
        System.out.println(length);
        SingleLinkedList2 newList = swap(singleLinkedList2.getHeadNode());
        newList.list();
        singleLinkedList2.list();
        //反转链表并打印;
        reverse(newList.getHeadNode());
        //不反转链表但反向打印;
        reversePrint(newList.getHeadNode());
        SingleLinkedList2 list2 = new SingleLinkedList2();
        list2.add(new HeroNode2(2, "卢俊义", "玉麒麟"));
        list2.add(new HeroNode2(4, "卢俊义", "玉麒麟"));
        list2.add(new HeroNode2(5, "卢俊义", "玉麒麟"));
        list2.add(new HeroNode2(6, "卢俊义", "玉麒麟"));
        SingleLinkedList2 list3 = new SingleLinkedList2();
        list3.add(new HeroNode2(1, "宋江", "呼保义"));
        list3.add(new HeroNode2(3, "宋江", "呼保义"));
        list3.add(new HeroNode2(5, "宋江", "呼保义"));
        list3.add(new HeroNode2(7, "宋江", "呼保义"));
        //按顺序合并链表
        System.out.println("合并链表并反向打印");
        HeroNode2 integer = integer(list3.getHeadNode(), list2.getHeadNode());
        reverse(integer);
    }

    public static int getLength(HeroNode2 headNode) {
        if (headNode.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode2 currentNode = headNode.next;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }

    public static HeroNode2 findLastIndexNode(HeroNode2 headNode, int index) {
        if (headNode.next == null) {
            System.out.println("链表为空！");
            return null;
        }
        int size = getLength(headNode);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode2 currentNode = headNode.next;
        for (int i = 0; i < size - index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public static SingleLinkedList2 swap(HeroNode2 headNode) {
        if (headNode.next == null) {
            System.out.println("链表为空！");
            return null;
        }
        SingleLinkedList2 newList = new SingleLinkedList2();
        HeroNode2 newHeadNode = newList.getHeadNode();
        int size = getLength(headNode);
        while (size-- > 0) {
            HeroNode2 formerNode = headNode;
            while (true) {
                if (formerNode.next == null) {
                    System.out.println("链表为空！");
                    return null;
                } else {
                    if (formerNode.next.next == null) {
                        break;
                    }
                    formerNode = formerNode.next;
                }
            }
            newHeadNode.next = formerNode.next;
            formerNode.next = null;
            newHeadNode = newHeadNode.next;
        }
        return newList;
    }

    //反转链表
    public static void reverse(HeroNode2 headNode) {
        //若当前链表为空或只有一个节点，则无需翻转，直接返回
        if (headNode.next == null || headNode.next.next == null) {
            return;
        }
        //定义一个辅助指针，帮助我们遍历原来的链表
        HeroNode2 currentNode = headNode.next;
        HeroNode2 nextNode = null;
        HeroNode2 newHeadNode = new HeroNode2(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并将其放在新的链表的最前段;
        while (currentNode != null) {
            nextNode = currentNode.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            currentNode.next = newHeadNode.next;//将currentNode的下一个节点指向新链表的最前端;
            newHeadNode.next = currentNode;
            currentNode = nextNode;
        }
        headNode.next = newHeadNode.next;
        HeroNode2 tempNode = headNode.next;
        while (tempNode != null) {
            System.out.println(tempNode.toString());
            tempNode = tempNode.next;
        }
    }

    //可以利用栈这个数据结构，将各个节点压入栈中，然后利用栈的先进后出特点，实现逆序打印;
    //举例演示栈的使用，利用集合实现;
    public static void reversePrint(HeroNode2 headNode) {
        if (headNode.next == null) {
            return;
        }
        Stack<HeroNode2> stack = new Stack<>();
        HeroNode2 currentNode = headNode.next;
        while (currentNode != null) {
            stack.push(currentNode);
            currentNode = currentNode.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //合并两个有序的单链表，合并之后的链表仍然有序
    public static HeroNode2 integer(HeroNode2 headNode1, HeroNode2 headNode2) {
        if (headNode1.next == null) {
            if (headNode2.next == null) {
                return null;
            }
            return headNode2;
        } else if (headNode2.next == null) {
            return headNode1;
        } else {
            //声明一个新链表;
            HeroNode2 newHead = new HeroNode2(0, "", "");
            HeroNode2 newHeadNode = newHead;
            //声明指针变量;
            HeroNode2 cur1 = headNode1.next;
            HeroNode2 cur2 = headNode2.next;
            //平行比较两个链表同一索引位置的数据大小;
            while (cur1 != null && cur2 != null) {
                //保存两个节点的下一节点;
                HeroNode2 next1 = cur1.next;
                HeroNode2 next2 = cur2.next;
                int compare = cur1.compareTo(cur2);
                if (compare < 0) {
                    //将两个节点按顺序添加到新链表中;
                    newHeadNode.next = cur1;
                    newHeadNode.next.next = cur2;
                    //将新链表所在节点后移两个节点：
                    newHeadNode = newHeadNode.next.next;
                } else if (compare > 0) {
                    newHeadNode.next = cur2;
                    newHeadNode.next.next = cur1;
                    //将新链表所在节点后移两个节点：
                    newHeadNode = newHeadNode.next.next;
                } else {
                    System.out.println("检测到同序号节点！");
                    System.out.println("a:" + cur1.toString());
                    System.out.println("b:" + cur2.toString());
                    System.out.print("请选择要保留的节点[a/b]:");
                    char choice = ' ';
                    while (true) {
                        choice = scanner.next().charAt(0);
                        if (choice == 'a') {
                            newHeadNode.next = cur1;
                            break;
                        } else if (choice == 'b') {
                            newHeadNode.next = cur2;
                            break;
                        }
                        System.out.print("输入无效，请重新选择：");
                    }
                    //将新链表所在节点后移一个节点：
                    newHeadNode = newHeadNode.next;
                }
                //将两个链表指针后移一个节点;
                cur1 = next1;
                cur2 = next2;
            }
            //将有剩余的链表的剩余部分添加到新链表中;
            if (cur1 == null) {
                newHeadNode.next = cur2;
            } else if (cur2 == null) {
                newHeadNode.next = cur1;
            }
            return newHead;
        }
    }
}

class HeroNode2 implements Comparable {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }


    @Override
    public int compareTo(@NotNull Object o) {
        if (o instanceof HeroNode2) {
            HeroNode2 tempNode = (HeroNode2) o;
            if (this.no > tempNode.no) {
                return 1;
            } else if (this.no < tempNode.no) {
                return -1;
            } else {
                return 0;
            }
        } else {
            throw new RuntimeException();
        }
    }
}

class SingleLinkedList2 {
    private HeroNode2 headNode;

    public SingleLinkedList2() {
        this.headNode = new HeroNode2(0, "", "");
    }

    public HeroNode2 getHeadNode() {
        return headNode;
    }

    public void add(HeroNode2 heroNode2) {
        HeroNode2 temp = headNode;
        //遍历链表，找到最后一个节点；
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode2;
    }

    public void list() {
        if (headNode.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode2 temp = headNode.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    public void addByOrder(HeroNode2 heroNode2) {
        HeroNode2 temp = heroNode2;
        //标志添加的英雄是否存在，默认不存在;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode2.no) {
                break;
            } else if (temp.next.no == heroNode2.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag;
        if (flag) {
            System.out.println("该座次上的英雄已存在！");
        } else {
            heroNode2.next = temp.next;
            temp.next = heroNode2;
        }
    }

    public void update(HeroNode2 newHeroNode) {
        if (headNode.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode2 temp = headNode.next;
        //标识是否找到该节点;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.println("该座次不存在！");
        }
    }

    public void del(int no) {
        HeroNode2 temp = headNode;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("该节点不存在！");
        }
    }
}