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
	private String articleId;

	@JsonProperty("datetime")
	private String datetime;

	@JsonProperty("dayOfTheWeek")
	private String dayOfTheWeek;

	@JsonProperty("officeHname")
	private String officeHname;

	@JsonProperty("title")
	private String contentTitle;

	@JsonProperty("imageUrl")
	private String imageUrl;

	@JsonProperty("imageDesc")
	private String imageDesc;

	@JsonProperty("content")
	private String content;

	@JsonProperty("writer")
	private String writer;
}
