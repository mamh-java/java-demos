package com.cil.controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


/**
 * Created by bob on 2017.08.16.
 */
public class Test {


    public static void main(String[] args) throws Exception{
//        try {
//            DatagramSocket socket = new DatagramSocket();
////            String s = "测试文字ABC";
//            String s = "8154255F41EA3C386C969D8B575EBD5DF211FAE79BD830F1F7E9C6B8AC4F8016{\"msgSource\":\"PcClient\",\"msgType\":\"IdleMsg\",\"msgVersion\":\"1.0\",\"msgDir\":\"Q\",\"msgSeqNum\":\"20170817102155\",\"merCode\":\"999290070110025\",\"termCode\":\"00000001\",\"termVersion\":\"2.3\",\"hashKey\":\"fee7b31a3bb4f930e8d229fbcf3b9fd6\"}";
//            byte[] buffer = s.getBytes();
//            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("127.0.0.1"), 39999);
//            socket.send(packet);
//            byte[] buffer1 = new byte[1024];
//            DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length);
//            socket.receive(packet1);
//            byte data[] = packet1.getData();// 接收的数据
//            InetAddress address = packet1.getAddress();// 接收的地址
//            System.out.println("接收的文本:" + new String(data));
//            System.out.println("接收的ip地址:" + address.toString());
//            System.out.println("接收的端口:" + packet1.getPort()); // 9004
//            socket.close();
//        } catch (SocketException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        DatagramSocket ds = null;  //建立套间字udpsocket服务

        try {
            ds = new DatagramSocket(13469);  //实例化套间字，指定自己的port
        } catch (SocketException e) {
            System.out.println("Cannot open port!");
            System.exit(1);
        }

        byte[] buf= "Hello, I am sender!".getBytes();  //数据
        InetAddress destination = null ;
            destination = InetAddress.getByName("10.30.2.13");  //需要发送的地址
        DatagramPacket dp =
                new DatagramPacket(buf, buf.length, destination , 39999);
        //打包到DatagramPacket类型中（DatagramSocket的send()方法接受此类，注意10000是接受地址的端口，不同于自己的端口！）

        try {
            ds.send(dp);  //发送数据
        } catch (IOException e) {
        }
        ds.close();
    }
}
