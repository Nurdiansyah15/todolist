package com.nurd.to_do_list_app.services;

import com.nurd.to_do_list_app.domain.models.Role;
import com.nurd.to_do_list_app.domain.models.User;
import com.nurd.to_do_list_app.domain.repos.UserRepo;
import com.nurd.to_do_list_app.securities.JwtService;
import com.nurd.to_do_list_app.utils.dto.AuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthDto.ResponseRegisterDto register(AuthDto.RequestRegisterDto requestRegisterDto) {
        if (userRepo.findByEmail(requestRegisterDto.getEmail()).isPresent()) {
            throw new IllegalStateException("email already exists");
        }

        User user = new User();
        user.setUsername(requestRegisterDto.getUsername());
        user.setEmail(requestRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestRegisterDto.getPassword()));

        Set<Role> roles = new HashSet<>();

        if (requestRegisterDto.getRoles().isEmpty()) {
            roles.add(Role.ROLE_USER);
        } else {
            for (var role : requestRegisterDto.getRoles()) {
                try {
                    roles.add(Role.valueOf("ROLE_" + role.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }

        user.setRoles(roles);
        userRepo.save(user);
        return new AuthDto.ResponseRegisterDto(user.getUuid(), user.getUsername(), user.getEmail(), user.getRoles());
    }

    public AuthDto.ResponseLoginDto login(AuthDto.RequestLoginDto requestLoginDto) {

        User user = userRepo.findByEmail(requestLoginDto.getEmail()).orElseThrow(() -> new IllegalStateException("invalid credentials"));

        if (!passwordEncoder.matches(requestLoginDto.getPassword(), user.getPassword())) {
            throw new IllegalStateException("invalid username or password");
        }

        String jwt = jwtService.generateToken(user);

        return new AuthDto.ResponseLoginDto(user.getUuid(), jwt);
    }
}
