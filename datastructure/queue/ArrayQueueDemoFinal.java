package datastructure.queue;

import java.util.Scanner;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-19 17:24
 */
public class ArrayQueueDemoFinal {
    public static void main(String[] args) {
        ArrayQueue3 queue3 = new ArrayQueue3(4);
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean loop = true;
        while (loop) {
            System.out.print("s(show)：显示队列;");
            System.out.print("e(exit): 退出程序;");
            System.out.print("a(add)：添加数据;");
            System.out.println("g(get)：获取数据;");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue3.showQueue();
                    break;
                case 'e':
                    System.out.println("退出程序！");
                    loop = false;
                    break;
                case 'a':
                    while (true) {
                        try {
                            System.out.println("请输入一个整数：");
                            queue3.addQueue(scanner.nextInt());
                            break;
                        } catch (Exception e) {
                            scanner.next();
                            System.out.println("输入不合法，请重新输入！");
                        }
                    }
                    break;
                case 'g':
                    try {
                        System.out.printf("res = %d\n", queue3.getQueue());
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

class ArrayQueue3 {
    private int maxSize;
    private int front;//初始值为0
    private int rear;//初始值为0
    private int[] array;

    public ArrayQueue3(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满！");
            return;
        }
        //直接将数据加入
        array[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        int temp = array[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空！");
            return;
        }
        for (int i = front; i < front + getSize(); i++) {
            System.out.printf("array[%d] = %d\n", i % maxSize, array[i % maxSize]);
        }
    }

    //获得当前队列有效数据的个数;
    public int getSize() {
        return (rear + maxSize - front) % maxSize;
    }
}