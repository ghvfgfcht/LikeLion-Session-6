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

        if(memberRepository.findById(newMemberInfo.getId()).isEmpty()){
            CustomApiResponse<?> fail = CustomApiResponse.createFailWithoutData(HttpStatus.INTERNAL_SERVER_ERROR.value(), "회원가입에 실패하였습니다.");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(fail);
        }

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

        // 존재하지 않는 회원일 경우
        if(memberRepository.findByUserId(member.getUserId()).isEmpty()){
            CustomApiResponse<?> fail = CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), "존재하지 않는 회원입니다.");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fail);
        }

        // 비밀번호가 일치하지 않는 경우
        if(memberRepository.findByUserId(member.getUserId()).isPresent() && memberRepository.findByPassword(member.getPassword()).isEmpty()){
            CustomApiResponse<?> fail = CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), "비밀번호를 확인해주세요.");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fail);
        }

        CustomApiResponse<?> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "로그인에 성공하였습니다!");

        return ResponseEntity.ok(response);
    }

    // 회원 탈퇴
    @Override
    public ResponseEntity<CustomApiResponse<?>> withdraw(Long memberId) {
        memberRepository.deleteById(memberId);

        if(memberRepository.findById(memberId).isPresent()){
            CustomApiResponse<?> fail = CustomApiResponse.createFailWithoutData(HttpStatus.INTERNAL_SERVER_ERROR.value(), "회원 탈퇴에 실패하였습니다.");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(fail);
        }

        CustomApiResponse<?> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "회원 탈퇴에 성공하였습니다.");

        return ResponseEntity.ok(response);
    }
}
