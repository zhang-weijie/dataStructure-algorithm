package datastructure.stack;

import javax.swing.tree.TreeCellRenderer;

/**
 * 以单链表的形式实现栈
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-25 16:21
 */
public class SingleListStackDemo {
    public static void main(String[] args) {
        SingleListStack stack = new SingleListStack();
        stack.push(new PersonNode(1, "宋江"));
        stack.push(new PersonNode(3, "吴用"));
        stack.push(new PersonNode(2, "卢俊义"));
        stack.show();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.show();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.show();
    }
}

class SingleListStack {
    private PersonNode head = new PersonNode(0, "");

    public void show() {
        if (isEmpty()) {
            System.out.println("栈空！");
            return;
        }
        PersonNode cur = head.getNext();
        while (true) {
            if (cur == null) {
                break;
            }
            System.out.println(cur.toString());
            cur = cur.getNext();
        }
    }

    public boolean isEmpty() {
        return head.getNext() == null;
    }

    public void push(PersonNode person) {
        if (isEmpty()) {
            head.setNext(person);
            return;
        }
        PersonNode next = head.getNext();
        head.setNext(person);
        person.setNext(next);
    }

    public PersonNode pop() {
        if (isEmpty()) {
            System.out.println("栈空！");
            return null;
        }
        if (head.getNext().getNext() == null) {
            PersonNode target = head.getNext();
            head.setNext(null);
            return target;
        }
        PersonNode target = head.getNext();
        PersonNode next = target.getNext();
        head.setNext(next);
        return target;
    }
}

class PersonNode {
    private int no;
    private String name;
    private PersonNode next;

    public PersonNode(int no, String name) {
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

    public PersonNode getNext() {
        return next;
    }

    public void setNext(PersonNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "PersonNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
