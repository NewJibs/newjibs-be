package com.ssafy.newjibs.member.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate birth;

	@Column(name = "join_date", nullable = false, columnDefinition = "DATETIME(0)")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime joinDate;

	@Column(name = "member_image")
	private String memberImage;

	@Column(name = "activated", nullable = false)
	@NotNull
	private Boolean activated;

	@ManyToMany
	@JoinTable(
		name = "member_authority",
		joinColumns = {@JoinColumn(name = "member_id")},
		inverseJoinColumns = {@JoinColumn(name = "authority_name")}
	)
	private Set<Authority> authorities;
}
