package com.ssafy.newjibs.news.util;

import org.springframework.stereotype.Component;

import com.ssafy.newjibs.news.domain.News;
import com.ssafy.newjibs.news.dto.NewsContentDto;
import com.ssafy.newjibs.news.dto.NewsTitleDto;

@Component
public class NewsMapper {
	public News dtosToEntity(NewsTitleDto newsTitleDto, NewsContentDto newsContentDto) {
		return News.builder()
			.articleId(newsTitleDto.getArticleId())
			.pressCorporationName(newsTitleDto.getPressCorporationName())
			.title(newsTitleDto.getTitle())
			.summaryContent(newsTitleDto.getSummaryContent())
			.linkUrl(newsTitleDto.getLinkUrl())
			.thumbnail(newsTitleDto.getThumbnail())
			.publishDateTime(newsTitleDto.getPublishDateTime())
			.contentSummary(newsContentDto.getContentSummary())
			.content(newsContentDto.getContent())
			.imageUrl(newsContentDto.getImageUrl())
			.imageDesc(newsContentDto.getImageDesc())
			.writer(newsContentDto.getWriter())
			.isDeleted(false)
			.build();
	}

	public NewsTitleDto toTitleDto(News news) {
		return NewsTitleDto.builder()
			.articleId(news.getArticleId())
			.pressCorporationName(news.getPressCorporationName())
			.title(news.getTitle())
			.summaryContent(news.getSummaryContent())
			.thumbnail(news.getThumbnail())
			.publishDateTime(news.getPublishDateTime())
			.build();
	}

	public NewsContentDto toContentDto(News news) {
		return NewsContentDto.builder()
			.articleId(news.getArticleId())
			.pressCorporationName(news.getPressCorporationName())
			.publishDateTime(news.getPublishDateTime())
			.title(news.getTitle())
			.contentSummary(news.getContentSummary())
			.content(news.getContent())
			.imageUrl(news.getImageUrl())
			.imageDesc(news.getImageDesc())
			.writer(news.getWriter())
			.build();
	}
}
