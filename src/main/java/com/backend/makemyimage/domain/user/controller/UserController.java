package com.backend.makemyimage.domain.user.controller;

import com.backend.makemyimage.domain.user.dto.request.JoinRequest;
import com.backend.makemyimage.domain.user.service.UserService;
import com.backend.makemyimage.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Void>> join(@RequestBody JoinRequest joinRequest) {
        userService.join(joinRequest);
        ApiResponse<Void> response= new ApiResponse<>(HttpStatus.CREATED,"User registered successfully",null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
