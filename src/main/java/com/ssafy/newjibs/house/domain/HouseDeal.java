package com.ssafy.newjibs.house.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
@Access(AccessType.FIELD)
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "housedeal")
public class HouseDeal {
	@Id
	@Column(name = "no", nullable = false)
	private Long no;

	@Column(name = "dealAmount")
	private String dealAmount;

	@Column(name = "dealYear")
	private Integer dealYear;

	@Column(name = "dealMonth")
	private Integer dealMonth;

	@Column(name = "dealDay")
	private Integer dealDay;

	@Column(name = "area")
	private String area;

	@Column(name = "floor")
	private String floor;

	@Column(name = "cancelDealType")
	private String cancelDealType;

	// @ManyToOne
	// @JoinColumn(name = "aptCode")
	// private HouseInfo houseInfo;

	@JoinColumn(name = "aptCode")
	private Long aptCode;
}
