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
public class NewsTitleDto {
	@JsonProperty("articleId")
	String articleId;

	@JsonProperty("datetime")
	String datetime;

	@JsonProperty("dayOfTheWeek")
	String dayOfTheWeek;

	@JsonProperty("officeHname")
	String officeHname;

	@JsonProperty("officeId")
	String officeId;

	@JsonProperty("title")
	String mainPageTitle;
}
