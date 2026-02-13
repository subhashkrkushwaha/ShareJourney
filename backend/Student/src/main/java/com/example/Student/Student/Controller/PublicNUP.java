package com.example.Student.Student.Controller;

import com.example.Student.Student.DTOClasse.UserDto;
import com.example.Student.Student.Entity.User;
import com.example.Student.Student.Service.UserService;
import com.example.Student.Student.Utitlis.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

//Not uses in this project

@RestController
@RequestMapping("/public")
public class PublicNUP {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public  String health(){
        return "Health Check is working";
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/create")
    public UserDto createUser(@RequestBody User user) {
                   return userService.registerUser(user);
    }
    // Map base Authentication not safe  (Harder to debug & maintain,Bad for Android apps)
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        User user = userService.loadByUsername(loginRequest.get("userName"));
        if (!passwordEncoder.matches(loginRequest.get("password"), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }
//        String token = jwtUtil.generateToken(user.getUserName());
      UserDetails userDetails =
              (UserDetails) userService.loadByUsername(user.getUserName());

      String token = jwtUtil.generateToken(userDetails);

      return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}