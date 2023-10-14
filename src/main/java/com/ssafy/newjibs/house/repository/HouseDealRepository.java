package com.ssafy.newjibs.house.repository;

import com.ssafy.newjibs.house.domain.HouseDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseDealRepository extends JpaRepository<HouseDeal, Long> {

}
