package com.ssafy.newjibs.member.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.ssafy.newjibs.member.options.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "member")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	@Column(name = "email", nullable = false, unique = true)
	@NotNull
	private String email;

	@Column(name = "password", nullable = false)
	@NotNull
	private String password;

	@Column(name = "name", nullable = false)
	@NotNull
	private String name;

	@Column(name = "birth", nullable = false)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;

	@Column(name = "join_date", nullable = false, columnDefinition = "DATETIME(0)")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime joinDate;

	@Column(name = "member_image")
	private String memberImage;

	@Column(name = "role", nullable = false)
	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "provider")
	private String provider;

	@Column(name = "is_deleted", nullable = false)
	@NotNull
	private Boolean isDeleted;

	public Member update(String name, String provider) {
		this.name = name;
		this.provider = provider;
		return this;
	}
}
