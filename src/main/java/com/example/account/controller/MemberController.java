package com.example.account.controller;

import com.example.account.dto.MemberRequestDto;
import com.example.account.service.MemberServiceImpl;
import com.example.account.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberServiceImpl memberService;

    // SignUp
    @PostMapping("/sign-up")
    public ResponseEntity<CustomApiResponse<?>> signUp(@Valid @RequestBody MemberRequestDto.SignUpRequestDto member) {
        ResponseEntity<CustomApiResponse<?>> signUpResponse = memberService.signUp(member);

        return signUpResponse;
    }

    // Login
    @PostMapping("login")
    public ResponseEntity<CustomApiResponse<?>> login(@Valid @RequestBody MemberRequestDto.LoginRequestDto member) {
        ResponseEntity<CustomApiResponse<?>> loginResponse = memberService.login(member);

        return loginResponse;
    }

    // Withdrawal Member
    @DeleteMapping("withdraw/{userId}")
    public ResponseEntity<CustomApiResponse<?>> withdraw(@Valid @PathVariable String userId) {
        ResponseEntity<CustomApiResponse<?>> withdrawMember = memberService.withdraw(userId);

        return withdrawMember;
    }
}
