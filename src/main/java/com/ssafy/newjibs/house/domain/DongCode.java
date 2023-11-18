package com.ssafy.newjibs.house.domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "dongcode")
public class DongCode {
	@Id
	@Column(name = "dongCode", nullable = false)
	private String dongCode;

	@Column(name = "sidoName")
	private String sidoName;

	@Column(name = "gugunName")
	private String gugunName;

	@Column(name = "dongName")
	private String dongName;

	@OneToMany(mappedBy = "dongCode", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HouseInfo> houseInfos;
}
