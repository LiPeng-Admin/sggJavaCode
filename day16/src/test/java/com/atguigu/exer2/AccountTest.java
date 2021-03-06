package com.atguigu.exer2;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName AccountTest.java
 * @Description TODO
 * @createTime 2022年04月01日 00:01:00
 */
/*
* 银行有一个账户
* 有两个储户分别向同一个账号存3000元，每次存1000，存3次，每次存完打印账户余额
* 分析：
* 1：是否是多线程问题？是，两个储户
* 2：是否有共享数据？有？账户
* 3：是否有线程安全问题？有
* 4：需要考虑如何解决线程安全问题？同步机制：三种方式
*
* */
public class AccountTest {
    public static void main(String[] args) {

        Account account=new Account();
        Customer customer=new Customer(account);
        Customer customer1=new Customer(account);
        customer.start();
        customer1.start();

    }

}
class Account{
    private double balance;

    //存钱
    public synchronized void  deposit(double amt){

        if(amt>0){
            balance+=amt;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+",存钱成功，余额为："+balance);
        }

    }

}
class Customer extends Thread{

    private  Account acc;
    public Customer(Account acc){
        this.acc=acc;

    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acc.deposit(1000);

        }
    }
}

