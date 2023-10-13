package com.ssafy.newjibs.crawler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebCrawlerScheduler {
	private final WebCrawler webCrawler;

	@Scheduled(cron = "0 0 * * * ?") // execute every hour
	public void crawlData() {
		webCrawler.crawlAndSaveNews();
	}
}