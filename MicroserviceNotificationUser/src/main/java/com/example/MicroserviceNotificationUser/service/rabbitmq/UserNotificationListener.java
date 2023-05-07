package com.example.MicroserviceNotificationUser.service.rabbitmq;

import com.example.MicroserviceNotificationUser.dto.UserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserNotificationListener {

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = {"userNotification"})
    public void receiveUser(UserDTO userDTO){

        if(userDTO.getUserName().equals("Juan") || userDTO.getUserName().equals("Maria")){
            System.out.print("El usuario "+ userDTO.getUserName());
            if(userDTO.getStatus()){
                System.out.println(" se ha Conectado");
            }else{
                System.out.println(" se ha Desconectado");
            }

        }

    }
}
