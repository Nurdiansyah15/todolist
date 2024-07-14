package com.nurd.to_do_list_app.utils.dto;

import com.nurd.to_do_list_app.domain.models.Role;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


public class AuthDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestLoginDto {
        @NotNull
        private String username;
        @NotNull
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestRegisterDto {
        @NotNull
        private String username;
        @NotNull
        @Email
        private String email;
        @NotNull
        private String password;
        @NotNull
        private Set<String> roles;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseLoginDto {
        private Long id;
        private String token;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseRegisterDto {
        private Long id;
        private String username;
        private String email;
        private Set<Role> roles;
    }
}
