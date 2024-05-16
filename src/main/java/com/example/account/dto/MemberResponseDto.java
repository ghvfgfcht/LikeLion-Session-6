package com.example.account.dto;

import lombok.*;

import java.time.LocalDateTime;

public class MemberResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class signUpSuccessDto{
        private Long member_id;
        private LocalDateTime created_at;
    }
}
