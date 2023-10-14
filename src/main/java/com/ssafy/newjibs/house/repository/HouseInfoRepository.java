package com.ssafy.newjibs.house.repository;

import com.ssafy.newjibs.house.domain.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseInfoRepository extends JpaRepository<HouseInfo, Long> {

}