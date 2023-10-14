package com.ssafy.newjibs.news.util;

import org.springframework.stereotype.Component;

import com.ssafy.newjibs.news.domain.News;
import com.ssafy.newjibs.news.dto.NewsContentDto;
import com.ssafy.newjibs.news.dto.NewsDto;
import com.ssafy.newjibs.news.dto.NewsTitleDto;

@Component
public class NewsMapper {
	public News dtoToEntity(NewsDto newsDto) {
		return News.builder()
			.articleId(newsDto.getArticleId())
			.datetime(newsDto.getDatetime())
			.dayOfTheWeek(newsDto.getDayOfTheWeek())
			.officeHname(newsDto.getOfficeHname())
			.officeId(newsDto.getOfficeId())
			.mainPageTitle(newsDto.getMainPageTitle())
			.contentTitle(newsDto.getContentTitle())
			.content(newsDto.getContent())
			.writer(newsDto.getWriter())
			.build();
	}

	public News eachDtosToEntity(NewsTitleDto newsTitleDto, NewsContentDto newsContentDto) {
		return News.builder()
			.articleId(newsTitleDto.getArticleId())
			.datetime(newsTitleDto.getDatetime())
			.dayOfTheWeek(newsTitleDto.getDayOfTheWeek())
			.officeHname(newsTitleDto.getOfficeHname())
			.officeId(newsTitleDto.getOfficeId())
			.mainPageTitle(newsTitleDto.getMainPageTitle())
			.contentTitle(newsContentDto.getContentTitle())
			.content(newsContentDto.getContent())
			.imageUrl(newsContentDto.getImageUrl())
			.imageDesc(newsContentDto.getImageDesc())
			.writer(newsContentDto.getWriter())
			.isDeleted(false)
			.build();
	}

	public NewsDto eachDtosToEntireDto(NewsTitleDto newsTitleDto, NewsContentDto newsContentDto) {
		return NewsDto.builder()
			.articleId(newsTitleDto.getArticleId())
			.datetime(newsTitleDto.getDatetime())
			.dayOfTheWeek(newsTitleDto.getDayOfTheWeek())
			.officeHname(newsTitleDto.getOfficeHname())
			.officeId(newsTitleDto.getOfficeId())
			.mainPageTitle(newsTitleDto.getMainPageTitle())
			.contentTitle(newsContentDto.getContentTitle())
			.content(newsContentDto.getContent())
			.writer(newsContentDto.getWriter())
			.build();
	}

	public NewsTitleDto toTitleDto(News news) {
		return NewsTitleDto.builder()
			.articleId(news.getArticleId())
			.datetime(news.getDatetime())
			.dayOfTheWeek(news.getDayOfTheWeek())
			.officeHname(news.getOfficeHname())
			.officeId(news.getOfficeId())
			.mainPageTitle(news.getMainPageTitle())
			.build();
	}

	public NewsContentDto toContentDto(News news) {
		return NewsContentDto.builder()
			.articleId(news.getArticleId())
			.datetime(news.getDatetime())
			.dayOfTheWeek(news.getDayOfTheWeek())
			.officeHname(news.getOfficeHname())
			.contentTitle(news.getContentTitle())
			.content(news.getContent())
			.writer(news.getWriter())
			.build();
	}
}
