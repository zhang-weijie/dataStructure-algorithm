package datastructure.singlelinkedlist;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-21 6:41
 */
public class LinkedListDemo2 {
    static Scanner scanner = new Scanner(System.in);
    static boolean loop = true;

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        char command = ' ';
        while (loop) {
            System.out.print("a(add):增\t");
            System.out.print("r(remove):删\t");
            System.out.print("s(set):改\t");
            System.out.print("g(get):查\t");
            System.out.print("l(list):列\t");
            System.out.println("e(exit):退\t");
            command = scanner.next().charAt(0);
            executeCommand(list, command);
        }
    }

    public static void executeCommand(DoubleLinkedList list, char command) {
        int numOfParas = 0;
        String methodName = "";
        switch (command) {
            case 'a':
                numOfParas = 3;
                methodName = "addNode";
                break;
            case 's':
                numOfParas = 3;
                methodName = "setNode";
                break;
            case 'r':
                numOfParas = 1;
                methodName = "removeNode";
                break;
            case 'g':
                numOfParas = 1;
                methodName = "getNodePlus";
                break;
            case 'l':
                numOfParas = 0;
                methodName = "list";
                break;
            case 'e':
                loop = false;
                return;
            default:
                throw new RuntimeException();
        }
        Class clazz = DoubleLinkedList.class;

        try {
            if (numOfParas == 0) {
                try {
                    clazz.getDeclaredMethod(methodName).invoke(list);
                    return;
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
            System.out.print("请输入英雄座次：");
            int no;
            if ((no = scanner.nextInt()) < 0) {
                throw new RuntimeException();
            }
            if (numOfParas == 1) {
                clazz.getDeclaredMethod(methodName, int.class).invoke(list, no);
                return;
            }
            System.out.print("请输入英雄姓名：");
            String name = scanner.next();
            System.out.print("请输入英雄绰号：");
            String nickName = scanner.next();
            clazz.getDeclaredMethod(methodName, int.class, String.class, String.class).invoke(list, no, name, nickName);
            return;
        } catch (RuntimeException e) {
            System.out.println("输入不合法或列表为空/该座次不存在！");
        } catch (InvocationTargetException e) {
            System.out.println("输入不合法或列表为空/该座次不存在！");
        } catch (Exception e) {
            scanner.next();
            System.out.println("输入不合法！");
        }
    }
}


class HeroNode1 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode1 former;
    public HeroNode1 next;

    public HeroNode1(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode1{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

class DoubleLinkedList {
    private HeroNode1 headNode = new HeroNode1(0, "", "");

    public void addNode(int no, String name, String nickName) {
        HeroNode1 currentNode = headNode.next;
        if (currentNode != null) {
            while (currentNode.no < no) {
                if (currentNode.next == null) {
                    HeroNode1 newNode = new HeroNode1(no, name, nickName);
                    currentNode.next = newNode;
                    newNode.former = currentNode;
                    return;
                }
                currentNode = currentNode.next;
            }
            if (currentNode.no == no) {
                System.out.println("该座次上的英雄已存在！");
                return;
            }
            HeroNode1 newNode = new HeroNode1(no, name, nickName);
            currentNode.former.next = newNode;
            newNode.former = currentNode.former;
            newNode.next = currentNode;
            currentNode.former = newNode;
            return;
        }
        HeroNode1 newNode = new HeroNode1(no, name, nickName);
        headNode.next = newNode;
        newNode.former = headNode;
    }

    public void removeNode(int no) {
        HeroNode1 targetNode = getNode(no);
        HeroNode1 formerNode = targetNode.former;
        HeroNode1 nextNode = targetNode.next;
        if (nextNode != null) {
            nextNode.former = formerNode;
        }
        formerNode.next = nextNode;
    }

    public void setNode(int no, String name, String nickName) {
        try {
            HeroNode1 targetNode = getNode(no);
            targetNode.setName(name);
            targetNode.setNickName(nickName);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void getNodePlus(int no) {
        HeroNode1 currentNode = getNode(no);
        System.out.println(currentNode.toString());
    }

    public HeroNode1 getNode(int no) {
        HeroNode1 currentNode = headNode.next;
        if (currentNode != null) {
            while (currentNode.no < no) {
                currentNode = currentNode.next;
            }
            if (currentNode.no == no) {

                return currentNode;
            }
        }
        throw new RuntimeException();
    }

    public void list() {
        HeroNode1 currentNode = headNode.next;
        if (currentNode != null) {
            while (currentNode != null) {
                System.out.println(currentNode.toString());
                currentNode = currentNode.next;
            }
            return;
        }
        throw new RuntimeException();
    }
}