package com.demo.algorithms.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zmj
 * @date 2020/6/17 19:14
 * @Description 基数排列
 */
public class CardinalitySorting {
    public static void main(String[] args) {
        int[] array = new int[80000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
        }
//        System.out.println(Arrays.toString(array));
//        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        long start = System.currentTimeMillis();
        CardinalitySortingDemo.sort(array);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(array));
        System.out.println("用时" + ((end - start) / 1000.0) + "秒");
    }
}

class CardinalitySortingDemo {
    public static void sort(int[] arr) {
        int maxDigits = getMaxDigits(arr);
        // 桶
        int[][] barrel = new int[10][arr.length];
        // 桶中个数记录数组
        int[] NumberRecord = new int[10];
        // i:循环次数标志,j:除数
        for (int i = 0, j = 1; i < maxDigits; i++, j *= 10) {

            // 遍历原始数组放入桶中
            for (int num : arr) {
                int temp = num / j % 10;
                barrel[temp][NumberRecord[temp]++] = num;
            }
            int temp = 0;
            // 将桶中数据按桶顺序放回原数组
            for (int m = 0; m < NumberRecord.length; m++) {
                if (NumberRecord[m] != 0) {
                    for (int k = 0; k < NumberRecord[m]; k++) {
                        arr[temp++] = barrel[m][k];
                    }
                    NumberRecord[m] = 0;
                }
            }
        }


    }


    /**
     * 获取数组中最大位数
     *
     * @param arr
     * @return
     */
    private static int getMaxDigits(int[] arr) {
        int maxNum = arr[0];
        for (int i : arr) {
            if (i > maxNum) {
                maxNum = i;
            }
        }
        return (String.valueOf(maxNum)).length();
    }
}