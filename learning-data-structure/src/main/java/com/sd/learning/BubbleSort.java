package com.sd.learning;

/**
 * 冒泡排序
 */
public class BubbleSort {

    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static void sort(int[] arr){
        if(arr == null && arr.length == 0){
            return;
        }
        // 是否有排序标志，优化默认有序的数列集合
        boolean isSortFlag = true;
        for (int i = 0; i <arr.length && isSortFlag ; i++) {
            isSortFlag = false;
            for (int j = arr.length-1; j >i ; j--) {
                if(arr[j] < arr[j-1]){
                    isSortFlag = true;
                    swap(arr,j,j-1);
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {3,5,1,7,2,8,4,5,3,2};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println("args = [" + arr[i] + "]");
        }
    }
}
