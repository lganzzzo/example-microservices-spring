package com.example.Facade.service;

import com.example.Facade.dto.UserDto;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient("user-service")
public interface UserService {

    @RequestLine("GET /users")
    List<UserDto> getUsers();

    @RequestLine("GET /users/{id}")
    UserDto getUserById(@Param("id") Long id);

}
