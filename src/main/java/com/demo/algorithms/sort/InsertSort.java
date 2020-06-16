package com.demo.algorithms.sort;

import java.util.Random;

/**
 * @author zmj
 * @date 2020/6/16 19:22
 * @Description
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = new int[80000];
        Random random = new Random();
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = random.nextInt(100000);
        }
        array[array.length - 1] = -1;
        long start = System.currentTimeMillis();
        int[] sort = InsertSortDemo.sort(array);
        long end = System.currentTimeMillis();
        for (int i : sort) {
            System.out.println(i);
        }
        System.out.println("用时" + ((end - start) / 1000.0) + "秒");
    }
}
class InsertSortDemo{
    public static int[] sort(int[] array){
        if (array.length <= 0) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            int num = array[i];
            int index = i;
            for (int j = i - 1; j >= 0 ; j--) {
                if (num > array[j]){
                    break;
                }
                array[j + 1] = array[j];
                index = j;
            }
            array[index] = num;
        }
        return array;
    }
}