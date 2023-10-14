package com.ssafy.newjibs.news.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newjibs.news.domain.News;
import com.ssafy.newjibs.news.dto.NewsContentDto;
import com.ssafy.newjibs.news.dto.NewsTitleDto;
import com.ssafy.newjibs.news.repository.NewsRepository;
import com.ssafy.newjibs.news.util.NewsMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NewsService {
	private final NewsRepository newsRepository;
	private final NewsMapper newsMapper;

	public void createNews(NewsTitleDto newsTitleDto, NewsContentDto newsContentDto) {
		if (newsRepository.existsByArticleId(newsTitleDto.getArticleId())) {
			return;
		}
		News news = newsMapper.eachDtosToEntity(newsTitleDto, newsContentDto);
		newsRepository.save(news);
	}

	public List<NewsTitleDto> readAllNews() {
		return newsRepository.findAllByIsDeletedFalse()
			.stream()
			.map(newsMapper::toTitleDto)
			.collect(Collectors.toList());
	}

	public NewsContentDto readNews(String articleId) {
		News news = newsRepository.findByArticleId(articleId).orElseThrow(RuntimeException::new);// exception
		return newsMapper.toContentDto(news);
	}

	public void deleteNews(String articleId) {
		News news = newsRepository.findByArticleId(articleId).orElseThrow(RuntimeException::new);// exception
		news.setIsDeleted(true);// soft deletion
	}
}
