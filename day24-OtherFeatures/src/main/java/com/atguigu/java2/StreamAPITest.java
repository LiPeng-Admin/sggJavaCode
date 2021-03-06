package com.atguigu.java2;

import com.atguigu.java3.Employee;
import com.atguigu.java3.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1:Stream关注的是对数据的运算，与cpu打交道
 * 集合关注的是数据的存储，与内存打交道
 * <p>
 * 2：  ① Stream 自己不会存储元素。
 * ② Stream 不会改变源对象。相反，他们会返回一个持有结果的新 Stream。
 * ③ Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行
 * 3：Stream执行过程
 * 1:Stream实例化
 * 2：一系列的中间操作（过滤，映射。。。）
 * 3：终止操作
 * <p>
 * 4：说明
 * 一个中间操作链，对数据源的数据进行处理
 * 一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 *
 * @Description
 * @Author lipeng
 * @create 2022/5/30
 */
public class StreamAPITest {
    //创建Stream方式一：通过集合
    @Test
    public void test1(){
        List<Employee>employees= EmployeeData.getEmployees();

        //default Stream<E> stream() : 返回一个顺序流
        Stream<Employee> stream = employees.stream();

        // default Stream<E> parallelStream() : 返回一个并行流

        Stream<Employee> parallelStream = employees.parallelStream();
    }

    //创建 Stream方式二：通过数组
    @Test
    public void test2(){
        //调用Arrays类的static <T> Stream<T> stream(T[] array): 返回一个流
        int[]arr=new int []{1,2,3,4,5};
        IntStream stream = Arrays.stream(arr);

        Employee employee = new Employee(1001, "Tom");
        Employee employee1 = new Employee(1002, "Jery");
        Employee[] employees = {employee, employee1};
        Stream<Employee> employeeStream = Arrays.stream(employees);


    }
    //创建 Stream方式三：通过Stream的of()
    @Test
    public void test3(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);

    }

    //创建 Stream方式四：创建无限流
    @Test
    public void test4(){
        //      迭代
//      public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //遍历前10个偶数
        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);
        //      生成
//      public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

}
