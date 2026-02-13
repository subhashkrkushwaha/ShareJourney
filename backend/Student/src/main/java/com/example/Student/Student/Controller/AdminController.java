package com.example.Student.Student.Controller;


import com.example.Student.Student.DTOClasse.UserDto;
import com.example.Student.Student.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;


    @GetMapping("/all-user-list")
    public ResponseEntity<?> getAllUser(){
       List<UserDto> userList= userService.getAllUsers();
       if(userList != null && !userList.isEmpty()){
           return new ResponseEntity<>(userList, HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
