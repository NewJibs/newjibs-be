package com.ssafy.newjibs.notice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeUtility {
    final String ADMIN_NAME = "admin";

    public boolean isAdministrator(String author) {// todo: check session auth
        return author.equals(ADMIN_NAME);
    }
}
