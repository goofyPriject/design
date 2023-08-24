package com.example.design.adapt;

import java.util.function.Function;

public class Client {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target adapter = new Adapter(adaptee);
        Demo demo = new Demo();
        Demo1 demo1 = new Demo1();
        adapter.request(demo1); // 通过适配器调用被适配者的方法
    }
}
