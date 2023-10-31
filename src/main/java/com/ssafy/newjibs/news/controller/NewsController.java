package com.ssafy.newjibs.news.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newjibs.crawler.WebCrawler;
import com.ssafy.newjibs.news.dto.NewsContentDto;
import com.ssafy.newjibs.news.dto.NewsTitleDto;
import com.ssafy.newjibs.news.service.NewsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/news")
public class NewsController {
	private final NewsService newsService;
	private final WebCrawler webCrawler;

	@ApiOperation(value = "뉴스 크롤링 테스트")
	@GetMapping("/test/crawling")
	public ResponseEntity<Void> crawlNews() {
		webCrawler.crawlAndSaveNews();
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "뉴스 제목들을 읽어온다.")
	@GetMapping
	public ResponseEntity<List<NewsTitleDto>> getAllNewsTitles() {
		return ResponseEntity.ok(newsService.readAllNews());
	}

	@ApiOperation(value = "특정 뉴스 본문을 읽어온다.")
	@GetMapping("/{articleId}")
	public ResponseEntity<NewsContentDto> getNewsContent(@PathVariable String articleId) {
		return ResponseEntity.ok(newsService.readNews(articleId));
	}

	@ApiOperation(value = "뉴스를 삭제한다.")
	@DeleteMapping("/{articleId}")
	public ResponseEntity<Void> removeNews(@PathVariable String articleId) {
		newsService.deleteNews(articleId);
		return ResponseEntity.ok().build();
	}
}
