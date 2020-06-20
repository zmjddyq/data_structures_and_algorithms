package com.demo.structures.tree;

import org.junit.Test;

import java.util.*;

/**
 * @author zmj
 * @date 2020/6/20 9:26
 * @Description 哈夫曼树
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree huffmanTree = new HuffmanTree();
        System.out.println("根节点为: " + huffmanTree.creatHuffmanTree(arr));
        huffmanTree.preTraversal();
    }

}

class HuffmanTree {

    private static Node root;

    /**
     * 前序遍历
     */
    public void preTraversal() {
        if (root == null) {
            System.out.println("空数！无法遍历");
            return;
        }
        root.preTraversal();
    }

    /**
     * 将数组变为哈夫曼树
     *
     * @param arr
     * @return
     */
    public Node creatHuffmanTree(int[] arr) {
        // 创建节点队列
        // 将集合中的节点进行升序排序
        Queue<Node> nodes = new PriorityQueue<>(arr.length);
        // 遍历数组创建节点
        for (int i : arr) {
            nodes.add(new Node(i));
        }

        // 当集合中只剩根节点时退出循环
        while (nodes.size() > 1) {
            // 取出最小的两个节点组成子树
            Node leftNode = nodes.poll();
            Node rightNode = nodes.poll();
            Node node = new Node(leftNode.value + rightNode.value);
            node.left = leftNode;
            node.right = rightNode;
            // 将子树的根节点重新放回集合中
            nodes.add(node);
        }
        // 退出循环，此时队列中仅剩的一个元素为根节点
        root = nodes.poll();
        return root;
    }

    @Test
    public void dequeTest() {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Queue<Node> nodes = new PriorityQueue<>(arr.length);
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        System.out.println(nodes.toString());
        while (nodes.size() > 0) {
            Node poll = nodes.poll();
            System.out.println("取出的元素为: " + poll);
            System.out.println(nodes.toString());
        }
        System.out.println("***************************************");
        // 创建节点集合
        List<Node> nodes1 = new ArrayList<>(arr.length);
        // 遍历数组创建节点
        for (int i : arr) {
            nodes1.add(new Node(i));
        }
        Collections.sort(nodes1);
        System.out.println(nodes1);

    }

}

class Node implements Comparable<Node> {
    public int value;
    public Node left;
    public Node right;

    /**
     * 前序遍历
     */
    public void preTraversal() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preTraversal();
        }
        if (this.right != null) {
            this.right.preTraversal();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 升序排序
        return this.value - o.value;
    }
}