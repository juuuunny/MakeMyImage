package com.backend.makemyimage.domain.user.service;

import com.backend.makemyimage.domain.user.dto.request.JoinRequest;
import com.backend.makemyimage.domain.user.entity.User;
import com.backend.makemyimage.domain.user.repository.UserRepository;
import com.backend.makemyimage.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(JoinRequest joinRequest)
    {
        if(userRepository.existByEmail(joinRequest.getEmail())){
            throw new CustomException(HttpStatus.CONFLICT, "Email already in use");
        }

        userRepository.save(User
                .builder()
                .name(joinRequest.getName())
                .password(bCryptPasswordEncoder.encode(joinRequest.getPassword()))
                .email(joinRequest.getEmail())
                .build());

    }

}
