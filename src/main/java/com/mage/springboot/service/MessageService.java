package com.mage.springboot.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class MessageService {

    @RabbitListener(queues = "atguigu.news")
    public void receive(Object o) {
        System.err.println("收到消息： " + o);
    }

    @RabbitListener(queues = "atguigu.emps")
    public void receive1(Object o) {
        System.err.println("收到消息1： " + o);
    }
    @RabbitListener(queues = "atguigu")
    public void receive2(Message message) {
        byte[] body = message.getBody();

        MessageProperties properties = message.getMessageProperties();

        System.err.println("收到消息2： " + new String(body));
        System.err.println("收到消息2： " + properties);

    }
}
