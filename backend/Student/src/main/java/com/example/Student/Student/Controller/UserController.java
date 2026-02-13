package com.example.Student.Student.Controller;

import com.example.Student.Student.DTOClasse.UserDto;
import com.example.Student.Student.Entity.User;
import com.example.Student.Student.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted";
    }
}
