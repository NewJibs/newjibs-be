package com.ssafy.newjibs.house.domain;

import java.util.List;

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
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "dongcode")
public class DongCode {
	@Id
	@Column(nullable = false)
	private String dongCode;// use api inner id as a pk

	@Column(nullable = false)
	private String sidoName;

	@Column(nullable = false)
	private String gugunName;

	@Column(nullable = false)
	private String dongName;

	@Column(nullable = false)
	@OneToMany(mappedBy = "dongCode", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HouseInfo> houseInfos;
}
