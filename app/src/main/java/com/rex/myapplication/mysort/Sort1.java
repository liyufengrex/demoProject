package com.rex.myapplication.mysort;

/**
 * @Description: 二分查找法
 * @Author: liyufeng
 * @CreateDate: 2020-06-09 14:13
 */

public class Sort1 {

    public static int binarySearch(int[] data, int searchKey) {
        int low = 0;
        int high = data.length - 1;
        int middle = 0;

        if (data[low] > searchKey || data[high] < searchKey) {
            return -1;
        }
        while (low <= high) {
            middle = (high - low) / 2;
            if (data[middle] == searchKey) {
                return middle;
            } else if (data[middle] > searchKey) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }


    public static int binarySearch1(int[] arr, int key, int low, int high) {
        if (arr[low] > key || arr[high] < key) {
            return -1;
        }
        int middle = (high - low) / 2;
        if (key > arr[middle]) {
            binarySearch1(arr, key, middle + 1, high);
        } else if (key < arr[middle]) {
            binarySearch1(arr, key, low, middle - 1);
        } else {
            return middle;
        }
        return -1;
    }

}
