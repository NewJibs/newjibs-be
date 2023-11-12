package com.ssafy.newjibs.crawler;

import java.io.IOException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebCrawlerScheduler {
	private final WebCrawler webCrawler;

	@Scheduled(cron = "0 0 */6 * * ?") // execute every six hours
	public void crawlData() throws IOException {
		webCrawler.crawlTodayNews();
	}
}