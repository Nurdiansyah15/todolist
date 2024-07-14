package com.nurd.to_do_list_app.utils.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Set<String> roles; // <1>
}
