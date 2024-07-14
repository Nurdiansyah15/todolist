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

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthDto.ResponseRegisterDto register(AuthDto.RequestRegisterDto requestRegisterDto) {
        if (userRepo.findByUsername(requestRegisterDto.getUsername()).isPresent()) {
            throw new IllegalStateException("username already exists");
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
        return new AuthDto.ResponseRegisterDto(user.getId(), user.getUsername(), user.getEmail(), user.getRoles());
    }

    public AuthDto.ResponseLoginDto login(AuthDto.RequestLoginDto requestLoginDto) {
        User user = userRepo.findByUsername(requestLoginDto.getUsername()).orElseThrow(() -> new IllegalStateException("invalid username or password"));
        if (!passwordEncoder.matches(requestLoginDto.getPassword(), user.getPassword())) {
            throw new IllegalStateException("invalid username or password");
        }

        String jwt = jwtService.generateToken(user);

        return new AuthDto.ResponseLoginDto(user.getId(), jwt);
    }
}
