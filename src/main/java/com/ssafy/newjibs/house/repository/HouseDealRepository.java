package com.ssafy.newjibs.house.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.newjibs.house.domain.HouseDeal;
import com.ssafy.newjibs.house.dto.Coordinate;
import com.ssafy.newjibs.house.dto.DealNoDto;
import com.ssafy.newjibs.house.dto.HouseDto;

@Repository
public interface HouseDealRepository extends JpaRepository<HouseDeal, Long> {
	@Query("SELECT new com.ssafy.newjibs.house.dto.Coordinate(" +
		"hinfo.aptCode, hinfo.lat, hinfo.lng, " +
		"MIN(hdeal.dealAmount), MAX(hdeal.dealAmount)) " +
		"FROM HouseDeal hdeal " +
		"JOIN hdeal.houseInfo hinfo " +
		"WHERE hdeal.dealYear = 2020 " +
		"GROUP BY hinfo.aptCode, hinfo.lat, hinfo.lng")
	List<Coordinate> find2020CoordinatesWithMinMaxDealAmount();

	@Query("SELECT new com.ssafy.newjibs.house.dto.HouseDto(" +
		"hinfo.aptCode, hdeal.no, hdeal.dealYear, hdeal.dealMonth, hdeal.dealDay, hdeal.area, " +
		"dcode.sidoName, dcode.gugunName, dcode.dongName, " +
		"hinfo.buildYear, hinfo.roadName, hinfo.roadNameBonBun, hinfo.roadNameBubun, " +
		"hinfo.roadNameSeq, hinfo.roadNameBasementCode, hinfo.roadNameCode, hinfo.dong, " +
		"hinfo.bonbun, hinfo.bubun, hinfo.sigunguCode, hinfo.eubmyundongCode, " +
		"hinfo.landCode, hinfo.apartmentName, hinfo.jibun, hinfo.lng, hinfo.lat) " +
		"FROM HouseDeal hdeal " +
		"JOIN hdeal.houseInfo hinfo " +
		"JOIN hinfo.dongCode dcode " +
		"WHERE hinfo.aptCode = :aptCode")
	List<HouseDto> findHouseDtosByAptCode(@Param("aptCode") Long aptCode);

	@Query("SELECT new com.ssafy.newjibs.house.dto.DealNoDto(hd2020.no, hd2022.no) " +
		"FROM HouseDeal hd2020 " +
		"JOIN HouseDeal hd2022 ON hd2020.houseInfo.aptCode = hd2022.houseInfo.aptCode AND hd2020.area = hd2022.area " +
		"WHERE hd2020.dealYear = 2020 AND hd2022.dealYear = 2022")
	List<DealNoDto> findDealNosForYears();
}
