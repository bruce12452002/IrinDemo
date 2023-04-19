package com.example.demo;

public class Test {

    public static void main(String[] args) {
        System.out.println("one");
        assert 6 < 0; // 如果有開啟 assertion，而且是 false，就不會往下執行
        System.out.println("two");
    }


}
