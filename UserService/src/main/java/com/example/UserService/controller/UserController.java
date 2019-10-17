package com.example.UserService.controller;

import com.example.UserService.database.Database;
import com.example.UserService.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private Database database;

    @RequestMapping(
            value = {"/users"},
            method = RequestMethod.POST, produces = "application/json"
    )
    public ResponseEntity<UserDto> postUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(database.createUser(user), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/users"},
            method = RequestMethod.GET, produces = "application/json"
    )
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(database.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/users/{id}"},
            method = RequestMethod.GET, produces = "application/json"
    )
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        return new ResponseEntity<>(database.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/users/{id}"},
            method = RequestMethod.PUT, produces = "application/json"
    )
    public ResponseEntity<UserDto> putUserById(@PathVariable long id, @RequestBody UserDto user) {
        user.setId(id);
        return new ResponseEntity<>(database.updateUser(user), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/users/{id}"},
            method = RequestMethod.DELETE, produces = "text/plain"
    )
    public ResponseEntity<String> deleteUserById(@PathVariable long id) {
        if(database.deleteUser(id)){
            return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not deleted. Perhaps no such User in the Database", HttpStatus.NOT_FOUND);
    }

}
