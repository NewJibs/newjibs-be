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

	@Column(nullable = false, columnDefinition = "DATE") // set String to DATE on column
	private String datetime;

	@Column(nullable = false)
	private String dayOfTheWeek;

	@Column(nullable = false)
	private String officeHname;

	@Column(nullable = false)
	private String officeId;

	@Column(nullable = false)
	private String mainPageTitle;

	@Column(nullable = false)
	private String contentTitle;

	@Column
	@Nullable
	private String imageUrl;

	@Column
	@Nullable
	private String imageDesc;

	@Lob// set type to CLOB
	@Column(nullable = false)
	private String content;

	@Lob
	@Nullable
	private String writer;

	@Column(nullable = false)
	private Boolean isDeleted;
}
