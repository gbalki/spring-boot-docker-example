package com.serhatbalki.spring_boot_docker_example.repository;

import com.serhatbalki.spring_boot_docker_example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
