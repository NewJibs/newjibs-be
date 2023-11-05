package com.ssafy.newjibs.crawler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.newjibs.api.ApiExplorer;
import com.ssafy.newjibs.api.news.NewsApi;
import com.ssafy.newjibs.news.dto.NewsContentDto;
import com.ssafy.newjibs.news.dto.NewsDto;
import com.ssafy.newjibs.news.dto.NewsTitleDto;
import com.ssafy.newjibs.news.repository.NewsRepository;
import com.ssafy.newjibs.news.service.NewsService;
import com.ssafy.newjibs.parser.NewsParser;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class WebCrawler {
	private final NewsService newsService;
	private final NewsRepository newsRepository;
	private final NewsParser newsParser;

	public void crawlBeforeNews() throws IOException {
		LocalDate endDate = LocalDate.now().minusDays(1);
		LocalDate startDate = endDate.minusDays(6);

		for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
			List<NewsTitleDto> titleList = crawlNewsTitles(date);
			for (NewsTitleDto newsTitleDto : titleList) {
				if (newsRepository.existsByArticleId(newsTitleDto.getArticleId())) {
					continue;
				}
				String contentUrl = newsTitleDto.getLinkUrl();
				NewsContentDto newsContentDto = crawlNewsContent(newsTitleDto, contentUrl);
				newsService.createNews(newsTitleDto, newsContentDto);
			}
		}
	}

	public void crawlTodayNews() throws IOException {
		List<NewsTitleDto> titleList = crawlNewsTitles(LocalDate.now());
		for (NewsTitleDto newsTitleDto : titleList) {
			if (newsRepository.existsByArticleId(newsTitleDto.getArticleId())) {
				continue;
			}
			String contentUrl = newsTitleDto.getLinkUrl();
			NewsContentDto newsContentDto = crawlNewsContent(newsTitleDto, contentUrl);
			newsService.createNews(newsTitleDto, newsContentDto);
		}
	}

	public List<NewsTitleDto> crawlNewsTitles(LocalDate date) throws IOException {
		String jsonData = ApiExplorer.getData(NewsApi.getUrl(date.toString(), 1, 6));// crawl 6 news per day
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonData, NewsDto.class).getList();
	}

	public NewsContentDto crawlNewsContent(NewsTitleDto newsTitleDto, String contentUrl) {
		return newsParser.parseHtml(newsTitleDto, readDocument(contentUrl));
	}

	private Document readDocument(String url) {
		Document document;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return document;
	}
}
