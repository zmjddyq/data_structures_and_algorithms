package com.demo.structures.linkedlist;

import org.junit.Test;

import java.util.Objects;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author zmj
 * @date 2020/6/10 19:20
 * @Description
 */
public class SingleLinkedListDemo {
    @Test
    public void addListTest() {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByNo(new HeroNode(4, "林冲", "豹子头"));
        singleLinkedList.addByNo(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList.addByNo(new HeroNode(9, "燕青", "浪子"));
        singleLinkedList.addByNo(new HeroNode(8, "孙二娘", "母夜叉"));
        singleLinkedList.list();
        System.out.println("******************************");
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByNo(new HeroNode(10, "李逵", "黑旋风"));
        singleLinkedList1.addByNo(new HeroNode(5, "鲁智深", "花和尚"));
        singleLinkedList1.addByNo(new HeroNode(3, "吴用", "智多星"));
        singleLinkedList1.addByNo(new HeroNode(2, "卢俊义", "玉麒麟"));
        singleLinkedList1.list();
        System.out.println("******************************");
        singleLinkedList.addList(singleLinkedList1);
        singleLinkedList.list();
        System.out.println("******************************");
        singleLinkedList1.list();
    }

    @Test
    public void reverseListTest() {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByNo(new HeroNode(4, "林冲", "豹子头"));
        singleLinkedList.addByNo(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList.addByNo(new HeroNode(3, "吴用", "智多星"));
        singleLinkedList.addByNo(new HeroNode(2, "卢俊义", "玉麒麟"));
        System.out.println("翻转前: ");
        singleLinkedList.list();
        System.out.println("翻转后:");
        singleLinkedList.reverseList();
        singleLinkedList.list();

    }

    @Test
    public void reverseGetTest() {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByNo(new HeroNode(4, "林冲", "豹子头"));
        singleLinkedList.addByNo(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList.addByNo(new HeroNode(3, "吴用", "智多星"));
        singleLinkedList.addByNo(new HeroNode(2, "卢俊义", "玉麒麟"));
        for (int i = 5; i <= 25; i++) {
            singleLinkedList.addByNo(new HeroNode(i, "路人" + i, "龙套" + i));
        }
        System.out.println("正常打印: ");
        singleLinkedList.list();
        System.out.println("递归翻转打印:");
        long start = System.currentTimeMillis();
        singleLinkedList.reversePrintln();
        long end = System.currentTimeMillis();
        System.out.println("递归方法所用时间: " + (end - start));
        System.out.println("栈翻转打印:");
        start = System.currentTimeMillis();
        singleLinkedList.reversePrintln1();
        end = System.currentTimeMillis();
        System.out.println("栈方法所用时间: " + (end - start));
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        try {
            singleLinkedList.addByNo(new HeroNode(4, "林冲", "豹子头"));
            singleLinkedList.addByNo(new HeroNode(1, "宋江", "及时雨"));
            singleLinkedList.addByNo(new HeroNode(3, "吴用", "智多星"));
            singleLinkedList.addByNo(new HeroNode(2, "卢俊义", "玉麒麟"));
            System.out.println("删除前：");
            System.out.println("有效节点的个数为: " + singleLinkedList.nodeSize());
            System.out.println("倒数第一个节点为: " + singleLinkedList.getLastIndexNode(4));
            singleLinkedList.list();
            System.out.println("删除后:");
            singleLinkedList.delete(4);
            singleLinkedList.delete(1);
            System.out.println("有效节点的个数为: " + singleLinkedList.nodeSize());
            singleLinkedList.list();
            System.out.println("查询:");
            System.out.println(singleLinkedList.get(3));
            System.out.println("修改:");
            System.out.println(singleLinkedList.update(3, "有用", null));
            System.out.println(singleLinkedList.get(3));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class SingleLinkedList {
    /**
     * 初始化头结点
     */
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 合并两个有序链表
     *
     * @param linkedList
     */
    public void addList(SingleLinkedList linkedList) {
        HeroNode temp = linkedList.getTemp.get().next;
        HeroNode next = null;
        while (temp != null) {
            next = temp.next;
            addByNo(temp);
            temp = next;
        }
        linkedList.head = this.head;
    }

    /**
     * 倒叙打印
     */
    public void reverse(HeroNode heroNode) {
        if (heroNode != null) {
            reverse(heroNode.next);
            System.out.println(heroNode);
        }
    }

    /**
     * 方法一:递归倒叙打印
     */
    public void reversePrintln() {
        reverse(head.next);
    }

    /**
     * 方法二:栈归倒叙打印
     */
    public void reversePrintln1() {
        HeroNode temp = getTemp.get().next;
        Stack<HeroNode> heroNodes = new Stack<>();
        // 按顺序压入栈
        while (temp != null) {
            heroNodes.push(temp);
            temp = temp.next;
        }
        // 取出
        while (heroNodes.size() > 0) {
            System.out.println(heroNodes.pop());
        }
    }

    /**
     * 将链表倒放
     */
    public void reverseList() {
        HeroNode headTemp = getTemp.get().next;
        if (headTemp.next == null) {
            return;
        }
        HeroNode reverseHead = new HeroNode(0, "", "");
        HeroNode next = null;
        while (headTemp != null) {
            next = headTemp.next;
            headTemp.next = reverseHead.next;
            reverseHead.next = headTemp;
            headTemp = next;
        }
        this.head = reverseHead;
    }

    /**
     * 获取倒数第index个有效节点
     *
     * @param index
     * @return
     */
    public HeroNode getLastIndexNode(int index) {
        HeroNode temp = getTemp.get().next;
        int nodeSize = nodeSize();
        int findIndex = nodeSize - index;
        if (findIndex < 0 || findIndex == nodeSize) {
            throw new RuntimeException("超出有效节点个数！");
        }
        for (int i = 0; i < findIndex; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 获取有效节点的个数
     *
     * @return
     */
    public int nodeSize() {
        HeroNode temp = head;
        int size = 0;
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    /**
     * 添加节点
     *
     * @param heroNode
     */
    @Deprecated
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * 添加节点(按照no排序)
     *
     * @param heroNode
     */
    public void addByNo(HeroNode heroNode) {
        HeroNode temp = head;
        Integer no = heroNode.getNo();
        while (true) {
            if (temp.next == null || temp.next.getNo() > no) {
                if (no.equals(temp.getNo())) {
                    throw new RuntimeException("添加数据已存在!");
                }
                break;
            }
            temp = temp.next;
        }
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    /**
     * 遍历链表
     */
    public void list() {
        HeroNode temp = getTemp.get().next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 通过no删除节点
     */
    public void delete(Integer no) {
        HeroNode temp = getParentNode.apply(no);
        temp.next = temp.next.next;
    }

    /**
     * 通过no查询节点
     */
    public HeroNode get(Integer no) {
        return getParentNode.apply(no).next;
    }

    /**
     * 通过no修改指定节点
     */
    public boolean update(Integer no, String name, String nickname) {
        Boolean flag = false;
        HeroNode heroNode = get(no);
        if (name != null) {
            heroNode.setName(name);
            flag = true;
        }
        if (nickname != null) {
            heroNode.setNickname(nickname);
            flag = true;
        }
        return flag;
    }

    /**
     * 获取临时遍历指针
     */
    private Supplier<HeroNode> getTemp = () -> {
        HeroNode temp = head;
        if (temp.next == null) {
            throw new RuntimeException("链表为空!");
        }
        return temp;
    };
    /**
     * 通过no查询父节点
     */
    private Function<Integer, HeroNode> getParentNode = no -> {
        HeroNode temp = getTemp.get();
        while (temp.next != null) {
            if (Objects.equals(no, temp.next.getNo())) {
                return temp;
            }
            temp = temp.next;
        }
        throw new RuntimeException("没找到指定节点");
    };
}

class HeroNode {
    private Integer no;
    private String name;
    private String nickname;
    public HeroNode next;

    public HeroNode(Integer no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}