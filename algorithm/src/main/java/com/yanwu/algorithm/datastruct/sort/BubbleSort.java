package com.yanwu.algorithm.datastruct.sort;


import java.util.Arrays;
import java.util.function.IntConsumer;

/**
 * @Description: BubbleSort
 * @Author yanwu
 * @Date 2022/9/22 10:59
 * @Version 1.0
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = {4, 2, 3, 1, 5};
        sort(a, a.length);
        for (int i = 0; i < a.length; i ++) {
            System.out.println(a[i]);
        }
    }

    /**
     * 最好时间复杂度，满有序度，O(n)
     * 最坏时间复杂度，有序度为 0，O(n^2)
     * 平均时间复杂度，取有序度为 n * (n - 1) >> 2 的场景，交换次数为：满有序度 - 基础有序度，比较次数远大于交换次数，所以复杂度为 O(n^2)
     * @param a 待排序数组
     * @param n 数组大小
     */
    static void sort(int[] a, int n) {
        boolean flag = false;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n - i - 1; ++ j) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) return;
        }
    }

    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
