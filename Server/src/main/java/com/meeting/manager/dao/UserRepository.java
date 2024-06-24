package com.meeting.manager.dao;

import com.meeting.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User,String> {
    List<User> findByUsernameAndStatus(String username, String status);
    List<User> findByUsername(String username);
    List<User> findByRole(String role);
}
