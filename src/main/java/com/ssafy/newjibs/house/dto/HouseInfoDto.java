package com.ssafy.newjibs.house.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseInfoDto {
	@JsonProperty("aptCode")
	private Long aptCode;

	@JsonProperty("no")
	private Long no;

	@JsonProperty("area")
	private String area;

	@JsonProperty("sidoName")
	private String sidoName;

	@JsonProperty("gugunName")
	private String gugunName;

	@JsonProperty("dongName")
	private String dongName;

	@JsonProperty("buildYear")
	private Integer buildYear;

	@JsonProperty("roadName")
	private String roadName;

	@JsonProperty("roadNameBonBun")
	private String roadNameBonBun;

	@JsonProperty("roadNameBubun")
	private String roadNameBubun;

	@JsonProperty("roadNameSeq")
	private String roadNameSeq;

	@JsonProperty("roadNameBasementCode")
	private String roadNameBasementCode;

	@JsonProperty("roadNameCode")
	private String roadNameCode;

	@JsonProperty("dong")
	private String dong;

	@JsonProperty("bonbun")
	private String bonbun;

	@JsonProperty("bubun")
	private String bubun;

	@JsonProperty("sigunguCode")
	private String sigunguCode;

	@JsonProperty("eubmyundongCode")
	private String eubmyundongCode;

	@JsonProperty("landCode")
	private String landCode;

	@JsonProperty("apartmentName")
	private String apartmentName;

	@JsonProperty("jibun")
	private String jibun;

	@JsonProperty("lng")
	private String lng;

	@JsonProperty("lat")
	private String lat;
}
