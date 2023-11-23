package com.ssafy.newjibs.news.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsTitleDto {
	@JsonProperty("articleId")
	private String articleId;

	@JsonProperty("pressCorporationName")
	private String pressCorporationName;

	@JsonProperty("title")
	private String title;

	@JsonProperty("summaryContent")
	private String summaryContent;

	@JsonProperty("linkUrl")
	private String linkUrl;

	@JsonProperty("thumbnail")
	private String thumbnail;

	@JsonProperty("publishDateTime")
	private String publishDateTime;
}
