package com.ssafy.newjibs.member.options;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthorityConstant {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    private final String role;
}
