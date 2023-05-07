package com.example.userState.service.rabbitmq;

import com.example.userState.controller.dto.UserDTO;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
@EnableRabbit
public class sendUserDataService {

    private final RabbitTemplate rabbitTemplate;

    public sendUserDataService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    public void sendData(UserDTO userDTO){

        System.out.print("El usuario "+ userDTO.getUserName());
        if(userDTO.getStatus()){
            System.out.println(" ha iniciado sesion");
        }else{
            System.out.println(" ha cerrado sesion");
        }

        rabbitTemplate.convertAndSend("sessionExchange", "user", userDTO);

    }
}

