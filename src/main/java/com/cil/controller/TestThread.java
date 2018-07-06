package com.cil.controller;

import com.cil.Tools.HttpTools;

import java.util.Random;

/**
 * Created by bob on 2018.01.15.
 */
public class TestThread extends Thread {

    @Override
    public void run() {
        Random ran = new Random(10);
        System.out.println(ran);
        String param = "{\"company\":\"东莞银行\",\"level\":\"经理\",\"name\":\"jackie.zheng" + ran.nextInt(10) + "\",\"phone\":\"176xxxx4921\",\"avatar\":\"https://xxxx\",\"answer\":{\"merNums\":\"30万\",\"atmNums\":\"160万\",\"currencyNums\":\"55种\",\"cardNums\":\"31亿张\",\"headquarters\":\"纽约\",\"bankNums\":\"35\"}}";
        HttpTools.sendPost("http://localhost:8085/wechat/visaSta", param);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 32; i++) {
            Thread t = new TestThread();
            t.start();
        }

    }
}


