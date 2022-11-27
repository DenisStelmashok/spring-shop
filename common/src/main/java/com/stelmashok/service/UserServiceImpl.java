package com.stelmashok.service;

import com.stelmashok.dao.UserRepository;
import com.stelmashok.domain.Role;
import com.stelmashok.domain.User;
import com.stelmashok.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final MailSenderService mailSenderService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MailSenderService mailSenderService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
    }

    @Override
    @Transactional
    public boolean save(UserDto userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            throw new IllegalArgumentException("Password is not equals");
        }
        User user = User.builder()
                .name(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.CLIENT)
                .activateCode(java.util.UUID.randomUUID().toString())
                .build();
        userRepository.save(user);
        return true;
    }

    @Override
    public void save(User user) {

        userRepository.save(user);
        if (user.getActivateCode() != null && !user.getActivateCode().isEmpty()) {
            mailSenderService.sendActivateCode(user);
        }
    }
    @Override
    public List<UserDto> getAll(){
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByName(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found with name: " + username);
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles
        );
    }

    private UserDto toDto (User user) {
        return UserDto.builder()
                .username(user.getName())
                .email(user.getEmail())
                .activated(user.getActivateCode() == null)
                .build();
    }
    @Override
    public User findByName(String name) {
        return userRepository.findFirstByName(name);

    }
    @Override
    @Transactional
    public void updateProfile (UserDto dto) {
        User savedUser = userRepository.findFirstByName(dto.getUsername());
        if (savedUser == null){
            throw new RuntimeException("User not found with name: " + dto.getUsername());

        }
        boolean isChanged = false;
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()){
            savedUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            isChanged = true;
        }

        if (!Objects.equals(dto.getEmail(), savedUser.getEmail())){
            savedUser.setEmail(dto.getEmail());
            isChanged = true;
        }
        if (isChanged){
            userRepository.save(savedUser);
        }
    }
    @Override
    @Transactional
    public boolean activateUser(String activateCode) {
        if(activateCode == null || activateCode.isEmpty()){
            return false;
        }
        User user = userRepository.findFirstByActivateCode(activateCode);
        if(user == null){
            return false;
        }

        user.setActivateCode(null);
        userRepository.save(user);

        return true;
    }
}
