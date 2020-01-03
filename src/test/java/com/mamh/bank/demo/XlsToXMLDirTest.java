package com.mamh.bank.demo;

import com.google.gson.Gson;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XlsToXMLDirTest {

    @Test
    public void test() {
        String[] args = {"4", "", ""};
        int actionNumber = Integer.parseInt(args[0]);
        switch (actionNumber) {

            case 4:
                XlsToXMLDir.doCollectAllStrings(args);
                break;

        }
    }


    @Test
    public void testSimpleDateFormat(){

        String s = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        System.out.println(s);
        System.out.println(new Date());


        SimpleDateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy ZZZZ");

    }


    @Test
    public void testSimpleDateFormat1(){
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy ZZZZ");

        String s = format.format(new Date());
        System.out.println(s);
        System.out.println(new Date());

            Gson gson = new Gson();
            System.out.println(gson.toJson(format));


    }
}
