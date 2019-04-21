package cn.javakk.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听mobileMessage
 * @Author: javakk
 * @Date: 2019/4/21 0:56
 */

@Component
@RabbitListener(queues = "register")
public class MobileMessageListener {

    @Autowired
    private MessageService messageService;

    /**
     * 发送短信
     * @param message
     */
    @RabbitHandler
    public void sendMessage(Map<String, String> message){
        messageService.sendRegisterMessage(message.get("mobile"), message.get("code"));
        System.out.println("手机号：" + message.get("mobile"));
    }
}
