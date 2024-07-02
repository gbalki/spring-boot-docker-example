package com.serhatbalki.spring_boot_docker_example.service;

import com.serhatbalki.spring_boot_docker_example.dto.UserDto;
import com.serhatbalki.spring_boot_docker_example.entity.User;
import com.serhatbalki.spring_boot_docker_example.error.NotFoundException;
import com.serhatbalki.spring_boot_docker_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserDto save(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
        userRepository.save(user);
        return userDto;
    }

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new IllegalStateException("Kullanıcı bulunamadı");
        }
        return users.stream()
                .map(user -> UserDto.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build())
                .collect(Collectors.toList());
    }

    public UserDto getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NotFoundException();
        }
        return UserDto.builder().username(user.getUsername()).password(user.getPassword()).build();
    }

    public String deleteByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NotFoundException();
        }
        userRepository.delete(user);
        return "User deleted";
    }

    public UserDto updateUser(String username, UserDto userDto) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NotFoundException();
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return userDto;
    }
}
