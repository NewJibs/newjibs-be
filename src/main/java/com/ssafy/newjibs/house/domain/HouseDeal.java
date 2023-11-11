package com.ssafy.newjibs.house.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class HouseDeal {
	@Id
	@Column(nullable = false)
	private Long no;

	@ManyToOne
	@JoinColumn(name = "aptCode")
	private HouseInfo houseInfo;

	@Column(nullable = false)
	private String dealAmount;

	@Column(nullable = false)
	private Integer dealYear;

	@Column(nullable = false)
	private Integer dealMonth;

	@Column(nullable = false)
	private Integer dealDay;

	@Column(nullable = false)
	private String area;

	@Column(nullable = false)
	private String floor;

	@Column(nullable = false)
	private String cancelDealType;
}
