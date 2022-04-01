package com.atguigu.java3;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName WindowTest.java
 * @Description TODO
 * @createTime 2022年03月30日 21:42:00
 */
/*例子：创建三个窗口卖票，总票数为100 张:
/  1：  问题：卖票过程中，存在重票，错票 --线程的安全问题
// :2：  问题出现的原因:当某个线程操作车票的过程中，尚未操作完成，其他线程参与进来，也操作车票
//   3： 如何解决：当一个线程a在操作ticket的时候，其他线程不能参与进来，直到线程a操作完ticket 时，
//    其他线程才可以开始操作，这种情况即使线程a出现了阻塞，也不能改变
4:在java 中，我们通过同步机制，来解决线程的安全问题
方式一：同步代码块
 synchronized (同步监视器){
 //需要被同步的代码
 }
 //说明：1：操作共享数据的代码，即为需要被同步的代码
        2：共享数据：多个线程共同操作的变量，比如：ticket就是共享数据
        3：同步监视器，俗称：锁。任何一个类的对象，都可以充当锁
              要求：多个线程必须要共用同一把锁
         补充：在实现Runnable接口创建多线程的方式中，我们可以考虑使用this 充电同步监视器
方式二：同步方法
         如果操作共享数据的代码完整的声明在方法中，我们不妨将此方法声明同步的



5：同步的方式，解决了线程安全问题--优势
    操作同步代码时，只能有一个线程参与，其他线程等待。相当于一个单线程的过程，效率低，--局限性
 */




public class WindowTest {
    public static void main(String[] args) {
        Window window=new Window();

        Thread thread =new Thread(window);
        Thread thread2 =new Thread(window);
        Thread thread3 =new Thread(window);


        thread.start();
        thread2.start();
        thread3.start();


    }
}
class Window implements  Runnable{
    private int ticket=100;
//    Object o=new Object();

    @Override
    public void run() {

        while (true){
            synchronized (this) { //此时的this:唯一的Window 对象：windows   //方式二：synchronized (o) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
