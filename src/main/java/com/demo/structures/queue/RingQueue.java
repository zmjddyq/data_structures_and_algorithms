package com.demo.structures.queue;

import java.util.Scanner;

/**
 * @author zmj
 * @date 2020/6/10 15:18
 * @Description 队列demo
 */
public class RingQueue{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean flag = true;
        System.out.print("请输入创建队列的大小:");
        int maxSize = scanner.nextInt();
        RingQueueDemo ringQueue = new RingQueueDemo(maxSize);
        while (flag) {
            System.out.println("请输入一个字符，s：显示队列，a：添加数据，g：取出数据，h：查看队列头，e：退出程序");
            System.out.print("输入:");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    try {
                        ringQueue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.print("请输入要添加的数据:");
                    int value = scanner.nextInt();
                    try {
                        ringQueue.add(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        ringQueue.get();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'h':
                    try {
                        System.out.println(ringQueue.headQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'e':
                    flag = false;
                    break;
                default:
                    break;
            }

        }
        System.out.println("程序退出");
    }
}
class RingQueueDemo {
    private int[] queue;
    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾
     */
    private int rear;
    /**
     * 队列长度
     */
    private int maxSize;

    public RingQueueDemo(int maxSize){
        this.maxSize = maxSize+1;
        queue = new int[maxSize + 1];
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return front == rear;
    }

    /**
     * 判断是否已满
     */
    public boolean isFull(){
        return (rear + 1 ) % maxSize == front;
    }

    /**
     * 添加数据
     */
    public void add(int value){
        if (isFull()){
            throw new RuntimeException("队列已满,添加失败！");
        }
        queue[rear] = value;
        rear = (rear + 1) % maxSize;
    }
    /**
     * 取出数据
     */
    public int get(){
        if (isEmpty()){
            throw new RuntimeException("队列已无数据，取出失败！");
        }
        int i = queue[front];
        queue[front] = 0;
        front = (front + 1) % maxSize;
        return i;
    }
    /**
     * 显示所有数据
     */
    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.println(queue[i % maxSize]);
        }
    }
    /**
     * 获得最大有效个数
     */
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }
    /**
     * 显示头数据
     */
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        return queue[front];
    }
}

