package com.yix.serviceverificationcode.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberCodeControllerTest {

    @Test
    public void testCode() {
        double mathRandow = (Math.random()*9 + 1)*(Math.pow(10,5));
        System.out.println(mathRandow);
        int resultInt = (int)mathRandow;
        System.out.println(resultInt);

    }

}