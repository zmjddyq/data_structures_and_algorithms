package com.demo.algorithms.sort;

import java.util.Random;

/**
 * @author zmj
 * @date 2020/6/16 16:25
 * @Description 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
        }
        long start = System.currentTimeMillis();
        int[] sort = BubbleSortDemo.sort(array);
        long end = System.currentTimeMillis();
        for (int i : sort) {
            System.out.println(i);
        }
        System.out.println("用时" + ((end - start) / 1000.0) + "秒");
    }

}
class BubbleSortDemo{
    public static int[] sort(int[] array) {
        // 判断是否交换位置标志位
        boolean flag;
        for (int i = 0; i < array.length - 1; i++) {
            flag = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    int n = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = n;
                }
            }
            if (flag == false) {
                break;
            }
        }
        return array;
    }
}
