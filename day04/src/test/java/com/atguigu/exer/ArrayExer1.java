package com.atguigu.exer;

public class ArrayExer1 {
    public static void main(String[] args) {
        int[][]arr=new int[][]{{3,5,8},{12,9},{7,0,6,4}};
        int sum=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
                sum+=arr[i][j];

            }
        }
        System.out.println();
        System.out.println("二维数组的总和： "+sum);
    }
}
