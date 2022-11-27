package com.stelmashok.service;

import com.stelmashok.domain.User;
import com.stelmashok.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save (UserDto userDTO);

    void save (User user);
    List<UserDto> getAll();

    User findByName(String name);
    void updateProfile(UserDto userDTO);
    boolean activateUser(String activateCode);
}
