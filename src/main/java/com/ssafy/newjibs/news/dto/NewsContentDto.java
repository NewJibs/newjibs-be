package com.ssafy.newjibs.news.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsContentDto {
	@JsonProperty("articleId")
	String articleId;

	@JsonProperty("datetime")
	String datetime;

	@JsonProperty("dayOfTheWeek")
	String dayOfTheWeek;

	@JsonProperty("officeHname")
	String officeHname;

	@JsonProperty("title")
	String contentTitle;

	@JsonProperty("content")
	String content;
	
	String writer;
}
