package com.ssafy.newjibs.crawler.url;

import org.springframework.stereotype.Component;

import com.ssafy.newjibs.news.dto.NewsTitleDto;

@Component
public class UrlBuilder {
	// ex) https://m2.land.naver.com/news/readNews?source=headline&prscoId=421&articleId=0007101087&bssYmd=20231011
	// -> BASEURL/news/readNews?source=headline&prscoId={officeId}&artiId={articleId}&bssYmd={date}
	public String getNewsContentUrl(NewsTitleDto newsTitleDto) {
		StringBuilder sb = new StringBuilder();
		sb.append(UrlSrc.BASE_URL.getUrl()).append("/readNews?source=headline")
			.append("&prscoId=").append(newsTitleDto.getOfficeId())
			.append("&artiId=").append(newsTitleDto.getArticleId())
			.append("&bssYmd=").append(newsTitleDto.getDatetime());
		return sb.toString();
	}
}
