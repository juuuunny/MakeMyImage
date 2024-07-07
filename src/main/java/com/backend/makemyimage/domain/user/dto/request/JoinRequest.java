package com.backend.makemyimage.domain.user.dto.request;

import lombok.Getter;

@Getter
public class JoinRequest {
    private String name;
    private String password;
    private String email;
}
