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

	@JsonProperty("pressCorporationName")
	private String pressCorporationName;

	@JsonProperty("title")
	private String title;

	@JsonProperty("publishDateTime")
	private String publishDateTime;

	@JsonProperty("contentSummary")
	private String contentSummary;

	@JsonProperty("content")
	private String content;

	@JsonProperty("imageUrl")
	private String imageUrl;

	@JsonProperty("imageDesc")
	private String imageDesc;

	@JsonProperty("writer")
	private String writer;
}
