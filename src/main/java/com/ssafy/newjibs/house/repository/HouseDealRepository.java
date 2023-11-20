package com.ssafy.newjibs.house.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.newjibs.house.domain.HouseDeal;
import com.ssafy.newjibs.house.dto.Coordinate;
import com.ssafy.newjibs.house.dto.HouseDto;
import com.ssafy.newjibs.house.dto.HouseResultDto;

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
		"hinfo.aptCode, hdeal.no, hdeal.dealAmount, hdeal.dealYear, hdeal.dealMonth, hdeal.dealDay, hdeal.area, " +
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


	@Query("SELECT new com.ssafy.newjibs.house.dto.HouseResultDto(" +
		"hinfo2020.aptCode, hd2020.no, hd2020.dealAmount, hd2020.dealYear, hd2020.dealMonth, hd2020.dealDay, " +
		"hd2022.dealAmount, hd2022.dealYear, hd2022.dealMonth, hd2022.dealDay, hd2022.dealAmount," +
		"hd2022.area, dcode2022.sidoName, dcode2022.gugunName, dcode2022.dongName, " +
		"hinfo2022.buildYear, hinfo2022.roadName, hinfo2022.roadNameBonBun, hinfo2022.roadNameBubun, " +
		"hinfo2022.roadNameSeq, hinfo2022.roadNameBasementCode, hinfo2022.roadNameCode, hinfo2022.dong, " +
		"hinfo2022.bonbun, hinfo2022.bubun, hinfo2022.sigunguCode, hinfo2022.eubmyundongCode, " +
		"hinfo2022.landCode, hinfo2022.apartmentName, hinfo2022.jibun, hinfo2022.lng, hinfo2022.lat) " +
		"FROM HouseDeal hd2020 " +
		"JOIN hd2020.houseInfo hinfo2020 " +
		"JOIN hinfo2020.dongCode dcode2020, " +
		"HouseDeal hd2022 " +
		"JOIN hd2022.houseInfo hinfo2022 " +
		"JOIN hinfo2022.dongCode dcode2022 " +
		"WHERE hd2020.no = :no " +
		"AND hd2020.houseInfo.aptCode = hd2022.houseInfo.aptCode " +
		"AND hd2020.area = hd2022.area " +
		"AND hd2022.dealYear = 2022")
	HouseResultDto findHouseResultDtoForGiven2020DealNo(@Param("no") Long no);


}
