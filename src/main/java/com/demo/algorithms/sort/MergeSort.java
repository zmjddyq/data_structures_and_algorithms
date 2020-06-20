package com.demo.algorithms.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zmj
 * @date 2020/6/17 16:34
 * @Description 归并排序 用时0.021秒
 */
public class MergeSort {
    public static void main(String[] args) {

        int[] array = new int[8000000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
        }
//        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        long start = System.currentTimeMillis();
        MergeSortDemo.sort(array);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(array));
        System.out.println("用时" + ((end - start) / 1000.0) + "秒");
    }
}

class MergeSortDemo {
    public static void sort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int[] temp = new int[arr.length];
        mergeSort(arr, low, high, temp);
    }

    /**
     * 递归实现归并算法
     *
     * @param arr
     * @param low
     * @param high
     * @param temp
     */
    private static void mergeSort(int[] arr, int low, int high, int[] temp) {
        if (low < high) {
            int mid = (high + low)/2;
            // 拆分
            mergeSort(arr, low, mid, temp);
            mergeSort(arr, mid + 1, high, temp);
            // 合并
            merge(arr, low, mid, high, temp);
        }
    }

    /**
     * 合并
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // 探测指针p1,p2
        int p1 = left;
        int p2 = mid + 1;
        int index = left;
        while (p1 <= mid && p2 <= right) {
            // 在临时变量中放入较小的值
            if (arr[p2] < arr[p1]) {
                temp[index++] = arr[p2++];
            } else {
                temp[index++] = arr[p1++];
            }
        }
        // 当p1未检测完
        while (p1 <= mid) {
            temp[index++] = arr[p1++];
        }
        // 当p2未检测完
        while (p2 <= right) {
            temp[index++] = arr[p2++];
        }

        // 将临时序列复制回原序列
        for (int i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
}