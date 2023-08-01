package datastructure.singlecirclelinkedlist;

import java.util.concurrent.ForkJoinPool;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-25 10:16
 */
public class SingleCircleLinkedListDemo1 {
    public static void main(String[] args) {

    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}

class SingleCircleLinkedList1 {
    //创建一个first节点
    private Boy first;

    public void addBoy(int nums) {
        //对nums做校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        //声明辅助指针，帮助构建环形链表;
        Boy curBoy = null;
        //使用for循环创建环形列表;
        for (int i = 1; i < nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //若果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空！");
            return;
        }
        //因为first不能变更，因此使用辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.println(curBoy.toString());
            if (curBoy.getNext().equals(first)) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //startNo表示从第几个小孩开始;
    //countNum表示数几下;
    //nums表示最初有多少个小孩在圈中
    public void countBoy(int startNo, int countNum, int nums) {
        //校验数据
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误！请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext().equals(first)) {
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true) {
            if (helper.equals(first)) {
                break;
            }
            //让first和helper指针同时移动countNum - 1;
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点就是要出圈的点;
            System.out.println(first.toString());
            //这时让first指向的节点出圈;
            first = first.getNext();
            first.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩是%d号\n", first.getNo());
    }
}