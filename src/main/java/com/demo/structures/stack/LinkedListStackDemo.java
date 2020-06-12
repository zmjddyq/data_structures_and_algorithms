package com.demo.structures.stack;

import java.util.Scanner;

/**
 * @author zmj
 * @date 2020/6/11 20:07
 * @Description 单链表模拟栈demo
 *
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean flag = true;
        System.out.print("请输入创建栈的大小:");
        int maxSize = scanner.nextInt();
        LinkedListStack linkedListStack = new LinkedListStack(maxSize);
        while (flag) {
            System.out.println("请输入一个字符，l：遍历栈，p：添加数据，g：取出数据，e：退出程序");
            System.out.print("输入:");
            key = scanner.next().charAt(0);
            switch (key){
                case 'l':
                    try {
                        linkedListStack.list();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    System.out.print("请输入要添加的数据:");
                    int value = scanner.nextInt();
                    try {
                        linkedListStack.push(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'g':
                    try {
                        System.out.println("取出的数据为： "  + linkedListStack.pop());
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
        scanner.close();
        System.out.println("程序退出");
    }
}

class LinkedListStack {
    /**
     * 栈大小
     */
    private int maxSize;
    /**
     * 头
     */
    private MyNode head ;
    /**
     * 栈顶
     */
    private int top = 0;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        head = new MyNode(0);
    }

    /**
     * 判断是否栈满
     */
    public boolean isFull() {
        return top == maxSize;
    }

    /**
     * 判断是否是空栈
     */
    public boolean isEmpty(){
        return top == 0;
    }

    /**
     * 入栈
     */
    public void push(int value){
        MyNode myNode = new MyNode(value);
        if (isFull()) {
            throw new RuntimeException("栈已满，添加失败!");
        }
        myNode.next = head.next;
        head.next = myNode;
        top++;
    }

    /**
     * 出栈
     */
    public int pop(){
        if (isEmpty()) {
            throw new RuntimeException("栈为空，取出失败!");
        }
        int value = head.next.value;
        if (top == 1){
            head.next = null;
            top--;
            return value;
        }
        head.next = head.next.next;
        top--;
        return value;
    }

    /**
     * 遍历
     */
    public void list(){
        if (isEmpty()) {
            throw new RuntimeException("栈为空，取出失败!");
        }
        MyNode temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }
}
class MyNode{
    public int value;
    public MyNode next;

    public MyNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "value=" + value +
                '}';
    }
}