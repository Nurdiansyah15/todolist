package com.nurd.to_do_list_app.services;

import com.nurd.to_do_list_app.domain.models.Role;
import com.nurd.to_do_list_app.domain.models.User;
import com.nurd.to_do_list_app.domain.repos.UserRepo;
import com.nurd.to_do_list_app.utils.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User create(UserDto obj) {
        User newUser = new User();
        newUser.setUsername(obj.getUsername());
        newUser.setEmail(obj.getEmail());
        newUser.setPassword(obj.getPassword());

        Set<Role> roles = new HashSet<>();

        if (obj.getRoles().isEmpty()) {
            roles.add(Role.ROLE_USER);
        } else {
            for (var role : obj.getRoles()) {
                roles.add(Role.valueOf("ROLE_" + role.toUpperCase()));
            }
        }

        newUser.setRoles(roles);
        return userRepo.save(newUser);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User update(Long id, UserDto obj) {
        User foundUser = findById(id);
        foundUser.setUsername(obj.getUsername().isEmpty() ? foundUser.getUsername() : obj.getUsername());
        foundUser.setEmail(obj.getEmail().isEmpty() ? foundUser.getEmail() : obj.getEmail());
        foundUser.setPassword(obj.getPassword().isEmpty() ? foundUser.getPassword() : obj.getPassword());

        Set<Role> roles = new HashSet<>(foundUser.getRoles());

        for (var role : obj.getRoles()) {
            roles.add(Role.valueOf("ROLE_" + role.toUpperCase()));
        }

        foundUser.setRoles(roles);
        return userRepo.save(foundUser);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

}
