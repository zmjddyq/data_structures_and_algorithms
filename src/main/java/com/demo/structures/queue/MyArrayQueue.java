package com.demo.structures.queue;

import java.util.Scanner;

/**
 * @author zmj
 * @date 2020/6/10 15:18
 * @Description 队列demo
 */
 public class MyArrayQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean flag = true;
        System.out.print("请输入创建队列的大小:");
        int maxSize = scanner.nextInt();
        ArrayQueueDemo arrayQueue = new ArrayQueueDemo(maxSize);
        while (flag) {
            System.out.println("请输入一个字符，s：显示队列，a：添加数据，g：取出数据，h：查看队列头，e：退出程序");
            System.out.print("输入:");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    try {
                        arrayQueue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.print("请输入要添加的数据:");
                    int value = scanner.nextInt();
                    try {
                        arrayQueue.add(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'g':
                    try {
                        arrayQueue.get();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'h':
                    try {
                        System.out.println(arrayQueue.headQueue());
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
class ArrayQueueDemo {
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

    public ArrayQueueDemo(int maxSize){
        this.maxSize = maxSize;
        queue = new int[maxSize];
        this.front = -1;
        this.rear = -1;
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
        return rear == maxSize - 1;
    }

    /**
     * 添加数据
     */
    public void add(int value){
        if (isFull()){
            throw new RuntimeException("队列已满,添加失败！");
        }
        rear++;
        queue[rear] = value;
    }
    /**
     * 取出数据
     */
    public int get(){
        if (isEmpty()){
            throw new RuntimeException("队列已无数据，取出失败！");
        }
        front++;
        int i = queue[front];
        queue[front] = 0;
        return i;
    }
    /**
     * 显示所有数据
     */
    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        for (int i = 0; i < maxSize; i++) {
            System.out.printf("queue[%d]=%d\n", i, queue[i]);
        }
    }
    /**
     * 显示头数据
     */
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        return queue[front + 1];
    }
}

