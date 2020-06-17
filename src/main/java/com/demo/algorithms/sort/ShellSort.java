package com.demo.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zmj
 * @date 2020/6/16 20:23
 * @Description 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = new int[80000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
        }
        /*int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};*/
        long start = System.currentTimeMillis();
        ShellSortDemo.sort1(array);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(array));
        System.out.println("用时" + ((end - start) / 1000.0) + "秒");
    }
}

class ShellSortDemo {
    // 希尔排序交换法 用时7.084秒
    public static void sort(int[] array) {
        for (int i = array.length / 2; i > 0; i /= 2) {
            for (int j = i; j < array.length; j++) {
                for (int k = j - i; k >= 0; k -= i) {
                    if (array[k + i] < array[k]) {
                        int num = array[k + i];
                        array[k + i] = array[k];
                        array[k] = num;
                    }
                }
            }
           /* System.out.println(Arrays.toString(array));*/
        }
    }

    // 希尔排序移位交换法 用时0.022秒
    public static void sort1(int[] array){
        for (int i = array.length/2; i > 0; i /= 2) {
            for (int j = i; j < array.length; j++) {
                int num = array[j];
                int index = j;
                for (int k = j - i; k >= 0 ; k -= i) {
                    if (num > array[k]) {
                        break;
                    }
                    index = k;
                    array[k + i] = array[k];
                }
                array[index] = num;
            }
            /*System.out.println(Arrays.toString(array));*/
        }
    }
}