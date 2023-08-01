package datastructure.hashtable;

import java.sql.SQLOutput;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-20 10:37
 */
public class EmpHashTable {
    static int maxSize = 16;

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(maxSize);
        hashTab.add(new Emp(34, "Tom", "Avenue 1"));
        hashTab.add(new Emp(1, "Jerry", "Street 1"));
        hashTab.del(34);
        hashTab.set(34, new Emp(17, "Pike", "Road 1"));
        hashTab.list(17);
    }
}


class HashTab {
    EmpLinkedList[] empLinkedListArr;

    //初始化长度为maxSize的数组;
    public HashTab(int maxSize) {
        this.empLinkedListArr = new EmpLinkedList[maxSize];
        for (int i = 0; i < maxSize; i++) {
            empLinkedListArr[i] = new EmpLinkedList();
        }
    }

    //定义一个散列函数用于确定要处理的Emp对象在哪一条EmpLinkedList上;
    private int hashFun(int id) {
        return id % empLinkedListArr.length;

    }

    //实现EmpLinkedList的增删改查功能;
    public void add(Emp emp) {
        //判断emp.id对应的EmpLinkedList是否已经创建;否则新建一个
        EmpLinkedList temp = empLinkedListArr[hashFun(emp.id)];
        if (temp == null) {
            temp = new EmpLinkedList();
        }
        temp.add(emp);
    }

    public void del(int id) {
        empLinkedListArr[hashFun(id)].del(id);
    }

    public void set(int id, Emp emp) {
        empLinkedListArr[hashFun(emp.id)].set(id, emp);
    }

    public Emp get(int id) {
        return empLinkedListArr[hashFun(id)].get(id);
    }

    public void list(int id) {
        empLinkedListArr[hashFun(id)].list();
    }

}

class EmpLinkedList {
    Emp head;

    public EmpLinkedList() {
        this.head = new Emp(0, "", "");
    }

    public void add(Emp emp) {
        Emp temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = emp;
    }

    public void del(int id) {
        Emp temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.id == id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("该员工不存在！");
        }
    }

    public void set(int id, Emp emp) {
        Emp temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.id == id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next.name = emp.name;
            temp.next.addr = emp.addr;
        } else {
            System.out.println("该员工不存在！");
        }
    }

    public Emp get(int id) {
        Emp temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.id == id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            return temp.next;
        } else {
            return null;
        }
    }

    public void list() {
        Emp temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
}

class Emp {
    int id;
    String name;
    String addr;
    Emp next;

    public Emp(int id, String name, String addr) {
        this.id = id;
        this.name = name;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
