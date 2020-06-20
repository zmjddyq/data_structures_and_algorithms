package com.demo.algorithms.search;

import java.util.ArrayList;

/**
 * @author zmj
 * @date 2020/6/18 9:28
 * @Description 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 42, 42, 42, 42, 42, 42, 47, 68, 894};
        int search = BinarySearchDemo.search(arr, 1);
        if (search == -1) {
            System.out.println("没有找到查询的数据");
        } else {
            System.out.println("查询到的位置为： " + search);
        }
        ArrayList<Integer> searchList = BinarySearchDemo.searchList(arr, 42);
        if (searchList.isEmpty()) {
            System.out.println("没有找到查询的数据");
        } else {
            System.out.println("查询到的位置为： " + searchList);
        }
    }
}

class BinarySearchDemo {
    public static int search(int[] arr, int value) {
        return binarySearch(arr, 0, arr.length - 1, value);
    }

    /**
     * 二分法查找
     *
     * @param arr   原数组
     * @param left  左下标
     * @param right 右下标
     * @param value 查询的值
     * @return 查询的值所在下标
     */
    private static int binarySearch(int[] arr, int left, int right, int value) {
        System.out.println("hello");
        // 没有查询到数据返回-1
        if (right < left) {
            return -1;
        }
        // 中值
        int mid = (right + left) / 2;
        // 查询的值小于中值
        if (value < arr[mid]) {
            return binarySearch(arr, left, mid - 1, value);
        } else if (value > arr[mid]) {
            // 查询的值大于中值
            return binarySearch(arr, mid + 1, right, value);
        } else {
            // 找到数据返回下标
            return mid;
        }
    }

    public static ArrayList<Integer> searchList(int[] arr, int value) {
        return binarySearchList(arr, 0, arr.length - 1, value);
    }

    /**
     * 二分法查找(多个值)
     *
     * @param arr   原数组
     * @param left  左下标
     * @param right 右下标
     * @param value 查询的值
     * @return 查询的值所在下标集合
     */
    private static ArrayList<Integer> binarySearchList(int[] arr, int left, int right, int value) {
        ArrayList<Integer> indexList = new ArrayList<>();
        // 没有查询到数据返回-1
        if (right < left) {
            return indexList;
        }
        // 中值
        int mid = (right + left) / 2;
        // 查询的值小于中值
        if (value < arr[mid]) {
            return binarySearchList(arr, left, mid - 1, value);
        } else if (value > arr[mid]) {
            // 查询的值大于中值
            return binarySearchList(arr, mid + 1, right, value);
        } else {
            // 找到数据查看是否有重复值
            indexList.add(mid);
            if (arr[mid - 1] == value) {
                for (int i = mid - 1; i >= 0 && arr[i] == value; i--) {
                    indexList.add(i);
                }
            }
            if (arr[mid + 1] == value) {
                for (int i = mid + 1; i < arr.length && arr[i] == value; i++) {
                    indexList.add(i);
                }
            }
            indexList.sort(Integer::compareTo);
            return indexList;
        }
    }
}