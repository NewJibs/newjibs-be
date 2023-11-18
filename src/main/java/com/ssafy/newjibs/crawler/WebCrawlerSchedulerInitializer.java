package com.ssafy.newjibs.crawler;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebCrawlerSchedulerInitializer {
	private final WebCrawler webCrawler;
	private final DynamicScheduler dynamicScheduler;

	@PostConstruct
	public void init() {
		dynamicScheduler.startScheduling();
	}
}
