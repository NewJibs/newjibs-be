package com.ssafy.newjibs.house.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "houseinfo")
public class HouseInfo {
	@Id
	@Column(nullable = false)
	private Long aptCode;

	@ManyToOne
	@JoinColumn(name = "dongCode")
	private DongCode dongCode;

	@Column(nullable = false)
	private Integer buildYear;

	@Column(nullable = false)
	private String roadNameBonBun;

	@Column(nullable = false)
	private String roadNameBubun;

	@Column(nullable = false)
	private String roadNameSeq;

	@Column(nullable = false)
	private String roadNameBasementCode;

	@Column(nullable = false)
	private String roadNameCode;

	@Column(nullable = false)
	private String dong;

	@Column(nullable = false)
	private String bonbun;

	@Column(nullable = false)
	private String bubun;

	@Column(nullable = false)
	private String sigunguCode;

	@Column(nullable = false)
	private String eubmyundongCode;

	@Column(nullable = false)
	private String landCode;

	@Column(nullable = false)
	private String apartmentName;

	@Column(nullable = false)
	private String jibun;

	@Column(nullable = false)
	private String lng;

	@Column(nullable = false)
	private String lat;

	@Column(nullable = false)
	@OneToMany(mappedBy = "houseInfo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HouseDeal> houseDeals;
}
