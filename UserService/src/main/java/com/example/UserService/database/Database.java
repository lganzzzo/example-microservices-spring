package com.example.UserService.database;

import com.example.UserService.database.model.User;
import com.example.UserService.dto.UserDto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Database {

    private long idCounter;
    private Map<Long, User> usersById = new HashMap<>();

    public Database() {

        idCounter = 1;

        createUser(new UserDto(0, "F. Scott Fitzgerald"));
        createUser(new UserDto(0, "Harper Lee"));
        createUser(new UserDto(0, "J.K. Rowling"));
        createUser(new UserDto(0, "George Orwell"));
        createUser(new UserDto(0, "J.D. Salinger"));

    }

    private User serializeFromDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName());
    }

    private UserDto deserializeToDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }

    public synchronized UserDto createUser(UserDto userDto) {
        User user = serializeFromDto(userDto);
        user.setId(idCounter ++);
        usersById.put(user.getId(), user);
        return deserializeToDto(user);
    }

    public synchronized UserDto updateUser(UserDto userDto) {

        User user = serializeFromDto(userDto);

        if(user.getId() < 0) {
            throw new RuntimeException("User Id cannot be less than 0");
        }

        User existingUser = usersById.get(user.getId());
        if(existingUser == null) {
            throw new RuntimeException("Such user not found");
        }

        usersById.put(user.getId(), user);

        return deserializeToDto(user);

    }

    public synchronized UserDto getUserById(long id) {
        return deserializeToDto(usersById.get(id));
    }

    public synchronized List<UserDto> getUsers() {
        List<UserDto> result = new LinkedList<>();
        for(User user : usersById.values()) {
            result.add(deserializeToDto(user));
        }
        return result;
    }

    public synchronized boolean deleteUser(long id) {
        return usersById.remove(id) != null;
    }

}