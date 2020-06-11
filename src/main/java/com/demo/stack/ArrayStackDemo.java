package com.demo.stack;

import java.util.Scanner;

/**
 * @author zmj
 * @date 2020/6/11 20:07
 * @Description
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean flag = true;
        System.out.print("请输入创建栈的大小:");
        int maxSize = scanner.nextInt();
        ArrayStack arrayStack = new ArrayStack(maxSize);
        while (flag) {
            System.out.println("请输入一个字符，l：遍历栈，p：添加数据，g：取出数据，e：退出程序");
            System.out.print("输入:");
            key = scanner.next().charAt(0);
            switch (key){
                case 'l':
                    try {
                        arrayStack.list();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    System.out.print("请输入要添加的数据:");
                    int value = scanner.nextInt();
                    try {
                        arrayStack.push(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'g':
                    try {
                        System.out.println("取出的数据为： "  + arrayStack.pop());
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

class ArrayStack{
    /**
     * 栈大小
     */
    private int maxSize;
    /**
     * 模拟栈
     */
    private int[] stack = null;
    /**
     * 栈顶
     */
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断是否栈满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断是否是空栈
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(int value){
        if (isFull()) {
            throw new RuntimeException("栈已满，添加失败!");
        }
        stack[++top] = value;
    }

    /**
     * 出栈
     */
    public int pop(){
        if (isEmpty()) {
            throw new RuntimeException("栈为空，取出失败!");
        }
        int value = stack[top];
        stack[top--] = 0;
        return value;
    }

    /**
     * 遍历
     */
    public void list(){
        if (isEmpty()) {
            throw new RuntimeException("栈为空，取出失败!");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}