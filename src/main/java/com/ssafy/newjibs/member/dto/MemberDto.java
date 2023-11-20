package com.ssafy.newjibs.member.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
	@JsonProperty("email")
	@Email(message = "Invalid email format")
	private String email;

	@JsonProperty("name")
	private String name;

	@JsonProperty("birth")
	private LocalDate birth;

	@JsonProperty("join_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime joinDate;

	@JsonProperty("image_url")
	private String imageUrl;

	@JsonProperty("point")
	private Long point;

	private Set<AuthorityDto> authorityDtoSet;
}
