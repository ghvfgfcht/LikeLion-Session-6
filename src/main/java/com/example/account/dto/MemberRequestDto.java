package com.example.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class MemberRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class signUpRequestDto{
        @NotBlank(message = "로그인 아이디를 입력해주세요.")
        private String userId;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        @Email(message = "이메일 형식을 맞춰주세요.")
        @NotBlank
        private String email;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class loginRequestDto{
        @NotBlank(message = "로그인 아이디를 입력해주세요.")
        private String userId;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }
}
