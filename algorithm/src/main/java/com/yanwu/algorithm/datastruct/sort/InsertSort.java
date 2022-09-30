package com.yanwu.algorithm.datastruct.sort;

/**
 * @Description: InsertSort
 * @Author yanwu
 * @Date 2022/9/22 11:29
 * @Version 1.0
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] a = {4, 2, 3, 1, 1, 8, 7, 5};
        sort(a, a.length);
        for (int i = 0; i < a.length; i ++) {
            System.out.println(a[i]);
        }
    }

    static void sort(int[] a, int n) {
        for (int i = 1; i < n; ++ i) {
            int j = i - 1;
            int val = a[i];
            for (; j >= 0; -- j) {
                if (a[j] > val) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = val;
        }
    }
}
