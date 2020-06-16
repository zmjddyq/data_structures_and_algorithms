package com.demo.algorithms.sort;

import java.util.Random;

/**
 * @author zmj
 * @date 2020/6/16 18:25
 * @Description 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = new int[80000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
        }
        long start = System.currentTimeMillis();
        int[] sort = SelectSortDemo.sort(array);
        long end = System.currentTimeMillis();
        for (int i : sort) {
            System.out.println(i);
        }
        System.out.println("用时" + ((end - start) / 1000.0) + "秒");
    }
}
class SelectSortDemo{
    public static int[] sort(int[] array){

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int min = array[minIndex];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    minIndex = j;
                    min = array[minIndex];
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }
}