package com.demo.algorithms.search;

/**
 * @author zmj
 * @date 2020/6/18 8:13
 * @Description 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {9, 5, 23, 7, 1, 38, 43, 18, 463, 15, 8};
        int search = SeqSearchDemo.search(arr, 1);
        if (search == -1) {
            System.out.println("没有找到查询的数据");
        } else {
            System.out.println("查询到的位置为： " + search);
        }
    }
}

class SeqSearchDemo {
    /**
     * 线性查找
     *
     * @param arr 查找的数组
     * @param num 要查找的数字
     * @return 查到：所在位置下标
     * 未查到：-1
     */
    public static int search(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }
}