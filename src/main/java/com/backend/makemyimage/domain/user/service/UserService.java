package com.backend.makemyimage.domain.user.service;

import com.backend.makemyimage.domain.user.dto.request.JoinRequest;
import com.backend.makemyimage.domain.user.dto.request.LoginRequest;
import com.backend.makemyimage.domain.user.dto.response.LoginResponse;
import com.backend.makemyimage.domain.user.dto.response.UserInfoResponse;
import com.backend.makemyimage.domain.user.entity.User;
import com.backend.makemyimage.domain.user.repository.UserRepository;
import com.backend.makemyimage.domain.user.security.CustomUserDetails;
import com.backend.makemyimage.global.exception.CustomException;
import com.backend.makemyimage.global.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public void join(JoinRequest joinRequest)
    {
        if(userRepository.existsByEmail(joinRequest.getEmail())){
            throw new CustomException(HttpStatus.CONFLICT, "Email already in use");
        }

        userRepository.save(User
                .builder()
                .name(joinRequest.getName())
                .password(bCryptPasswordEncoder.encode(joinRequest.getPassword()))
                .email(joinRequest.getEmail())
                .build());

    }

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()));

            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = jwtUtil.createJwt(customUserDetails.getUsername(), customUserDetails.getEmail(), 60 * 60 * 1000L);

            return new LoginResponse(token);
        } catch (AuthenticationException e) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
    }

    public UserInfoResponse getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String email = userDetails.getEmail(); // 사용자 이메일

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User not found"));
        UserInfoResponse userInfoResponse= UserInfoResponse.builder().name(user.getName()).email(user.getEmail()).build();
        return userInfoResponse;
    }
}
