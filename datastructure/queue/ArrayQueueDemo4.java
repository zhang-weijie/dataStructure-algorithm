package datastructure.queue;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-13 20:52
 */
public class ArrayQueueDemo4 {
    public static void main(String[] args) {
        ArrayQueue4 queue4 = new ArrayQueue4(5);
        queue4.addItem(0);
        queue4.addItem(1);
        queue4.addItem(2);
        queue4.addItem(3);
        queue4.addItem(4);
        queue4.addItem(5);
        System.out.println("-----1-----");
        queue4.showQueue();
        System.out.println("----------");
        queue4.getItem();
        System.out.println("-----2-----");
        queue4.showQueue();
        System.out.println("----------");
        queue4.addItem(5);
        System.out.println("-----3-----");
        queue4.showQueue();
        System.out.println("----------");


    }
}

class ArrayQueue4 {
    private int maxSize;
    private int front = 0;
    private int rear = 0;
    private int[] array;

    public ArrayQueue4(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
    }

    public boolean isFull() {
        return front - rear == maxSize;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    private int getSize() {
        return front - rear;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空！");
            return;
        }

        for (int i = rear; i < rear + getSize(); i++) {
            System.out.println(array[i % maxSize]);
        }
    }

    public void addItem(int newData) {
        if (isFull()) {
            System.out.println("队列已满！");
            return;
        }

        array[front++ % maxSize] = newData;
        System.out.printf("您的序号为%d\n", front);
    }

    public int getItem() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }

        System.out.printf("轮到%d号！\n", rear + 1);
        return array[rear++ % maxSize];

    }
}


