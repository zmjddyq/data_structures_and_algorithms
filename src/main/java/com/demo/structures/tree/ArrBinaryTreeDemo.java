package com.demo.structures.tree;

import java.util.Arrays;

/**
 * @author zmj
 * @date 2020/6/19 14:28
 * @Description 数组存储顺序二叉树
 * 1) 顺序二叉树通常只考虑完全二叉树
 * 2) 第n个元素的左子节点为2*n+ 1
 * 3) 第n个元素的右子节点为2*n+ 2
 * 4) 第n个元素的父节点为(n-1)/ 2
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[7];
        for (int i = 0; i < 7; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("*************************");
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preTraversal();
    }
}
class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    /**
     * 将数组通过前序遍历打印
     * @param
     *
     */
    public void preTraversal(){
        preTraversal(0);
    }
    private void preTraversal(int index){
        if (arr == null && arr.length == 0) {
            System.out.println("遍历失败，数组为空");
            return;
        }
        System.out.println(arr[index]);
        // 左递归遍历
        if ((2 * index + 1) < arr.length) {
            preTraversal((2 * index + 1));
        }
        // 右递归遍历
        if ((2 * index + 2) < arr.length) {
            preTraversal((2 * index + 2));
        }
    }
}