package com.ssafy.newjibs.member.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSelfInfoDto {
	@JsonProperty("email")
	private String email;

	@JsonProperty("name")
	private String name;

	@JsonProperty("birth")
	private LocalDate birth;

	@JsonProperty("join_date")
	private LocalDateTime joinDate;

	@JsonProperty("image_url")
	private String imageUrl;

	@JsonProperty("point")
	private Long point;
}
