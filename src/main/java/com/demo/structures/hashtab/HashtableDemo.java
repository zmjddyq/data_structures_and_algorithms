package com.demo.structures.hashtab;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author zmj
 * @date 2020/6/18 15:25
 * @Description
 */
public class HashtableDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要创建MyHashtable的大小：");
        int maxSize = scanner.nextInt();
        MyHashtable myHashtable = new MyHashtable(maxSize);
        char key;
        menu:
        while (true) {
            System.out.println("\na:添加员工,l:显示所有员工,s:查询员工,e:退出系统");
            System.out.print("请输入:");
            key = scanner.next().charAt(0);
            switch (key) {
                case 'a':
                    System.out.print("请输入要添加的员工id：");
                    int id = scanner.nextInt();
                    System.out.print("请输入要添加的员工姓名：");
                    String name = scanner.next();
                    myHashtable.add(new Employee(id, name));
                    break;
                case 'l':
                    myHashtable.list();
                    break;
                case 's':
                    System.out.print("请输入要查询的员工id：");
                    id = scanner.nextInt();
                    Employee employee = myHashtable.selectById(id);
                    if (employee == null) {
                        System.out.println("没有找到当前用户！");
                        break;
                    }
                    System.out.print("查询到第" + employee.id + "号员工为：" + employee.name + ";\t");
                    break;
                case 'e':
                    scanner.close();
                    break menu;
                default:
                    break;
            }
        }
        System.out.println("退出系统成功!");
    }
}

/**
 * 哈希表
 */
class MyHashtable {
    private EmpLinkedList[] linkedLists;
    private int maxSize;

    public MyHashtable() {
        this(10);
        this.maxSize = 10;
    }

    public MyHashtable(int maxSize) {
        this.maxSize = maxSize;
        linkedLists = new EmpLinkedList[maxSize];
    }

    /**
     * 通过id查找员工
     * @param id
     */
    public Employee selectById(int id){
        int i = myHash(id);
        EmpLinkedList linkedList = linkedLists[i];
        if (linkedList == null) {
            return null;
        }
        Employee employee = linkedList.selectById(id);
        if (employee == null) {
            return null;
        }
        return employee;

    }
    /**
     * 添加员工
     * @param emp
     */
    public void add(Employee emp) {
        Employee employee = selectById(emp.id);
        if (employee != null) {
            System.out.println("该id已经存在！第" + employee.id + "号员工为：" + employee.name + ";\t");
            return;
        }
        int i = myHash(emp.id);
        if (linkedLists[i] == null) {
            linkedLists[i] = new EmpLinkedList();
        }
        linkedLists[i].add(emp);
    }

    /**
     * 遍历员工表
     */
    public void list() {
        boolean flag = true;
        for (int i = 0; i < maxSize; i++) {
            if (linkedLists[i] == null) {
//                System.out.println("第" + (i + 1) + "号链表为空！");
                continue;
            }
            linkedLists[i].list();
            flag = false;
        }
        if (flag) {
            System.out.println("当前哈希表为空");
        }
    }

    /**
     * 获取存放的链表的下标
     *
     * @param id
     * @return
     */
    private int myHash(int id) {
        return id % maxSize;
    }

}

/**
 * 员工链表
 */
class EmpLinkedList {
    public Employee head;

    /**
     * 通过id寻找员工
     * @param id
     */
    public Employee selectById(int id){
        // 链表为空
        if (head == null) {
            return null;
        }
        // 链表不为空
        // 创建临时指针遍历
        Employee temp = head;
        while (temp.id != id){
            if (temp.next == null) {
                return null;
            }
            temp = temp.next;
        }
        return temp;
    }
    /**
     * 添加员工到链表最后
     *
     * @param emp
     */
    public void add(Employee emp) {
        // 如果链表中还没有数据，则直接存入
        if (head == null) {
            head = emp;
            return;
        }
        // 定义临时指针遍历
        Employee temp = head;
        // 有数据则遍历到链表末尾进行添加
        while (temp.next != null) {
            temp = temp.next;
        }
        // 跳出循环后temp指向链表中最后的元素
        temp.next = emp;
    }

    /**
     * 遍历当前链表
     */
    public void list() {
        // 链表为空
        if (head == null) {
            System.out.println("当前链表为空！");
            return;
        }
        // 链表不为空
        // 创建临时指针遍历
        Employee temp = head;
        // 遍历输出
        while (temp != null) {
            System.out.print("第" + temp.id + "号员工为：" + temp.name + ";\t");
            temp = temp.next;
        }
        System.out.println();
    }
}

class Employee {
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}