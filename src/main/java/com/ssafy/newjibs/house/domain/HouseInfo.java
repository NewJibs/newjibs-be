package com.ssafy.newjibs.house.domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
@Access(AccessType.FIELD)
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "houseinfo")
public class HouseInfo {
	@Id
	@Column(name = "aptCode", nullable = false)
	private Long aptCode;

	@Column(name = "buildYear")
	private Integer buildYear;

	@Column(name = "roadName")
	private String roadName;

	@Column(name = "roadNameBonBun")
	private String roadNameBonBun;

	@Column(name = "roadNameBubun")
	private String roadNameBubun;

	@Column(name = "roadNameSeq")
	private String roadNameSeq;

	@Column(name = "roadNameBasementCode")
	private String roadNameBasementCode;

	@Column(name = "roadNameCode")
	private String roadNameCode;

	@Column(name = "dong")
	private String dong;

	@Column(name = "bonbun")
	private String bonbun;

	@Column(name = "bubun")
	private String bubun;

	@Column(name = "sigunguCode")
	private String sigunguCode;

	@Column(name = "eubmyundongCode")
	private String eubmyundongCode;

	@ManyToOne
	@JoinColumn(name = "dongCode")
	private DongCode dongCode;

	@Column(name = "landCode")
	private String landCode;

	@Column(name = "apartmentName")
	private String apartmentName;

	@Column(name = "jibun")
	private String jibun;

	@Column(name = "lng")
	private String lng;

	@Column(name = "lat")
	private String lat;

	@OneToMany(mappedBy = "houseInfo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HouseDeal> houseDeals;
}
