package datastructure.singlelinkedlist;

import java.util.Scanner;

/**
 * 增删改查列表
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-19 19:05
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        Scanner scanner = new Scanner(System.in);
        char command = ' ';
        boolean loop = true;
        while (loop) {
            System.out.print("a：增;");
            System.out.print("d：删;");
            System.out.print("c：改;");
            System.out.print("g：查;");
            System.out.print("l: 列;");
            System.out.println("e: 退");

            command = scanner.next().charAt(0);
            switch (command) {
                case 'a':
                    while (true) {
                        try {
                            int no;
                            String name;
                            String nickName;
                            System.out.print("请输入英雄座次：");
                            if ((no = scanner.nextInt()) < 1) {
                                System.out.println("输入不合法！");
                                continue;
                            }
                            System.out.print("请输入英雄姓名：");
                            name = scanner.next();
                            System.out.print("请输入英雄绰号：");
                            nickName = scanner.next();
                            try {
                                list.addHero(no, name, nickName);
                            } catch (Exception e) {
                                System.out.println("该座次上的英雄已存在！");
                            }
                            break;
                        } catch (Exception e) {
                            scanner.next();
                            System.out.println("输入不合法！");
                        }
                    }
                    break;
                case 'd':
                    while (true) {
                        try {
                            int no;
                            System.out.print("请输入要删除的英雄座次：");
                            if ((no = scanner.nextInt()) < 1) {
                                System.out.println("输入不合法！");
                                continue;
                            }
                            list.delHero(no);
                            break;
                        } catch (Exception e) {
                            scanner.next();
                            System.out.println("输入不合法！");
                        }
                    }
                    break;
                case 'c':
                    while (true) {
                        try {
                            int no;
                            String name;
                            String nickName;
                            System.out.print("请输入要修改的英雄座次：");
                            if ((no = scanner.nextInt()) < 1) {
                                System.out.println("输入不合法！");
                                continue;
                            }
                            System.out.print("请输入新英雄的姓名：");
                            name = scanner.next();
                            System.out.print("请输入新英雄的绰号：");
                            nickName = scanner.next();
                            list.changeHero(no, name, nickName);
                            break;
                        } catch (Exception e) {
                            scanner.next();
                            System.out.println("输入不合法！");
                        }
                    }
                    break;
                case 'g':
                    while (true) {
                        try {
                            int no;
                            System.out.print("请输入要查找的英雄座次：");
                            if ((no = scanner.nextInt()) < 1) {
                                System.out.println("输入不合法！");
                                continue;
                            }
                            list.getHeroNode(no);
                            break;
                        } catch (Exception e) {
                            scanner.next();
                            System.out.println("输入不合法！");
                        }
                    }
                    break;
                case 'l':
                    list.list();
                    break;
                case 'e':
                    System.out.println("退出程序...");
                    loop = false;
                    break;
                default:
                    System.out.println("输入不合法！");
            }
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public HeroNode(int hNo, String hName, String hNickName) {
        no = hNo;
        name = hName;
        nickName = hNickName;
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

class SingleLinkedList {
    private HeroNode headNode = new HeroNode(0, "", "");

    public void list() {
        HeroNode formerNode = headNode;
        HeroNode temp = null;
        while ((temp = formerNode.next) != null) {
            System.out.println(temp.toString());
            formerNode = formerNode.next;
        }
        if (formerNode == headNode) {
            System.out.println("链表为空！");
        }
    }

    public void addHero(int no, String name, String nickName) {
        HeroNode formerNode = headNode;
        HeroNode temp = null;
        while (formerNode.next != null) {
            if (no < formerNode.next.no) {
                temp = formerNode.next;
                formerNode.next = new HeroNode(no, name, nickName);
                formerNode.next.next = temp;
                return;
            } else if (no > formerNode.next.no) {
                formerNode = formerNode.next;
            } else {
                throw new RuntimeException();
            }
        }
        formerNode.next = new HeroNode(no, name, nickName);
    }

    public void delHero(int no) {
        HeroNode formerNode = null;
        HeroNode temp = null;
        if ((formerNode = getFormerHeroNode(no)) != null) {
            temp = formerNode.next.next;
            formerNode.next = temp;
            return;
        }
        System.out.println("该英雄不存在！");
    }

    public void changeHero(int no, String name, String nickName) {
        HeroNode formerNode = null;
        HeroNode temp = null;
        if ((formerNode = getFormerHeroNode(no)) != null) {
            temp = formerNode.next.next;
            formerNode.next = new HeroNode(no, name, nickName);
            formerNode.next.next = temp;
            return;
        }
        System.out.println("该英雄不存在！");
    }

    public void getHeroNode(int no) {
        HeroNode formerNode = null;
        if ((formerNode = getFormerHeroNode(no)) != null) {
            System.out.println(formerNode.next.toString());
            return;
        }
        System.out.println("该英雄不存在");
    }

    public HeroNode getFormerHeroNode(int no) {
        HeroNode formerHeroNode = headNode;
        HeroNode temp = null;
        while ((temp = formerHeroNode.next) != null) {
            if (temp.no == no) {
                return formerHeroNode;
            }
            formerHeroNode = temp;
        }
        return null;
    }
}
