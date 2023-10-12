package com.ssafy.newjibs.house.detail.housedeal.repository;

import com.ssafy.newjibs.house.detail.housedeal.domain.HouseDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseDealRepository extends JpaRepository<HouseDeal, Long> {

}
