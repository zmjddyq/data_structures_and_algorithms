package com.demo.structures.stack;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author zmj
 * @date 2020/6/12 9:32
 * @Description 数组模拟栈计算器demo(中缀表达式)
 */
public class ComputerStackDemo {
    public static void main(String[] args) {
        String Formula = "7*2*2-5+1-5+3-4";
        numStack numStack = new numStack(10);
        ComputerStack OperatorStack = new ComputerStack(10);
        int num1 = 0;
        int num2 = 0;
        char ch = ' ';
        int res = 0;
        char[] chars = Formula.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (OperatorStack.isOperator(chars[i])) {
                if (OperatorStack.isFull()) {
                    throw new RuntimeException("栈已满，添加失败!");
                }
                if (OperatorStack.isEmpty()) {
                    OperatorStack.push(chars[i]);
                    continue;
                }
                // 判断优先级
                if (OperatorStack.Compare(chars[i])) {
                    if (numStack.isEmpty()) {
                        throw new RuntimeException("数栈为空，取出失败!");
                    }
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    char pop = OperatorStack.pop();
                    res = numStack.Calculation(pop, num1, num2);
                    OperatorStack.push(chars[i]);
                    numStack.push(res);
                } else {
                    // 优先级低直接入栈
                    OperatorStack.push(chars[i]);
                }
            } else {
                // 数字直接入栈
                if (numStack.isFull()) {
                    throw new RuntimeException("数栈已满!");
                }
                // 多位数判断
                if (i != chars.length - 1 && !OperatorStack.isOperator(chars[i + 1])) {
                    int j = i;
                    for (; j < chars.length; j++) {
                        if (OperatorStack.isOperator(chars[j])){
                            break;
                        }
                        stringBuilder.append(chars[j]);
                    }
                    i += j - 1;
                    numStack.push(Integer.parseInt(stringBuilder.toString()));
                    stringBuilder.setLength(0);
                    continue;
                }
                numStack.push(chars[i] - 48);
            }

        }
        while (numStack.getTop() != 0) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            ch = OperatorStack.pop();
            res = numStack.Calculation(ch, num1, num2);
            numStack.push(res);
        }
        System.out.println("最终结果为： " + numStack.pop());
    }
    @Test
    public void stringBuilderTest(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(1);
        stringBuilder.append(2);
        stringBuilder.append(3);
        System.out.println(stringBuilder);
        stringBuilder.setLength(0);
        System.out.println(stringBuilder);
    }
}

class ComputerStack {
    /**
     * 栈大小
     */
    private int maxSize;
    /**
     * 模拟栈
     */
    private char[] stack = null;
    /**
     * 栈顶
     */
    private int top = -1;

    public ComputerStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new char[maxSize];
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
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(char value) {
        if (isFull()) {
            throw new RuntimeException("栈已满，添加失败!");
        }
        stack[++top] = value;
    }

    /**
     * 出栈
     */
    public char pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，取出失败!");
        }
        char value = stack[top];
        stack[top] = ' ';
        top--;
        return value;
    }

    /**
     * 遍历
     */
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，取出失败!");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    /**
     * 判断否是运算符
     */
    public boolean isOperator(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    /**
     * 与栈顶的运算符进行比较
     *
     * @param Operator
     * @return priority(Operator) <= priority(stack[top])
     */
    public boolean Compare(char Operator) {
        return priority(Operator) <= priority(stack[top]);
    }

    /**
     * 判断运算符优先级
     * 数越大，优先级越高
     */
    public int priority(char value) {
        if (value == '+' || value == '-') {
            return 1;
        }
        if (value == '*' || value == '/') {
            return 2;
        }
        throw new RuntimeException("输入符号有误！");
    }

    /**
     * 进行计算
     */
    public int Calculation(char Operator, int num1, int num2) {
        switch (Operator) {
            case '+':
                return num1 + num2;

            case '-':
                return num2 - num1;

            case '*':
                return num1 * num2;

            case '/':
                return num2 / num1;

            default:
                break;
        }
        throw new RuntimeException("输入参数有误!");
    }

}

class numStack {
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

    public int getTop() {
        return top;
    }

    public numStack(int maxSize) {
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
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈已满，添加失败!");
        }
        stack[++top] = value;
    }

    /**
     * 出栈
     */
    public int pop() {
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
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，取出失败!");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    /**
     * 判断否是运算符
     */
    public boolean isOperator(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    /**
     * 判断运算符优先级
     * 数越大，优先级越高
     */
    public int priority(char value) {
        if (value == '+' || value == '-') {
            return 1;
        }
        if (value == '*' || value == '/') {
            return 2;
        }
        throw new RuntimeException("输入符号有误！");
    }

    /**
     * 进行计算
     */
    public int Calculation(char Operator, int num1, int num2) {
        switch (Operator) {
            case '+':
                return num1 + num2;

            case '-':
                return num2 - num1;

            case '*':
                return num1 * num2;

            case '/':
                return num2 / num1;

            default:
                break;
        }
        throw new RuntimeException("输入参数有误!");
    }

}