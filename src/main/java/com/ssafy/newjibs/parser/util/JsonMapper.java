package com.ssafy.newjibs.parser.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.ssafy.newjibs.news.dto.NewsTitleDto;

@Component
public class JsonMapper {
	public NewsTitleDto toNewsMainDto(JSONObject item) {
		return NewsTitleDto.builder()
			.articleId(item.getString("articleId"))
			.datetime(item.getString("datetime"))
			.dayOfTheWeek(item.getString("dayOfTheWeek"))
			.officeHname(item.getString("officeHname"))
			.officeId(item.getString("officeId"))
			.mainPageTitle(item.getString("title"))
			.build();
	}
}
