package datastructure.singlelinkedlist;

import java.util.Scanner;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-20 13:46
 */
public class LinkedListDemo1 {
    public static void main(String[] args) {
        SingleLinkedList1 list = new SingleLinkedList1();
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
                                list.addNode(no, name, nickName);
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
                            list.removeNode(no);
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
                            list.setNode(no, name, nickName);
                            break;
                        } catch (RuntimeException e) {
                            //输入的为非正整数时不需要scanner.next();
                            System.out.println("输入不合法");
                        } catch (Exception e) {
                            //输入的为包含非数字的文本时需要scanner.next();
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
                                throw new RuntimeException();
                            }
                            System.out.println(list.getNode(no).toString());
                            break;
                        } catch (RuntimeException e) {
                            //输入的为非正整数时不需要scanner.next();
                            System.out.println("该座次不存在！");
                        } catch (Exception e) {
                            //输入的为包含非数字的文本时需要scanner.next();
                            scanner.next();
                            System.out.println("输入不合法！");
                        }
                    }
                    break;
                case 'l':
                    list.show();
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

class SingleLinkedList1 {
    private HeroNode headNode = new HeroNode(0, "", "");
    private HeroNode tailNode;

    public void show() {
        HeroNode tempNode = headNode.next;
        if (tempNode != null) {
            while (tempNode != null) {
                System.out.println(tempNode.toString());
                tempNode = tempNode.next;
            }
        } else {
            System.out.println("列表为空");
        }
    }

    public void addNode(int no, String name, String nickName) {
        if (tailNode != null) {
            //根据新添加数据的索引值与首尾的距离判断从哪个方向遍历
            int offset = no - Math.abs(no - tailNode.no);
            if (offset > 0) {
                if (no > tailNode.no) {
                    tailNode.next = new HeroNode(no, name, nickName);
                    tailNode = tailNode.next;
                } else if (no < tailNode.no) {
                    HeroNode tempNode = getFormerNode(tailNode.no);
                    while (tempNode.no > no) {
                        tempNode = getFormerNode(tempNode.no);
                    }
                    if (tempNode.no < no) {
                        HeroNode nextNode = tempNode.next;
                        tempNode.next = new HeroNode(no, name, nickName);
                        tempNode.next.next = nextNode;
                    } else {
                        throw new RuntimeException();
                    }
                } else {
                    throw new RuntimeException();
                }
            } else {
                HeroNode tempNode = headNode.next;
                while (no > tempNode.no) {
                    tempNode = tempNode.next;
                }
                if (no < tempNode.no) {
                    HeroNode formerNode = getFormerNode(tempNode.no);
                    formerNode.next = new HeroNode(no, name, nickName);
                    formerNode.next.next = tempNode;
                } else {
                    throw new RuntimeException();
                }
            }
        } else {
            tailNode = new HeroNode(no, name, nickName);
            headNode.next = tailNode;
        }
    }

    public void removeNode(int no) {
        try {
            HeroNode targetNode = getNode(no);
            HeroNode formerNode = getFormerNode(targetNode.no);
            if (formerNode.next == tailNode) {
                tailNode = null;
            }
            formerNode.next = targetNode.next;

        } catch (Exception e) {
            System.out.println("该座次的英雄不存在！");
        }
    }

    public void setNode(int no, String name, String nickName) {
        try {
            HeroNode targetNode = getNode(no);
            targetNode.setName(name);
            targetNode.setNickName(nickName);
        } catch (Exception e) {
            System.out.println("改座次的英雄不存在！");
        }
    }

    public HeroNode getNode(int no) {
        if (tailNode != null) {
            int offset = no - Math.abs(no - tailNode.no);
            if (offset > 0) {
                if (no < tailNode.no) {
                    HeroNode tempNode = getFormerNode(tailNode.no);
                    while (no < tempNode.no) {
                        tempNode = getFormerNode(tempNode.no);
                    }
                    if (no == tempNode.no) {
                        return tempNode;
                    } else {
                        throw new RuntimeException();
                    }
                } else if (no == tailNode.no) {
                    return tailNode;
                } else {
                    throw new RuntimeException();
                }
            } else {
                HeroNode tempNode = headNode.next;
                while (no > tempNode.no) {
                    tempNode = tempNode.next;
                }
                if (no == tempNode.no) {
                    return tempNode;
                } else {
                    throw new RuntimeException();
                }
            }
        } else {
            throw new RuntimeException();
        }
    }

    public HeroNode getFormerNode(int no) {
        HeroNode tempNode = headNode;
        while (tempNode.next != null) {
            if (tempNode.next.no == no) {
                return tempNode;
            }
            tempNode = tempNode.next;
        }
        throw new RuntimeException();
    }
}