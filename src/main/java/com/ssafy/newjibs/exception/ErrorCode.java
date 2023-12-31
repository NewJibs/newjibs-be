package com.ssafy.newjibs.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
	// member error code
	MEMBER_NOT_FOUND(401, "유저정보를 찾을 수 없습니다."),
	DUPLICATED_EMAIL(409, "이미 가입되어있는 유저입니다."),
	ADMIN_NOT_ALLOWED(403, "관리자의 정보는 볼 수 없습니다."),

	// token error code
	TOKEN_FORBIDDEN(403, "token forbidden"),

	// image error code
	IMAGE_DELETE_ERROR(401, "삭제할 이미지를 찾을 수 없습니다."),
	IMAGE_TYPE_ERROR(400, "이미지 파일이 아닙니다."),
	IMAGE_NULL_ERROR(400, "이미지를 추가하지 않았습니다."),
	SIZE_ERROR(400, "파일의 용량이 너무 큽니다."),

	// house error code
	APT_CODE_NOT_FOUND(401, "아파트코드(aptCode)를 찾을 수 없습니다."),
	HOUSE_NOT_FOUND(401, "부동산 정보(no)를 찾을 수 없습니다."),

	// news error code
	NEWS_NOT_FOUND(401, "뉴스 정보를 찾을 수 없습니다."),

	// notice error code
	NOTICE_NOT_FOUND(401, "공지사항을 찾을 수 없습니다."),

	// === GLOBAL BASE ERROR CODE ===
	// 4xx: Client Errors
	BAD_REQUEST(400, "Bad Request"),
	UNAUTHORIZED(401, "Unauthorized"),
	FORBIDDEN(403, "Forbidden"),
	NOT_FOUND(404, "Not Found"),
	METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
	CONFLICT(409, "Conflict"),

	// 5xx: Server Errors
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
	NOT_IMPLEMENTED(501, "Not Implemented"),
	BAD_GATEWAY(502, "Bad Gateway"),
	SERVICE_UNAVAILABLE(503, "Service Unavailable"),
	GATEWAY_TIMEOUT(504, "Gateway Timeout"),
	HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported");

	private final Integer errorCode;
	private final String errorMsg;
}
