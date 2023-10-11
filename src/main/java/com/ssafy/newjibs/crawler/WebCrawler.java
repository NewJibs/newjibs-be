package com.ssafy.newjibs.crawler;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newjibs.crawler.url.UrlBuilder;
import com.ssafy.newjibs.crawler.url.UrlSrc;
import com.ssafy.newjibs.news.dto.NewsContentDto;
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
	private final UrlBuilder urlBuilder;

	public void crawlAndSaveNews() {
		List<NewsTitleDto> titleList = crawlNewsTitles();
		for (NewsTitleDto newsTitleDto : titleList) {
			if (newsRepository.existsByArticleId(newsTitleDto.getArticleId())) {
				continue;
			}
			String contentUrl = urlBuilder.getNewsContentUrl(newsTitleDto);
			NewsContentDto newsContentDto = crawlNewsContent(contentUrl, newsTitleDto);
			newsService.createNews(newsTitleDto, newsContentDto);
		}
	}

	public List<NewsTitleDto> crawlNewsTitles() {
		return newsParser.parseNewsTitles(readScriptWithJson(UrlSrc.BASE_URL.getUrl()));
	}

	public NewsContentDto crawlNewsContent(String contentUrl, NewsTitleDto newsTitleDto) {
		Document document = readDocument(contentUrl);
		// get json data on script block
		String title = document.select(".news_head_title").html();// title
		String content = document.select("[class^=article_type]").html();// content

		return newsParser.parseNewsContent(title, content, newsTitleDto);
	}

	private String readScriptWithJson(String url) {
		Document document;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// get json data on script block
		return document.select("script").html();
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
