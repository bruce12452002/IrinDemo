package com.example.demo;

public class CallBy {
    static class Animal {
        public void m() {
            System.out.println("animal");
        }
    }

    static class Dog extends Animal {
        public void m2() {
            System.out.println("Dog");
        }
    }

    static class Poppy extends Dog {
        public void m2() {
            System.out.println("Poppy");
        }
    }

    static class Cat extends Animal {
    }

    public void xxx() {
        Poppy a = new Poppy();
        a.m2();
    }

    Animal a = new Poppy();
    Dog c = (Dog) a;
    public static void main(String[] args) {
        CallBy c = new CallBy();
        Animal a = c.a;
        c.xxx();
        System.out.println();
    }

//        a.m();
//        List<String> list = new ArrayList();


}
