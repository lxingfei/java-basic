package com.leh.beans;

/**
 * Created by leh on 2019/9/1.
 */
public class Cat {

    /**
     * 构造函数  至少四种 四个权限
     */

    public Cat(){
        System.out.println("公有的构造方法执行了.........");
    }

    private Cat(int a){
        System.out.println("私有的构造方法执行了.........");
    }

    protected Cat(int a, String b){
        System.out.println("受保护的构造方法执行了.........");
    }

    Cat(char c){
        System.out.println("默认的构造方法执行了.........");
    }




    /**
     * 成员属性
     */

    private int i;

    protected  String k;

    boolean n;

    public char c;


    /**
     * 成员方法
     */

    private void pri(){

    }

    public void pub(){

    }

    protected void pro(){

    }

    void def(){

    }

    @Override
    public String toString() {
        return "Cat{" +
                "i=" + i +
                ", k='" + k + '\'' +
                ", n=" + n +
                ", c=" + c +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("Cat类的 main 方法执行了");
    }

}
