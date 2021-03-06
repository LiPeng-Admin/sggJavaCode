package com.atguigu.p2.bean;

/**
 * @author lipeng
 * @version 1.0.0
 * @ClassName Customer.java
 * @Description 实体类，用来封装客户信息
 * @createTime 2022年03月05日 23:07:00
 */
public class Customer {
    //属性
    private String name; //姓名
    private char gender; // 性别
    private int age;  //年龄
    private String  phone; // 电话号码
    private String email; //电子邮箱

    //构造器
    public Customer(){

    }
    public Customer(String name,char gender,int age,String phone,String email){
        this.name=name;
        this.gender=gender;
        this.age=age;
        this.phone=phone;
        this.email=email;
    }


    //set，get方法
    public void  setName(String name){
        this.name=name;

    }
    public String getName(){
        return name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public  void setAge(int age){
        this.age=age;
    }

    public int getAge() {
        return age;
    }

    public void setGender(char gender){
        this.gender=gender;

    }

    public char getGender() {
        return gender;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }

    public String getPhone() {
        return phone;
    }
}
