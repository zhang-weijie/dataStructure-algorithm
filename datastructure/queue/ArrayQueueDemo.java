package datastructure.queue;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-18 21:29
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接受用户输入;
        //创建一个扫描器;
        Scanner scanner = new Scanner(System.in);
        //接受用户输入;
        boolean loop = true;
        while (loop) {
            System.out.print("s(show)：显示队列;");
            System.out.print("e(exit): 退出程序;");
            System.out.print("a(add)：添加数据;");
            System.out.print("g(get)：获取数据;");
            System.out.print("h(head)：获取头数据;");
            System.out.println("t(tail)：获取尾数据;");
            key = scanner.next().charAt(0);//获取用户输入的一个字符;

            //判断用户意图
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
//                    System.exit(0);
                    System.out.println("程序退出");
                    loop = false;//通过修改循环条件退出程序;
                    break;
                case 'a':
                    System.out.println("请输入一个数：");
                    while (true) {
                        try {
                            int value = scanner.nextInt();
                            arrayQueue.addQueue(value);
                            break;
                        } catch (Exception e) {
                            scanner.next();//注意要先把阻塞在输入流中的字符读取完毕;
                            System.out.println("输入不合法，请重新输入！");
                        }
                    }
                    break;
                case 'g':
                    try {
                        int value = arrayQueue.getQueue();
                        System.out.println(value);
                    } catch (Exception e) {
                        System.out.println("队列为空！");
                    }
                    break;
                case 'h':
                    try {
                        int head = arrayQueue.showHead();
                        System.out.printf("头部数据为：%d\n", head);
                    } catch (Exception e) {
                        System.out.println("队列为空！");
                    }
                    break;
                case 't':
                    try {
                        int tail = arrayQueue.showTail();
                        System.out.printf("尾部数据为：%d\n", tail);
                    } catch (Exception e) {
                        System.out.println("队列为空");
                    }
                    break;
                default:
                    System.out.println("输入无效！");
                    break;
            }
        }

    }
}

//使用数组模拟队列-编写一个arrQueue类
class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] array;

    //创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[this.maxSize];
        front = -1;//指向队列头的前一个位置;
        rear = -1;//指向队列的尾部;
    }

    //判断队列是否为满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满！");
            return;
        }
        array[++rear] = n;
    }

    //获取队列的数据
    public int getQueue() {
        if (isEmpty()) {
            //通过抛出异常中止程序
            throw new RuntimeException("队列为空，不能取数据");
        }
        return array[++front];
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("数列为空");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.printf("array[%d] = %d\n", i, array[i]);
        }
    }

    //显示队列的头数据
    public int showHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return array[front + 1];
    }

    //显示队列的尾数据
    public int showTail() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return array[rear];
    }
}