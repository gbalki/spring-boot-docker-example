package com.serhatbalki.spring_boot_docker_example.controller;

import com.serhatbalki.spring_boot_docker_example.dto.UserDto;
import com.serhatbalki.spring_boot_docker_example.entity.User;
import com.serhatbalki.spring_boot_docker_example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @DeleteMapping("/deleteByUsername/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.deleteByUsername(username));
    }

    @PutMapping("/updateUser/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(username,userDto));
    }
}
