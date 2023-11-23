package com.ssafy.newjibs.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import com.ssafy.newjibs.news.dto.NewsContentDto;
import com.ssafy.newjibs.news.dto.NewsTitleDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NewsParser {

	public NewsContentDto parseHtml(NewsTitleDto newsTitleDto, Document document) {// parse content
		Document contentDocument = Jsoup.parse(document.select("#dic_area").html());// extract body from document

		return NewsContentDto.builder()
			// title dto already involves these fields
			.articleId(newsTitleDto.getArticleId())
			.pressCorporationName(newsTitleDto.getPressCorporationName())
			.title(newsTitleDto.getTitle())
			.publishDateTime(newsTitleDto.getPublishDateTime())

			// extract from css query
			.contentSummary(contentDocument.select("strong.media_end_summary").text())
			.imageUrl(contentDocument.select("img[data-src]").attr("data-src"))// image extraction through class
			.imageDesc(contentDocument.select("em.img_desc").text())// image description extraction through class
			.writer(document.select("span.byline_s").text())

			// extract from parser
			.content(parseNewsContent(contentDocument))

			.build();
	}

	public String parseNewsContent(Document contentDocument) {
		// remove redundant part
		contentDocument.select("strong").remove();
		contentDocument.select("span").remove();

		String content = contentDocument.html();
		content = content.replaceAll("<br>\\s*<br>", "<br>");// shorten <br> tags
		StringBuilder parsedStr = new StringBuilder();
		String[] lines = content.split("<br>");
		for (String line : lines) {
			parsedStr.append(line);
		}

		content = parsedStr.toString().replaceAll("<[^>]+>", "");// delete rest of the html tags
		content = content.replaceAll("(?:(\\r?\\n)\\s*){2,}", "$1"); // shorten crlf to once
		return content;
	}
}
