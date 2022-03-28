package com.atuguigu.java;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Error.java
 * @Description Error
 * @createTime 2022年03月27日 10:33:00
 */
/*
*Error:Java 虚拟机无法解决的严重问题，如：JVM 系统内部错误，资源耗尽等严重情况
* 比如：StackOverflowError和OOM，一般不编写针对性的代码进行处理
*
* */
public class ErrorTest {
    public static void main(String[] args) {
        //栈溢出：java.lang.StackOverflowError
//        main(args);
        //堆溢出：java.lang.OutOfMemoryError:OOM
        Integer [] arr=new Integer[1024*1024*1024];
    }
}
