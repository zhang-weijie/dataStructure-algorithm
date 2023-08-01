package datastructure.hashtable;

import java.awt.*;
import java.util.Scanner;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-20 15:01
 */
public class EmpHashTableFinal {
    public static void main(String[] args) {
        HashTable1 hashTable1 = new HashTable1(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("get:查找雇员");
            System.out.println("list:显示雇员");
            System.out.println("exit:退出");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入姓名");
                    String name = scanner.next();
                    //创建雇员;
                    Emp1 emp1 = new Emp1(id, name);
                    hashTable1.add(emp1);
                    break;
                case "get":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    hashTable1.getEmpById(id);
                    break;
                case "list":
                    hashTable1.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class HashTable1 {
    private EmpLinkedList1[] empLinkedListArray;
    private int size;

    public HashTable1(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList1[size];
        //注意要初始化每一条链表;
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList1();
        }

    }

    //编写一个散列函数；使用取模;
    public int hashFun(int id) {
        return id % size;
    }

    public void add(Emp1 emp1) {
        //根据员工的id确定员工应该加到哪条链表;
        int empLinkedListNo = hashFun(emp1.id);
        empLinkedListArray[empLinkedListNo].add(emp1);
    }

    //遍历所有的链表;
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list();
        }
    }


    public void getEmpById(int id) {
        int empLinkedListNo = hashFun(id);
        Emp1 emp = empLinkedListArray[empLinkedListNo].getEmpById(id);
        if (emp != null) {
            System.out.println(emp.toString());
        } else {
            System.out.println("该员工不存在！");
        }
    }
}

class Emp1 {
    public int id;
    public String name;
    public Emp1 next;

    public Emp1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}

class EmpLinkedList1 {
    private Emp1 head;

    //假定添加到末尾;
    public void add(Emp1 emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp1 curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void list() {
        if (head == null) {
            System.out.println("链表为空！");
            return;
        }
        System.out.println("当前的链表信息是：");
        Emp1 curEmp = head;
        while (true) {
            System.out.println(curEmp.toString());
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
    }

    public Emp1 getEmpById(int id) {
        if (head == null) {
            return null;
        }

        Emp1 curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}