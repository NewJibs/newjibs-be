package com.ssafy.newjibs.house.detail.dongcode.repository;

import com.ssafy.newjibs.house.detail.dongcode.domain.DongCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DongCodeRepository extends JpaRepository<DongCode, Long> {

}
