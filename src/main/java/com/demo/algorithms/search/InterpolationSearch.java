package com.demo.algorithms.search;

/**
 * @author zmj
 * @date 2020/6/18 10:29
 * @Description
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(InterpolationSearchDemo.search(arr, 56));
    }
}

class InterpolationSearchDemo {
    public static int search(int[] arr, int value) {
        return interpolationSearch(arr, 0, arr.length - 1, value);
    }

    /**
     * 二分法查找改进 → 插值查找
     *
     * @param arr   原数组
     * @param left  左下标
     * @param right 右下标
     * @param value 查询的值
     * @return 查询的值所在下标
     */
    private static int interpolationSearch(int[] arr, int left, int right, int value) {
        System.out.println("hello");
        // 中值改进
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        // 没有查询到数据返回-1
        if (right < left || mid < left || mid > right) {
            return -1;
        }
        // 查询的值小于中值
        if (value < arr[mid]) {
            return interpolationSearch(arr, left, mid - 1, value);
        } else if (value > arr[mid]) {
            // 查询的值大于中值
            return interpolationSearch(arr, mid + 1, right, value);
        } else {
            // 找到数据返回下标
            return mid;
        }
    }
}