package com.stelmashok.service;

import com.stelmashok.domain.User;
import com.stelmashok.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save (UserDTO userDTO);

    void save (User user);
    List<UserDTO> getAll();

    User findByName(String name);
    void updateProfile(UserDTO userDTO);
}
