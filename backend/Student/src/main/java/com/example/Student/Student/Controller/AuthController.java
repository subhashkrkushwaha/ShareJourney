package com.example.Student.Student.Controller;

import com.example.Student.Student.DTOClasse.LoginRequest;
import com.example.Student.Student.DTOClasse.UserDto;
import com.example.Student.Student.Entity.User;
import com.example.Student.Student.Service.UserDetailsServiceImpl;
import com.example.Student.Student.Service.UserService;
import com.example.Student.Student.Utitlis.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping("/health-check")
    public  String health(){
        return "Health Check is working";
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }
    @PutMapping("/update/{userName}")
    public ResponseEntity<UserDto> update(@RequestBody User user,@PathVariable String userName) {
        try {
            User userInDb = userService.loadByUsername(userName);
            if (userInDb != null) {
                userInDb.setUserName(user.getUserName());
                userInDb.setPassword(user.getPassword());
                userService.registerUser(userInDb);
            }
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.loadByUsername(loginRequest.getUserName());
        if (user == null || !passwordEncoder.
                matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid Credentials"));
        }
//        String token = jwtUtil.generateToken(user.getUserName());
//        UserDetails userDetail = (UserDetails) userService.loadByUsername(user.getUserName());
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted";
    }

}
