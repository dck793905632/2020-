package com.atguigu.Algorithm.binarysearchnorecursion;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr={1,3,8,10,11,67,100};
        int i = binarySearch(arr, 100);
        System.out.println(i);
    }

    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right=arr.length-1;
        while (left<=right){
            int mid=(left+right)/2;
            if (target==arr[mid]){
                return mid;
            }else if (arr[mid]>target){
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        return -1;
    }
}
