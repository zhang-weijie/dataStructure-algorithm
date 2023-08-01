package datastructure.queue;

import javax.swing.*;
import java.util.Scanner;

/**
 * //通过取模的方式实现环形队列
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-19 12:48
 */
public class ArrayQueueDemo2 {
    public static void main(String[] args) {
        ArrayQueue2 queue2 = new ArrayQueue2(3);
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean loop = true;
        while (loop) {
            System.out.print("s(show)：显示队列;");
            System.out.print("e(exit): 退出程序;");
            System.out.print("a(add)：添加数据;");
            System.out.print("i(info)：获取首尾");
            System.out.println("g(get)：获取数据;");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue2.showQueue();
                    break;
                case 'e':
                    System.out.println("退出程序！");
                    loop = false;
                    break;
                case 'a':
                    while (true) {
                        try {
                            System.out.println("请输入一个整数：");
                            queue2.addQueue(scanner.nextInt());
                            break;
                        } catch (Exception e) {
                            scanner.next();
                            System.out.println("输入不合法，请重新输入！");
                        }
                    }
                    break;
                case 'i':
                    queue2.info();
                    break;
                case 'g':
                    try {
                        System.out.printf("res = %d\n", queue2.getQueue());
                    } catch (Exception e) {
                        System.out.println("队列为空！");
                    }
                    break;
                default:
                    System.out.println("输入无效！");
                    break;
            }
        }
    }
}

class ArrayQueue2 {
    private int maxSize;
    private int front;
    private int rear;
    private int[] array;

    public ArrayQueue2(int maxSize) {
        this.maxSize = maxSize;
        front = 0;
        rear = 1;
        array = new int[maxSize];
    }

    public boolean isFull() {
        return (rear - front) == maxSize + 1;
    }

    public boolean isEmpty() {
        return rear - 1 == front;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空！");
            return;
        }
        for (int i = front; i < rear - 1; i++) {
            System.out.printf("array[%d] = %d\n", i % maxSize, array[i % maxSize]);
        }
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        int res = array[(front % maxSize)];
        array[(front % maxSize)] = 0;
        front++;
        return res;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满！");
            return;
        }
        array[((rear - 1) % maxSize)] = n;
        rear++;
    }

    public void info() {
        System.out.printf("front = %d, rear = %d\n", front, rear);
    }
}
