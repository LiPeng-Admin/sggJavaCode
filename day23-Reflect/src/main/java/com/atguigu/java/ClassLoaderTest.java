package com.atguigu.java;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description
 * @Author lipeng
 * @create 2022/5/22
 */
/*
* 了解类的加载器
*
* */
public class ClassLoaderTest {
    @Test
    public void test1(){
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        //调用系统类加载器的getParent():获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);

        //调用扩展类加载器的getParent():无法获取引导类加载器
        //引导类加载器主要负责加载java 的核心类库，无法加载自定义类的
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2); //null
    }
    /*
    * Properties:用来读取配置文件
    *
    * */
    @Test
    public void test2()  {
        Properties properties=new Properties();
        //读取配置文件的方式一：
        //此时的文件默认在当前的module下
//        FileInputStream fileInputStream=new FileInputStream("jdbc.properties");
//        properties.load(fileInputStream);

        //读取配置文件的方式二：
        //配置文件默认识别为:当前module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println("user:"+user+",password:"+password);
    }

}
