package com.ssafy.newjibs.api.news;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import static com.ssafy.newjibs.crawler.url.UrlSrc.BASE_URL;

public class NewsApi {
    public static URL getUrl(String baseDate, int page, int size) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL.getUrl());
        urlBuilder.append("/airsList.naver")
                .append("?" + URLEncoder.encode("baseDate", "UTF-8") + "=" + baseDate)
                .append("&" + URLEncoder.encode("page", "UTF-8") + "=" + page)
                .append("&" + URLEncoder.encode("size", "UTF-8") + "=" + size);
        return new URL(urlBuilder.toString());
    }
}
