package com.sahin.archiving_system.service.impl;

import com.sahin.archiving_system.dto.LoginResponse;
import com.sahin.archiving_system.dto.UserLoginRequest;
import com.sahin.archiving_system.dto.UserRegisterRequest;
import com.sahin.archiving_system.dto.UserRegisterResponse;
import com.sahin.archiving_system.mapper.UserResponseMapper;
import com.sahin.archiving_system.model.User;
import com.sahin.archiving_system.repository.UserRepository;
import com.sahin.archiving_system.security.JwtService;
import com.sahin.archiving_system.service.AuthService;
import com.sahin.archiving_system.service.FolderService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserResponseMapper userResponseMapper;
    private final JwtService jwtService;
    private final FolderService folderService;
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserResponseMapper userResponseMapper, JwtService jwtService, FolderService folderService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userResponseMapper = userResponseMapper;
        this.jwtService = jwtService;
        this.folderService = folderService;
    }

    @Override
    @Transactional
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setEmail(userRegisterRequest.getEmail());
        user.setFullName(userRegisterRequest.getFullName());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        User savedUser = userRepository.save(user);
        folderService.createInBoxFolder(user);
        return userResponseMapper.userToUserRegisterResponse(user);
    }

    @Override
    public LoginResponse authenticate(UserLoginRequest userLoginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword()));
        User authenticatedUser = userRepository.findByEmail(userLoginRequest.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(authenticatedUser);
        long expiresIn = jwtService.getExpirationTime();
        LoginResponse response = new LoginResponse(jwtToken, expiresIn, authenticatedUser.getFullName());
        return response;
    }
}
