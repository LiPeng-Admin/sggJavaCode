/*
* 使用二维数组答应一个10行的杨辉三角
* 提示：
* 1：第一行有1个元素，第n行有n 个元素
* 2：每一行的第一个元素和最后一个元素都是1
* 3：从第三行开始，对于非第一个元素和最后一个元素，即
* yangHui[i][j]=yangHui[i-1][j-1]+yangHui[i-1][j]
*
* */

package com.atguigu.exer;

public class YangHuiTest {
    public static void main(String[] args) {
        //1：声明并初始化二维数组
        int [][] yangHui=new int[10][];

        //2：给数组的元素赋值
        for(int i=0;i<yangHui.length;i++){
            yangHui[i]=new int[i+1];
            //2.1给首末元素赋值
            yangHui[i][0]=yangHui[i][i]=1;
            //2.2给每行的非首末元素赋值
            if(i>1){
                for(int j=1;j<yangHui[i].length-1;j++){
                    yangHui[i][j]=yangHui[i-1][j-1]+yangHui[i-1][j];

                }
            }
        }
        //3：遍历二维数组
        for(int i=0;i<yangHui.length;i++){
            for(int j=0;j<yangHui[i].length;j++){
                System.out.print(yangHui[i][j]+" ");
            }
            System.out.println();
        }


    }
}
