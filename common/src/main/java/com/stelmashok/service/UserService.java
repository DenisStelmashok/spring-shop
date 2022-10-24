package com.stelmashok.service;

import com.stelmashok.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean save (UserDTO userDTO);
}
