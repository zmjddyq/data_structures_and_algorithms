package com.demo.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zmj
 * @date 2020/6/19 18:28
 * @Description 堆排序 → 树 用时0.015秒
 */
public class HeapSort {
    public static void main(String[] args) {
        /*int[] arr = {4, 6, 8, 5, 9};*/
        int[] arr = new int[8000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10000000);
        }
        long start = System.currentTimeMillis();
        HeapSortDemo.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));
        System.out.println("用时" + ((end - start) / 1000.0) + "秒");
    }
}

class HeapSortDemo {
    public static void sort(int[] arr) {
        // 将数值变换成大顶堆
        // 到达最左边的最后一个父节点
        // 顺序 ： 下 → 上，左 → 右
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0 ; i--) {
            // 将堆顶元素和堆尾元素进行位置交换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // 剩下元素重组成大顶堆
            adjustHeap(arr,0,i);
        }

    }
    // 重组成大顶堆
    private static void adjustHeap(int[] arr, int i, int length) {
        // 设置临时变量记录父节点的值
        int temp = arr[i];
        for (int j = 2 * i + 1; j < length; j =  2 * j + 1) {
            // j指向左子结点
            // 左右子节点进行比较
            // j + 1 <length!!!!防止剩两个元素时还会调用第三个元素
            if (j + 1 <length && arr[j] < arr[j + 1]) {
                // 如果右子节点大于左子节点则指向右子节点
                j++;
            }
            if (arr[j] > temp) {
                // 当前子节点的值大于父节点则交换父子节点
                arr[i] = arr[j];
                // i节点往下移
                i = j;
            } else {
                break;
            }
        }
        // 退出循环将临时变量的值赋给当前的i指针位置
        arr[i] = temp;
    }
}
