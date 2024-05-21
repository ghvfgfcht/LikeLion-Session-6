package com.example.account.service;

import com.example.account.dto.MemberRequestDto;
import com.example.account.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    ResponseEntity<CustomApiResponse<?>> signUp(MemberRequestDto.SignUpRequestDto member);

    ResponseEntity<CustomApiResponse<?>> login(MemberRequestDto.LoginRequestDto member);

    ResponseEntity<CustomApiResponse<?>> withdraw(String memberId);
}
