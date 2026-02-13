package com.example.Student.Student.Service;

import com.example.Student.Student.DTOClasse.UserDto;
import com.example.Student.Student.Entity.User;
import com.example.Student.Student.ExceptionHandling.ResourceNotFoundException;
import com.example.Student.Student.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    public UserDto registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles((java.util.Set<String>) Arrays.asList("USER"));
//        user.setRoles((java.util.Set<String>) Arrays.asList("ADMIN"));
        user.setRoles(Set.of("ROLE_USER", "ROLE_ADMIN"));
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserDto.class);
    }
    public UserDto saveUser(User user) {
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserDto.class);
    }

    public User loadByUsername(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream().map(u -> modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}

