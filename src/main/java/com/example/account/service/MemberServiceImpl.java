package com.example.account.service;

import com.example.account.dto.MemberRequestDto;
import com.example.account.dto.MemberResponseDto;
import com.example.account.entity.Members;
import com.example.account.repository.MemberRepository;
import com.example.account.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 회원가입
    public ResponseEntity<CustomApiResponse<?>> signUp(MemberRequestDto.signUpRequestDto member) {
        Members newMemberInfo = Members.builder()
                .userId(member.getUserId())
                .password(member.getPassword())
                .email(member.getEmail())
                .build();

        memberRepository.save(newMemberInfo);

        log.info("new member: {}", newMemberInfo.getCreatedAt());

        MemberResponseDto.signUpSuccessDto result = MemberResponseDto.signUpSuccessDto.builder()
                .member_id(newMemberInfo.getId())
                .created_at(newMemberInfo.getCreatedAt())
                .build();

        CustomApiResponse<MemberResponseDto.signUpSuccessDto> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), result, "회원가입이 완료되었습니다.");

        return ResponseEntity.ok(response);
    }

    // 로그인
    @Override
    public ResponseEntity<CustomApiResponse<?>> login(MemberRequestDto.loginRequestDto member) {

        Optional<Members> loginResult = memberRepository.findByUserId(member.getUserId());

        if(loginResult.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        CustomApiResponse<?> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "로그인에 성공하였습니다!");

        return ResponseEntity.ok(response);
    }
}
