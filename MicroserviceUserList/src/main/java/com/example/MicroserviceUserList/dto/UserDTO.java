package com.example.MicroserviceUserList.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {

    private String userName;

    private Boolean status;

    public String getUserName() {
        return userName;
    }

    public Boolean getStatus() {
        return status;
    }
}
