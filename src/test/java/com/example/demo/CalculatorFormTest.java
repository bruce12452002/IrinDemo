package com.example.demo;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

public class CalculatorFormTest {

    static CalculatorForm calc = new CalculatorForm();

//    @Test
    public void xxx() {
        assertEquals(0.1+0.2, 0.3, 0.1); // 誤差 0.1 之內是 ok 的
        assertEquals(0.1+0.2, 0.3, 0); // 沒誤差就報錯了，因為0.1+0.2不是0.3
    }


    @Test
    public void testAddSub() {
        try {
            calc.testClick("CLEAR");
            calc.testClick("1");
            calc.testClick("+");
            calc.testClick("2");
            calc.testClick("=");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        double result = calc.getResult();
        // 原本的寫法只有兩個參數，但已經過時了，所以會有刪除線，因為有誤差的問題，就是我說的 0.1+0.2
//        assertEquals(3, result);
        assertEquals(3, result, 0);// 第三個參數是允許的誤差值
        System.out.println(result);
    }

    public static void main(String[] args) {
        calc.showWindow();
        Result result = JUnitCore.runClasses(CalculatorFormTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

}