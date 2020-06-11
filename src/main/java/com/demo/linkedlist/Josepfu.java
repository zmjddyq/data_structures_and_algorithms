package com.demo.linkedlist;

import org.junit.Test;

/**
 * @author zmj
 * @date 2020/6/11 15:26
 * @Description 单向环形链表
 * Josephu(约瑟夫、约瑟夫环)问题
 * Josephu问题为: 设编号为1, 2，... n的n个人围坐一圈，约定编号为k (1<=k<=n)的人从1开始报数,数
 * 到m的那个人出列，它的下一-位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由
 * 此产生- -个出队编号的序列。
 */
public class Josepfu {
    @Test
    public void addTest(){
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        for (int i = 1; i <= 5; i++) {
            circleSingleLinkedList.add(new Boy(i));
        }
        circleSingleLinkedList.list();
        System.out.println(circleSingleLinkedList.size());
        System.out.println("*****************************");
        circleSingleLinkedList.out(1,2);
    }
}

class CircleSingleLinkedList {
    Boy first = null;
    Boy cur = null;
    boolean firstAdd = true;

    /**
     * 出圈
     * @param startNo 从第几个开始
     * @param count 出圈编号
     */
    public void out(int startNo,int count){
        int size = size();
        if (size == 0) {
            throw new RuntimeException("链表为空！");
        }
        if (startNo < 1 || startNo > size || count < 1 ){
            throw new RuntimeException("输入参数有误！");
        }
        for (int i = 1; i < startNo; i++) {
            first = first.getNext();
            cur = cur.getNext();
        }
        while (!first.equals(cur)){
            for (int i = 1; i < count; i++) {
                first = first.getNext();
                cur = cur.getNext();
            }
            System.out.println(first.getNo());
            first = first.getNext();
            cur.setNext(first);
        }
        System.out.println(first.getNo());
    }

    /**
     * 添加节点
     */
    public void add(Boy boy) {
        if (boy.getNo() < 1) {
            throw new RuntimeException("编号不能小于零!");
        }
        if (firstAdd == true) {
            first = boy;
            cur = boy;
            boy.setNext(boy);
            firstAdd = false;
        }else {
            cur.setNext(boy);
            cur = cur.getNext();
            cur.setNext(first);
        }
    }

    /**
     * 遍历
     */
    public void list() {
        if (first == null) {
            throw new RuntimeException("链表为空!");
        }
        Boy temp = first;
        do {
            System.out.println(temp);
            temp = temp.getNext();
        }while (temp != first);
    }

    /**
     * 获取有效节点数
     */
    public int size(){
        int count = 0;
        if (first == null) {
            return count;
        }
        Boy temp = first;
        do {
            count ++;
            temp = temp.getNext();
        }while (temp != first);
        return count;
    }
}

class Boy {
    private int no;
    private Boy next;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}