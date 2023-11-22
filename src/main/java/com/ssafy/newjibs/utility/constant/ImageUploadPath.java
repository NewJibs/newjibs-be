package com.ssafy.newjibs.utility.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageUploadPath {
	PROFILE_PATH("profile/"),
	NOTICE_PATH("notice/");

	private final String path;
}
