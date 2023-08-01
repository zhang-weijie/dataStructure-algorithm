package datastructure.queue;

import javax.sound.midi.Soundbank;
import javax.xml.transform.sax.SAXSource;
import java.util.Scanner;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-19 10:27
 */
public class ArrayQueueDemo1 {
    public static void main(String[] args) {
        ArrayQueue1 queue1 = new ArrayQueue1(3);
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
                    queue1.showQueue();
                    break;
                case 'e':
                    System.out.println("退出程序！");
                    loop = false;
                    break;
                case 'a':
                    while (true) {
                        try {
                            System.out.println("请输入一个整数：");
                            queue1.addQueue(scanner.nextInt());
                            break;
                        } catch (Exception e) {
                            scanner.next();
                            System.out.println("输入不合法，请重新输入！");
                        }
                    }
                    break;
                case 'g':
                    try {
                        System.out.printf("res = %d\n", queue1.getQueue());
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

class ArrayQueue1 {
    private int maxSize;
    private int front;
    private int rear;
    private int[] array;

    public ArrayQueue1(int maxSize) {
        this.maxSize = maxSize;
        array = new int[this.maxSize];
        front = -1;
        rear = -1;
    }

    public void showQueue() {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("array[%d] = %d\n", i, array[i]);
        }
        System.out.println();
    }

    public boolean isFull() {
        return (rear == maxSize - 1) && (front == -1);
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满！");
            return;
        } else if (rear == maxSize - 1) {
            for (int i = front + 1; i < maxSize; i++) {
                int temp = array[i];
                array[i] = 0;
                array[i - (front + 1)] = temp;
            }
            rear -= (front + 1);
            front = -1;

        }
        array[++rear] = n;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        int res = array[++front];
        array[front] = 0;
        return res;
    }
}