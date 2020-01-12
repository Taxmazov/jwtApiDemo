package com.example.jwtApiDemo.controller;

import com.example.jwtApiDemo.dto.UserDto;
import com.example.jwtApiDemo.models.User;
import com.example.jwtApiDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/users/")
public class UserRestControllerV1 {

    private final UserService userService;
    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "id") int id){
        User user=userService.findById(id);
        if(user==null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
         UserDto result=UserDto.fromUser(user);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody  User user){
        userService.register(user);
        if(user==null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserDto result=UserDto.fromUser(user);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


}
