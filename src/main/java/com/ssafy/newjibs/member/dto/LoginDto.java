package com.ssafy.newjibs.member.dto;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
	@JsonProperty("email")
	@Email(message = "Invalid email format")
	private String email;

	@JsonProperty("password")
	private String password;
}
