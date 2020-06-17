package com.demo.algorithms.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.jar.JarEntry;

/**
 * @author zmj
 * @date 2020/6/17 15:12
 * @Description 快速排序 用时0.015秒
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = new int[80000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
        }
//        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        long start = System.currentTimeMillis();
        QuickSortDemo.sort(array);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(array));
        System.out.println("用时" + ((end - start) / 1000.0) + "秒");
    }
}
class QuickSortDemo{
    private static int getStandard(int[]array,int low,int high){
        // 设置第一位为基准值
        int temp = array[low];
        while (low < high){
            // 队尾开始扫描，如果扫描的值小于基准值则与high交换
            while (low < high && array[high] >= temp){
                high--;
            }
            array[low] = array[high];
            // 换成从对头开始扫描
            while (low < high && array[low] <= temp){
                low++;
            }
            array[high] = array[low];
        }
        // 如果前后指针重叠则是到达基准值所在位置
        if (low == high) {
            array[low] = temp;
        }
        // 返回基准值所在位置
        return low;
    }

    /**
     * 递归快排
     * @param array
     * @param low
     * @param high
     */
    private static void quickSort(int[]array,int low,int high){
        // 采用递归进行快排
        if (low < high) {
            int standard = getStandard(array,low, high);
            // 左排
            quickSort(array,low,standard - 1);
            // 右排
            quickSort(array,standard + 1,high);
        }
    }

    /**
     * 开始排序
     * @param array
     */
    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }
}