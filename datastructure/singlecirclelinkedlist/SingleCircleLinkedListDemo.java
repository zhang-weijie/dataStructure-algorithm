package datastructure.singlecirclelinkedlist;

/**
 * 约瑟夫问题：
 * 设编号为1,2,3...n的n个人围坐一圈，约定编号为k(1<=k<=n)的人从1开始报数，
 * 数到m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，以此类推，
 * 直到所有人出列为止，由此产生一个出队编号的序列。
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-23 14:32
 */
public class SingleCircleLinkedListDemo {
    public static void main(String[] args) {
        SingleCircleLinkedList list = new SingleCircleLinkedList();
//        list.add(new PersonNode(3,"吴用"));
//        list.add(new PersonNode(1,"宋江"));
//        list.add(new PersonNode(2,"卢俊义"));
//        list.list();
//        System.out.println(list.getSize());
//        list.delete(4);
//        list.delete(1);
//        list.delete(2);
//        list.delete(3);
//        list.list();
//        System.out.println(list.getSize());
//        list.addByOrder(new PersonNode(3,"吴用"));
//        list.addByOrder(new PersonNode(1,"宋江"));
//        list.addByOrder(new PersonNode(2,"卢俊义"));
//        list.list();
//        System.out.println(list.getSize());
//        list.update(new PersonNode(1,"公孙胜"));
//        list.update(new PersonNode(2,"关胜"));
//        list.update(new PersonNode(4,"时迁"));
//        list.update(new PersonNode(3,"呼延灼"));
//        list.update(new PersonNode(5,"林冲"));
//        list.list();
//        System.out.println(list.getSize());
//        System.out.println(list.getNode(1));
//        System.out.println(list.getNode(5));
//        list.clear();
//        list.list();
//        System.out.println(list.getSize());
        list.addByOrder(new PersonNode(2, "宋江"));
        list.addByOrder(new PersonNode(3, "宋江"));
        list.addByOrder(new PersonNode(1, "宋江"));
        list.addByOrder(new PersonNode(4, "宋江"));
        list.addByOrder(new PersonNode(6, "宋江"));
        list.addByOrder(new PersonNode(5, "宋江"));
        list.addByOrder(new PersonNode(7, "宋江"));
//        list.list();
//        System.out.println(list.getSize());
////        list.solveJoseph(3,2);
//        list.delete(1);
//        list.delete(3);
//        list.delete(7);
//        list.list();
//        System.out.println(list.getSize());
        list.solveJoseph(4, 5);
        list.list();
    }
}

class PersonNode {
    public int no;
    public String name;
    public PersonNode next;

    public PersonNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

class SingleCircleLinkedList {
    private PersonNode first;

    public void clear() {
        if (first == null) {
            System.out.println("列表为空！");
            return;
        }
        first = null;
    }

    public void list() {
        if (first == null) {
            System.out.println("链表为空!");
            return;
        }
        PersonNode cur = first;
        while (true) {
            System.out.println(cur.toString());
            if (cur.next.equals(first)) {
                break;
            }
            cur = cur.next;
        }
    }

    public void add(PersonNode person) {
        if (first == null) {
            first = person;
            first.next = person;
            return;
        }
        PersonNode temp = first.next;
        first.next = person;
        person.next = temp;
    }

    public void addByOrder(PersonNode person) {
        if (first == null) {
            add(person);
            return;
        }
        if (first.no == person.no) {
            System.out.println("该节点已存在！");
            return;
        }
        if (first.no > person.no) {
            //如果新添加的节点序号小于首节点则将其置于首节点之前;
            PersonNode cur1 = first;
            while (!cur1.next.equals(first)) {
                cur1 = cur1.next;
            }
            cur1.next = person;
            person.next = first;
            //再将首节点前移一个节点;
            first = person;
            return;
        }
        PersonNode cur2 = first;
        boolean flag = false;
        while (true) {
            PersonNode cur3 = cur2.next;
            if (cur3.no == person.no) {
                System.out.println("该节点已存在！");
                return;
            }
            if (cur3.equals(first)) {
                break;
            }
            if (cur3.no > person.no) {
                flag = true;
                break;
            }
            cur2 = cur3;
        }
        if (flag) {
            PersonNode next = cur2.next;
            cur2.next = person;
            person.next = next;
            return;
        }
        PersonNode cur4 = first;
        while (!cur4.next.equals(first)) {
            cur4 = cur4.next;
        }
        cur4.next = person;
        person.next = first;
    }

    public void update(PersonNode person) {
        if (first == null) {
            System.out.println("链表为空！");
            return;
        }
        PersonNode cur = first.next;
        //声明flag判断是否存在要修改的节点;
        boolean flag = false;
        while (true) {
            if (cur.no == person.no) {
                flag = true;
                break;
            }
            if (cur.equals(first)) {
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            cur.name = person.name;
            return;
        }
        System.out.println("要修改的节点不存在！");
    }

    public void delete(int no) {
        if (first == null) {
            System.out.println("链表为空！");
            return;
        }
        if (first.no == no) {
            if (first.equals(first.next)) {
                first = null;
                return;
            }
            PersonNode cur = first;
            while (!cur.next.equals(first)) {
                cur = cur.next;
            }
            PersonNode next = first.next;
            cur.next = next;
            first = next;
            return;
        }
        PersonNode cur1 = first;
        while (true) {
            PersonNode cur2 = cur1.next;
            if (cur2.no == no) {
                cur1.next = cur2.next;
                return;
            }
            if (cur2.next.equals(first)) {
                break;
            }
            cur1 = cur2;
        }
        System.out.println("该节点不存在！");
    }

    //定义一个getSize()获取环形单链表的长度;
    public int getSize() {
        if (first == null) {
            return 0;
        }
        if (first.equals(first.next)) {
            return 1;
        }
        PersonNode cur = first.next;
        int size = 2;
        while (true) {
            if (cur.next.equals(first)) {
                break;
            }
            cur = cur.next;
            size++;
        }
        return size;
    }

    //定义一个getNode()方法获取指定节点;
    public PersonNode getNode(int no) {
        if (first == null) {
            System.out.println("链表为空！");
            return null;
        }
        if (first.next == null) {
            if (first.no == no) {
                return first;
            }
            System.out.println("该节点不存在！");
            return null;
        }
        PersonNode cur = first.next;
        while (true) {
            if (cur.no == no) {
                return cur;
            }
            if (cur.next.equals(first)) {
                break;
            }
            cur = cur.next;
        }
        System.out.println("该节点不存在！");
        return null;
    }

    public void solveJoseph(int startNo, int distance) {
        PersonNode startNode = getNode(startNo);
        if (startNode == null) {
            System.out.println("该起始节点不存在！");
            return;
        }
        int size;
        while ((size = getSize()) > 0) {
            PersonNode cur1 = startNode;
            int count = distance % size;
            while (count-- > 0) {
                cur1 = cur1.next;
            }
            System.out.println(cur1.toString());
            startNode = cur1.next;
            delete(cur1.no);
        }
    }
}