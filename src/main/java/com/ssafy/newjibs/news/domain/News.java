package com.ssafy.newjibs.news.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "news")
public class News {
	@Id
	@Column(nullable = false)
	private String articleId;// use article's generated id

	@Column(name = "press_corporation_name", nullable = false)
	private String pressCorporationName;

	@Column(nullable = false)
	private String title;

	@Lob
	@Column(name = "summary_content", nullable = false)
	private String summaryContent;// summary on title

	@Column(name = "link_url", nullable = false)
	private String linkUrl;

	@Column(nullable = false)
	private String thumbnail;

	@Column(name = "publish_date_time", nullable = false, columnDefinition = "DATETIME") // set String to DATETIME on column
	private String publishDateTime;

	@Column(name = "content_summary", nullable = false)
	private String contentSummary;// summary on content

	@Lob
	@Column(nullable = false)
	private String content;

	@Column(name = "image_url")
	@Nullable
	private String imageUrl;

	@Lob
	@Column(name = "image_desc")
	@Nullable
	private String imageDesc;

	@Nullable
	private String writer;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted;
}