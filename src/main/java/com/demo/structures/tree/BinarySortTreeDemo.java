package com.demo.structures.tree;

import java.lang.reflect.Array;
import java.nio.file.NotLinkException;
import java.util.SortedMap;

/**
 * @author zmj
 * @date 2020/6/21 15:07
 * @Description
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        int[] arr = {15, 10, 25, 4, 60, 1, 8, 19, 23, 18, 22, 24,16,17,18};
        binarySortTree.add(arr);
        binarySortTree.inTraversal();
        System.out.println("***************************");
        System.out.println(binarySortTree.contain(10));
        System.out.println("***************************");
        binarySortTree.delete(16);
        System.out.println("删除后");
        binarySortTree.inTraversal();
    }
}

class BinarySortTree {
    private Node root;
    // true:左 false:右
    boolean flag;
    public Integer delete(int value) {
        Node node = searchParent(value);
        if (node == null) {
            return null;
        }
        Node temp = node;
        if (node != root) {
            if (temp.left != null && temp.left.value == value) {
                temp = temp.left;
                flag = true;
            } else {
                temp = temp.right;
                flag = false;
            }
            // 当要删除的是叶子节点
            if (temp.left == null && temp.right == null) {
                int deleteResult = temp.value;
                if (flag) {
                    node.left = null;
                } else {
                    node.right = null;
                }
                return value;
            }
            // 当要删除的有左子节点
            if (temp.left != null && temp.right == null) {
                if (flag) {
                    node.left = temp.left;
                } else {
                    node.right = temp.left;
                }
                return value;
            }
            // 当要删除的有右子节点
            if (temp.left == null && temp.right != null) {
                if (flag) {
                    node.left = temp.right;
                } else {
                    node.right = temp.right;
                }
                return value;
            }
        }
        // 当要删除的有两个子节点
        Node smallTemp = temp.right;
        if (smallTemp.left == null) {
            if (flag) {
                node.left = smallTemp;
                smallTemp.left = temp.left;
                return value;
            }
        }
        while (true) {
            if (smallTemp.left == null) {
                break;
            }
            smallTemp = smallTemp.left;
        }
        searchParent(smallTemp.value).left = null;
        temp.value = smallTemp.value;
        return value;
    }

    /**
     * 判断是否包含指定的值
     *
     * @param value
     * @return true:包含
     * false:不包含
     */
    public boolean contain(int value) {
        Node node = searchParent(value);
        if (node == null) {
            return false;
        }
        return true;
    }

    /**
     * 获取查找值的父节点
     *
     * @param value 要查找的值
     * @return root：值为根节点
     * null: 未找到
     * node: 查找值的父节点
     */
    private Node searchParent(int value) {
        if (root == null) {
            throw new RuntimeException("空树无法查找!");
        }
        if (root.value == value) {
            return root;
        }
        Node temp = root;
        while (true) {
            if (value < temp.value) {
                if (temp.left == null) {
                    return null;
                }
                if (temp.left.value == value) {
                    return temp;
                }
                temp = temp.left;
                continue;
            }
            if (value > temp.value) {
                if (temp.right == null) {
                    return null;
                }
                if (temp.right.value == value) {
                    return temp;
                }
                temp = temp.right;
                continue;
            }
        }
    }

    /**
     * 批量添加元素
     *
     * @param values
     */
    public void add(int[] values) {
        if (values == null && values.length == 0) {
            throw new RuntimeException("输入数组为空,无法添加!");
        }
        for (int value : values) {
            add(value);
        }
    }

    /**
     * 添加元素
     *
     * @param value
     * @return
     */
    public int add(int value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
            return value;
        }
        Node temp = root;
        while (true) {
            if (temp.value == value) {
                return -1;
            }
            if (value < temp.value) {
                if (temp.left == null) {
                    temp.left = node;
                    return value;
                }
                temp = temp.left;
                continue;
            }
            if (temp.right == null) {
                temp.right = node;
                return value;
            }
            temp = temp.right;
        }
    }

    /**
     * 中序遍历
     */
    public void inTraversal() {
        if (root == null) {
            System.out.println("空树无法遍历!");
            return;
        }
        inTraversal(root);
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    private void inTraversal(Node node) {
        if (node.left != null) {
            inTraversal(node.left);
        }
        System.out.println(node);
        if (node.right != null) {
            inTraversal(node.right);
        }
    }

    public BinarySortTree() {
    }

    public BinarySortTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    static class Node {

        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}