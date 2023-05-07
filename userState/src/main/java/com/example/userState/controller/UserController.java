package com.example.userState.controller;

import com.example.userState.controller.dto.UserDTO;
import com.example.userState.service.rabbitmq.sendUserDataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private sendUserDataService dataService;

    public UserController(sendUserDataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping(path = "/user")
    public void updateUserState(@RequestBody UserDTO userDTO) {
            dataService.sendData(userDTO);

    }
}
