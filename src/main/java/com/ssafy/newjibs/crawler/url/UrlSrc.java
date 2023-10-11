package com.ssafy.newjibs.crawler.url;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UrlSrc {
	BASE_URL("https://m2.land.naver.com/news");// 네이버 부동산

	private final String url;
}
