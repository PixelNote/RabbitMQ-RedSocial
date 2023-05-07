package com.example.MicroserviceUserList.repository;

import com.example.MicroserviceUserList.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
