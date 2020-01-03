package com.mamh.bank.demo;


public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        int actionNumber = Integer.parseInt(args[0]);
        switch(actionNumber) {

            case 4:
                XlsToXMLDir.doCollectAllStrings(args);
                break;

        }


    }
}
