package com.backend.makemyimage.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoResponse {
    private String name;
    private String email;

    @Builder
    public UserInfoResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

