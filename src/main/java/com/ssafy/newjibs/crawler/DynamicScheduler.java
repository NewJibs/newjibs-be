package com.ssafy.newjibs.crawler;

import java.io.IOException;
import java.util.Date;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DynamicScheduler {

	private final TaskScheduler scheduler;
	private final WebCrawler webCrawler;

	// scheduling init
	public void startScheduling() {
		scheduleNext();
	}

	private void scheduleNext() {
		long delay = getRandomDelay();
		scheduler.schedule(
			() -> {
				try {
					webCrawler.crawlTodayNews();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				scheduleNext(); // schedule next task
			},
			new Date(System.currentTimeMillis() + delay)
		);
	}

	// return 5:30 ~ 6:30 random time delay
	private long getRandomDelay() {
		return (long)(Math.random() * (23400000 - 19800000)) + 19800000;
	}
}
