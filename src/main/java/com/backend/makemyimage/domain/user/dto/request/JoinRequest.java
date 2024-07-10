package com.backend.makemyimage.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinRequest {
    private String name;
    private String password;
    private String email;
}
