package com.ejh.accountapp.bank.web;

import com.ejh.accountapp.bank.config.auth.CustomUserDetails;
import com.ejh.accountapp.bank.dto.ResponseDto;
import com.ejh.accountapp.bank.dto.user.JoinRequestDto;
import com.ejh.accountapp.bank.dto.user.JoinResponseDto;
import com.ejh.accountapp.bank.dto.user.UpdateUserPasswordRequest;
import com.ejh.accountapp.bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> join(@RequestBody @Valid JoinRequestDto joinRequestDto, BindingResult bindingResult) {
        JoinResponseDto joinUser = userService.join(joinRequestDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 완료", joinUser), HttpStatus.CREATED);
    }

    // 회원 탈퇴 API 추가 예정

    @PutMapping("/s/users/password")
    public ResponseEntity<?> updateUserPassword(@RequestBody @Valid UpdateUserPasswordRequest updateUserPasswordRequest,
                                                BindingResult bindingResult, @AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.updateUserPassword(updateUserPasswordRequest, userDetails.getUser().getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "패스워드 변경 완료", null), HttpStatus.OK);
    }
}
