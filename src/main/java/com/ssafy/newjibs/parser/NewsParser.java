package com.ssafy.newjibs.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import com.ssafy.newjibs.news.dto.NewsContentDto;
import com.ssafy.newjibs.news.dto.NewsTitleDto;
import com.ssafy.newjibs.parser.dto.ContentParsingDto;
import com.ssafy.newjibs.parser.util.JsonMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NewsParser {
	private final JsonMapper jsonMapper;
	final int WRITER_LENGTH_LIMIT = 30;

	public List<NewsTitleDto> parseJson(String script) {// parse title
		String JSON_START = "pageInfo = ";
		String JSON_END = "}\n";

		// JSON data extraction
		int start = script.indexOf(JSON_START) + JSON_START.length();
		int end = script.indexOf(JSON_END, start) + 1;
		String jsonText = script.substring(start, end).replace("=", ":");// set text js to json

		JSONObject jsonObject = new JSONObject(jsonText);
		List<NewsTitleDto> list = new ArrayList<>();
		// parse JSON
		try {
			// field extraction
			JSONArray newsList = jsonObject.getJSONArray("newsList");
			for (int i = 0; i < newsList.length(); i++) {
				list.add(jsonMapper.toNewsMainDto(newsList.getJSONObject(i)));
			}
		} catch (Exception e) {// exception
			e.printStackTrace();
		}
		return list;
	}

	public NewsContentDto parseHtml(NewsTitleDto newsTitleDto, Document document) {// parse content
		Document contentDocument = Jsoup.parse(document.select("#article_body").html());// extract body from document
		ContentParsingDto contentParsingDto = parseNewsContent(contentDocument.html());// it contains content and writer

		return NewsContentDto.builder()
			// title dto already involves these fields
			.articleId(newsTitleDto.getArticleId())
			.datetime(newsTitleDto.getDatetime())
			.dayOfTheWeek(newsTitleDto.getDayOfTheWeek())
			.officeHname(newsTitleDto.getOfficeHname())
			// extract from css query
			.contentTitle(document.select(".news_head_title").text())// title extraction through class
			.imageUrl(contentDocument.select("img[src]").attr("src"))// image extraction through class
			.imageDesc(contentDocument.select(".img_desc").text())// image description extraction through class
			// extract from content parser
			.content(contentParsingDto.getContent())
			.writer(contentParsingDto.getWriter())
			.build();
	}

	public ContentParsingDto parseNewsContent(String contentHtml) {
		String writer = "";
		String content = contentHtml.replaceAll("<br>\\s*<br>", "<br>");// shorten <br> tags

		StringBuilder parsedStr = new StringBuilder();
		String[] lines = content.split("<br>");

		// build content text and extract image, imageDesc and writer
		for (String line : lines) {// todo: kmp
			// image section
			if (line.contains("img src=\"")) {// extract image
				line = line.replaceAll("<em[^>]*>[^<]*</em>", "");// delete image and desc
				parsedStr.append(line);
				continue;
			}

			// writer line
			if (line.contains("기자") && (line.contains("@") || line.length() <= WRITER_LENGTH_LIMIT)) {
				writer = line;
				break;
			}
			parsedStr.append(line);
		}

		content = parsedStr.toString().replaceAll("<[^>]+>", "");// delete rest of the html tags
		content = content.replaceAll("(?:(\\r?\\n)\\s*){2,}", "$1"); // shorten crlf to once

		return ContentParsingDto.builder()
			.content(content)
			.writer(writer)
			.build();
	}
}
