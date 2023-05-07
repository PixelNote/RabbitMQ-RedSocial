package com.example.MicroserviceUserList.service.rabbitmq;


import com.example.MicroserviceUserList.data.User;
import com.example.MicroserviceUserList.dto.UserDTO;
import com.example.MicroserviceUserList.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserListListener {

    private UserRepository userRepository;

    public UserListListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


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

    @RabbitListener(queues = {"userList"})
    public void receiveUser(UserDTO userDTO){
      userRepository.save(new User(userDTO.getUserName(),userDTO.getStatus()));

      List<User> users = userRepository.findAll();

        System.out.println("Lista de usuarios: ");
        for(User user: users){
            System.out.print("Nombre: "+user.getUserName());
            if(user.getStatus()){
                System.out.println(", Estado: Conectado");
            }else{
                System.out.println(", Estado: Desconectado");
            }
        }

    }

}
