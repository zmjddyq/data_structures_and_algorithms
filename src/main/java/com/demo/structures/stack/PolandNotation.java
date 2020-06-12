package com.demo.structures.stack;

import com.sun.jmx.remote.internal.ArrayQueue;
import org.junit.Test;

import java.util.*;

/**
 * @author zmj
 * @date 2020/6/12 14:33
 * @Description 逆波兰表达式计算器demo(后缀表达式)
 */
public class PolandNotation {
    public static void main(String[] args) {
        try {
//            String suffixExpression = "30 4 + 5 * 6 -";
            String infixExpression = "1+((2+3)*4)-5";
            String suffixExpression = InfixToSuffix(infixExpression);
            List<String> strings = stringArrayList(suffixExpression);
            int computer = computer(strings);
            System.out.println(computer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 将后缀表达式变为list
     *
     * @param strings
     * @return
     */
    static List<String> stringArrayList(String strings) {
        String[] s = strings.split(" ");
        List<String> strings1 = Arrays.asList(s);
        return strings1;
    }

    /**
     * 计算
     *
     * @param suffixExpression
     * @return
     */
    static int computer(List<String> suffixExpression) {
        Stack<String> strings = new Stack<>();
        for (String s : suffixExpression) {
            // 判断一到多位数字的正则表达式
            if (s.matches("\\d+")) {
                strings.push(s);
                continue;
            }
            // 若为运算符则进行弹出数字进行运算
            int num2 = Integer.parseInt(strings.pop());
            int num1 = Integer.parseInt(strings.pop());
            int result = 0;
            switch (s) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                default:
                    throw new RuntimeException("输入符号有误!");
            }
            strings.push(String.valueOf(result));

        }
        return Integer.parseInt(strings.pop());
    }

    static String InfixToSuffix(String infix) {
        char[] chars = infix.toCharArray();
        // 运算符栈
        Stack<Character> OperatorStack = new Stack<>();
        // 结果队列
        ArrayQueue arrayQueue = new ArrayQueue(20);
        for (int i = 0; i < chars.length; i++) {
            // 数字
            if (!isOperator(chars[i])) {
                // 多位数判断
                if (i != chars.length - 1 && !isOperator(chars[i + 1])) {
                    StringBuilder stringBuilder = new StringBuilder();
                    int j = i;
                    for (; j < chars.length; j++) {
                        if (isOperator(chars[j])) {
                            break;
                        }
                        stringBuilder.append(chars[j]);
                    }
                    i += j - 1;
                    arrayQueue.add(Integer.parseInt(stringBuilder.toString()));
                    stringBuilder.setLength(0);
                    continue;
                }
                arrayQueue.add(chars[i] - 48);
                continue;
            }
            // 运算符
            // 判断运算符栈是否为空或者为左括号,为空直接压入栈中
            while (true) {
                if (OperatorStack.isEmpty() || chars[i] == '(') {
                    OperatorStack.push(chars[i]);
                    break;
                }
                // 判断是否为右括号
                if (chars[i] == ')') {
                    // 不是右括号直接进入队列
                    while (OperatorStack.peek() != '(') {
                        arrayQueue.add(OperatorStack.pop());
                    }
                    // 找到左括号
                    OperatorStack.pop();
                    break;
                }
                // 进行运算符优先级判断
                //
                // 优先级小或运算符栈顶为左括号 直接入栈
                if (OperatorStack.peek() == '(' || priority(chars[i]) < priority(OperatorStack.peek())) {
                    OperatorStack.push(chars[i]);
                    break;
                }
                // 优先级大
                // 取出栈顶的运算符放进队列，重新循环判断
                Character pop = OperatorStack.pop();
                arrayQueue.add(pop);
                continue;
            }

        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!OperatorStack.isEmpty()){
            arrayQueue.add(OperatorStack.pop());
        }
        for (Object o : arrayQueue) {
            stringBuilder.append(o);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    @Test
    public void InfixToSuffixTest() {
        String infixExpression = "1+((2+3)*4)-5";
        String suffixExpression = InfixToSuffix(infixExpression);
        System.out.println(suffixExpression);
    }

    /**
     * 判断是否是运算符
     *
     * @param value
     * @return
     */
    static boolean isOperator(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/' || value == '(' || value == ')';
    }


    /**
     * 判断运算符优先级
     * 数越大，优先级越高
     */
    static int priority(char value) {
        if (value == '+' || value == '-') {
            return 1;
        }
        if (value == '*' || value == '/') {
            return 2;
        }
        throw new RuntimeException("输入符号有误！");
    }
}
