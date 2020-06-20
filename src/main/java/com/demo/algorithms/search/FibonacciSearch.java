package com.demo.algorithms.search;

import java.util.Arrays;

/**
 * @author zmj
 * @date 2020/6/18 12:14
 * @Description
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(FibonacciSearchDemo.search(arr, 100));
    }
}

class FibonacciSearchDemo {
    private static int maxSize = 20;

    /**
     * 获取一个斐波那契数列
     * maxSize = 20
     * [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765]
     */
    private static int[] getFibonacciArray() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int search(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        int[] f = getFibonacciArray();
        // 表示斐波那契分割数值的下标
        int k = 0;
        int mid = 0;
        // 获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 防止f[k]值大于数组的长度，使用array类构造一个新的数组
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 使用原始数组中最后的元素填充数组
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        // 循环找到我们想要的值
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (value < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (value > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}